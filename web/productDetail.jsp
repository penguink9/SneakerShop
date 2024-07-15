<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Detail Product</title>
        <!-- Roboto Font -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700&display=swap">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="https://mdbootstrap.com/previews/ecommerce-demo/css/bootstrap.min.css">
        <!-- Material Design Bootstrap -->
        <link rel="stylesheet" href="https://mdbootstrap.com/previews/ecommerce-demo/css/mdb-pro.min.css">
        <!-- Material Design Bootstrap Ecommerce -->
        <link rel="stylesheet" href="https://mdbootstrap.com/previews/ecommerce-demo/css/mdb.ecommerce.min.css">
        <!-- Your custom styles (optional) -->

        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>

        <style>
            .gallery-wrap .img-big-wrap img {
                height: 450px;
                width: auto;
                display: inline-block;
                cursor: zoom-in;
            }
            .gallery-wrap .img-small-wrap .item-gallery {
                width: 60px;
                height: 60px;
                border: 1px solid #ddd;
                margin: 7px 2px;
                display: inline-block;
                overflow: hidden;
            }
            .gallery-wrap .img-small-wrap {
                text-align: center;
            }
            .gallery-wrap .img-small-wrap img {
                max-width: 100%;
                max-height: 100%;
                object-fit: cover;
                border-radius: 4px;
                cursor: zoom-in;
            }
            .img-big-wrap img{
                width: 100% !important;
                height: auto !important;
            }
        </style>
        <script>
            function validateForm() {
                const sizes = document.getElementsByName('size');
                let sizeSelected = false;
                for (let i = 0; i < sizes.length; i++) {
                    if (sizes[i].checked) {
                        sizeSelected = true;
                        break;
                    }
                }
                if (!sizeSelected) {
                    alert('Please select a size before adding to cart.');
                    return false;
                }

                const quantity = document.querySelector('input[name="quantity"]').value;
                const maxQuantity = ${product.quantity};
                if (quantity > maxQuantity) {
                    alert('Selected quantity exceeds the quantity of product.');
                    return false;
                }
                return true;
            }
        </script>
    </head>
    <body class="skin-light">
        <jsp:include page="menu.jsp"></jsp:include>

            <div class="jumbotron color-grey-light mt-70">
                <div class="d-flex align-items-center h-100">
                    <div class="container text-center py-5">
                        <h3 class="mb-0">Detail Product</h3>
                    </div>
                </div>
            </div>

            <!--Main Navigation-->

            <!--Main layout-->
            <main>
                <div class="container">
                    <!--Section: Block Content-->
                    <section class="mb-5">
                        <div class="row">
                            <div class="col-md-6 mb-4 mb-md-0">
                                <div id="mdb-lightbox-ui"></div>
                                <div class="mdb-lightbox">
                                    <div class="row product-gallery mx-1">
                                        <div class="col-12 mb-0">
                                            <figure class="view overlay rounded z-depth-1 main-img" style="max-height: 450px;">
                                                <a href="${product.image}" data-size="710x823">
                                                <img src="${product.image}" class="img-fluid z-depth-1" style="margin-top: -90px;">
                                            </a>
                                        </figure>
                                        <figure class="view overlay rounded z-depth-1" style="visibility: hidden;">
                                            <a href="${product.image2}" data-size="710x823">
                                                <img src="${product.image2}" class="img-fluid z-depth-1">
                                            </a>
                                        </figure>
                                        <figure class="view overlay rounded z-depth-1" style="visibility: hidden;">
                                            <a href="${product.image3}" data-size="710x823">
                                                <img src="${product.image3}" class="img-fluid z-depth-1">
                                            </a>
                                        </figure>
                                        <figure class="view overlay rounded z-depth-1" style="visibility: hidden;">
                                            <a href="${product.image4}" data-size="710x823">
                                                <img src="${product.image4}" class="img-fluid z-depth-1">
                                            </a>
                                        </figure>
                                    </div>
                                    <div class="col-12">
                                        <div class="row">
                                            <div class="col-3">
                                                <div class="view overlay rounded z-depth-1 gallery-item hoverable">
                                                    <a href="${product.image}">
                                                        <img src="${product.image}" class="img-fluid">
                                                    </a>
                                                </div>
                                            </div>
                                            <div class="col-3">
                                                <div class="view overlay rounded z-depth-1 gallery-item hoverable">
                                                    <a href="${product.image2}">
                                                        <img src="${product.image2}" class="img-fluid">
                                                    </a>
                                                </div>
                                            </div>
                                            <div class="col-3">
                                                <div class="view overlay rounded z-depth-1 gallery-item hoverable">
                                                    <a href="${product.image3}">
                                                        <img src="${product.image3}" class="img-fluid">
                                                    </a>
                                                </div>
                                            </div>
                                            <div class="col-3">
                                                <div class="view overlay rounded z-depth-1 gallery-item hoverable">
                                                    <a href="${product.image4}">
                                                        <img src="${product.image4}" class="img-fluid">
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <h5>${product.productName}</h5>
                            <p><span class="mr-1"><strong>${String.format("%.0f", product.price * 0.9)} VNĐ</strong></span>
                                <span class="text-grey"><strong><s>${String.format("%.0f", product.price)} VNĐ</s></strong></span>
                            </p>
                            <p class="pt-1">${product.description}</p>
                            <span class="pl-0 pb-0 w-25 text-grey">Số lượng trong kho:${product.quantity}</span>
                            <form action="addtocart?pid=${product.productID}" method="post" onsubmit="return validateForm();">
                                <div class="table-responsive mb-2">
                                    <table class="table table-sm table-borderless">
                                        <tbody>
                                            <tr>
                                                <td class="pl-0 pb-0 w-25">Quantity</td>
                                                <td class="pb-0">Select size</td>
                                            </tr>
                                            <tr>
                                                <td class="pl-0">
                                                    <div class="mt-1">
                                                        <div class="def-number-input number-input safari_only mb-0" style="display: flex; align-items: center;">
                                                            <button type="button" onclick="this.parentNode.querySelector('input[type=number]').stepDown()" class="minus"></button>
                                                            <input class="quantity" min="1" name="quantity" value="1" type="number">
                                                            <button type="button" onclick="this.parentNode.querySelector('input[type=number]').stepUp()" class="plus"></button>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="mt-1">
                                                        <c:if test="${product.isSizeAvailable('S')}">
                                                            <div class="form-check form-check-inline pl-0">
                                                                <input type="radio" class="form-check-input" id="small" value="M" name="size">
                                                                <label class="form-check-label small text-uppercase card-link-secondary" for="small">37</label>
                                                            </div>
                                                        </c:if>
                                                        <c:if test="${product.isSizeAvailable('M')}">
                                                            <div class="form-check form-check-inline pl-0">
                                                                <input type="radio" class="form-check-input" id="medium" value="M" name="size">
                                                                <label class="form-check-label small text-uppercase card-link-secondary" for="medium">38</label>
                                                            </div>
                                                        </c:if>
                                                        <c:if test="${product.isSizeAvailable('L')}">
                                                            <div class="form-check form-check-inline pl-0">
                                                                <input type="radio" class="form-check-input" id="large" value="L" name="size">
                                                                <label class="form-check-label small text-uppercase card-link-secondary" for="large">39</label>
                                                            </div>
                                                        </c:if>
                                                    </div>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="mt-1">
                                    <button type="submit" class="btn btn-light btn-md mr-1 mb-2"><i class="fas fa-shopping-cart pr-2"></i>Add to cart</button>
                                </div>

                                <c:if test="${not empty mess}">
                                    <div class="alert alert-success"> ${mess} </div>
                                </c:if>
                                <c:if test="${not empty error}">
                                    <div class="alert alert-danger"> ${error} </div>
                                </c:if>

                            </form>
                        </div>
                    </div>
                </section>

                <section class="text-center">
                    <h4 class="text-center my-5"><strong>Related products</strong></h4>
                    <!-- Grid row -->
                    <div class="row">
                        <c:forEach items="${listRelatedProduct}" var="o">
                            <!-- Grid column -->
                            <div class="col-md-6 col-lg-3 mb-5">
                                <!-- Card -->
                                <div class="">
                                    <div class="view zoom overlay z-depth-2 rounded">
                                        <img class="img-fluid w-100" src="${o.image}" alt="Sample">
                                        <h4 class="mb-0"><span class="badge badge-primary badge-pill badge-news">Sale 10%</span></h4>
                                        <a href="detail?pid=${o.productID}">
                                            <div class="mask">
                                                <img class="img-fluid w-100" src="${o.image}">
                                                <div class="mask rgba-black-slight"></div>
                                            </div>
                                        </a>
                                    </div>
                                    <div class="pt-4">
                                        <p><span class="text-danger mr-1"><strong>${String.format("%.0f", o.price * 0.9)} VNĐ</strong></span>
                                            <span class="text-grey"><strong><s>${String.format("%.0f", o.price)} VNĐ</s></strong></span>
                                        </p>
                                    </div>
                                </div>
                                <!-- Card -->
                            </div>
                            <!-- Grid column -->
                        </c:forEach>
                    </div>
                    <!-- Grid row -->
                </section>
            </div>
        </main>
        <!--Section: Block Content-->
        <jsp:include page="footer.jsp"></jsp:include>
        <!-- JQuery -->
    </body>
</html>
