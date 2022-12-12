package com.epam.murodil.controller;

import com.epam.murodil.constants.ControllerConstants;
import com.epam.murodil.controller.command.QueryCommands;

import java.util.HashMap;
import java.util.Map;

public class FriendlyUrls {
    public static final Map<String, String> FRIENDLY_REDIRECTIONS = new HashMap<String, String>()
    {{
        put("/login", ControllerConstants.APPLICATION_PREFIX + QueryCommands.SIGN_IN);
        put("/add_medicine", ControllerConstants.APPLICATION_PREFIX + QueryCommands.NEW_MEDICINE);
        put("/register", ControllerConstants.APPLICATION_PREFIX + QueryCommands.SIGN_UP);
        put("/logout", ControllerConstants.APPLICATION_PREFIX + QueryCommands.LOGOUT);
        put("/pharmacy", ControllerConstants.APPLICATION_PREFIX + QueryCommands.MEDICINE);
        put("/orders", ControllerConstants.APPLICATION_PREFIX + QueryCommands.ORDERS);
        put("/receipts", ControllerConstants.APPLICATION_PREFIX + QueryCommands.RECEIPTS);
        put("/accounts", ControllerConstants.APPLICATION_PREFIX + QueryCommands.ACCOUNTS);
    }};
}
