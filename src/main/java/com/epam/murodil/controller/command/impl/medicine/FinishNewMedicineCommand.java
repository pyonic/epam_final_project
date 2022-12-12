package com.epam.murodil.controller.command.impl.medicine;

import com.epam.murodil.controller.command.Command;
import com.epam.murodil.controller.navigation.Router;
import com.epam.murodil.constants.ControllerConstants;
import static com.epam.murodil.constants.QueryConstants.*;

import com.epam.murodil.controller.command.QueryCommands;
import com.epam.murodil.exceptions.ServiceException;
import com.epam.murodil.service.impl.MedicineServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.rmi.UnexpectedException;

public class FinishNewMedicineCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws UnexpectedException, ServiceException {
        String title = request.getParameter(TITLE);
        String description = request.getParameter(DESCRIPTION);
        String price = request.getParameter(PRICE);

        boolean need_receipt = request.getParameter(NEED_RECEIPT).equals(ON);

        MedicineServiceImpl.getInstance().insertMedicine(title, description, BigDecimal.valueOf(Double.valueOf(price)), need_receipt);
        return new Router(ControllerConstants.APPLICATION_PREFIX + QueryCommands.DEFAULT, Router.PageChangeType.REDIRECT);
    }
}
