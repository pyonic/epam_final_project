package com.epammurodil.controller.command.impl;

import com.epammurodil.constants.ControllerConstants;
import static com.epammurodil.constants.QueryConstants.*;
import com.epammurodil.controller.command.Command;
import com.epammurodil.controller.command.QueryCommands;
import com.epammurodil.controller.navigation.Router;
import com.epammurodil.service.impl.MedicineServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.rmi.UnexpectedException;

public class FinishNewMedicineCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws UnexpectedException {
        String title = request.getParameter(TITLE);
        String description = request.getParameter(DESCRIPTION);
        String price = request.getParameter(PRICE);

        boolean need_receipt = request.getParameter(NEED_RECEIPT).equals(ON);

        MedicineServiceImpl.getInstance().insertMedicine(title, description, BigDecimal.valueOf(Double.valueOf(price)), need_receipt);
        return new Router(ControllerConstants.APPLICATION_PREFIX + QueryCommands.DEFAULT, Router.PageChangeType.REDIRECT);
    }
}
