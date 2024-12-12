<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Product Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>


<h2>Product Management </h2>
<button type="button" onclick="location.href='/product_servlet?action=show-add'">Add Product</button>

<h3>Find Product</h3>
<form action="product_servlet?action=find-product" method="post">
    <label for="productName">Name</label>
    <input type="text" id="productName"  name="name">

    <label for="price" class="form-label">Price</label>
    <input type="number" id="price" name="price">

    <label for="quantity" >Quantity</label>
    <input type="number" id="quantity"  name="quantity" >

    <label for="color" >Color</label>
    <input type="text" id="color" name="color" >

    <label for="category" >Category</label>
    <input type="text" id="category"  name="Category" >


    <button class="btn btn-secondary" type="reset">Reset</button>
    |
    <button class="btn btn-success" type="submit">Search</button>
</form>

<table class="table table-striped ">
    <thead>
    <tr class="table-primary">
        <th scope="col">STT</th>
        <th scope="col">Product Name</th>
        <th scope="col">Price</th>
        <th scope="col">Quantity</th>
        <th scope="col">Color</th>
        <th scope="col">Category</th>
        <th scope="col">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${products}" var="pro" varStatus="loop">
        <tr>
            <td>${loop.count}</td>
            <td>${pro.productName}</td>
            <td>${pro.productPrice}</td>
            <td>${pro.quantity}</td>
            <td>${pro.color}</td>
            <td>${pro.getCategoryName(pro.categoryID)}</td>
            <td>
                <button type="button" class="btn btn-danger" data-bs-toggle="modal" onclick="showDeleteModal(${pro.productID}, '${pro.productName}')">
                    Delete
                </button>

            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<!-- Modal chung -->
<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="deleteModalLabel">Warning</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Are you sure to remove <span id="deleteModalCount"></span> from your database ?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Close</button>
                <button type="button" class="btn btn-danger" id="confirmDeleteButton">
                    Delete
                </button>
            </div>
        </div>
    </div>
</div>

<script>
    function showDeleteModal(productID,productName) {
        document.getElementById('deleteModalCount').textContent = productName;
        const confirmButton = document.getElementById('confirmDeleteButton');
        confirmButton.onclick = function () {
            location.href = '/product_servlet?action=remove-product&productID='+productID;
        };
        const deleteModal = new bootstrap.Modal(document.getElementById('deleteModal'));
        deleteModal.show();
    }
</script>
</body>
</html>