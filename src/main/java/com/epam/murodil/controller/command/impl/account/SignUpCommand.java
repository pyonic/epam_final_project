package com.epam.murodil.controller.command.impl.account;

import com.epam.murodil.constants.ErrorConstants;
import com.epam.murodil.constants.QueryConstants;
import com.epam.murodil.controller.command.Command;
import com.epam.murodil.controller.navigation.Router;
import com.epam.murodil.exceptions.ServiceException;
import com.epam.murodil.model.entity.Account;
import com.epam.murodil.constants.ControllerConstants;
import com.epam.murodil.service.impl.AccountServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.rmi.UnexpectedException;
import java.util.Map;

public class SignUpCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws UnexpectedException, ServiceException {
        HttpSession session = request.getSession();
        String method = request.getMethod();
        if (method.equals(QueryConstants.GET_REQUEST)) {
            String page = ControllerConstants.REGISTER_JSP;
            return new Router(page, Router.PageChangeType.FORWARD);
        } else if (method.equals(QueryConstants.POST_REQUEST)) {
            String first_name = request.getParameter(QueryConstants.FIRST_NAME);
            String last_name = request.getParameter(QueryConstants.LAST_NAME);
            String email = request.getParameter(QueryConstants.EMAIL);
            String phone = request.getParameter(QueryConstants.PHONE);
            String password = request.getParameter(QueryConstants.PASSWORD);
            String password_confirm = request.getParameter(QueryConstants.PASSWORD_CONFIRM);

            request.setAttribute(QueryConstants.FIRST_NAME, first_name);
            System.out.println(request.getAttribute(QueryConstants.FIRST_NAME));
            request.setAttribute(QueryConstants.LAST_NAME, last_name);
            request.setAttribute(QueryConstants.EMAIL, email);
            request.setAttribute(QueryConstants.PHONE, phone);
            request.setAttribute(QueryConstants.PASSWORD, password);
            request.setAttribute(QueryConstants.PASSWORD_CONFIRM, password_confirm);


            Map<String, Object> signUpUser = AccountServiceImpl.getInstance().signUpAccount(first_name, last_name, email, phone, password, password_confirm);

            if (signUpUser.containsKey(ErrorConstants.ERROR_KEY)) {
                request.setAttribute(ErrorConstants.ERROR_KEY, signUpUser.get(ErrorConstants.ERROR_KEY));
                return new Router(ControllerConstants.REGISTER_JSP, Router.PageChangeType.FORWARD);
            } else if (signUpUser.containsKey(QueryConstants.SESSION_USER)) {
                Account account = (Account) signUpUser.get(QueryConstants.SESSION_USER);
                session.setAttribute(QueryConstants.SESSION_USER, account);
                session.setAttribute(QueryConstants.SESSION_USER_EMAIL, email);
                session.setAttribute(QueryConstants.SESSION_USER_FULL_NAME, account.getFullName());
                return new Router(ControllerConstants.MAIN_PAGE_ROUTER, Router.PageChangeType.REDIRECT);
            }
        }
        return new Router(ControllerConstants.MAIN_PAGE_ROUTER, Router.PageChangeType.REDIRECT);
    }
}
