package com.epammurodil.controller.command.impl;

import static com.epammurodil.constants.ErrorConstants.*;
import static com.epammurodil.constants.QueryConstants.*;
import com.epammurodil.constants.ControllerConstants;
import com.epammurodil.controller.command.Command;
import com.epammurodil.controller.navigation.Router;
import com.epammurodil.model.entity.Account;
import com.epammurodil.service.impl.AccountServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.rmi.UnexpectedException;
import java.util.Map;

public class SignUpCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws UnexpectedException {
        HttpSession session = request.getSession();
        String method = request.getMethod();
        if (method.equals(GET_REQUEST)) {
            String page = ControllerConstants.REGISTER_JSP;
            return new Router(page, Router.PageChangeType.FORWARD);
        } else if (method.equals(POST_REQUEST)) {
            String first_name = request.getParameter(FIRST_NAME);
            String last_name = request.getParameter(LAST_NAME);
            String email = request.getParameter(EMAIL);
            String phone = request.getParameter(PHONE);
            String password = request.getParameter(PASSWORD);
            String password_confirm = request.getParameter(PASSWORD_CONFIRM);

            request.setAttribute(FIRST_NAME, first_name);
            System.out.println(request.getAttribute(FIRST_NAME));
            request.setAttribute(LAST_NAME, last_name);
            request.setAttribute(EMAIL, email);
            request.setAttribute(PHONE, phone);
            request.setAttribute(PASSWORD, password);
            request.setAttribute(PASSWORD_CONFIRM, password_confirm);


            Map<String, Object> signUpUser = AccountServiceImpl.getInstance().signUpAccount(first_name, last_name, email, phone, password, password_confirm);

            if (signUpUser.containsKey(ERROR_KEY)) {
                request.setAttribute(ERROR_KEY, signUpUser.get(ERROR_KEY));
                return new Router(ControllerConstants.REGISTER_JSP, Router.PageChangeType.FORWARD);
            } else if (signUpUser.containsKey(SESSION_USER)) {
                Account account = (Account) signUpUser.get(SESSION_USER);
                session.setAttribute(SESSION_USER, account);
                session.setAttribute(SESSION_USER_EMAIL, email);
                session.setAttribute(SESSION_USER_FULL_NAME, account.getFullName());
                return new Router(ControllerConstants.MAIN_PAGE_ROUTER, Router.PageChangeType.REDIRECT);
            }
        }
        return new Router(ControllerConstants.MAIN_PAGE_ROUTER, Router.PageChangeType.REDIRECT);
    }
}
