package com.jartiste.bloodbridge.application.command.donor;

import com.jartiste.bloodbridge.application.command.Command;
import com.jartiste.bloodbridge.application.command.CommandResult;
import com.jartiste.bloodbridge.application.service.DonorService;
import com.jartiste.bloodbridge.application.service.ReceiverService;
import com.jartiste.bloodbridge.domain.entity.Receiver;
import com.jartiste.bloodbridge.infrastructure.logging.AppLogger;
import com.jartiste.bloodbridge.presentation.dto.DonorDTO;
import com.jartiste.bloodbridge.presentation.dto.ReceiverDTO;
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
        ReceiverService receiverService = getService(request, ReceiverService.class);

        if("GET".equalsIgnoreCase(request.getMethod())) {
            List<DonorDTO> donorList = donorService.findAllDonors();
            List<ReceiverDTO> receiverList = receiverService.getAllReceivers();

            if(null == donorList || donorList.isEmpty()) {
                request.getSession().setAttribute(ERROR, "No Donors Yet");
                return CommandResult.forward(VIEW_PATH);
            }

            if(null == receiverList || receiverList.isEmpty()) {
                request.getSession().setAttribute(ERROR, "No Receivers Yet");
                return CommandResult.forward(VIEW_PATH);
            }

            request.setAttribute("receivers", receiverList);
            request.setAttribute("donors", donorList);



            return CommandResult.forward(VIEW_PATH);
        }
        return CommandResult.redirect(request.getContextPath() + REDIRECT_PATH);
    }
}
