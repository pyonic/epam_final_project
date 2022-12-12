package com.epam.murodil.controller.command.impl;

import com.epam.murodil.controller.command.Command;
import com.epam.murodil.controller.navigation.Router;
import com.epam.murodil.constants.ControllerConstants;
import jakarta.servlet.http.HttpServletRequest;

import java.rmi.UnexpectedException;

public class NotFoundCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws UnexpectedException {
        String page = ControllerConstants.NOT_FOUND_JSP;
        return new Router(page, Router.PageChangeType.FORWARD);
    }
}
