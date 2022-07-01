<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.webLocaleName}"/>
<fmt:setBundle basename="language"/>
<jsp:useBean id="pageConstants" class="com.epam.internetMarket.util.constants.PageConstants"/>
<jsp:useBean id="productDao" class="com.epam.internetMarket.dao.impl.ProductDaoImpl"/>
<html>
<head>
    <title><fmt:message key="market.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<jsp:include page="${pageConstants.headerPage}"/>
<!-- Section-->
<section class="py-5">
    <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
            <c:forEach var="product" items="${productDao.allProducts}">
                <div class="col mb-5">
                    <div class="card h-100">
                        <!-- Product image-->
                        <img class="card-img-top" src="img/${product.imageUrl}"
                             alt="${product.id}+img"
                                width="300" height="250"/>
                        <!-- Product details-->
                        <div class="card-body p-4">
                            <div class="text-center font-monospace">
                                <!-- Product name-->
                                <h5 class="fw-bolder">${product.name}</h5>
                                <!-- Product price-->
                                ${productDao.getProductCost(product.name)}₸
                            </div>
                        </div>
                        <!-- Product actions-->
                        <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                            <form action="GetProductPage" method="GET">
                                <div class="text-center">
                                    <input type="hidden" name = "productId" value="${product.id}">
                                    <button type="submit" class="btn btn-outline-dark mt-auto"><fmt:message key="market.details"/></button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
    </div>
</section>
</body>
</html>

