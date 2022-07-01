<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.webLocaleName}"/>
<fmt:setBundle basename="language"/>
<jsp:useBean id="pageConstants" class="com.epam.internetMarket.util.constants.PageConstants"/>
<jsp:useBean id="productDao" class="com.epam.internetMarket.dao.impl.ProductDaoImpl"/>
<jsp:useBean id="orderDao" class="com.epam.internetMarket.dao.impl.OrderDaoImpl"/>
<c:if test="${sessionScope.loggedUser == null}">
    <c:redirect url="${pageConstants.loginPage}"/>
</c:if>

<html>
<head>
    <title><fmt:message key="orderDetails.allOrders"/></title>
</head>
<body>
<jsp:include page="${pageConstants.headerPage}"/>
<div class="container">
    <div class="col-md-12">
        <div class="offer-dedicated-body-left">
            <div class="tab-content" id="pills-tabContent">
                <div class="tab-pane fade active show" id="pills-reviews" role="tabpanel"
                     aria-labelledby="pills-reviews-tab">
                    <div class="bg-white rounded shadow-sm p-4 mb-4 restaurant-detailed-ratings-and-reviews">
                        <h5 class="mb-1"><fmt:message key="orderDetails.allOrders"/></h5>
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col"><fmt:message key="orderDetails.name"/></th>
                                <th scope="col"><fmt:message key="orderDetails.image"/></th>
                                <th scope="col"><fmt:message key="orderDetails.count"/></th>
                                <th scope="col"><fmt:message key="orderDetails.cost"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="product" items="${sessionScope.orderDetailsList}">
                                <tr>
                                    <form action="GetProductPage" method="GET">
                                        <th scope="row">
                                            <button type="submit" class="btn btn-link" name = "productId" value="${product.productId}">
                                                    ${productDao.getProductById(product.productId).name}
                                            </button>
                                        </th>
                                    </form>
                                    <td><img src="img/${productDao.getProductById(product.productId).imageUrl}" width="130" height="50"/></td>
                                    <td>${product.count}</td>
                                    <td>${product.cost}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

