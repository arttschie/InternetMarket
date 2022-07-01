<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.webLocaleName}"/>
<fmt:setBundle basename="language"/>
<jsp:useBean id="pageConstants" class="com.epam.internetMarket.util.constants.PageConstants"/>
<jsp:useBean id="productDao" class="com.epam.internetMarket.dao.impl.ProductDaoImpl"/>
<c:if test="${sessionScope.loggedUser == null}">
    <c:redirect url="${pageConstants.loginPage}"/>
</c:if>
<html>
<head>
    <title><fmt:message key="cart.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js'></script>
    <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js'></script>
    <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css' rel="stylesheet">
</head>
<body>
<c:if test="${fn:endsWith(pageContext.request.requestURI, '/cart.jsp')}">
    <jsp:include page="${pageConstants.headerPage}"/>
</c:if>

<c:if test="${sessionScope.userCart != null }">
    ${sessionScope.userCart.size()}
    <div class="cart-box">
        <div class="order_summary">
            <div class="summary_card">

                <c:forEach var="cartProduct" items="${sessionScope.userCart}">
                    <div class="card_item">
                        <div class="product_img">
                            <img src="img/${productDao.getProductById(cartProduct.productId).imageUrl}"
                                 alt="" width="300" height="175"/>
                        </div>
                        <form action="EditCartProductCount" method="POST">
                            <div class="product_info">
                                <input type="hidden" name="productId" value="${cartProduct.productId}">
                                <div class="product_rate_info">
                                    <h1>${productDao.getProductById(cartProduct.productId).cost}₸</h1>
                                    <input type="number" name="cartCount" value="${cartProduct.count}" min="0" required>
                                </div>
                                <div class="product_rate_info">
                                    <button type="submit" class="btn btn-dark"><fmt:message key="cart.edit"/></button>
                                </div>
                            </div>
                        </form>
                        <form action="DeleteCart" method="POST">
                            <div class="product_info">
                                <div class="product_rate_info">
                                    <input type="hidden" name="productId" value="${cartProduct.productId}">
                                    <button type="submit" class="btn btn-danger"><fmt:message
                                            key="cart.delete"/></button>
                                </div>
                            </div>
                        </form>
                    </div>
                </c:forEach>
                <c:if test="${requestScope.successCartUpdate == 'no'}">
                    <p><a class="text-danger"><fmt:message key="cart.updateError"/></a></p>
                </c:if>
                <hr/>

                <form action="MakeOrder" method="POST">
                    <div class="order_total">
                        <p><fmt:message key="cart.totalCost"/></p>
                        <input type="hidden" name="totalCost" value="${sessionScope.cartSum}">
                        <h4>${sessionScope.cartSum}₸</h4>
                        <button type="submit" class="btn btn-success"><fmt:message key="cart.buy"/></button>
                    </div>
                </form>

            </div>
        </div>
    </div>
</c:if>
</body>
</html>
