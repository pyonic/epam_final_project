package com.epammurodil.controller.command;

import com.epammurodil.controller.navigation.Router;
import jakarta.servlet.http.HttpServletRequest;

import java.rmi.UnexpectedException;

public interface Command {
    Router execute(HttpServletRequest request) throws UnexpectedException;
}
