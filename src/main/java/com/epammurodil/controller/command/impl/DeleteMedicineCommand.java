package com.epammurodil.controller.command.impl;

import com.epammurodil.constants.QueryConstants;
import com.epammurodil.constants.ControllerConstants;
import com.epammurodil.controller.command.Command;
import com.epammurodil.controller.navigation.Router;
import com.epammurodil.service.impl.MedicineServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.rmi.UnexpectedException;

public class DeleteMedicineCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws UnexpectedException {
        String medicine_id = request.getParameter(QueryConstants.MEDICINE_ID);
        MedicineServiceImpl.getInstance().deleteOne(Integer.parseInt(medicine_id));
        return new Router(ControllerConstants.MAIN_PAGE_ROUTER, Router.PageChangeType.REDIRECT);
    }
}
