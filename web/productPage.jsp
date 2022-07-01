<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.webLocaleName}"/>
<fmt:setBundle basename="language"/>
<jsp:useBean id="pageConstants" class="com.epam.internetMarket.util.constants.PageConstants"/>
<jsp:useBean id="userDao" class="com.epam.internetMarket.dao.impl.UserDaoImpl"/>
<jsp:useBean id="productDao" class="com.epam.internetMarket.dao.impl.ProductDaoImpl"/>
<c:if test="${sessionScope.loggedUser == null}">
    <c:redirect url="${pageConstants.loginPage}"/>
</c:if>
<jsp:include page="${pageConstants.headerPage}"/>
<html>
<head>
    <title>${sessionScope.product.name}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js'></script>
    <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js'></script>
    <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css' rel="stylesheet">
</head>
<body>
<div class="container bootdey">
    <div class="col-md-12">
        <section class="panel">
            <div class="panel-body">
                <div class="row">
                    <div class="col-md-6">
                        <div class="pro-img-details">
                            <img src="img/${sessionScope.product.imageUrl}" alt="">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <h4 class="pro-d-title">
                            ${sessionScope.product.name}
                        </h4>
                        <p class="fw-light"><fmt:message key="productPage.category"/>${productDao.getCategoryName(sessionScope.product.productCategoryId, sessionScope.webLocaleId)}</p>
                        <p><fmt:message key="productPage.description"/>${sessionScope.product.description}</p>
                        <form action="AddCart" method="POST">
                            <div class="form-group">
                                <label><fmt:message key="cart.quantity"/></label>
                                <input type="number" placeholder="1" min="0" class="form-control quantity" name="cartCount" required>
                            </div>
                            <p>
                                <button class="btn btn-round btn-danger" type="submit"><i class="fa fa-shopping-cart"></i>
                                    <fmt:message key="cart.add"/>
                                </button>
                                <c:if test="${requestScope.successCart == 'yes'}">
                            <p><a class="text-success"><fmt:message key="cart.cartAdded"/></a></p>
                            </c:if>
                            <c:if test="${requestScope.successCart != 'yes' and requestScope.successCart != null}">
                                <p><a class="text-danger"><fmt:message key="${requestScope.successCart}"/> </a></p>
                            </c:if>
                            </p>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </div>
</div>
</body>
</html>
