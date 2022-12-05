<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 09.11.2022
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.epammurodil.constants.ControllerConstants" %>
<%@ page import="com.epammurodil.controller.command.QueryCommands" %>
<jsp:include page="components/header.jsp" />
<jsp:include page="components/navigation.jsp" />
<div class="container">
    <div class="login_box">
        <div class="title">Add Medicine</div>
        <form action="/pharmacy?query=${QueryCommands.ADD_MEDICINE}" method="POST">
            <!-- Email input -->
            <div class="form-outline mb-4">
                <input type="text" id="form2Example1" class="form-control" name="title" required/>
                <label class="form-label" for="form2Example1">Name*</label>
            </div>

            <div class="form-outline mb-4">
                <textarea class="form-control" id="exampleFormControlTextarea1"  name="description" rows="3" required></textarea>
                <label class="form-label" for="form2Example1">Description*</label>
            </div>

            <div class="form-outline mb-4">
                <input type="text" id="form2Example1" class="form-control" name="price" required />
                <label class="form-label" for="form2Example1">Price*</label>
            </div>

            <div class="form-outline mb-4" style="text-align: center;">
                <input class="form-check-input" type="checkbox" name="need_receipt" id="defaultCheck1">
                <label class="form-label" for="form2Example1">Receipt from doctor is needed</label>
            </div>

            <button type="submit" class="btn btn-primary btn-block mb-4 text-center" style="margin: 0 auto;">Add</button>

        </form>
    </div>
</div>
<jsp:include page="components/footer.jsp" />