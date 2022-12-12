package com.epam.murodil.controller;

import com.epam.murodil.constants.ControllerConstants;
import com.epam.murodil.controller.command.QueryCommands;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "AppController", urlPatterns ={"/accounts","/receipts","/orders", "/add_medicine", "/login", "/register", "/logout", "/pharmacy/*" } )
public class AppController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        redirector(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        redirector(request, response);
    }

    public void redirector(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullUri = request.getRequestURI();
        String uri = "/" + fullUri.split("/")[1];
        System.out.println(uri);
        StringBuilder redirect_uri = new StringBuilder(FriendlyUrls.FRIENDLY_REDIRECTIONS.get(uri));

        Arrays.stream(fullUri.replace(uri.replace("/", ""), "").split("/")).forEach(q -> {
            if (q.length() > 0) {
                redirect_uri.append("&q=" + q);
            }
        });

        if (redirect_uri != null) {
            System.out.println(redirect_uri.toString());
            request.getRequestDispatcher(redirect_uri.toString()).forward(request, response);
        } else {
            request.getRequestDispatcher(ControllerConstants.APPLICATION_PREFIX + QueryCommands.NOT_FOUND).forward(request, response);
        }
    }
}
