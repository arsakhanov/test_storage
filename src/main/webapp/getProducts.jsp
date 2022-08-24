<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: baki_
  Date: 23.08.2022
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Все продукты</title>
</head>
<body>
<table>
    <tr>
        <th>Vendor code</th>
        <th>Product name</th>
        <th>Last purchase price</th>
        <th>Last sale price</th>
    </tr>
    <c:forEach items="${productList}" var="products">
        <tr>
            <td>${products.vendorCode}</td>
            <td>${products.name}</td>
            <td>${products.lastPurchasePrice}</td>
            <td>${products.lastSalePrice}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
