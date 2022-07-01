<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.webLocaleName}"/>
<fmt:setBundle basename="language"/>
<jsp:useBean id="pageConstants" class="com.epam.internetMarket.util.constants.PageConstants"/>
<!DOCTYPE html>
<html>
<head>
    <script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js' integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
    <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js' integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy" crossorigin="anonymous"></script>
</head>
<body>
<div class="header2 bg-success-gradiant">
    <div class="">
        <nav class="navbar navbar-expand-lg h2-nav"><a class="navbar-brand" href="index.jsp"><fmt:message
                key="header.main"/></a>
            <div class="collapse navbar-collapse hover-dropdown" id="header2">
                <ul class="navbar-nav">
                    <li class="nav-item"><a class="nav-link" href="catalog.jsp"><fmt:message key="header.catalog"/></a></li>
                    <li class="nav-item"><a class="nav-link" href="profile.jsp"><fmt:message key="header.profile"/></a>
                    </li>
                    <c:if test="${sessionScope.loggedUser.isAdmin == true}">
                        <li class="nav-item dropdown position-relative"><a class="nav-link dropdown-toggle"
                                                                           id="h2-dropdown" data-toggle="dropdown"
                                                                           aria-haspopup="true" aria-expanded="false"><fmt:message key="header.adminPage"/><i class="fa fa-angle-down ml-1 font-12"></i> </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="addCloth.jsp"><fmt:message key="header.addProduct"/></a></li>
                                <li><a class="dropdown-item" href="editClothes.jsp"><fmt:message key="header.editProduct"/></a></li>
                                <li><a class="dropdown-item" href="editUsers.jsp"><fmt:message key="header.editUsers"/></a></li>
                            </ul>
                        </li>
                        <li class="nav-item"><a class="nav-link" href="addCloth.jsp"></a></li>
                    </c:if>
                </ul>

                <ul class="navbar-nav ml-auto">
                    <li class="nav-item active">
                        <a class="nav-link"><i class="icon-bubble"></i>
                            <form action="ChangeLocale" method="POST">
                                <select name="localeToChange">
                                    <c:forEach var="locale" items="${sessionScope.allLocales}">
                                        <option value="${locale.id}">${locale.shortName}</option>
                                    </c:forEach>
                                </select>
                                <input type="hidden" name="page" value="${pageContext.request.servletPath}"/>
                                <button type="submit" class="btn btn-outline-light">
                                    <fmt:message key="header.language.button"/>
                                </button>
                            </form>
                        </a>
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
        </nav>
    </div>
</div>