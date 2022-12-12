package com.epam.murodil.controller.filter;

import com.epam.murodil.constants.EntityConstants;
import com.epam.murodil.constants.QueryConstants;
import com.epam.murodil.model.entity.Account;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "AuthFilters",  dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST}, urlPatterns = {"/pharmacy"})
public class AuthFilters implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute(QueryConstants.SESSION_USER);
        if (account != null) {
            request.setAttribute(QueryConstants.SESSION_USER, account);
            request.setAttribute(QueryConstants.SESSION_USER_ROLE, account.getRole());
            session.setAttribute(QueryConstants.SESSION_USER, account);
            session.setAttribute(QueryConstants.SESSION_USER_ROLE, account.getRole());
        } else {
            session.setAttribute(QueryConstants.SESSION_USER_ROLE, EntityConstants.GUEST_ROLE);
        }
        chain.doFilter(request, response);
    }
}
