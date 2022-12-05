package com.epammurodil.controller.filter;

import com.epammurodil.constants.ControllerConstants;
import com.epammurodil.constants.EntityConstants;
import com.epammurodil.constants.QueryConstants;
import com.epammurodil.model.entity.Account;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "AdminsOnly", urlPatterns = {"/accounts"})
public class AdminsOnly implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute(QueryConstants.SESSION_USER);
        if (account == null) {
            httpResponse.sendRedirect(ControllerConstants.LOG_IN_ROUTER);
        } else if (account.getRole().equals(EntityConstants.ADMIN_ROLE) == false) {
            httpResponse.sendRedirect(ControllerConstants.MAIN_PAGE_ROUTER);
        } else {
            chain.doFilter(request, response);
        }
    }
}
