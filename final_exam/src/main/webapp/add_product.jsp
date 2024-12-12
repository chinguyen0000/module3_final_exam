<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 12/12/2024
  Time: 8:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add Product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
<div style="margin: 20px">
    <h3>Add new product</h3>

    <form action="product_servlet?action=add-product" method="post">
        <label for="productName" class="form-label">Name</label>
        <input type="text" id="productName" class="form-control" name="name" required>
        <br>
        <label for="price" class="form-label">Price</label>
        <input type="number" id="price" class="form-control" name="price" required min="10000001" >
        <br>
        <label for="quantity" class="form-label">Quantity</label>
        <input type="number" id="quantity" class="form-control" name="quantity" required min="1">
        <br>
        <label for="color" class="form-label">Color</label>
        <select class="form-select" id="color" aria-label="Floating label disabled select example" name="color" required>
            <option value="Đỏ">Đỏ</option>
            <option value="Xanh">Xanh</option>
            <option value="Đen">Đen</option>
            <option value="Trắng">Trắng</option>
            <option value="Vàng">Vàng</option>
        </select>
        <br>
        <label for="description" class="form-label">Description</label>
        <input type="text" id="description" class="form-control" name="description" >
        <br>
        <label for="category" class="form-label">Category</label>
        <select class="form-select" id="category" aria-label="Floating label disabled select example" name="category" required>
            <c:forEach items="${categories}" varStatus="loop" var="cate">
                <option value="${cate.categoryID}">${cate.categoryName}</option>
            </c:forEach>
        </select>
        <br>
        <button class="btn btn-success" type="submit">Create</button>
        |
        <button class="btn btn-secondary" onclick="location.href='/product_servlet'">Back</button>
    </form>
</div>

</body>
</html>
