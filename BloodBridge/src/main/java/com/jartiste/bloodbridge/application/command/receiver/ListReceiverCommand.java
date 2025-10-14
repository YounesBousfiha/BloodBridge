package com.jartiste.bloodbridge.application.command.receiver;

import com.jartiste.bloodbridge.application.command.Command;
import com.jartiste.bloodbridge.application.command.CommandResult;
import com.jartiste.bloodbridge.application.command.donor.ListDonorsCommand;
import com.jartiste.bloodbridge.application.service.ReceiverService;
import com.jartiste.bloodbridge.infrastructure.logging.AppLogger;
import com.jartiste.bloodbridge.presentation.dto.ReceiverDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.List;

public class ListReceiverCommand implements Command {

    private static final String ERROR = "error";
    private static final String VIEW_PATH = "/WEB-INF/views/receivers/list.jsp";
    public static final String REDIRECT_PATH = "/receivers/list";
    public static final Logger logger = AppLogger.getLogger(ListReceiverCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReceiverService receiverService = getService(request, ReceiverService.class);

        if("GET".equalsIgnoreCase(request.getMethod())) {

            List<ReceiverDTO> receiverList = receiverService.getAllReceivers();

            if(null == receiverList || receiverList.isEmpty()) {
                request.getSession().setAttribute(ERROR, "No Receivers Yet");
                return CommandResult.forward(VIEW_PATH);
            }

            request.setAttribute("receivers", receiverList);


            return CommandResult.forward(VIEW_PATH);
        }

        return CommandResult.redirect(request.getContextPath() + REDIRECT_PATH);
    }
}
