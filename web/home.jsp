<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <!-- Your custom styles (optional) -->

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
            .dropdown-item:hover {
            background-color: #000 !important;
        }
        </style>
    </head>
    <body>
        <%@ include file="menu.jsp"%>

        <!-- Carousel wrapper -->
        <div id="introCarousel" class="carousel slide carousel-fade shadow-2-strong" data-mdb-ride="carousel" style="margin-top:35px;">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-mdb-target="#introCarousel" data-mdb-slide-to="0" class="active"></li>
                <li data-mdb-target="#introCarousel" data-mdb-slide-to="1"></li>
                <li data-mdb-target="#introCarousel" data-mdb-slide-to="2"></li>
            </ol>

            <!-- Inner -->
            <div class="carousel-inner">
                <!-- Single item -->
                <div class="carousel-item active">

                </div>

                <!-- Single item -->
                <div class="carousel-item">

                </div>

                <!-- Single item -->
                <div class="carousel-item">

                </div>
            </div>
            <!-- Inner -->

            <!-- Controls -->
            <a class="carousel-control-prev" href="#introCarousel" role="button" data-mdb-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#introCarousel" role="button" data-mdb-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
        <!-- Carousel wrapper -->


        <div class="container">
            <div class="row" style="margin-top:25px">            
                <h1 style="text-align:center; width:100%" id="bestSelling">BEST SELLINGS</h1>
                <div class="col-sm-12">
                    <div id="contentBestSelling" class="row">
                        <c:forEach items="${bestSellings}" var="o">
                            <div class="col-12 col-md-6 col-lg-3">
                                <div class="card">
                                    <div class="view zoom z-depth-2 rounded">
                                        <img class="img-fluid w-100" src="${o.image}" alt="Card image cap">

                                    </div>
                                    <div class="card-body">
                                        <h4 class="card-title text-center"><a href="detail?pid=${o.productID}" title="View Product">${o.productName}</a></h4>

                                        <div class="row">
                                            <div class="col">
                                                <p class="btn btn-success btn-block">${o.price} VND</p>
                                            </div> 
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="row" style="margin-top:25px">            
                <h1 style="text-align:center; width:100%" id="newest">SẢN PHẨM MỚI NHẤT</h1>
                <div class="col-sm-12">
                    <div id="contentmoinhat" class="row">
                        <c:forEach items="${list12Last}" var="o">
                            <div class=" col-12 col-md-6 col-lg-3">
                                <div class="card">
                                    <div class="view zoom z-depth-2 rounded">
                                        <img class="img-fluid w-100" src="${o.image}" alt="Card image cap">

                                    </div>
                                    <div class="card-body">
                                        <h4 class="card-title text-center"><a href="detail?pid=${o.productID}" title="View Product">${o.productName}</a></h4>

                                        <div class="row">
                                            <div class="col">
                                                <p class="btn btn-success btn-block">${o.price} VND</p>
                                            </div> 
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                </div>

            </div>


            <div class="row" style="margin-top:50px">            
                <div class="col-sm-12">
                    <div id="content" class="row">
                        <div class=" col-12 col-md-12 col-lg-6">
                            <div class="card-body">
                                <h4 class="card-title show_txt" style="text-align:center; font-size:18pt; color:#b57b00;">Về chúng tôi</h4>
                                <h2 class="card-title show_txt" style="text-align:center; font-size:24pt;">Sneaker Shop</h2>
                                <p style="text-align:center;">Website Shop Sneaker được tạo ra bởi Group 6 Class SE18B06 FPTU Da Nang</p>
                                <p>Bạn đang cần tìm một đôi giày thể thao sneaker đẹp và hợp thời trang và đang hot Trends 
                                    đến từ các thương hiệu lớn nhưng lại không đủ hầu bao để sắm được hàng chính hãng? 
                                    Hãy đến với chúng tôi</p>                  
                            </div>  
                        </div>
                        <div class=" col-12 col-md-12 col-lg-6">
                            <img class="card-img-top" src="images/aboutus.png" alt="Card image cap">        
                        </div>
                    </div>
                </div>
            </div>





        </div>

        <jsp:include page="footer.jsp"></jsp:include>


        <!-- MDB -->
        <script type="text/javascript" src="js/mdb.min.js"></script>
        <!-- Custom scripts -->
        <script type="text/javascript" src="js/script.js"></script>

        <!-- SCRIPTS -->
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