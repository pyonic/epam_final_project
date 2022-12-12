package com.epam.murodil.controller;

import com.epam.murodil.constants.ProjectConstants;
import com.epam.murodil.exceptions.DaoException;
import com.epam.murodil.model.entity.Medicine;
import com.epam.murodil.service.impl.MedicineServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "RestController", value = "/api/*")
public class RestController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestPath = request.getRequestURI().split("/")[2];
        response.addHeader(ProjectConstants.CORS, ProjectConstants.CORS_HOST);
        response.setContentType(ProjectConstants.JSON_CONTENT);
        if (requestPath.equals("medicines")) {
            String search = request.getParameter("search");
            if (search == null || search.length() < 4) {
                response.setStatus(ProjectConstants.BAD_REQUEST_STATUS);
                response.getWriter().write(ProjectConstants.INVALID_SEARCH);
            } else {
                try {
                    List<Medicine> medicines = MedicineServiceImpl.getInstance().searchMedicine(search);
                    StringBuilder sb = new StringBuilder();
                    medicines.forEach(m -> {
                        String medicine = String.format(ProjectConstants.MEDICINES_JSON_ARRAY_TEMPLATE, m.getTitle(), String.valueOf(m.getPrice()), m.getSlug());
                        sb.append(medicine + ",");
                    });
                    String jsonResponse = ProjectConstants.EMPTY;

                    if (sb.length() > 1) jsonResponse = sb.substring(0, sb.length() - 1);
                    else response.setStatus(ProjectConstants.NOT_FOUND_STATUS);

                    response.getWriter().write(String.format(ProjectConstants.MEDICINES_JSON_RESPONSE, jsonResponse));
                } catch (DaoException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public String jsonCNV(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(obj);
        return jsonString;
    }
}
