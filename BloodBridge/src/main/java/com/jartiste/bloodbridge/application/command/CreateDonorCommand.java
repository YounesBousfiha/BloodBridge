package com.jartiste.bloodbridge.application.command;

import com.jartiste.bloodbridge.application.service.DonorService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CreateDonorCommand  implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DonorService donorService = getService(request, DonorService.class);

        /* Check the Method if it gets this display the Form, if it posts, then its submission */
        /* call service/ useCase */
    }
}
