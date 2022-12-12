package com.epam.murodil.controller.command.impl.order;

import com.epam.murodil.controller.command.Command;
import com.epam.murodil.controller.navigation.Router;
import com.epam.murodil.constants.EntityConstants;
import com.epam.murodil.constants.EntityFields;
import com.epam.murodil.constants.QueryConstants;
import com.epam.murodil.constants.ControllerConstants;
import com.epam.murodil.exceptions.DaoException;
import com.epam.murodil.model.entity.Account;
import com.epam.murodil.model.entity.Order;
import com.epam.murodil.service.impl.OrdersServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.rmi.UnexpectedException;
import java.util.List;

public class OrdersCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws UnexpectedException, DaoException {
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
