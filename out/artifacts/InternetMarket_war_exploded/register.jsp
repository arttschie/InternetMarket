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
    <title><fmt:message key="register.register"/> </title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js'></script>
    <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js'></script>
    <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css' rel="stylesheet">
</head>
<body>
<div class="row justify-content-md-center h-100">
    <div class="card-wrapper">
        <div class="card fat">
            <div class="card-body">
                <h4 class="card-title"><fmt:message key="register.register"/></h4>
                <form method="POST" class="my-login-validation" action="Register">
                    <div class="form-group">
                        <label for="username"><fmt:message key="register.username"/></label>
                        <input id="username" type="text" class="form-control" name="username" autofocus required>
                    </div>

                    <div class="form-group">
                        <label for="password"><fmt:message key="register.password"/></label>
                        <input id="password" type="password" class="form-control" name="password"  data-eye required>
                    </div>

                    <div class="form-group">
                        <label for="retypePassword"><fmt:message key="register.retype-password"/></label>
                        <input id="retypePassword" type="password" class="form-control" name="retypePassword"  data-eye required>
                    </div>

                    <div class="form-group">
                        <label for="firstName"><fmt:message key="register.firstName"/></label>
                        <input id="firstName" type="text" class="form-control" name="firstName"  autofocus required>
                    </div>

                    <div class="form-group">
                        <label for="lastName"><fmt:message key="register.lastName"/></label>
                        <input id="lastName" type="text" class="form-control" name="lastName"  autofocus required>
                    </div>

                    <div class="form-group">
                        <label for="birthday"><fmt:message key="register.birthday"/></label>
                        <input id="birthday" type="date" class="form-control" name="birthday"  autofocus required>
                    </div>

                    <div class="form-group">
                        <label for="phoneNumber"><fmt:message key="register.phoneNumber"/></label>
                        <input id="phoneNumber" type="text" class="form-control" name="phoneNumber"  autofocus required>
                    </div>

                    <div class="form-group">
                        <label for="address"><fmt:message key="register.address"/></label>
                        <input id="address" type="text" class="form-control" name="address"  autofocus required>
                    </div>

                    <div class="form-group m-0">
                        <button type="submit" class="btn btn-primary btn-block">
                            <fmt:message key="register.register"/>
                        </button>
                    </div>
                    <c:if test="${requestScope.errorRegister != null}">
                        <p><a class="text-danger"><fmt:message key="${requestScope.errorRegister}"/></a></p>
                    </c:if>
                    <div class="mt-4 text-center">
                        <fmt:message key="register.alreadyHaveAccount?"/> <a href="login.jsp"><fmt:message key="register.signIn"/></a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
