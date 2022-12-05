package com.epammurodil.controller.command.impl;

import com.epammurodil.constants.EntityConstants;
import com.epammurodil.constants.EntityFields;
import com.epammurodil.constants.QueryConstants;
import com.epammurodil.constants.ControllerConstants;
import com.epammurodil.controller.command.Command;
import com.epammurodil.controller.navigation.Router;
import com.epammurodil.model.entity.Account;
import com.epammurodil.model.entity.Order;
import com.epammurodil.service.impl.OrdersServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.rmi.UnexpectedException;
import java.util.List;

public class OrdersCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws UnexpectedException {
        String method = request.getMethod();
        if (method.equals(QueryConstants.POST_REQUEST)) {
            String order_id = request.getParameter(QueryConstants.ORDER_ID);
            String status  = request.getParameter(QueryConstants.STATUS);
            OrdersServiceImpl.getInstance().updateStatus(order_id, status);
        }
        List<Order> orders;
        Account account = (Account) request.getAttribute(QueryConstants.SESSION_USER);
        if (account == null) {
            return new Router(ControllerConstants.SIGN_IN_JSP, Router.PageChangeType.FORWARD);
        }

        String role = account.getRole();
        if (role.equals(EntityConstants.CUSTOMER_ROLE)) {
            orders = OrdersServiceImpl.getInstance().getUserOrders(account.getId());
        } else {
            orders = OrdersServiceImpl.getInstance().getAllOrders();
        }
        request.setAttribute(EntityFields.ORDERS, orders);
        return new Router(ControllerConstants.ORDERS_JSP, Router.PageChangeType.FORWARD);
    }
}
