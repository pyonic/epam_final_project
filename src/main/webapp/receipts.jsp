<%@ page import="com.epammurodil.constants.QueryConstants" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 01.12.2022
  Time: 9:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="components/header.jsp" />
<jsp:include page="components/navigation.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="title">Request for receipt</div>
<div class="container">
  <div class="mb-6" style="max-width: 50%; margin: 0 auto;">
    <form method="POST">
      <div class="form-group">
        <label>Description letter</label>
        <textarea name="description" class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
        <small id="emailHelp" class="form-text text-muted">Describe your sickness.</small>
      </div>
      <button type="submit" class="btn btn-primary">Submit</button>
    </form>
    <c:forEach items="${receipts}" var="receipt">
      <div class="list_item">
        <div class="m_info">
          <div class="m_description">${receipt.getDescription()}</div>
        </div>
        <% if (session.getAttribute(QueryConstants.SESSION_USER_ROLE).equals("DOCTOR")) { %>
          <div class="status" style="color: #ffb100; font-size: 16px">
            <a href="/?order_for=${receipt.getCustomer_id()}&current_receipt_id=${receipt.getId()}&description=${receipt.getDescription()}">Order for customer</a>
          </div>
        <% } else { %>
        <div class="status" style="color: #ffb100; font-size: 16px">
          <i class="fas fa-hourglass"></i>
          <i class="fa fa-check" aria-hidden="true"></i>
        </div>
        <% } %>
      </div>
    </c:forEach>
  </div>
</div>
<jsp:include page="components/footer.jsp" />