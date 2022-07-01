<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.webLocaleName}" />
<fmt:setBundle basename="language"/>
<jsp:useBean id="pageConstants" class="com.epam.internetMarket.util.constants.PageConstants"/>
<c:if test="${sessionScope.loggedUser != null}">
    <c:redirect url="${pageConstants.profilePage}"/>
</c:if>
<jsp:include page="${pageConstants.headerPage}"/>
<html>
<head>
    <title><fmt:message key="login.login"/> </title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js'></script>
    <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js'></script>
    <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css' rel="stylesheet">
</head>
<body>
<div class="row justify-content-md-center h-100">
    <div class="card-wrapper">
        <div class="brand">
            <p></p>
        </div>
        <div class="card fat">
            <div class="card-body">
                <h4 class="card-title"><fmt:message key="login.login"/></h4>
                    <form method="POST" class="my-login-validation" action="Login">
                        <div class="form-outline mb-4">
                            <label class="form-label" for="username"><fmt:message key="login.username"/></label>
                            <input type="text" id="username" class="form-control" name="username"/>
                        </div>
                        <div class="form-outline mb-4">
                            <label class="form-label" for="password"><fmt:message key="login.password"/></label>
                            <input type="password" id="password" class="form-control" name="password"/>
                        </div>
                        <div class="form-outline mb-4">
                            <button type="submit" class="btn btn-primary btn-block mb-4"><fmt:message key="login.login"/></button>
                        </div>
                            <c:if test="${requestScope.errorLogin != null}">
                            <p><a class="text-danger"><fmt:message key="${requestScope.errorLogin}"/></a></p>
                        </c:if>
                        <div class="text-center">
                            <p><fmt:message key = "login.notHaveAccount?"/> <a href="register.jsp"><fmt:message key="header.signUp"/></a></p>
                        </div>
                        <div class="row mb-4">
                            <div class="col">
                                <a href="forgotPassword.jsp"><fmt:message key="login.forgotPassword"/></a>
                            </div>
                        </div>
                    </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>