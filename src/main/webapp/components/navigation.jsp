<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 10.11.2022
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.epammurodil.controller.command.QueryCommands" %>
<div class="container-flex">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="/">Pharmacy+</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="/">Main</a>
                </li>
                <% if (session.getAttribute("SESSION_USER_ROLE").equals("PHARMACIST")) { %>
                    <li class="nav-item active">
                        <a class="nav-link" href="/add_medicine">Add Medicine</a>
                    </li>
                <% } %>
                <% if (session.getAttribute("SESSION_USER_ROLE").equals("ADMIN")) { %>
                    <li class="nav-item active">
                        <a class="nav-link" href="/accounts">Accounts</a>
                    </li>
                <% } %>
                <%-- Only register --%>
                <% if (session.getAttribute("SESSION_USER") == null) { %>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Login/Register
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="/login">Login</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="/register">Register</a>
                        </div>
                    </li>
                <% } else { %>
                    <li class="nav-item">
                        <a class="nav-link" href="/receipts">Receipts</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/orders">Orders</a>
                    </li>
                    <li class="nav-item"><a class="nav-link" href="/logout">Log-out</a></li>
                <% } %>

            </ul>
        </div>
    </nav>
</div>
