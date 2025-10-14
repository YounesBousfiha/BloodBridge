package com.jartiste.bloodbridge.application.command.donor;

import com.jartiste.bloodbridge.application.command.Command;
import com.jartiste.bloodbridge.application.command.CommandResult;
import com.jartiste.bloodbridge.application.service.DonationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AssociateDonorCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* call service/ useCase */
        DonationService donationService = getService(request, DonationService.class);

        String donorId = request.getParameter("donorId");
        String receiverId = request.getParameter("receiverId");

        if(null == donorId || null == receiverId) {
            throw new IllegalArgumentException("Donor ID and Receiver ID cannot be null");
        }

        boolean associated = donationService.associateDonorToReceiver(
                Long.parseLong(donorId),
                Long.parseLong(receiverId)
        );

        if (!associated) {
            request.getSession().setAttribute("error", "Error while associating donor to receiver");
        }

        return CommandResult.redirect(request.getContextPath() + "/donors");
    }
}
