package com.jartiste.bloodbridge.application.command.receiver;

import com.jartiste.bloodbridge.application.command.Command;
import com.jartiste.bloodbridge.application.command.CommandResult;
import com.jartiste.bloodbridge.application.service.ReceiverService;
import com.jartiste.bloodbridge.domain.enums.BloodType;
import com.jartiste.bloodbridge.domain.enums.UrgentReceveur;
import com.jartiste.bloodbridge.presentation.dto.ReceiverDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CreateReceiverCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReceiverService receiverService = getService(request, ReceiverService.class);

        if("GET".equalsIgnoreCase(request.getMethod())) {
            return CommandResult.forward("/WEB-INF/views/receiver/createReceiverForm.jsp");
        }

        if("POST".equalsIgnoreCase(request.getMethod())) {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String cin = request.getParameter("cin");
            String bloodType = request.getParameter("bloodType");
            String telephone = request.getParameter("telephone");
            String weight = request.getParameter("weight");
            String dateDeNaissance = request.getParameter("dateDeNaissance");
            String urgencyLevel = request.getParameter("urgentReceveur");

            // Create DTO and set properties
            ReceiverDTO dto = new ReceiverDTO();
            dto.setFirstName(firstName);
            dto.setLastName(lastName);
            dto.setCin(cin);
            dto.setBloodType(BloodType.valueOf(bloodType));
            dto.setPhoneNumber(telephone);
            dto.setUrgencyLevel(UrgentReceveur.valueOf(urgencyLevel));

            receiverService.createReceiver(dto);

            request.getSession().setAttribute("success", "Receiver Created Successfully");

            return CommandResult.redirect(request.getContextPath() + "/receivers/list");
        }

        return null;
    }
}
