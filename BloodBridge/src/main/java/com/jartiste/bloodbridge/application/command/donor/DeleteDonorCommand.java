package com.jartiste.bloodbridge.application.command.donor;

import com.jartiste.bloodbridge.application.command.Command;
import com.jartiste.bloodbridge.application.command.CommandResult;
import com.jartiste.bloodbridge.application.service.DonorService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DeleteDonorCommand  implements Command {

    public static final String ERROR = "error";
    public static final String PATH = "/donors/list";
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DonorService donorService = getService(request, DonorService.class);
        if("POST".equalsIgnoreCase(request.getMethod())) {
            String donorID = request.getParameter("donorId");
            if(null == donorID || donorID.isEmpty()) {
                request.getSession().setAttribute(ERROR, "Donor ID is required");
                return CommandResult.redirect(request.getContextPath() + PATH);
            }

            try {
                boolean deleted = donorService.deleteDonor(donorID);
                if(deleted) {
                    request.getSession().setAttribute("message", "Donor is deleted Successfully");
                } else {
                    request.getSession().setAttribute(ERROR, "Donor could not be deleted");
                }
            } catch (Exception e) {
                request.getSession().setAttribute(ERROR, "An error occur while deleting the donors");
            }

            return CommandResult.redirect(request.getContextPath() + PATH);

        }
        return CommandResult.redirect(request.getContextPath() + PATH);
    }
}
