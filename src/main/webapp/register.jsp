<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 09.11.2022
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="components/header.jsp" />
<jsp:include page="components/navigation.jsp"></jsp:include>

<div class="container">
    <div class="login_box">
        <div class="title">Register</div>
        <form action="/register" method="POST">
            <!-- Email input -->
            <div class="field_group">
                <div class="form-outline mb-4">
                    <input type="text" name="first_name" value="${first_name}" class="form-control" required/>
                    <label class="form-label">Your first name*</label>
                </div>

                <div class="form-outline mb-4">
                    <input type="text" name="last_name" value="${last_name}" class="form-control" required />
                    <label class="form-label">Your last name*</label>
                </div>
            </div>

            <div class="form-outline mb-4">
                <input type="email" name="email" value="${email}" class="form-control" required />
                <label class="form-label">Your email*</label>
            </div>

            <div class="form-outline mb-4">
                <input type="tel" name="phone" VALUE="${phone}" class="form-control"  />
                <label class="form-label">Your phone number</label>
            </div>
            <div class="field_group">
                <div class="form-outline mb-4">
                    <input type="password" name="password" value="${password}" class="form-control" required />
                    <label class="form-label">Password*</label>
                </div>

                <div class="form-outline mb-4">
                    <input type="password" name="password_confirm" value="${password_confirm}" class="form-control" required />
                    <label class="form-label">Please, repeat password*</label>
                </div>
            </div>
            <div style="color: rgba(243,54,54,0.67); text-align: center; margin-bottom: 5px;">${error}</div>

            <button type="submit" class="btn btn-primary btn-block mb-4" value="Register" style="margin: 0 auto">Sign Up</button>

            <div class="text-center">
                <p>Already have an account? <a href="/login">Log-in</a></p>
            </div>
        </form>
    </div>
</div>
<jsp:include page="components/footer.jsp" />