<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 09.11.2022
  Time: 22:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.epammurodil.controller.command.QueryCommands" %>
<jsp:include page="components/header.jsp" />
<jsp:include page="components/navigation.jsp" />
<div class="container">
    <div class="login_box">
        <div class="title">Authorize</div>
        <form action="/login" method="post">
            <!-- Email input -->
            <div class="form-outline mb-4">
                <input type="email" name="email" id="form2Example1" class="form-control" />
                <label class="form-label" for="form2Example1">Email address</label>
            </div>

            <!-- Password input -->
            <div class="form-outline mb-4">
                <input type="password" name="password" id="form2Example2" class="form-control" />
                <label class="form-label" for="form2Example2">Password</label>
            </div>
            <div style="color: rgba(243,54,54,0.67); text-align: center; margin-bottom: 5px;">${error}</div>
            <!-- 2 column grid layout for inline styling -->

            <!-- Submit button -->
            <button type="submit" class="btn btn-primary btn-block mb-4"  style="margin: 0 auto;">Sign in</button>

            <!-- Register buttons -->
            <div class="text-center">
                <p>Not a member? <a href="/register">Register now</a></p>
            </div>
        </form>
    </div>
    </div>
<jsp:include page="components/footer.jsp" />