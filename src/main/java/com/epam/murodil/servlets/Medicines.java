//package com.epammurodil.servlets;
//
//import com.epammurodil.bean.Medicine;
//import com.epammurodil.constants.ProjectConstants;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//@WebServlet(urlPatterns = {"/medicines", "/medicines/json"})
//public class Medicines extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String path = req.getServletPath();
//        PrintWriter printWriter = resp.getWriter();
//        if (path.contains(ProjectConstants.JSON)) {
//            resp.setContentType(ProjectConstants.JSON_CONTENT);
//            // Send medicines json [ {} , {}]
//        } else {
//
//        }
//    }
//
//    public String jsonCNV(Object obj) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = mapper.writeValueAsString(obj);
//        return jsonString;
//    }
//}
