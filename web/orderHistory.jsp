
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order History</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ------>
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

        <!-- Roboto Font -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700&display=swap"> 
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
        <link rel="stylesheet" href="https://mdbootstrap.com/previews/ecommerce-demo/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://mdbootstrap.com/previews/ecommerce-demo/css/mdb-pro.min.css">
        <link rel="stylesheet" href="https://mdbootstrap.com/previews/ecommerce-demo/css/mdb.ecommerce.min.css"> 
    </head>
    <body>
        <header>
            <%@ include file="menu.jsp"%>
        </header>
        <main>
            <div class="container pt-4 text-center" style="margin-top: 50px">
                <h1 class="text-center my-4">Danh Sách Đặt Hàng</h1>
                <section class="mb-4">
                    <div class="card">
                        <div class="card-body">
                            <c:if test="${not empty orderList}">
                                <div class="table-responsive">
                                    <table class="table table-hover text-nowrap">
                                        <thead>
                                            <tr>
                                                <th scope="col">Order ID</th>
                                                <th scope="col">Date</th>
                                                <th scope="col">Receiver</th>
                                                <th scope="col">Phone</th>
                                                <th scope="col">Delivery Address</th>
                                                <th scope="col">Price($)</th>
                                                <th scope="col">Status</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${orderList}" var="i" varStatus="status">
                                                <tr>
                                                    <td>${i.orderID}</td>
                                                    <td>${i.date}</td>
                                                    <td>${i.receiver}</td>
                                                    <td>${i.phone}</td>
                                                    <td>${i.deliveryAddress}</td>
                                                    <td>${String.format("%.0f", i.totalMoney)} VNĐ</td>
                                                    <td>
                                                        <c:if test="${!i.status}">
                                                            Delivered
                                                        </c:if>
                                                        <c:if test="${i.status}">
                                                            Delivering
                                                        </c:if>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </c:if>
                            <c:if test="${empty orderList}">
                                <div class="pb-5 text-center">
                                    <img src="images/emptyorder.jpg" alt="Empty Order" class="col-12"/>
                                    <a href="shop?cid=0" class="text-light">
                                        <button type="button" class="btn btn-danger">View Shop</button>
                                    </a>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </section>

            </div>
        </main>
    </body>
</html>
