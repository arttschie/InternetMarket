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
    <title><fmt:message key="editPassword.title"/> - ${sessionScope.loggedUser.username}</title>
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
                        <form action="EditPassword" method="POST">
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0"><fmt:message key="editPassword.username"/></h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    ${sessionScope.loggedUser.username}
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0"><fmt:message key="editPassword.currentPassword"/></h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="password" class="form-control" name="currentPassword" required>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0"><fmt:message key="editPassword.newPassword"/></h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="password" class="form-control" name="password" required>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0"><fmt:message key="editPassword.retypePassword"/></h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="password" class="form-control" name="retypePassword" required>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-12">
                                    <button type="submit" class="btn btn-primary btn-block">
                                        <fmt:message key="editPassword.button"/>
                                    </button>
                                </div>
                            </div>
                            <div class="row">
                                <c:if test="${requestScope.successPasswordEdit == 'yes'}">
                                    <p><a class="text-success"><fmt:message key="profile.passwordUpdated"/></a></p>
                                </c:if>
                                <c:if test="${requestScope.successPasswordEdit != 'yes' && requestScope.successPasswordEdit != null}">
                                    <p><a class="text-danger"><fmt:message
                                            key="${requestScope.successPasswordEdit}"/> </a></p>
                                </c:if>
                            </div>
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