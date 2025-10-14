package com.jartiste.bloodbridge.application.command.donor;

import com.jartiste.bloodbridge.application.command.Command;
import com.jartiste.bloodbridge.application.command.CommandResult;
import com.jartiste.bloodbridge.application.service.DonorService;
import com.jartiste.bloodbridge.domain.enums.BloodType;
import com.jartiste.bloodbridge.domain.enums.Contraindication;
import com.jartiste.bloodbridge.infrastructure.logging.AppLogger;
import com.jartiste.bloodbridge.presentation.dto.DonorDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CreateDonorCommand  implements Command {

    private static final Logger logger = AppLogger.getLogger(CreateDonorCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DonorService donorService = getService(request, DonorService.class);

        if("GET".equalsIgnoreCase(request.getMethod())) {
            return CommandResult.forward("/WEB-INF/views/donor/createDonorForm.jsp");
        }

        if("POST".equalsIgnoreCase(request.getMethod())) {
            DonorDTO dto = new DonorDTO();
            dto.setFirstName(request.getParameter("firstName"));
            dto.setLastName(request.getParameter("lastName"));
            dto.setCin(request.getParameter("cin"));
            dto.setBloodType(BloodType.valueOf(request.getParameter("bloodType")));
            dto.setTelephone(request.getParameter("telephone"));
            dto.setWeight(Integer.parseInt(request.getParameter("weight")));
            dto.setDateDeNaissance(LocalDate.parse(request.getParameter("dateDeNaissance")));
            String[] contraindications = request.getParameterValues("contraIndications");
            if(null != contraindications) {
                dto.setContraindications(
                        Arrays.stream(contraindications)
                                .map(Contraindication::valueOf)
                                .collect(Collectors.toSet())
                );
            }

            if(donorService.createDonor(dto)) {
                request.getSession().setAttribute("message", "Donor is created Successfully");
            } else {
                request.getSession().setAttribute("error", "Error while creating the donor");
            }

            return CommandResult.redirect(request.getContextPath() + "/donors/list");
        }

        return null;
    }
}
