package com.jartiste.bloodbridge.application.command.receiver;

import com.jartiste.bloodbridge.application.command.Command;
import com.jartiste.bloodbridge.application.command.CommandResult;
import com.jartiste.bloodbridge.application.service.ReceiverService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DeleteReceiverCommand  implements Command {

    public static final String ERROR = "error";
    public static final String PATH = "/receivers/list";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ReceiverService receiverService = getService(request, ReceiverService.class);

            if("POST".equalsIgnoreCase(request.getMethod())) {
                String receiverID = request.getParameter("receiverId");
                if(null == receiverID || receiverID.isEmpty()) {
                    request.getSession().setAttribute(ERROR, "Receiver ID is required");
                    return CommandResult.redirect(request.getContextPath() + PATH);
                }

                try {
                    boolean deleted = receiverService.deleteReceiver(Long.parseLong(receiverID));
                    if(deleted) {
                        request.getSession().setAttribute("message", "Receiver is deleted Successfully");
                    } else {
                        request.getSession().setAttribute(ERROR, "Receiver could not be deleted");
                    }
                } catch (Exception e) {
                    request.getSession().setAttribute(ERROR, "An error occur while deleting the receivers");
                }

                return CommandResult.redirect(request.getContextPath() + PATH);
            }
        return null;
    }
}
