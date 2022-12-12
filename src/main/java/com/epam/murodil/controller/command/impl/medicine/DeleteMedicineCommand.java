package com.epam.murodil.controller.command.impl.medicine;

import com.epam.murodil.controller.command.Command;
import com.epam.murodil.controller.navigation.Router;
import com.epam.murodil.constants.QueryConstants;
import com.epam.murodil.constants.ControllerConstants;
import com.epam.murodil.exceptions.DaoException;
import com.epam.murodil.service.impl.MedicineServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.rmi.UnexpectedException;

public class DeleteMedicineCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws UnexpectedException, DaoException {
        String medicine_id = request.getParameter(QueryConstants.MEDICINE_ID);
        MedicineServiceImpl.getInstance().deleteOne(Integer.parseInt(medicine_id));
        return new Router(ControllerConstants.MAIN_PAGE_ROUTER, Router.PageChangeType.REDIRECT);
    }
}
