<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 01.12.2022
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="components/header.jsp" />
<jsp:include page="components/navigation.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
    .nav-item {
        width: 200px;
    }
    .nav-item > button {
        width: 200px !important;
    }
    .mt30 {
        margin-top: 30px !important;
    }
</style>
<div class="container mt30">
    <ul class="nav nav-tabs" id="myTab" role="tablist">
        <li class="nav-item" role="presentation">
            <button class="nav-link active" id="home-tab" data-bs-toggle="tab" data-bs-target="#home" type="button" role="tab" aria-controls="home" aria-selected="true">Customers</button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="profile-tab" data-bs-toggle="tab" data-bs-target="#profile" type="button" role="tab" aria-controls="profile" aria-selected="false">Pharmacists</button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="contact-tab" data-bs-toggle="tab" data-bs-target="#contact" type="button" role="tab" aria-controls="contact" aria-selected="false">Doctors</button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="admins-tab" data-bs-toggle="tab" data-bs-target="#admins" type="button" role="tab" aria-controls="admins" aria-selected="false">Administrators</button>
        </li>
    </ul>
    <style>
        .account_item {
            border: 1px solid  #dee2e6 #dee2e6 #fff;
            padding: 10px 0;
            font-weight: 500;
        }
        .account_item > form {
            display: flex;
            justify-content: space-between;
        }
    </style>
    <div class="tab-content" id="myTabContent">
        <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
            <div class="account_forms">
                <c:forEach items="${customers}" var="customer">
                    <div class="account_item">
                        <form method="post">
                            <div class="account_name" style="min-width: 200px">${customer.getFullName()}</div>
                            <input type="hidden" value="${customer.getId()}" name="account_id">
                            <select class="form-select" name="role" aria-label="Default select example">
                                <option selected>Select role for account</option>
                                <option value="CUSTOMER">Customer</option>
                                <option value="PHARMACIST">Pharmacist</option>
                                <option value="DOCTOR">Doctor</option>
                            </select>
                            <button class="btn btn-primary">Promote</button>
                        </form>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
            <c:forEach items="${pharmacists}" var="pharmacist">
                <div class="account_item">
                    <form method="post">
                        <div class="account_name" style="min-width: 200px">${pharmacist.getFullName()}</div>
                        <input type="hidden" value="${pharmacist.getId()}" name="account_id">
                        <select class="form-select" name="role" aria-label="Default select example">
                            <option selected>Select role for account</option>
                            <option value="CUSTOMER">Customer</option>
                            <option value="PHARMACIST">Pharmacist</option>
                            <option value="DOCTOR">Doctor</option>
                        </select>
                        <button class="btn btn-primary">Promote</button>
                    </form>
                </div>
            </c:forEach>
        </div>
        <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">
            <c:forEach items="${doctors}" var="doctor">
                <div class="account_item">
                    <form method="post">
                        <div class="account_name" style="min-width: 200px">${doctor.getFullName()}</div>
                        <input type="hidden" value="${doctor.getId()}" name="account_id">
                        <select class="form-select" name="role" aria-label="Default select example">
                            <option selected>Select role for account</option>
                            <option value="CUSTOMER">Customer</option>
                            <option value="PHARMACIST">Pharmacist</option>
                            <option value="DOCTOR">Doctor</option>
                        </select>
                        <button class="btn btn-primary">Promote</button>
                    </form>
                </div>
            </c:forEach>
        </div>
        <div class="tab-pane fade" id="admins" role="tabpanel" aria-labelledby="admins-tab">
            <c:forEach items="${admins}" var="admin">
                <div class="account_item">
                    <form method="post">
                        <div class="account_name" style="min-width: 200px">${admin.getFullName()}</div>
                        <input type="hidden" value="${admin.getId()}" name="account_id">
                        <button class="btn btn-primary">Promote</button>
                    </form>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<jsp:include page="components/footer.jsp" />