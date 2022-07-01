<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.webLocaleName}" />
<fmt:setBundle basename="language"/>
<jsp:useBean id="pageConstants" class="com.epam.internetMarket.util.constants.PageConstants"/>
<jsp:useBean id="parameterConstants" class="com.epam.internetMarket.util.constants.ParameterConstants"/>
<jsp:useBean id="productDao" class="com.epam.internetMarket.dao.impl.ProductDaoImpl"/>
<jsp:useBean id="productCategoryDao" class="com.epam.internetMarket.dao.impl.ProductCategoryImpl"/>
<c:if test="${sessionScope.loggedUser == null}">
    <c:redirect url="${pageConstants.profilePage}"/>
</c:if>
<c:if test="${sessionScope.loggedUser.isAdmin == false}">
    <c:redirect url="${pageConstants.profilePage}"/>
</c:if>
<html>
<head>
    <title><fmt:message key="addProduct.addProduct"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js'></script>
    <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js'></script>
    <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css' rel="stylesheet">
</head>
<body>
<jsp:include page="${pageConstants.headerPage}"/>
<div class="container">
    <div class="brand">
        <p><br></p>
    </div>


    <div class="row">
        <div class="col-xl-4 col-md-8 mb-6">
            <h1><fmt:message key="addProduct.addProduct"/></h1>
            <form action="AddProduct" method="POST">
                <div class="form-group">
                    <label><fmt:message key="addProduct.addName"/></label>
                    <input type="text" class="form-control" name="productName" placeholder="<fmt:message key="addProduct.addName"/>" required>
                </div>
                <div class="form-group">
                    <label><fmt:message key="addProduct.description"/></label>
                    <input type="text" class="form-control" name="productDescription" placeholder="<fmt:message key="addProduct.description"/>" required>
                </div>
                <div class="form-group">
                    <label><fmt:message key="addProduct.addCost"/></label>
                    <input type="number" class="form-control" name="productCost" placeholder="1000" required>
                </div>
                <div class="form-group">
                    <label><fmt:message key="addProduct.addCategory"/></label>
                    <select class="form-control" name="categoryId" required>
                        <c:forEach var="productCategory" items="${productCategoryDao.allProductCategories}">
                            <option value="${productCategory.id}">${productCategoryDao.getProductCategoryName(productCategory.id, sessionScope.webLocaleId)}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label><fmt:message key="addProduct.addCount"/></label>
                    <input type="number" class="form-control-file" name="productCount">
                </div>
                <div class="form-group">
                    <label><fmt:message key="addProduct.imageUrl"/></label>
                    <input type="text" class="form-control" name="productImageUrl" placeholder="url" required>
                </div>
                <button type="submit" class="btn btn-outline-dark btn-lg"><fmt:message key="addProduct.button"/></button>
                <c:if test="${requestScope.successProduct == 'yes'}">
                    <p><a class="text-success"><fmt:message key="addProduct.productAdded"/></a></p>
                </c:if>
                <c:if test="${requestScope.successProduct == 'no'}">
                    <p><a class="text-danger"><fmt:message key="addProduct.productError"/></a></p>
                </c:if>
            </form>
            <hr>
    </div>
    <div class="brand">
        <p><br></p>
    </div>
</div>
<jsp:include page="${pageConstants.footerPage}"/>
</body>
</html>
