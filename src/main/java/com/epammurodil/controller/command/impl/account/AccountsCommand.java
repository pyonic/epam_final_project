package com.epammurodil.controller.command.impl.account;

import static com.epammurodil.constants.EntityFields.*;

import com.epammurodil.constants.ControllerConstants;
import com.epammurodil.constants.QueryConstants;
import com.epammurodil.controller.command.Command;
import com.epammurodil.controller.navigation.Router;
import com.epammurodil.model.entity.Account;
import com.epammurodil.service.impl.AccountServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.rmi.UnexpectedException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class AccountsCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws UnexpectedException {
        String method = request.getMethod();
        if (method.equals(QueryConstants.POST_REQUEST)) {
            int account_id = Integer.parseInt(request.getParameter(ACCOUNT_ID));
            String role = request.getParameter(ROLE);
            if (Arrays.asList(Account.ACCOUNT_ROLES).contains(role)) {
                AccountServiceImpl.getInstance().updateRole(account_id, role);
            }
        }
        Map<String, List<Account>> usersMap = AccountServiceImpl.getInstance().getMapUsers();
        request.setAttribute(CUSTOMERS, usersMap.get(CUSTOMERS));
        request.setAttribute(PHARMACISTS, usersMap.get(PHARMACISTS));
        request.setAttribute(DOCTORS, usersMap.get(DOCTORS));
        request.setAttribute(ADMINS, usersMap.get(ADMINS));
        return new Router(ControllerConstants.USERS_JSP, Router.PageChangeType.FORWARD);
    }
}
