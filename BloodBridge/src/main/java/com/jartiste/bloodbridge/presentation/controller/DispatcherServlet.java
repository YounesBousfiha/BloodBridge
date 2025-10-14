package com.jartiste.bloodbridge.presentation.controller;

import com.jartiste.bloodbridge.application.command.*;
import com.jartiste.bloodbridge.application.command.donation.CreateAssociationCommand;
import com.jartiste.bloodbridge.application.command.donor.*;
import com.jartiste.bloodbridge.application.command.receiver.CreateReceiverCommand;
import com.jartiste.bloodbridge.application.command.receiver.DeleteReceiverCommand;
import com.jartiste.bloodbridge.application.command.receiver.EditReceiverCommand;
import com.jartiste.bloodbridge.application.command.receiver.ListReceiverCommand;
import com.jartiste.bloodbridge.infrastructure.logging.AppLogger;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DispatcherServlet extends HttpServlet {
    private final Map<String, Command> commands = new HashMap<>();
    private static final Logger logger = AppLogger.getLogger(DispatcherServlet.class);

    @Override
    public void init() {
        /* DonorCommands List */
        commands.put("/donors/create", new CreateDonorCommand());
        commands.put("/donors/list", new ListDonorsCommand());
        commands.put("/donors/edit", new EditDonorCommand());
        commands.put("/donors/delete", new DeleteDonorCommand());

        /* ReceiverCommandsList */
        commands.put("/receivers/create", new CreateReceiverCommand());
        commands.put("/receivers/list", new ListReceiverCommand());
        commands.put("/receivers/edit", new EditReceiverCommand());
        commands.put("/receivers/delete", new DeleteReceiverCommand());

        /* association  */
        commands.put("/donors/assign", new CreateAssociationCommand());



    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        process(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        process(req, res);
    }

    private void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String path = req.getServletPath() + req.getPathInfo();

        Command command = commands.get(path);

        if(null == command) {
            res.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        CommandResult result = command.execute(req, res);

        if(result.isRedirect()) {
            res.sendRedirect(result.getPath());
        } else {
            req.getRequestDispatcher(result.getPath()).forward(req, res);
        }

    }
}
