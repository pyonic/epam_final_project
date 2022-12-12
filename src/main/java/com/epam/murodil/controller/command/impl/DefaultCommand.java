package com.epam.murodil.controller.command.impl;

import com.epam.murodil.constants.EntityConstants;
import com.epam.murodil.controller.command.Command;
import com.epam.murodil.controller.navigation.Router;
import com.epam.murodil.exceptions.DaoException;
import com.epam.murodil.exceptions.ServiceException;
import com.epam.murodil.model.entity.Account;
import com.epam.murodil.model.entity.Medicine;

import static com.epam.murodil.constants.QueryConstants.*;
import com.epam.murodil.constants.ControllerConstants;
import com.epam.murodil.service.impl.MedicineServiceImpl;
import com.epam.murodil.utils.LoggerUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.rmi.UnexpectedException;
import java.sql.SQLException;
import java.util.List;

public class DefaultCommand  implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws UnexpectedException {
        String page = ControllerConstants.DEFAULT_PAGE;
        HttpSession session = request.getSession();
        Account account = (Account) request.getAttribute(SESSION_USER);

        if (account != null && account.getRole().equals(EntityConstants.DOCTOR_ROLE)) {
            String order_for = request.getParameter(ORDER_FOR);
            String description = request.getParameter(DESCRIPTION);
            String current_receipt_id = request.getParameter(RECEIPT_ID);
            if (order_for != null) {
                session.setAttribute(ORDER_FOR, order_for);
                session.setAttribute(DESCRIPTION, description);
                session.setAttribute(RECEIPT_ID, current_receipt_id);
                return new Router(ControllerConstants.MAIN_PAGE_ROUTER, Router.PageChangeType.REDIRECT);
            }
        }

        List<Medicine> medicines;
        try {
            medicines = MedicineServiceImpl.getInstance().getList();
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute(MEDICINES, medicines);
        return new Router(page, Router.PageChangeType.FORWARD);
    }
}
