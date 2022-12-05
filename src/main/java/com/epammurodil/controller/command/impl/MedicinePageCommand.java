package com.epammurodil.controller.command.impl;

import com.epammurodil.constants.QueryConstants;
import com.epammurodil.constants.ControllerConstants;
import com.epammurodil.controller.command.Command;
import com.epammurodil.controller.command.QueryCommands;
import com.epammurodil.controller.navigation.Router;
import com.epammurodil.model.dao.impl.RatingsDao;
import com.epammurodil.model.entity.Account;
import com.epammurodil.model.entity.Medicine;
import com.epammurodil.model.entity.Rating;
import com.epammurodil.service.impl.MedicineServiceImpl;
import com.epammurodil.service.impl.OrdersServiceImpl;
import com.epammurodil.service.impl.RatingServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.rmi.UnexpectedException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class MedicinePageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws UnexpectedException {
        Account user = (Account) request.getAttribute(QueryConstants.SESSION_USER);
        String medicine_slug = request.getParameter("q");
        if (medicine_slug == null) return new Router(ControllerConstants.DEFAULT_PAGE, Router.PageChangeType.FORWARD);
        try {
            Medicine medicine = MedicineServiceImpl.getInstance().getBySlag(medicine_slug);
            if (medicine == null) {
                return new Router(ControllerConstants.APPLICATION_PREFIX + QueryCommands.NOT_FOUND, Router.PageChangeType.FORWARD);
            }
            List<Rating> ratings = RatingServiceImpl.getInstance().getRatingsForMedicine(medicine.getId());
            Map<String, Object> rate_data = RatingServiceImpl.getInstance().getRatingData(medicine.getId());

            if (user != null) {
                Boolean userRated = RatingServiceImpl.getInstance().userHasRated(medicine.getId(), user.getId());
                Boolean userMadeOrder = OrdersServiceImpl.getInstance().userOrdered(medicine.getId(), user.getId());
                if (userMadeOrder && !userRated) {
                    request.setAttribute(QueryConstants.CAN_REVIEW, true);
                }
            }

            request.setAttribute(QueryConstants.RATE_DATA, rate_data);
            request.setAttribute(QueryConstants.RATINGS, ratings);
            request.setAttribute(QueryConstants.MEDICINE, medicine);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new Router(ControllerConstants.MEDICINE_PAGE_JSP, Router.PageChangeType.FORWARD);
    }
}
