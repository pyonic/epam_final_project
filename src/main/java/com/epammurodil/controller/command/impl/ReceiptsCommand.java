package com.epammurodil.controller.command.impl;

import com.epammurodil.constants.EntityConstants;
import com.epammurodil.constants.QueryConstants;
import com.epammurodil.constants.ControllerConstants;
import com.epammurodil.controller.command.Command;
import com.epammurodil.controller.navigation.Router;
import com.epammurodil.model.dao.impl.ReceiptsDao;
import com.epammurodil.model.entity.Account;
import com.epammurodil.model.entity.Receipt;
import com.epammurodil.service.impl.ReceiptServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.List;

public class ReceiptsCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws UnexpectedException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute(QueryConstants.SESSION_USER);

        if (account == null) {
            return new Router(ControllerConstants.SIGN_IN_JSP, Router.PageChangeType.FORWARD);
        }

        int userId = account.getId();
        List<Receipt> receipts;
        if (account.getRole().equals(EntityConstants.DOCTOR_ROLE)) {
            receipts = ReceiptsDao.getInstance().getList();
        } else {
            receipts = ReceiptsDao.getInstance().getForUser(userId);
        }
        request.setAttribute(QueryConstants.RECEIPTS, receipts);

        String method = request.getMethod();
        if (!method.equals(QueryConstants.GET_REQUEST)) {
            String description = request.getParameter(QueryConstants.DESCRIPTION);
            if (description.isEmpty()) {
                return new Router(ControllerConstants.RECEIPTS_JSP, Router.PageChangeType.FORWARD);
            }

            ReceiptServiceImpl.getInstance().insert(userId, description);

            List<Receipt> receipts_updated = ReceiptsDao.getInstance().getForUser(userId);
            request.setAttribute(QueryConstants.RECEIPTS, receipts_updated);

        }
        return new Router(ControllerConstants.RECEIPTS_JSP, Router.PageChangeType.FORWARD);
    }
}
