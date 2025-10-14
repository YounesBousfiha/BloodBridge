package com.jartiste.bloodbridge.application.command.donor;

import com.jartiste.bloodbridge.application.command.Command;
import com.jartiste.bloodbridge.application.command.CommandResult;
import com.jartiste.bloodbridge.application.service.DonorService;
import com.jartiste.bloodbridge.infrastructure.logging.AppLogger;
import com.jartiste.bloodbridge.presentation.dto.DonorDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;

import java.io.IOException;
import java.time.LocalDate;

public class EditDonorCommand implements Command {

    private static final Logger logger = AppLogger.getLogger(EditDonorCommand.class);
    private static final String ERROR = "error";
    private static final String PATH = "/donors/list";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DonorService donorService = getService(request, DonorService.class);

        /*if("GET".equalsIgnoreCase(request.getMethod())) {
            String donorId = request.getParameter("donorId");
            logger.error("DEBUG: donorId => {} ", donorId);
            DonorDTO donorOpt = donorService.findDonorbyId(donorId);
            if(null == donorOpt) {
                logger.error("Donor is Null");
                request.getSession().setAttribute(ERROR, "Donor Not Found");
                return CommandResult.redirect(request.getContextPath() + PATH);
            }

            request.setAttribute("donor", donorOpt);
            return CommandResult.forward("/WEB-INF/views/donors/editForm.jsp");
        }*/

        if("POST".equalsIgnoreCase(request.getMethod())) {

            String donorId = request.getParameter("donorId");

            DonorDTO donorOpt = donorService.findDonorbyId(donorId);

            if(null == donorOpt) {
                request.getSession().setAttribute(ERROR, "Donor Not Found");
                return CommandResult.redirect(request.getContextPath() + PATH);
            }


            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String cin = request.getParameter("cin");
            String telephone = request.getParameter("telephone");
            String dateDeNaissance = request.getParameter("dateDeNaissance");
            String weightStr = request.getParameter("weight");

            logger.error("DEBUG: Received firstName => {} ", firstName);
            logger.error("DEBUG: Received lastName => {} ", lastName);
            logger.error("DEBUG: Received cin => {} ", cin);
            logger.error("DEBUG: Received telephone => {} ", telephone);
            logger.error("DEBUG: Received dateDeNaissance => {} ", dateDeNaissance);
            logger.error("DEBUG: Received weightStr => {} ", weightStr);

            donorOpt.setFirstName(firstName);
            donorOpt.setLastName(lastName);
            donorOpt.setCin(cin);
            donorOpt.setTelephone(telephone);
            donorOpt.setDateDeNaissance(LocalDate.parse(dateDeNaissance));
            donorOpt.setWeight(Integer.parseInt(weightStr));

            logger.error("DEBUG: Donor DTO to be updated => {} ", donorOpt);
            DonorDTO updatedDonor = donorService.updateDonor(donorOpt);

            request.getSession().setAttribute("message", "Donor is updated Successfully");
            if(null == updatedDonor) {
                request.getSession().setAttribute(ERROR, "Error while updating donor");
                return CommandResult.redirect(request.getContextPath() + PATH);
            }

            return CommandResult.redirect(request.getContextPath() + PATH);
        }

        return CommandResult.redirect(request.getContextPath() + PATH);
    }
}
