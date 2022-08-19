<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.webLocaleName}"/>
<fmt:setBundle basename="language"/>
<jsp:useBean id="pageConstants" class="com.epam.internetMarket.util.constants.PageConstants"/>
<c:if test="${sessionScope.loggedUser == null}">
    <c:redirect url="${pageConstants.loginPage}"/>
</c:if>
<html>
<head>
    <title><fmt:message key="editProfile.title"/> ${sessionScope.loggedUser.username}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<jsp:include page="${pageConstants.headerPage}"/>
<div class="container">
    <div class="main-body">
        <div class="row gutters-sm">
            <div class="col-md-12">
                <div class="card mb-3">
                    <div class="card-body">
                        <form action="EditProfile" method="POST">
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0"><fmt:message key="editProfile.username"/></h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    ${sessionScope.loggedUser.username}
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0"><fmt:message key="editProfile.email"/></h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    ${sessionScope.loggedUser.email}
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0"><fmt:message key="editProfile.firstName"/></h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="text" class="form-control" placeholder="${sessionScope.loggedUser.firstName}" name="firstName" required>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0"><fmt:message key="editProfile.lastName"/></h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="text" class="form-control" placeholder="${sessionScope.loggedUser.lastName}" name="lastName" required>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0"><fmt:message key="editProfile.birthday"/></h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="date" class="form-control" placeholder="${sessionScope.loggedUser.birthday}" name="birthday" required>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0"><fmt:message key="editProfile.phoneNumber"/></h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="text" class="form-control" placeholder="${sessionScope.loggedUser.phoneNumber}" name="phoneNumber" required>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0"><fmt:message key="editProfile.address"/></h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="text" class="form-control" placeholder="${sessionScope.loggedUser.address}" name="address" required>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-12">
                                    <button type="submit" class="btn btn-primary btn-block">
                                        <fmt:message key="editProfile.button"/>
                                    </button>
                                </div>
                            </div>
                            <input type="hidden" name="statusId" value="${sessionScope.loggedUser.statusId}">
                            <input type="hidden" name="pageName" value="editProfile.jsp">
                            <input type="hidden" name="userId" value="${sessionScope.loggedUser.id}">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>