package com.jartiste.bloodbridge.application.command.receiver;

import com.jartiste.bloodbridge.application.command.Command;
import com.jartiste.bloodbridge.application.command.CommandResult;
import com.jartiste.bloodbridge.application.service.ReceiverService;
import com.jartiste.bloodbridge.domain.enums.BloodType;
import com.jartiste.bloodbridge.presentation.dto.ReceiverDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class EditReceiverCommand implements Command {

    public static final String ERROR = "error";
    public static final String PATH = "/receivers/list";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReceiverService receiverService = getService(request, ReceiverService.class);

        if("POST".equalsIgnoreCase(request.getMethod())) {

            String receiverId = request.getParameter("id");

            ReceiverDTO receiverDTO = receiverService.getReceiverById(Long.parseLong(receiverId));

            if (null == receiverDTO) {
                request.getSession().setAttribute(ERROR, "Receiver Not Found");
                return CommandResult.redirect(request.getContextPath() + PATH);
            }
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String telephone = request.getParameter("telephone");


            receiverDTO.setFirstName(firstName);
            receiverDTO.setLastName(lastName);
            receiverDTO.setPhoneNumber(telephone);

            ReceiverDTO updatedReceiver = receiverService.updateReceiver(receiverDTO);

            if(null == updatedReceiver) {
                request.getSession().setAttribute(ERROR, "Error while updating receiver");
                return CommandResult.redirect(request.getContextPath() + PATH);
            }

            return CommandResult.redirect(request.getContextPath() + PATH);
        }

        return CommandResult.redirect(request.getContextPath() + PATH);
    }
}
