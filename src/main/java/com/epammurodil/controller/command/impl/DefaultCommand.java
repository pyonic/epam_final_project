package com.epammurodil.controller.command.impl;

import com.epammurodil.constants.EntityConstants;
import static com.epammurodil.constants.QueryConstants.*;
import com.epammurodil.constants.ControllerConstants;
import com.epammurodil.controller.command.Command;
import com.epammurodil.controller.navigation.Router;
import com.epammurodil.model.entity.Account;
import com.epammurodil.model.entity.Medicine;
import com.epammurodil.service.impl.MedicineServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import javax.management.Query;
import java.rmi.UnexpectedException;
import java.sql.SQLException;
import java.util.ArrayList;
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute(MEDICINES, medicines);
        return new Router(page, Router.PageChangeType.FORWARD);
    }
}
