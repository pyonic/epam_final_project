package com.epam.murodil.controller.command;

import com.epam.murodil.controller.navigation.Router;
import com.epam.murodil.exceptions.DaoException;
import com.epam.murodil.exceptions.ServiceException;
import jakarta.servlet.http.HttpServletRequest;

import java.rmi.UnexpectedException;

public interface Command {
    Router execute(HttpServletRequest request) throws UnexpectedException, DaoException, ServiceException;
}
