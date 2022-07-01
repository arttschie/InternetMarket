<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.webLocaleName}"/>
<fmt:setBundle basename="language"/>
<jsp:useBean id="pageConstants" class="com.epam.internetMarket.util.constants.PageConstants"/>
<jsp:useBean id="productDao" class="com.epam.internetMarket.dao.impl.ProductDaoImpl"/>
<jsp:useBean id="orderDao" class="com.epam.internetMarket.dao.impl.OrderDaoImpl"/>
<c:if test="${sessionScope.loggedUser == null}">
    <c:redirect url="${pageConstants.loginPage}"/>
</c:if>

<!DOCTYPE html>
<html>
<head>
    <title>${sessionScope.loggedUser.username}</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css'>
    <link rel="stylesheet" href="css/profile.css">
    <script src='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js'></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
</head>
<body>
<jsp:include page="${pageConstants.headerPage}"/>
<div class="container">
    <div class="main-body">
        <div class="row gutters-sm">
            <div class="col-md-12">
                <div class="card mb-3">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="profile.fullName"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${sessionScope.loggedUser.firstName} ${sessionScope.loggedUser.lastName}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="profile.username"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${sessionScope.loggedUser.username}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="profile.birthday"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${sessionScope.loggedUser.birthday}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="profile.phoneNumber"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${sessionScope.loggedUser.phoneNumber}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="profile.address"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${sessionScope.loggedUser.address}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-12">
                                <a class="btn btn-info" href="editProfile.jsp"><fmt:message key="profile.edit"/></a>
                                <a class="btn btn-info" href="editPassword.jsp"><fmt:message key="profile.editPassword"/></a>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <c:if test="${requestScope.successOrder == 'yes'}">
                                    <p><a class="text-success"><fmt:message key="profile.orderCompleted"/></a></p>
                                </c:if>
                                <c:if test="${requestScope.successOrder == 'no'}">
                                    <p><a class="text-danger"><fmt:message key="profile.orderError"/></a></p>
                                </c:if>
                                <c:if test="${requestScope.successProfileEdit == 'yes'}">
                                    <p><a class="text-success"><fmt:message key="profile.userUpdated"/></a></p>
                                </c:if>
                                <c:if test="${requestScope.successProfileEdit == 'no'}">
                                    <p><a class="text-danger"><fmt:message key="profile.userUpdateError"/></a></p>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="${pageConstants.cartPage}"/>

<div class="container">
    <div class="col-md-12">
        <div class="offer-dedicated-body-left">
            <div class="tab-content" id="pills-tabContent">
                <div class="tab-pane fade active show" id="pills-reviews" role="tabpanel"
                     aria-labelledby="pills-reviews-tab">
                    <c:if test="${sessionScope.userOrders.size() != 0 or sessionScope.userOrders == null}">
                        <div class="bg-white rounded shadow-sm p-4 mb-4 restaurant-detailed-ratings-and-reviews">
                            <h5 class="mb-1"><fmt:message key="profile.allOrders"/></h5>
                            <table class="table">
                                <thead>
                                <tr>
                                    <th scope="col"><fmt:message key="profile.number"/></th>
                                    <th scope="col"><fmt:message key="profile.date"/></th>
                                    <th scope="col"><fmt:message key="profile.totalCost"/></th>
                                    <th scope="col"><fmt:message key="profile.status"/></th>
                                </tr>
                                </thead>
                                <tbody>

                                <c:forEach var="userOrder" items="${sessionScope.userOrders}">
                                    <form action="GetOrderDetailPage" method="POST">
                                        <tr>
                                            <th scope="row"><button type="submit" name="orderId" value="${userOrder.id}" class="btn btn-link">${userOrder.id}</button></th>
                                            <td>${userOrder.dateStart}</td>
                                            <td>₸${userOrder.totalCost}</td>
                                            <td>${orderDao.getStatusName(userOrder.statusId, sessionScope.webLocaleId)}</td>
                                        </tr>
                                    </form>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
