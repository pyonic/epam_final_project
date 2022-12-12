package com.epam.murodil.controller.command.impl.order;

import com.epam.murodil.constants.QueryConstants;
import com.epam.murodil.controller.command.Command;
import com.epam.murodil.controller.navigation.Router;
import com.epam.murodil.exceptions.DaoException;
import com.epam.murodil.model.entity.Account;
import com.epam.murodil.constants.ControllerConstants;
import com.epam.murodil.service.impl.OrdersServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.rmi.UnexpectedException;
import java.sql.SQLException;

public class MakeOrderCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws UnexpectedException {
        HttpSession session = request.getSession();

        if (session.getAttribute(QueryConstants.SESSION_USER) == null) {
            return new Router(ControllerConstants.SIGN_IN_JSP, Router.PageChangeType.FORWARD);
        }

        String medicine_id = request.getParameter(QueryConstants.MEDICINE_ID);
        String delivery_address = request.getParameter(QueryConstants.DELIVERY_ADDRESS);
        String dosage = request.getParameter(QueryConstants.MEDICINE_DOSAGE);
        String amount = request.getParameter(QueryConstants.AMOUNT);
        if (amount.isEmpty()) amount = dosage;

        if (medicine_id == null) return new Router(ControllerConstants.MAIN_PAGE_ROUTER, Router.PageChangeType.REDIRECT);
        Account account = (Account) session.getAttribute(QueryConstants.SESSION_USER);
        Integer account_id = account.getId();

        if (session.getAttribute(QueryConstants.ORDER_FOR) != null) {
            account_id = Integer.parseInt((String) session.getAttribute(QueryConstants.ORDER_FOR));
            if (session.getAttribute(QueryConstants.RECEIPT_ID) != null) {
                session.setAttribute(QueryConstants.ORDER_FOR, null);
            }
        }

        String page = ControllerConstants.THANKS_PAGE;

        try {
            OrdersServiceImpl.getInstance().insert(Integer.parseInt(medicine_id), account_id, BigDecimal.valueOf(Long.parseLong(dosage)), BigDecimal.valueOf(Long.parseLong(amount)), delivery_address);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }

        return new Router(page, Router.PageChangeType.FORWARD);
    }
}
