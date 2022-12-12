package com.epam.murodil.controller.command.impl.medicine;

import com.epam.murodil.constants.QueryConstants;
import com.epam.murodil.controller.command.Command;
import com.epam.murodil.controller.navigation.Router;
import com.epam.murodil.exceptions.DaoException;
import com.epam.murodil.exceptions.ServiceException;
import com.epam.murodil.model.entity.Account;
import com.epam.murodil.model.entity.Medicine;
import com.epam.murodil.model.entity.Rating;
import com.epam.murodil.constants.ControllerConstants;
import com.epam.murodil.controller.command.QueryCommands;
import com.epam.murodil.service.impl.MedicineServiceImpl;
import com.epam.murodil.service.impl.OrdersServiceImpl;
import com.epam.murodil.service.impl.RatingServiceImpl;
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
        } catch (DaoException e) {
            throw new RuntimeException(e);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        return new Router(ControllerConstants.MEDICINE_PAGE_JSP, Router.PageChangeType.FORWARD);
    }
}
