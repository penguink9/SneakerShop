

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Edit</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>
        <style>
            img{
                width: 200px;
                height: 120px;
            }
        </style>
    <body>

        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Edit <b>Product</b></h2>
                        </div>
                        <div class="col-sm-6">
                        </div>
                    </div>
                </div>
            </div>
            <div id="editEmployeeModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="editProduct" method="post">
                            <div class="modal-header">						
                                <h4 class="modal-title">Edit Product</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            </div>
                            <div class="modal-body">					
                                <div class="form-group">
                                    <input value="${detail.getProductID()}" name="id" type="hidden" class="form-control" readonly required>
                                </div> 
                                <div class="form-group">
                                    <label>Name</label>
                                    <input value="${detail.getProductName()}" name="name" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Size</label>
                                    <input value="${detail.getSize()}" name="size" type="text" class="form-control" >
                                </div>
                                <div class="form-group">
                                    <label>Category</label>
                                    <select name="category" class="form-select" aria-label="Default select example" >
                                        <c:forEach items="${sessionScope.listCategories}" var="o">
                                            <option value="${o.categoryID}" <c:if test="${o.categoryID == detail.categoryID}">selected</c:if>>${o.categoryName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Price</label>
                                    <input value="${detail.getPrice()}" name="price" type="text" class="form-control" >
                                </div>
                                <div class="form-group">
                                    <label>Quantity</label>
                                    <input value="${detail.getQuantity()}" name="quantity" type="text" class="form-control" >
                                </div>
                                <div class="form-group">
                                    <label>QuantitySold</label>
                                    <input value="${detail.getQuantitySold()}" name="quantitysold" type="text" class="form-control" >
                                </div>
                                <div class="form-group">
                                    <label>Image</label>
                                    <input value="${detail.getImage()}" name="image" type="text" class="form-control" >
                                </div>
                                <div class="form-group">
                                    <label>Image2</label>
                                    <input value="${detail.getImage2()}" name="image2" type="text" class="form-control" >
                                </div>
                                <div class="form-group">
                                    <label>Image3</label>
                                    <input value="${detail.getImage3()}" name="image3" type="text" class="form-control" >
                                </div>
                                <div class="form-group">
                                    <label>Image4</label>
                                    <input value="${detail.getImage4()}" name="image4" type="text" class="form-control" >
                                </div>

                                <div class="form-group">
                                    <label>Discount</label>
                                    <input value="${detail.getDiscount()}" name="discount" type="text" class="form-control" >
                                </div>
                                <div class="form-group">
                                    <label>Description</label>
                                    <textarea name="description" class="form-control" >${detail.description}</textarea>
                                </div>
                                <div class="form-group">
                                    <label>Status</label>
                                    <select name="status" class="form-control">
                                        <option value="true" ${detail.isStatus() ? 'selected' : ''}>Active</option>
                                        <option value="false" ${!detail.isStatus() ? 'selected' : ''}>Inactive</option>
                                    </select>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <input type="submit" class="btn btn-success" value="Edit">
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </div>


        <script src="js/manager.js" type="text/javascript"></script>
    </body>
</html>