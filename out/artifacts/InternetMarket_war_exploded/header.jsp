<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.webLocaleName}" />
<fmt:setBundle basename="language"/>
<jsp:useBean id="pageConstants" class="com.epam.internetMarket.util.constants.PageConstants"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js'></script>
    <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js'></script>
    <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css' rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="index.jsp"><fmt:message key="header.main"/></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="market.jsp"><fmt:message key="header.market"/></a>
                </li>
                <li class="nav-item"><a class="nav-link" href="profile.jsp"><fmt:message key="header.profile"/></a>
                    <c:if test="${sessionScope.loggedUser.isAdmin == true}">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <fmt:message key="header.adminPage"/>
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="addProduct.jsp"><fmt:message key="header.addProduct"/></a></li>
                        <li><a class="dropdown-item" href="editProducts.jsp"><fmt:message key="header.editProduct"/></a></li>
                        <li><a class="dropdown-item" href="editUsers.jsp"><fmt:message key="header.editUsers"/></a></li>
                    </ul>
                </li>
                </c:if>
                <li class="nav-item">
                    <form action="ChangeLocale" method="POST">
                        <select name="localeToChange">
                            <c:forEach var="locale" items="${sessionScope.allLocales}">
                                <option value="${locale.id}">${locale.shortName}</option>
                            </c:forEach>
                        </select>
                        <input type="hidden" name="page" value="${pageContext.request.servletPath}"/>
                        <button type="submit" class="btn btn-outline-dark">
                            <fmt:message key="header.language.button"/>
                        </button>
                    </form>
                </li>
                <c:if test="${sessionScope.loggedUser == null}">
                    <li class="nav-item active"><a class="nav-link" href="login.jsp"><fmt:message
                            key="header.login"/> </a></li>
                    <li class="nav-item"><a class="btn rounded-pill btn-dark py-2 px-4"
                                            href=register.jsp><fmt:message key="header.signUp"/> </a></li>
                </c:if>
                <c:if test="${sessionScope.loggedUser != null}">
                    <li class="nav-item">
                        <form action="Logout" method="post">
                            <a class="btn rounded-pill btn-dark py-2 px-4">
                                <button type="submit" class="btn btn-dark">
                                    <fmt:message key="header.logout"/>
                                </button>
                            </a>
                        </form>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>