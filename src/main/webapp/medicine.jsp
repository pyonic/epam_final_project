<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 12.11.2022
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 09.11.2022
  Time: 22:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.epammurodil.controller.command.QueryCommands" %>
<%@ page import="com.epammurodil.constants.QueryConstants" %>
<jsp:include page="components/header.jsp" />
<jsp:include page="components/navigation.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">
    <div class="title to_left mb0 lb">${medicine.getTitle()}<span class="exclamation">
        <c:if test="${medicine.isNeedReceipt() == true}" >
            (doctor's receipt is required)
        </c:if>
        </span>
    </div>
        <div class="key_value_box">
            <div class="kv_item">
                <div class="key">Description: </div>
                <div class="value">${medicine.getDescription()}</div>
            </div>
            <div class="kv_item">
                <div class="key">Rating: </div>
                <div class="value">&#11088; ${rate_data.get("rating")} <span class="exclamatio smaller">(${rate_data.get("count")} rates)</span></div>
            </div>
            <div class="kv_item">
                <div class="key">Sold: </div>
                <div class="value">4500</div>
            </div>
            <div class="kv_item">
                <div class="key">Price: </div>
                <div class="value medicine_price">${medicine.getPrice()}$</div>
            </div>
        </div>
        <div class="action_box">
            <% if (session.getAttribute(QueryConstants.SESSION_USER_ROLE).equals("ADMIN") || session.getAttribute(QueryConstants.SESSION_USER_ROLE).equals("PHARMACIST")) { %>
            <form method="POST" action="/pharmacy?query=DELETE_MEDICINE">
                <input type="hidden" value="${medicine.getId()}" name="medicine_id">
                <button type="submit" class="btn btn-danger reviewbtn">Delete</button>
            </form>
            <% } %>
            <c:choose>
                <c:when test="${sessionScope.SESSION_USER != null}">
                    <button type="button" class="btn btn-primary reviewbtn" data-toggle="modal" data-target="#new_order" data-whatever="@getbootstrap">Make order</button>
                </c:when>
                <c:otherwise>
                    <a href="/login"><button type="button" class="btn btn-primary reviewbtn">Login to order</button></a>
                </c:otherwise>
            </c:choose>

            <c:if test="${CAN_REVIEW}">
                <button type="button" class="btn btn-primary reviewbtn" data-toggle="modal" data-target="#exampleModal" data-whatever="@getbootstrap">Send review</button>
            </c:if>
        </div>
        <div class="ratings_block">
            <div class="ratings_title">Comments</div>
                <c:forEach items="${ratings}" var="rating">
                    <div class="ratings_box">
                        <div class="kv_item">
                            <div class=" key r_author">${rating.getAuthorFirstName()} ${rating.getAuthorLastName()}</div>
                            <div class=" value r_body">${rating.getBody()}<br><b>&#x203A; Rated medicine for  ${rating.getRating()}</b> </div>
                        </div>
                    </div>
                </c:forEach>
        </div>
    </div>
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <form action="/pharmacy?query=RATE_MEDICINE" method="POST">
            <input type="hidden" value="${medicine.getId()}" name="medicine_id">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Send review 777</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                            <div class="form-group">
                                <label for="recipient-name" class="col-form-label">Your rate from 0 to 5:</label>
                                <input type="number" class="form-control" name="rating" id="recipient-name" max="5">
                            </div>
                            <div class="form-group">
                                <label for="message-text" class="col-form-label">Additional comments:</label>
                                <textarea class="form-control" name="body" id="message-text"></textarea>
                            </div>
                    </div>
                    <div class="modal-footer">
                        <input type="submit" class="btn btn-primary" value="Send" />
                    </div>

                </div>
            </div>
        </form>
    </div>
    <div class="modal fade" id="new_order" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <form action="/pharmacy?query=MAKE_ORDER" method="POST">
            <input type="hidden" value="${medicine.getId()}" name="medicine_id">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Make order</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="recipient-name" class="col-form-label">Dosage per day*</label>
                            <input type="number" class="form-control" name="dosage">
                        </div>
                        <div class="form-group">
                            <label for="recipient-name" class="col-form-label">Amount</label>
                            <input type="number" class="form-control" name="amount">
                        </div>
                        <div class="form-group">
                            <label for="message-text" class="col-form-label">Delivery address*</label>
                            <input class="form-control" name="delivery_address"></input>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input type="submit" class="btn btn-primary" value="Send" />
                    </div>

                </div>
            </div>
        </form>
    </div>
<% if (session.getAttribute("description") != null) { %>
    <div class="modal" tabindex="-1" role="dialog" id="desc">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Attention you are ordering for customer</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <p><%= session.getAttribute("description") %></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary modal_cls" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
<% } %>
<jsp:include page="components/footer.jsp" />
<script type="text/javascript">
    $(window).on('load', function() {
        $('#desc').modal('show');
    });
    $('.modal_cls').on('click', function() {
        $('#desc').modal('hide');
    });
</script>