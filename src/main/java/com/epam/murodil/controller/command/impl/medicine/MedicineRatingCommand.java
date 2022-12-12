package com.epam.murodil.controller.command.impl.medicine;

import com.epam.murodil.constants.QueryConstants;
import com.epam.murodil.controller.command.Command;
import com.epam.murodil.controller.navigation.Router;
import com.epam.murodil.exceptions.DaoException;
import com.epam.murodil.exceptions.ServiceException;
import com.epam.murodil.model.entity.Account;
import com.epam.murodil.constants.ControllerConstants;
import com.epam.murodil.service.impl.RatingServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.rmi.UnexpectedException;

public class MedicineRatingCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws UnexpectedException, DaoException, ServiceException {
        Account account = (Account) request.getAttribute(QueryConstants.SESSION_USER);
        if (account != null) {
            int medicine_id = Integer.parseInt(request.getParameter(QueryConstants.MEDICINE_ID));
            int rating = Integer.parseInt(request.getParameter(QueryConstants.RATING));
            String body = request.getParameter(QueryConstants.BODY);
            RatingServiceImpl.getInstance().insertRating(medicine_id, rating, body, account.getId());
            return new Router(request.getHeader(QueryConstants.REQUEST_REFERER), Router.PageChangeType.REDIRECT);
        } else {
            String page = ControllerConstants.SIGN_IN_JSP;
            return new Router(page, Router.PageChangeType.REDIRECT);
        }
    }
}
