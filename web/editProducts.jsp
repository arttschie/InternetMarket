<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.webLocaleName}"/>
<fmt:setBundle basename="language"/>
<jsp:useBean id="pageConstants" class="com.epam.internetMarket.util.constants.PageConstants"/>
<jsp:useBean id="productDao" class="com.epam.internetMarket.dao.impl.ProductDaoImpl"/>
<jsp:useBean id="localeDao" class="com.epam.internetMarket.dao.impl.LocaleDaoImpl"/>
<jsp:useBean id="productCategoryDao" class="com.epam.internetMarket.dao.impl.ProductCategoryImpl"/>
<c:if test="${sessionScope.loggedUser == null}">
    <c:redirect url="${pageConstants.loginPage}"/>
</c:if>
<c:if test="${sessionScope.loggedUser.isAdmin == false}">
    <c:redirect url="${pageConstants.profilePage}"/>
</c:if>

<html>
<head>
    <title><fmt:message key="editProduct.title"/></title>
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
                        <h5 class="mb-1"><fmt:message key="editProduct.allProducts"/></h5>
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col"><fmt:message key="editProduct.name"/></th>
                                <th scope="col"><fmt:message key="editProduct.description"/></th>
                                <th scope="col"><fmt:message key="editProduct.cost"/></th>
                                <th scope="col"><fmt:message key="editProduct.count"/></th>
                                <th scope="col"><fmt:message key="editProduct.category"/></th>
                                <th scope="col"><fmt:message key="editProduct.imageUrl"/></th>
                                <th scope="col"><fmt:message key="editProduct.edit"/></th>
                                <th scope="col"><fmt:message key="editProduct.remove"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="product" items="${productDao.allProducts}">
                                <tr>
                                    <form action="EditProducts" method="POST">
                                        <td>${product.name}<br><input type="text" name="productName" required>
                                        </td>
                                        <td>${product.description}<br><input type="text" name="productDescription" required>
                                        </td>
                                        <td>${product.cost}<br><input type="number" name="productCost" required></td>
                                        <td>${product.count}<br><input type="number" name="productCount" required></td>
                                        <td>${productCategoryDao.getProductCategoryName(product.productCategoryId, sessionScope.webLocaleId)}<br>
                                            <select class="form-control" name="categoryId" required>
                                                <c:forEach var="productCategory" items="${productCategoryDao.allProductCategories}">
                                                    <option value="${productCategory.id}">${productCategoryDao.getProductCategoryName(productCategory.id, sessionScope.webLocaleId)}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>${product.imageUrl}<br><input type="text" name="productImageUrl" required>
                                        </td>
                                        <td>
                                            <button type="submit" class="btn btn-warning" name="productId"
                                                    value="${product.id}">
                                                <fmt:message key="editProduct.edit"/>
                                            </button>
                                        </td>
                                    </form>
                                    <form action="RemoveFromMarket" method="POST">
                                        <td>
                                            <button type="submit" class="btn btn-danger" name="productId"
                                                    value="${product.id}">
                                                <fmt:message key="editProduct.remove"/>
                                            </button>
                                        </td>
                                    </form>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <c:if test="${requestScope.successProduct == 'yes'}">
                            <p><a class="text-success"><fmt:message key="editProduct.productEdited"/></a></p>
                        </c:if>
                        <c:if test="${requestScope.successProduct == 'no'}">
                            <p><a class="text-danger"><fmt:message key="editProduct.productEditError"/></a></p>
                        </c:if>
                        <c:if test="${requestScope.deleteProduct == 'yes'}">
                            <p><a class="text-danger"><fmt:message key="editProduct.productRemoved"/></a></p>
                        </c:if>
                        <c:if test="${requestScope.deleteProduct == 'no'}">
                            <p><a class="text-danger"><fmt:message key="editProduct.removeError"/></a></p>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
