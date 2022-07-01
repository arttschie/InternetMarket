<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.webLocaleName}"/>
<fmt:setBundle basename="language"/>
<jsp:useBean id="pageConstants" class="com.epam.internetMarket.util.constants.PageConstants"/>
<jsp:useBean id="productDao" class="com.epam.internetMarket.dao.impl.ProductDaoImpl"/>
<!-- Navigation-->
<jsp:include page="${pageConstants.headerPage}"/>
<html>
<head>
    <link href="css/shop.css" rel="stylesheet"/>
    <script src='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js'></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
    <link rel="stylesheet" href='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css'>
    <link rel="stylesheet" href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.css'>
    <title><fmt:message key="market.title"/></title>
</head>
<body>

<!-- Header-->
<header class="bg-dark">
    <div class="container">
        <div class="card bg-dark">
            <img class="card-img" src="img/3.jpg" alt="Card image">
            <div class="text-center card-img-overlay">
                <h1 class="display-4 text-black-50 font-monospace"><fmt:message key="market.title"/></h1>
                <h1 class="lead fw-normal text-white-50 mb-0 font-monospace"><fmt:message key="market.homepage"/></h1>
            </div>
        </div>
    </div>
</header>
<!-- Section-->
<section class="py-5">
    <div class="container">
        <div class="row">
            <div class="col-xl-6 col-md-12 mb-10">

                <form action="FilterClothes" method="POST">
                    <div class="form-group">
                        <h2 class="display-3"><fmt:message key="shop.filterClothes"/> </h2>
                    </div>
                    <div class="form-group">
                        <c:forEach var="size" items="${sizeDao.allSizes}">
                            <input type="checkbox" name="clothSizeId" value="${size.id}">${size.sizeName}
                        </c:forEach>
                    </div>
                    <div>
                        <c:forEach var="company" items="${companyDao.allCompanies}">
                            <input type="checkbox" name="companyId" value="${company.id}">${company.companyName}
                        </c:forEach>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-light">
                            <fmt:message key="shop.button"/>
                        </button>
                    </div>
                </form>
            </div>

            <div class="col-xl-6 col-md-12 mb-10">
                <form action="SortClothes" method="GET">
                    <div class="form-group">
                        <h2 class="display-3"><fmt:message key="shop.sortClothes"/></h2>
                    </div>
                    <div class="form-group">
                        <select name="sortName">
                            <option value="priceAsc"><fmt:message key="shop.priceAscending"/></option>
                            <option value="priceDesc"><fmt:message key="shop.priceDescending"/></option>
                            <option value="newFirst"><fmt:message key="shop.newFirst"/></option>
                        </select>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-light">
                            <fmt:message key="shop.button"/>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <hr>
    <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
        <c:if test="${requestScope.filterApplied == 'yes'}">
            <c:forEach var="cloth" items="${sessionScope.filteredClothes}">
                <div class="col mb-5">
                    <div class="card h-100">
                        <!-- Product image-->
                        <img class="card-img-top" src="data:image/jpg;base64,${cloth.imageFromDd}"
                             alt="${cloth.id}+img"
                             width="450" height="250"/>
                        <!-- Product details-->
                        <div class="card-body p-4">
                            <div class="text-center font-monospace">
                                <!-- Product name-->
                                <h5 class="fw-bolder">${cloth.vendorCode}</h5>
                                <!-- Product price-->
                                ₸${cloth.price}
                            </div>
                        </div>
                        <!-- Product actions-->
                        <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                            <form action="GetClothPage" method="GET">
                                <div class="text-center">
                                    <input type="hidden" name = "clothId" value="${cloth.id}">
                                    <button type="submit" class="btn btn-outline-dark mt-auto"><fmt:message key="shop.detailed"/></button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>

        </c:if>

        <c:if test="${(requestScope.filterApplied == 'no') or (requestScope.filterApplied == null)}">
            <c:forEach var="cloth" items="${clothDao.availableClothes}">
                <div class="col mb-5">
                    <div class="card h-100">
                        <!-- Product image-->
                        <img class="card-img-top" src="data:image/jpg;base64,${cloth.imageFromDd}"
                             alt="${cloth.id}+img"
                             width="450" height="250"/>
                        <!-- Product details-->
                        <div class="card-body p-4">
                            <div class="text-center font-monospace">
                                <!-- Product name-->
                                <h5 class="fw-bolder">${cloth.vendorCode}</h5>
                                <!-- Product price-->
                                ₸${cloth.price}
                            </div>
                        </div>
                        <!-- Product actions-->
                        <form action="GetClothPage" method="POST">
                            <div class="text-center">
                                <input type="hidden" name = "clothId" value="${cloth.id}">
                                <input type="submit" class="btn btn-outline-dark mt-auto" value="<fmt:message key="shop.detailed"/>">
                            </div>
                        </form>

                    </div>
                </div>
            </c:forEach>
        </c:if>

    </div>
    </div>
</section>
</body>
</html>

