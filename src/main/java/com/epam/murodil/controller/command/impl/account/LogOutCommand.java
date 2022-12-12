package com.epam.murodil.controller.command.impl.account;

import com.epam.murodil.constants.ControllerConstants;
import com.epam.murodil.constants.QueryConstants;
import com.epam.murodil.controller.command.Command;
import com.epam.murodil.controller.navigation.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.rmi.UnexpectedException;

public class LogOutCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws UnexpectedException {
        HttpSession session = request.getSession();
        session.setAttribute(QueryConstants.SESSION_USER, null);
        return new Router(ControllerConstants.MAIN_PAGE_ROUTER, Router.PageChangeType.REDIRECT);
    }
}
