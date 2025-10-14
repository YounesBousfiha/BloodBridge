package com.jartiste.bloodbridge.application.command.receiver;

import com.jartiste.bloodbridge.application.command.Command;
import com.jartiste.bloodbridge.application.command.CommandResult;
import com.jartiste.bloodbridge.application.service.ReceiverService;
import com.jartiste.bloodbridge.presentation.dto.ReceiverDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class EditReceiverCommand implements Command {

    public static final String ERROR = "error";
    public static final String PATH = "/donors";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReceiverService receiverService = getService(request, ReceiverService.class);

        if("GET".equalsIgnoreCase(request.getMethod())) {
            String receiverID = request.getParameter("receiverId");
            if(null == receiverID || receiverID.isEmpty()) {
                request.getSession().setAttribute(ERROR, "Receiver ID is required");
                return CommandResult.redirect(request.getContextPath() + PATH);
            }

            try {
                var receiver = receiverService.getReceiverById(Long.parseLong(receiverID));
                if(receiver != null) {
                    request.setAttribute("receiver", receiver);
                    return CommandResult.forward("/WEB-INF/views/receiver/editReceiverForm.jsp");
                } else {
                    request.getSession().setAttribute(ERROR, "Receiver not found");
                    return CommandResult.redirect(request.getContextPath() + PATH);
                }
            } catch (Exception e) {
                request.getSession().setAttribute(ERROR, "An error occur while fetching the receiver");
                return CommandResult.redirect(request.getContextPath() + PATH);
            }
        }

        if("POST".equalsIgnoreCase(request.getMethod())) {
            ReceiverDTO dto = new ReceiverDTO();
            dto.setId(Long.parseLong(request.getParameter("id")));
            dto.setFirstName(request.getParameter("firstName"));
            dto.setLastName(request.getParameter("lastName"));
            dto.setCin(request.getParameter("cin"));
            dto.setPhoneNumber(request.getParameter("telephone"));
            dto.setWeight(request.getParameter("weight"));
            dto.setBloodType(request.getParameter("bloodType"));
            dto.setDateOfBirth(request.getParameter("dateDeNaissance"));
            ReceiverDTO updatedReceiver = receiverService.updateReceiver(dto);
            if(null == updatedReceiver) {
                request.getSession().setAttribute(ERROR, "Error while updating receiver");
                return CommandResult.redirect(request.getContextPath() + PATH);
            }

            return CommandResult.redirect(request.getContextPath() + PATH);
        }

        return CommandResult.redirect(request.getContextPath() + PATH);
    }
}
