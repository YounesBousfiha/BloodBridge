package com.jartiste.bloodbridge.presentation.controller;

import com.jartiste.bloodbridge.application.command.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DispatcherServlet extends HttpServlet {
    private final Map<String, Command> commands = new HashMap<>();

    @Override
    public void init() {
        /* DonorCommands List */
        commands.put("/donors/create", new CreateDonorCommand());
        commands.put("/donors/list", new ListDonorsCommand());
        commands.put("/donors/edit", new EditDonorCommand());
        commands.put("/donors/associate", new AssociateDonorCommand());
        commands.put("/donors/delete", new DeleteDonorCommand());

        /* ReceiverCommandsList */
        commands.put("/receiver/create", new CreateReceiverCommand());
        commands.put("/receiver/list", new ListReceiverCommand());
        commands.put("/receiver/edit", new EditReceiverCommand());
        commands.put("/receiver/delete", new DeleteReceiverCommand());
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
        String path = req.getPathInfo();

        Command command = commands.get(path);

        if(command != null) {
            command.execute(req, res);
        } else  {
            res.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
