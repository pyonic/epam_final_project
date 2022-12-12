package com.epam.murodil.controller;

import com.epam.murodil.controller.command.Command;
import com.epam.murodil.controller.navigation.Router;
import com.epam.murodil.constants.ControllerConstants;
import com.epam.murodil.controller.command.CommandType;
import com.epam.murodil.utils.LoggerUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet(name = "PharmacyController", urlPatterns = { "/pharmacy" })
public class Contoller extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    private static final Logger logger = LogManager.getLogger();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.info(LoggerUtils.getUrlInfoForLogger(request));
        response.setContentType(CONTENT_TYPE);
        String commandStr = request.getParameter(ControllerConstants.REQUEST_PARAMETER);
        Command command = CommandType.define(commandStr);
        try {

            Router router = command.execute(request);
            String page = router.getPage();
            switch (router.getType()) {
                case FORWARD: {
                    request.getRequestDispatcher(page).forward(request, response);
                    break;
                }
                case REDIRECT: {
                    response.sendRedirect(request.getContextPath() + page);
                    break;
                }
                default: {
                    logger.error("Invalid routing type!");
                    response.sendError(500);
                    break;
                }
            }

        } catch (Exception e) {
//            logger.error("Error while command execution: " + commandStr, e);
            throw new ServletException(e);
        }
    }
}
