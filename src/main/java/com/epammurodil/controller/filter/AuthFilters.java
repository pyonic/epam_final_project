package com.epammurodil.controller.filter;

import com.epammurodil.constants.EntityConstants;
import com.epammurodil.constants.QueryConstants;
import com.epammurodil.model.entity.Account;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
