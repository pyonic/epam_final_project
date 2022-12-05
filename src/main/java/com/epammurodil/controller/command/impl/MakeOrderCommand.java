package com.epammurodil.controller.command.impl;

import com.epammurodil.constants.QueryConstants;
import com.epammurodil.constants.ControllerConstants;
import com.epammurodil.controller.command.Command;
import com.epammurodil.controller.navigation.Router;
import com.epammurodil.model.entity.Account;
import com.epammurodil.service.impl.OrdersServiceImpl;
import com.epammurodil.service.impl.ReceiptServiceImpl;
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
                String receipt_id = (String) session.getAttribute(QueryConstants.RECEIPT_ID);
                ReceiptServiceImpl.getInstance().deleteOne(receipt_id);
            }
        }

        String page = ControllerConstants.THANKS_PAGE;

        try {
            OrdersServiceImpl.getInstance().insert(Integer.parseInt(medicine_id), account_id, BigDecimal.valueOf(Long.parseLong(dosage)), BigDecimal.valueOf(Long.parseLong(amount)), delivery_address);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return new Router(page, Router.PageChangeType.FORWARD);
    }
}
