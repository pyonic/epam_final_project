package com.epammurodil.controller.command.impl;

import com.epammurodil.constants.ErrorConstants;
import com.epammurodil.constants.QueryConstants;
import com.epammurodil.constants.ControllerConstants;
import com.epammurodil.controller.command.Command;
import com.epammurodil.controller.navigation.Router;
import com.epammurodil.service.impl.AccountServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import javax.management.Query;
import java.rmi.UnexpectedException;
import java.sql.SQLException;
import java.util.Map;

public class SignInCommand implements Command {
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
