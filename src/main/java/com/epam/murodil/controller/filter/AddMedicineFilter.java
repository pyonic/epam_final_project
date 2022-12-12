package com.epam.murodil.controller.filter;

import com.epam.murodil.constants.EntityConstants;
import com.epam.murodil.constants.ControllerConstants;
import com.epam.murodil.constants.QueryConstants;
import com.epam.murodil.model.entity.Account;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "AddMedicineFilter", dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST}, urlPatterns = {"/add_medicine"})
public class AddMedicineFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute(QueryConstants.SESSION_USER);
        if (account == null) {
            httpResponse.sendRedirect(ControllerConstants.LOG_IN_ROUTER);
        } else {
            String role = account.getRole();
            if ((role.equals(EntityConstants.PHARMACIST_ROLE) && role.equals(EntityConstants.ADMIN_ROLE) == false))  {
                httpResponse.sendRedirect(ControllerConstants.MAIN_PAGE_ROUTER);
            }
        }

        chain.doFilter(request, response);
    }
}
