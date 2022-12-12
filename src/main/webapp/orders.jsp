<%@ page import="com.epam.murodil.constants.QueryConstants" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 30.11.2022
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="components/header.jsp" />
<jsp:include page="components/navigation.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">
    <div class="title"> Orders list </div>
    <div class="orders_list">
        <c:forEach items="${orders}" var="order">
            <div class="order_bdy">
                <div class="order_item">
                    <div class="order_info">
                        <div class="order_medicine">${order.getMedicine().getTitle()}</div>
                        <div class="order_user"><div class="info_log"><i class="fa fa-user"></i></div> ${order.getCustomer().getFullName()}</div>
                        <div class="devliery_addres"><div class="info_log"><i class="fa fa-house"></i></div> ${order.getDelivery_address()}</div>
                        <div class="amount_order"><div class="info_log"><i class="fa fa-dollar-sign"></i></div> ${order.getPrice()}</div>
                        <div class="amount_order"><div class="info_log"><i class="fa fa-hourglass-half" aria-hidden="true"></i></div> ${order.getStatus()}</div>
                    </div>
                    <div class="price">${order.getPrice()}$</div>
                </div>
                <c:if test="${sessionScope.SESSION_USER_ROLE == 'PHARMACIST' || sessionScope.SESSION_USER_ROLE == 'ADMIN'}">
                    <div class="order_actions">
                        <form method="POST" style="display: flex;">
                            <input type="hidden" name="order_id" value="${order.getId()}">
                            <select class="form-select ssl" name="status">
                                <option value="${order.getStatus()}" selected>Update order status (${order.getStatus()})</option>
                                <option value="NEW">New</option>
                                <option value="DELIVERING">Delivering</option>
                                <option value="DELIVERED">Delivered</option>
                                <option value="DECLINED">Declined</option>
                            </select>
                            <button class="btn btn-primary" type="submit">Update</button>
                        </form>
                    </div>
                </c:if>
            </div>
        </c:forEach>
    </div>
</div>
<jsp:include page="components/footer.jsp" />