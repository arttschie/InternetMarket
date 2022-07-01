<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="pageConstants" class="com.epam.internetMarket.util.constants.PageConstants"/>
<fmt:setLocale value="${sessionScope.webLocaleName}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <title><fmt:message key="error.title"/> </title>
    <style>
        h1 {
            font-size: 7.5em;
            margin: 15px 0px;
            font-weight:bold;
        }
        h2 {
            font-weight:bold;
        }
        btn:hover {
            color: var(--white);
            background: var(--green);
            transition: 0.2s ease;

        }
    </style>
</head>
<body>
<jsp:include page="${pageConstants.headerPage}"/>

<div class="container">
    <div class="col-md-6 align-self-center">
        <h1>404</h1>
        <h2><fmt:message key="error.notFound"/></h2>
        <p><fmt:message key="error.notExist"/></p>
    </div>
</div>
</body>
</html>
