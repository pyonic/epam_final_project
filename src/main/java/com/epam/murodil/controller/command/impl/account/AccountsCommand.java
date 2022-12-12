package com.epam.murodil.controller.command.impl.account;

import com.epam.murodil.constants.ControllerConstants;
import com.epam.murodil.constants.EntityFields;
import com.epam.murodil.constants.QueryConstants;
import com.epam.murodil.controller.command.Command;
import com.epam.murodil.controller.navigation.Router;
import com.epam.murodil.exceptions.DaoException;
import com.epam.murodil.model.entity.Account;
import com.epam.murodil.service.impl.AccountServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.rmi.UnexpectedException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class AccountsCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws UnexpectedException, DaoException {
        String method = request.getMethod();
        if (method.equals(QueryConstants.POST_REQUEST)) {
            int account_id = Integer.parseInt(request.getParameter(EntityFields.ACCOUNT_ID));
            String role = request.getParameter(EntityFields.ROLE);
            if (Arrays.asList(Account.ACCOUNT_ROLES).contains(role)) {
                AccountServiceImpl.getInstance().updateRole(account_id, role);
            }
        }
        Map<String, List<Account>> usersMap = AccountServiceImpl.getInstance().getMapUsers();
        request.setAttribute(EntityFields.CUSTOMERS, usersMap.get(EntityFields.CUSTOMERS));
        request.setAttribute(EntityFields.PHARMACISTS, usersMap.get(EntityFields.PHARMACISTS));
        request.setAttribute(EntityFields.DOCTORS, usersMap.get(EntityFields.DOCTORS));
        request.setAttribute(EntityFields.ADMINS, usersMap.get(EntityFields.ADMINS));
        return new Router(ControllerConstants.USERS_JSP, Router.PageChangeType.FORWARD);
    }
}
