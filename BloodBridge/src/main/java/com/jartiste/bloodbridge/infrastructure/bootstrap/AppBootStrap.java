package com.jartiste.bloodbridge.infrastructure.bootstrap;

import com.jartiste.bloodbridge.application.service.DonorService;
import com.jartiste.bloodbridge.application.service.ReceiverService;
import com.jartiste.bloodbridge.domain.repository.DonorRepository;
import com.jartiste.bloodbridge.domain.repository.ReceiverRepository;
import com.jartiste.bloodbridge.infrastructure.persistence.DonorRepoImpl;
import com.jartiste.bloodbridge.infrastructure.persistence.ReceiverRepoImpl;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.xml.crypto.Data;

public class AppBootStrap implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/bloodbridgeDB");

            // init Repositories
            DonorRepository donorRepo = new DonorRepoImpl(ds);
            ReceiverRepository receiverRepo = new ReceiverRepoImpl(ds);


            // Init Services
            DonorService donorService = new DonorService(donorRepo);
            ReceiverService receiverService = new ReceiverService(receiverRepo);

            sce.getServletContext().setAttribute("donorService", donorService);
            sce.getServletContext().setAttribute("receiverService", receiverService);

        } catch (NamingException e) {
            throw new RuntimeException("Failed to initialize database connection", e);
        }
    }


}
