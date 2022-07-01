<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.webLocaleName}"/>
<fmt:setBundle basename="language"/>
<jsp:useBean id="pageConstants" class="com.epam.internetMarket.util.constants.PageConstants"/>
<jsp:useBean id="userDao" class="com.epam.internetMarket.dao.impl.UserDaoImpl"/>
<jsp:useBean id="orderDao" class="com.epam.internetMarket.dao.impl.OrderDaoImpl"/>
<c:if test="${sessionScope.loggedUser == null}">
    <c:redirect url="${pageConstants.loginPage}"/>
</c:if>
<c:if test="${sessionScope.loggedUser.isAdmin == false}">
    <c:redirect url="${pageConstants.profilePage}"/>
</c:if>
<html>
<head>
    <title><fmt:message key="editUsers.title"/></title>
</head>
<body>
<jsp:include page="${pageConstants.headerPage}"/>
<div style="padding: 25px">
    <h5 class="mb-1"><fmt:message key="editUsers.editPassword"/></h5>
    <table class="table">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="editUsers.username"/></th>
            <th scope="col"><fmt:message key="editUsers.password"/></th>
            <th scope="col"><fmt:message key="editUsers.retypePassword"/></th>
            <th scope="col"><fmt:message key="editUsers.edit"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${userDao.allUsers}">
            <tr>
                <form action="EditUsersPassword" method="POST">
                    <td>${user.username}</td>
                    <td><input type="password" name="password" required></td>
                    <td><input type="password" name="retypePassword" required></td>
                    <td>
                        <button type="submit" class="btn btn-warning" name="userId" value="${user.id}">
                            <fmt:message key="editUsers.edit"/>
                        </button>
                    </td>
                </form>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${requestScope.successPasswordEdit == 'yes'}">
        <p><a class="text-success"><fmt:message key="editUsers.passwordEdited"/></a></p>
    </c:if>
    <c:if test="${requestScope.successPasswordEdit == 'no'}">
        <p><a class="text-success"><fmt:message key="parameters.passwordNotMatch"/></a></p>
    </c:if>
</div>
<div style="padding: 25px">
    <h5 class="mb-1"><fmt:message key="editUsers.editProfile"/></h5>
    <table class="table">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="editUsers.username"/></th>
            <th scope="col"><fmt:message key="editUsers.firstName"/></th>
            <th scope="col"><fmt:message key="editUsers.lastName"/></th>
            <th scope="col"><fmt:message key="editUsers.birthday"/></th>
            <th scope="col"><fmt:message key="editUsers.phoneNumber"/></th>
            <th scope="col"><fmt:message key="editUsers.address"/></th>
            <th scope="col"><fmt:message key="editUsers.status"/></th>
            <th scope="col"><fmt:message key="editUsers.edit"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${userDao.allUsers}">
            <tr>
                <form action="EditProfile" method="POST">
                    <td>${user.username}</td>
                    <td>${user.firstName}<br><input type="text" name="firstName" required></td>
                    <td>${user.lastName}<br><input type="text" name="lastName" required></td>
                    <td>${user.birthday}<br><input type="date" name="birthday" required></td>
                    <td>${user.phoneNumber}<br><input type="text" name="phoneNumber" required></td>
                    <td>${user.address}<br><input type="text" name="address" required></td>
                    <td>
                        <select class="form-control" name="statusId" required>
                            <option value="1"><fmt:message key="editUsers.active"/></option>
                            <option value="2"><fmt:message key="editUsers.inactive"/></option>
                        </select>
                    </td>
                    <td>
                        <input type="hidden" name="pageName" value="editUsers.jsp">
                        <button type="submit" class="btn btn-warning" name="userId" value="${user.id}">
                            <fmt:message key="editUsers.edit"/>
                        </button>
                    </td>
                </form>
            <tr>
                <td scope="col"><fmt:message key="editUsers.orderId"/></td>
                <td scope="col"><fmt:message key="editUsers.orderDate"/></td>
                <td scope="col"><fmt:message key="editUsers.totalCost"/></td>
                <td scope="col"><fmt:message key="editUsers.status"/></td>
                <td scope="col"><fmt:message key="editUsers.editStatus"/></td>
            </tr>
            <c:forEach var="userOrder" items="${orderDao.getUserOrders(user.id)}">
                <form action="EditOrderStatus" method="POST">
                <tr>
                    <td>${userOrder.id}</td>
                    <td>${userOrder.dateStart}</td>
                    <td>${userOrder.totalCost}₸</td>
                    <td>${orderDao.getStatusName(userOrder.statusId, sessionScope.webLocaleId)}</td>
                    <td>
                        <select class="form-control" name="statusId" required>
                            <option value="2"><fmt:message key="editUsers.inactive"/></option>
                            <option value="3"><fmt:message key="editUsers.processed"/></option>
                            <option value="4"><fmt:message key="editUsers.onTheRoad"/></option>
                            <option value="5"><fmt:message key="editUsers.delivered"/></option>
                        </select>
                    </td>
                    <td>
                        <button type="submit" class="btn btn-warning" name="orderId" value="${userOrder.id}">
                            <fmt:message key="editUsers.edit"/>
                        </button>
                    </td>
                </tr>
                </form>
            </c:forEach>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${requestScope.successProfileEdit == 'yes'}">
        <p><a class="text-success"><fmt:message key="editUsers.profileEdited"/></a></p>
    </c:if>
    <c:if test="${requestScope.successProfileEdit == 'no'}">
        <p><a class="text-danger"><fmt:message key="editUsers.profileError"/></a></p>
    </c:if>
    <c:if test="${requestScope.successOrderUpdate == 'yes'}">
        <p><a class="text-success"><fmt:message key="editUsers.statusUpdated"/></a></p>
    </c:if>
    <c:if test="${requestScope.successOrderUpdate == 'no'}">
        <p><a class="text-danger"><fmt:message key="editUsers.statusUpdatedError"/></a></p>
    </c:if>
</div>
</body>
</html>