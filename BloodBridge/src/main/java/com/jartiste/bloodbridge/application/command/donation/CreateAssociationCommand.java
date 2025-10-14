package com.jartiste.bloodbridge.application.command.donation;

import com.jartiste.bloodbridge.application.command.Command;
import com.jartiste.bloodbridge.application.command.CommandResult;
import com.jartiste.bloodbridge.application.service.DonationService;
import com.jartiste.bloodbridge.infrastructure.logging.AppLogger;
import com.jartiste.bloodbridge.presentation.dto.AssociationResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;

import java.io.IOException;

public class CreateAssociationCommand implements Command {

    private static final Logger logger = AppLogger.getLogger(CreateAssociationCommand.class);
    private static final String ERROR = "error";
    private static final String PATH = "/donors/list";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DonationService donationService = getService(request, DonationService.class);

        if("POST".equalsIgnoreCase(request.getMethod())) {

            String donorId = request.getParameter("donorId");
            String receiverId = request.getParameter("receiverId");

            AssociationResult result = donationService.associateDonorToReceiver(Long.parseLong(donorId), Long.parseLong(receiverId));

            if(!result.isSuccess()) {
                request.getSession().setAttribute(ERROR, result.getMessage());
                return CommandResult.redirect(request.getContextPath() + PATH);
            }

            return CommandResult.redirect(request.getContextPath() + PATH);
        }

        return CommandResult.redirect(request.getContextPath() + PATH);
    }
}
