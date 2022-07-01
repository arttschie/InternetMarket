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
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="login.login"/> </title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
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
                    <div class="form-group">
                        <label for="username"><fmt:message key="login.username"/></label>
                        <input id="username" type="text" class="form-control" name="username" value="" autofocus required>
                    </div>

                    <div class="form-group">
                        <label for="password"><fmt:message key="login.password"/></label>
                        <input id="password" type="password" class="form-control" name="password" data-eye required/>
                        <i class="fa fa-eye" id="togglePassword" style="cursor: pointer;"></i>
                    </div>


                    <div class="form-group m-0">
                        <button type="submit" class="btn btn-primary btn-block">
                            <fmt:message key="login.login"/>
                        </button>
                    </div>
                    <c:if test="${requestScope.errorLogin != null}">
                        <p><a class="text-danger"><fmt:message key="${requestScope.errorLogin}"/> </a></p>
                    </c:if>
                    <div class="mt-4 text-center">
                        <fmt:message key="login.notHaveAccount?"/> <a href="register.jsp"><fmt:message key="login.createOne"/></a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
    const togglePassword = document.querySelector('#togglePassword');
    const password = document.querySelector('#password');

    togglePassword.addEventListener('click', function (e) {
        // toggle the type attribute
        const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
        password.setAttribute('type', type);
        // toggle the eye slash icon
        this.classList.toggle('fa-eye-slash');
    });
</script>
</body>
</html>