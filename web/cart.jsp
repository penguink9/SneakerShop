<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Cart View</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <link href="css/style.css" rel="stylesheet" type="text/css"/> 

    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css" />
    <!-- Google Fonts Roboto -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" /> 
    <!-- MDB -->
    <link rel="stylesheet" href="css/mdb.min.css" />
    <!-- Custom styles -->
    <link rel="stylesheet" href="css/style.css" />

    <style>
        .quantity-container {
            display: flex;
            align-items: center;
        }
        .quantity-container .btn {
            margin: 0 5px;
        }
        .quantity-container .quantity {
            text-align: center;
            width: 60px;
        }
        .shopping-cart {
            padding: 20px;
        }
        .shopping-cart .table-responsive {
            padding: 20px;
            border: 1px solid #dee2e6;
            border-radius: 5px;
        }
        .shopping-cart .table {
            margin-bottom: 0;
        }
        .shopping-cart .table th {
            border: none;
            background-color: #f8f9fa;
            font-weight: bold;
            text-transform: uppercase;
        }
        .shopping-cart .table td, .shopping-cart .table th {
            vertical-align: middle;
            border: none;
        }
        .shopping-cart .table td {
            border-top: 1px solid #dee2e6;
        }
        .shopping-cart .table td img {
            max-width: 70px;
            height: auto;
            vertical-align: middle;
        }
        .shopping-cart .quantity-container {
            display: flex;
            align-items: center;
        }
        .shopping-cart .quantity-container .btn {
            margin: 0 5px;
        }
        .shopping-cart .quantity-container .quantity {
            width: 60px;
            text-align: center;
        }
        .shopping-cart .btn-danger {
            background-color: #dc3545;
            border-color: #dc3545;
        }
        .shopping-cart .btn-danger:hover {
            background-color: #c82333;
            border-color: #bd2130;
        }
        .shopping-cart .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }
        .shopping-cart .btn-primary:hover {
            background-color: #0069d9;
            border-color: #0062cc;
        }
    </style>
</head>
<body>
    <jsp:include page="menu.jsp"></jsp:include>
    <div class="shopping-cart">
        <div class="px-4 px-lg-0">
            <c:if test="${sessionScope.cart.size != 0}">
                <div class="pb-5">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">
                                <!-- Shopping cart table -->
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead>
                                            <c:if test="${error != null}">
                                                <tr>
                                                    <td colspan="5">
                                                        <div class="alert alert-danger" role="alert">
                                                            ${error}
                                                        </div>
                                                    </td>
                                                </tr>
                                            </c:if>
                                            <c:if test="${cartMess != null}">
                                                <tr>
                                                    <td colspan="5">
                                                        <div class="alert alert-success" role="alert">
                                                            ${cartMess}
                                                        </div>
                                                    </td>
                                                </tr>
                                            </c:if>
                                            <tr class="text-center">
                                                <th scope="col" class="border-0 bg-light">
                                                    <div class="p-2 px-3 text-uppercase">Sản Phẩm</div>
                                                </th>
                                                <th scope="col" class="border-0 bg-light">
                                                    <div class="py-2 text-uppercase">Đơn Giá</div>
                                                </th>
                                                <th scope="col" class="border-0 bg-light">
                                                    <div class="py-2 text-uppercase">Kích Thước</div>
                                                </th>
                                                <th scope="col" class="border-0 bg-light">
                                                    <div class="py-2 text-uppercase">Số Lượng</div>
                                                </th>
                                                <th scope="col" class="border-0 bg-light">
                                                    <div class="py-2 text-uppercase">Xóa</div>
                                                </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${sessionScope.cart.listItems}" var="o">
                                                <tr>
                                                    <td>
                                                        <div class="p-2 d-flex align-items-center">
                                                            <img src="${o.product.image}" alt="" class="img-fluid rounded shadow-sm">
                                                            <div class="ml-3">
                                                                <h5 class="mb-0"><a href="detail?pid=${o.product.productID}" class="text-dark">${o.product.productName}</a></h5>
                                                                <span class="text-muted font-weight-normal font-italic"></span>
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td class="align-middle"><strong>${String.format("%.0f", o.product.price)} VNĐ</strong></td>
                                                    <td class="align-middle"><strong>${o.size}</strong></td>
                                                    <td class="align-middle">
                                                        <form action="editCart?pid=${o.product.productID}&size=${o.size}" method="post">
                                                            <div class="quantity-container">
                                                                <button type="button" onclick="this.parentNode.querySelector('input[type=number]').stepDown()" class="btn btn-secondary">-</button>
                                                                <input class="quantity form-control" min="1" name="quantity" value="${o.quantity}" type="number">
                                                                <button type="button" onclick="this.parentNode.querySelector('input[type=number]').stepUp()" class="btn btn-secondary">+</button>
                                                                <button type="submit" class="btn btn-primary ml-2">Edit</button>
                                                            </div>
                                                        </form>
                                                    </td>
                                                    <td class="align-middle">
                                                        <a href="deleteCart?pid=${o.product.productID}&size=${o.size}" class="text-dark">
                                                            <button type="button" class="btn btn-danger">Delete</button>
                                                        </a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- End Shopping cart table -->
                            </div>
                        </div>
                        <div class="row py-5 p-4 bg-white rounded shadow-sm">
                            <div class="col-lg-6">
                                <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold text-center">Thành tiền (Free Ship)</div>
                                <div class="p-4">
                                    <ul class="list-unstyled mb-4" id="contentTotalMoney">
                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Tổng tiền hàng</strong><strong>${String.format("%.0f", totalMoney)} VNĐ</strong></li>
                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Giảm giá</strong><strong>${String.format("%.0f", totalMoney * 0.1)} VNĐ</strong></li>
                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Tổng thanh toán</strong><h5 class="font-weight-bold">${String.format("%.0f", totalMoney * 0.9)} VNĐ</h5></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <form class="form-signin" action="order?money=${totalMoney * 0.9}" method="post">
                                    <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold text-center">Order</div>
                                    <c:if test="${error != null}">
                                        <div class="alert alert-danger" role="alert">${orderError}</div>
                                    </c:if>
                                    <c:if test="${mess != null}">
                                        <div class="alert alert-success" role="alert">${mess}</div>
                                    </c:if>
                                    <label for="receiver">Người nhận</label>
                                    <input name="receiver" type="text" id="receiver" class="form-control" placeholder="Receiver" required >
                                    <label for="phoneNumber">SĐT</label>
                                    <input name="phoneNumber" type="text" id="phoneNumber" class="form-control" placeholder="Phone Number" required >                
                                    <label for="deliveryAddress">Địa chỉ giao hàng</label>
                                    <input name="deliveryAddress" type="text" id="deliveryAddress" class="form-control" placeholder="Delivery Address" required >
                                    <button class="btn btn-dark rounded-pill py-2 btn-block text-white" type="submit"><i class="fas fa-american-sign-language-interpreting"></i>Đặt Hàng</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${sessionScope.cart.size == 0}">
                <div class="pb-5 text-center">
                    <img src="images/empty-cart.png" alt="Empty Cart" class="col-12"/>
                    <a href="shop?cid=0" class="text-light">
                        <button type="button" class="btn btn-danger">View Shop</button>
                    </a>
                </div>
            </c:if>
        </div>
    </div>
    <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
