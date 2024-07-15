<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta charset="ISO-8859-1">
        <title>Statistic</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="css/style.css" rel="stylesheet" type="text/css"/> 
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
        <link rel="stylesheet" href="https://mdbootstrap.com/previews/ecommerce-demo/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://mdbootstrap.com/previews/ecommerce-demo/css/mdb-pro.min.css">
        <link rel="stylesheet" href="https://mdbootstrap.com/previews/ecommerce-demo/css/mdb.ecommerce.min.css">
        <style>
            body {
                background-color: #fbfbfb;
            }
            @media (min-width: 991.98px) {
                main {
                    padding-left: 240px;
                }
            }

            /* Sidebar */
            .sidebar {
                position: fixed;
                top: 0;
                bottom: 0;
                left: 0;
                padding: 58px 0 0; /* Height of navbar */
                box-shadow: 0 2px 5px 0 rgb(0 0 0 / 5%), 0 2px 10px 0 rgb(0 0 0 / 5%);
                width: 240px;
                z-index: 600;
            }

            @media (max-width: 991.98px) {
                .sidebar {
                    width: 100%;
                }
            }
            .sidebar .active {
                border-radius: 5px;
                box-shadow: 0 2px 5px 0 rgb(0 0 0 / 16%), 0 2px 10px 0 rgb(0 0 0 / 12%);
            }

            .sidebar-sticky {
                position: relative;
                top: 0;
                height: calc(100vh - 48px);
                padding-top: 0.5rem;
                overflow-x: hidden;
                overflow-y: auto; /* Scrollable contents if viewport is shorter than content. */
            }</style>
    </head>
    <body>
        <header>
            <jsp:include page="admin_menu.jsp"></jsp:include>
            </header>
            <main>
                <div class="container pt-4">
                    <section class="mb-4">
                    <c:if test="${error != null}">
                        <div class="alert alert-danger" role="alert">
                            ${error}
                        </div>
                    </c:if>
                    <c:if test="${mess != null}">
                        <div class="alert alert-success" role="alert">
                            ${mess}
                        </div>
                    </c:if>
                    <div class="card">
                        <div class="card-header py-3 row">
                            <div class="col-sm-6">
                                <h5 class="mb-0 text-left">
                                    <strong>Orders</strong>
                                </h5>
                            </div>
                            <div class="col-sm-6">
                                <form action="OrderManager" method="post">
                                    <input type="hidden" name="action" value="searchByDate">
                                    <input type="date" id="dateHoaDon" name="dateHoaDon" class="form-control mb-0" style="width:30%" required value="${dob}">
                                    <button class="btn btn-dark rounded-pill py-2 btn-block text-white" type="submit" style="width:30%; margin: 5px"><i class="fas fa-american-sign-language-interpreting"></i>Search</button>

                                </form>

                            </div>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-hover text-nowrap">
                                    <thead>
                                        <tr>
                                            <th scope="col">Order ID</th>
                                            <th scope="col">User</th>
                                            <th scope="col">Price($)</th>
                                            <th scope="col">Date</th>
                                            <th scope="col">Receiver</th>
                                            <th scope="col">Phone</th>
                                            <th scope="col">Delivery Address</th>
                                            <th scope="col">Status</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${listAllInvoice}" var="i" varStatus="status">
                                            <tr>
                                                <td>${i.orderID}</td>
                                                <c:forEach items="${listAllAccount}" var="a">
                                                    <c:if test="${i.userName == a.userName}">
                                                        <td>${a.userName}</td>
                                                    </c:if>
                                                </c:forEach>
                                                <td>${String.format("%.02f", i.totalMoney)}</td>
                                                <td>${i.date}</td>
                                                <td>${i.receiver}</td>
                                                <td>${i.phone}</td>
                                                <td>${i.deliveryAddress}</td>
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
                                <!-- Pagination Controls -->
                                <nav aria-label="Page navigation">
                                    <ul class="pagination">
                                        <c:if test="${currentPage > 1}">
                                            <li class="page-item">
                                                <a class="page-link" href="OrderManager?page=${currentPage - 1}&dateHoaDon=${dob}" aria-label="Previous">
                                                    <span aria-hidden="true">&laquo;</span>
                                                </a>
                                            </li>
                                        </c:if>
                                        <c:forEach var="i" begin="1" end="${totalPages}">
                                            <li class="page-item ${i == currentPage ? 'active' : ''}">
                                                <a class="page-link" href="OrderManager?page=${i}&dateHoaDon=${dob}">${i}</a>
                                            </li>
                                        </c:forEach>
                                        <c:if test="${currentPage < totalPages}">
                                            <li class="page-item">
                                                <a class="page-link" href="OrderManager?page=${currentPage + 1}&dateHoaDon=${dob}" aria-label="Next">
                                                    <span aria-hidden="true">&raquo;</span>
                                                </a>
                                            </li>
                                        </c:if>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </main>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://mdbootstrap.com/previews/ecommerce-demo/js/jquery-3.4.1.min.js"></script>
        <script src="https://mdbootstrap.com/previews/ecommerce-demo/js/popper.min.js"></script>
        <script src="https://mdbootstrap.com/previews/ecommerce-demo/js/bootstrap.js"></script>
        <script src="https://mdbootstrap.com/previews/ecommerce-demo/js/mdb.min.js"></script>
        <script src="https://mdbootstrap.com/previews/ecommerce-demo/js/mdb.ecommerce.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js"></script>
    </body>
</html>
