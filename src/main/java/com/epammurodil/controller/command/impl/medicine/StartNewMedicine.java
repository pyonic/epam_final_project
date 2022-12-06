package com.epammurodil.controller.command.impl.medicine;

import com.epammurodil.constants.ControllerConstants;
import com.epammurodil.controller.command.Command;
import com.epammurodil.controller.navigation.Router;
import jakarta.servlet.http.HttpServletRequest;

import java.rmi.UnexpectedException;

public class StartNewMedicine implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws UnexpectedException {
        String page = ControllerConstants.NEW_MEDICINE_JSP;
        return new Router(page, Router.PageChangeType.FORWARD);
    }
}
