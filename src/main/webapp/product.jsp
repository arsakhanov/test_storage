<%--
  Created by IntelliJ IDEA.
  User: baki_
  Date: 23.08.2022
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String header = "Operations with products";
%>
<html>
<html>
<head>
    <title>Product</title>
</head>
<body>
<h2><%=header%>
</h2>
<form action="<%=request.getContextPath() %>/product" method="post">
    <table styly="with: 80%">
        <tr>
            <td>vendorCode</td>
            <td><input type="text" name="vendorCode"></td>
        </tr>
        <tr>
            <td>productName</td>
            <td><input type="text" name="productName"></td>
        </tr>
        <tr>
            <td>lastPurchasePrice</td>
            <td><input type="text" name="lastPurchasePrice"></td>
        </tr>
        <tr>
            <td>lastSalePrice</td>
            <td><input type="text" name="lastSalePrice"></td>
        </tr>
    </table>
    <button type="submit">Add Product</button>
</form>
</body>
</html>
