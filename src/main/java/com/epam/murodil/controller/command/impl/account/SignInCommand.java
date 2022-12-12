package com.epam.murodil.controller.command.impl.account;

import com.epam.murodil.constants.ErrorConstants;
import com.epam.murodil.constants.QueryConstants;
import com.epam.murodil.controller.command.Command;
import com.epam.murodil.controller.navigation.Router;
import com.epam.murodil.constants.ControllerConstants;
import com.epam.murodil.service.impl.AccountServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.rmi.UnexpectedException;
import java.sql.SQLException;
import java.util.Map;

public class SignInCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) throws UnexpectedException {
        HttpSession session = request.getSession();
        String method = request.getMethod();
        if (method.equals(QueryConstants.GET_REQUEST)) {
            String page = ControllerConstants.SIGN_IN_JSP;
            return new Router(page, Router.PageChangeType.FORWARD);
        } else {
            String email = request.getParameter(QueryConstants.EMAIL);
            String password = request.getParameter(QueryConstants.PASSWORD);
            try {
                Map<String, Object> userLogin = AccountServiceImpl.getInstance().signIn(email, password);
                if (userLogin.getOrDefault(QueryConstants.SESSION_USER, null) != null) {
                    session.setAttribute(QueryConstants.SESSION_USER, userLogin.get(QueryConstants.SESSION_USER));
                    return new Router(ControllerConstants.MAIN_PAGE_ROUTER, Router.PageChangeType.REDIRECT);
                }
                request.setAttribute(ErrorConstants.ERROR_KEY, ErrorConstants.AUTH_FAILURE);
                return new Router(ControllerConstants.SIGN_IN_JSP, Router.PageChangeType.FORWARD);
            } catch (SQLException e) {
                return new Router(ControllerConstants.SIGN_IN_JSP, Router.PageChangeType.FORWARD);
            }
        }
    }
}
