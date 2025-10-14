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
import java.util.List;

public class ListDonorsCommand implements Command {

    public static final String ERROR = "error";
    public static final String VIEW_PATH = "/WEB-INF/views/donors/list.jsp";
    public static final String REDIRECT_PATH = "/donors/list";
    public static final Logger logger = AppLogger.getLogger(ListDonorsCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DonorService donorService = getService(request, DonorService.class);

        if("GET".equalsIgnoreCase(request.getMethod())) {
            List<DonorDTO> donorList = donorService.findAllDonors();

            if(null == donorList || donorList.isEmpty()) {
                request.getSession().setAttribute(ERROR, "No Donors Yet");
                return CommandResult.forward(VIEW_PATH);
            }

            request.setAttribute("donors", donorList);



            return CommandResult.forward(VIEW_PATH);
        }
        return CommandResult.redirect(request.getContextPath() + REDIRECT_PATH);
    }
}
