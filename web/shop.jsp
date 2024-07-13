<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
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
        Font Awesome
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
        Bootstrap core CSS
        <link rel="stylesheet" href="https://mdbootstrap.com/previews/ecommerce-demo/css/bootstrap.min.css">
        Material Design Bootstrap
        <link rel="stylesheet" href="https://mdbootstrap.com/previews/ecommerce-demo/css/mdb-pro.min.css">
        Material Design Bootstrap Ecommerce
        <link rel="stylesheet" href="https://mdbootstrap.com/previews/ecommerce-demo/css/mdb.ecommerce.min.css"> 
        <style>
            /* Carousel styling */
            #introCarousel,
            .carousel-inner,
            .carousel-item,
            .carousel-item.active {
                height: 100vh;
            }

            .carousel-item:nth-child(1) {
                background-image: url('images/banner2_min.jpg');
                background-repeat: no-repeat;
                background-size: 100% 100%;
                background-position: center center;
            }

            .carousel-item:nth-child(2) {
                background-image: url('https://file.hstatic.net/1000230642/file/web-top-banner_68b9a0c957374772bdf25d1d0f312b11_master.jpg');
                background-repeat: no-repeat;
                background-size: 100% 100%;
                background-position: center center;
            }

            .carousel-item:nth-child(3) {
                background-image: url('https://file.hstatic.net/1000230642/file/banner_central_opt_2_76f1c057c7dc43ee9c8a36c6bee9ac4d_master.jpg');
                background-repeat: no-repeat;
                background-size: 100% 100%;
                background-position: center center;
            }

            /* Height for devices larger than 576px */
            @media (min-width: 992px) {
                #introCarousel {
                    margin-top: -58.59px;
                }
            }

            .navbar .nav-link {
                color: #fff !important;
            }
        </style>
    </head>

    <body class="skin-light" >

        <!--Main Navigation-->
        <%@ include file="menu.jsp"%>
        <!--Main Navigation-->

        <!--Main layout-->
        <main>
            <div class="container" style="margin-top:100px">

                <!--Grid row-->
                <div class="row mt-5">

                    <!--Grid column-->
                    <div class="col-md-4 mb-4">

                        <!-- Section: Sidebar -->
                        <section>

                            <!-- Section: Categories -->
                            <section>

                                <h5>Categories</h5>

                                <div class="text-muted small text-uppercase mb-5">
                                    <c:forEach items="${listCategories}" var="o">
                                        <p class="mb-3"><a href="shop?cid=${o.categoryID}" class="card-link-secondary">${o.categoryName}</a></p>
                                        </c:forEach>
                                </div>

                            </section>
                            <!-- Section: Categories -->

                            <!-- Section: Filters -->
                            <section>
                                <form class="filters" action="search" method="get">

                                    <h5 class="pt-2 mb-4">Filters</h5>

                                    <section class="mb-4">

                                        <div class="md-form md-outline mt-0 d-flex justify-content-between align-items-center">
                                            <input value="${txtS}" name="txt" type="text" class="form-control mb-0" placeholder="Search...">
                                            <input type="submit" class="btn btn-flat btn-md px-3 waves-effect" value="Search">
                                            <i class="fas fa-search fa-lg"></i>
                                        </div>

                                    </section>

                                    <!-- Section: Price -->
                                    <section class="mb-4">
                                        <h6 class="font-weight-bold mb-3">Price</h6>
                                        <div class="form-check pl-0 mb-3">
                                            <input type="radio" class="form-check-input" id="under100" name="materialExampleRadios" value="under100" 
                                                   <c:if test="${selectedPriceRange == 'under100'}">checked</c:if>>
                                                   <label class="form-check-label small text-uppercase card-link-secondary" for="under100">Under 1,000,000VNĐ</label>
                                            </div>
                                            <div class="form-check pl-0 mb-3">
                                                <input type="radio" class="form-check-input" id="100200" name="materialExampleRadios" value="100200" 
                                                <c:if test="${selectedPriceRange == '100200'}">checked</c:if>>
                                                <label class="form-check-label small text-uppercase card-link-secondary" for="100200">1,000,000VNĐ to 2,000,000VNĐ</label>
                                            </div>
                                            <div class="form-check pl-0 mb-3">
                                                <input type="radio" class="form-check-input" id="200above" name="materialExampleRadios" value="200above" 
                                                <c:if test="${selectedPriceRange == '200above'}">checked</c:if>>
                                                <label class="form-check-label small text-uppercase card-link-secondary" for="200above">2,000,000VNĐ & Above</label>
                                            </div>
                                            <!-- Clear button -->
                                            <div class="form-check pl-0 mb-3">
                                                <button type="button" class="btn btn-secondary" onclick="clearPriceFilter()">Clear</button>
                                            </div>
                                        </section>
                                        <!-- Section: Price -->
                                    </form>
                                </section>

                                <!-- Section: Filters -->

                            </section>
                            <!-- Section: Sidebar -->

                        </div>
                        <!--Grid column-->

                        <!--Grid column-->
                        <div class="col-md-8 mb-4">

                            <!-- Section: Block Content -->
                            <section class="mb-3">

                                <div class="row d-flex justify-content-around align-items-center">
                                    <div class="col-12 col-md-3 text-center text-md-left">
                                        <a href="#!" class="text-reset"><i class="fas fa-th-list fa-lg mr-1"></i></a> 
                                        <a href="#!" class="text-reset"><i class="fas fa-th-large fa-lg"></i></a>
                                    </div>
                                    <div class="col-12 col-md-5">
                                        <div class="d-flex flex-wrap">
                                            <div class="select-outline position-relative w-100">
                                                <label>SẢN PHẨM HIỆN CÓ</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-12 col-md-4 text-center">
                                        <nav aria-label="Page navigation example">
                                            <ul class="pagination pagination-circle justify-content-center float-md-right mb-0">
                                            <c:if test="${tag != 1}">
                                                <li class="page-item"><a href="shop?index=${tag-1 }" class="page-link"><i class="fas fa-chevron-left"></i></a></li>
                                                    </c:if> 
                                                    <c:forEach begin="1" end="${endPage }" var="i">
                                                <li class="${tag==i?"page-item active":"page-item" }"><a href="shop?index=${i }" class="page-link">${i }</a></li>
                                                </c:forEach>
                                                <c:if test="${tag != endPage}">
                                                <li class="page-item"><a href="shop?index=${tag+1 }" class="page-link"><i class="fas fa-chevron-right"></i></a></li>
                                                    </c:if> 
                                        </ul>
                                    </nav>
                                </div>
                            </div>

                        </section>
                        <!-- Section: Block Content -->

                        <!--Section: Block Content-->
                        <section>

                            <!-- Grid row -->
                            <div class="row" id="content">


                                <c:forEach items="${listP}" var="o">
                                    <!-- Grid column -->
                                    <div class="col-md-4 mb-5">

                                        <!-- Card -->
                                        <div class="">

                                            <div class="view zoom overlay rounded z-depth-2">
                                                <img class="img-fluid w-100"
                                                     src="${o.image }" alt="Sample">
                                                <a href="detail?pid=${o.productID}">
                                                    <div class="mask">
                                                        <img class="img-fluid w-100"
                                                             src="${o.image }">
                                                        <div class="mask rgba-black-slight"></div>
                                                    </div>
                                                </a>
                                            </div>

                                            <div class="text-center pt-4">

                                                <h5>${o.productName }</h5>
                                                <p><span class="mr-1"><strong>${o.price }VNĐ</strong></span></p>

                                            </div>

                                        </div>
                                        <!-- Card -->

                                    </div>
                                    <!-- Grid column -->
                                </c:forEach>     

                            </div>
                            <!-- Grid row -->
                        </section>
                        <!--Section: Block Content-->

                    </div>
                    </main>
                    <!-- Footer -->


                    <jsp:include page="footer.jsp"></jsp:include>
                    <!-- Footer -->
                    <script>
                        function clearPriceFilter() {
                            // Clear the selected price filter
                            document.querySelectorAll('input[name="materialExampleRadios"]').forEach((radio) => {
                                radio.checked = false;
                            });

                            // Submit the form without the price filter
                            document.querySelector('.filters').submit();
                        }
                    </script>
                    <!-- JQuery -->
                    <script src="https://mdbootstrap.com/previews/ecommerce-demo/js/jquery-3.4.1.min.js"></script>
                    <!-- Bootstrap tooltips -->

                    <script type="text/javascript" src="https://mdbootstrap.com/previews/ecommerce-demo/js/popper.min.js"></script>
                    <!-- Bootstrap core JavaScript -->
                    <script type="text/javascript" src="https://mdbootstrap.com/previews/ecommerce-demo/js/bootstrap.js"></script>
                    <!-- MDB core JavaScript -->
                    <script type="text/javascript" src="https://mdbootstrap.com/previews/ecommerce-demo/js/mdb.min.js"></script>
                    <!-- MDB Ecommerce JavaScript -->
                    <script type="text/javascript" src="https://mdbootstrap.com/previews/ecommerce-demo/js/mdb.ecommerce.min.js"></script>
                    </body>

                    </html>