<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--begin of menu-->
<nav class="navbar navbar-expand-md navbar-dark bg-dark" style="position: fixed; top: 0; width:100%;  z-index: 100000;">
    <div class="container">
        <a class="navbar-brand" href="home">Sneaker Shop</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
            <ul class="navbar-nav m-auto">
                <li class="nav-item">
                    <a class="nav-link" href="home">Home</a>
                </li> 
                <li class="nav-item">
                    <ul class="navbar-nav">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="shop" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Shop
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                <a class="dropdown-item" href="shop?cid=0">Tất cả sản phẩm</a>
                                <c:forEach items="${listCategories}" var="o">
                                    <a class="dropdown-item" href="shop?cid=${o.categoryID}" >${o.categoryName}</a>
                                </c:forEach>
                            </div>
                        </li>
                    </ul>
                </li> 
                <c:if test="${sessionScope.account != null}">
                    <li class="nav-item">
                        <a class="nav-link" href="#">${sessionScope.account.userName}</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="logout">Logout</a>
                    </li> 
                </c:if>
                <c:if test="${sessionScope.account == null}">
                    <li class="nav-item">
                        <a class="nav-link" href="login">Login</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.account == null}">
                    <li class="nav-item">
                        <a class="nav-link" href="register">Register</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.account != null}">
                    <li class="nav-item">
                        <a class="nav-link" href="EditProfile.jsp">My Profile</a>
                    </li>
                </c:if>
                <%--  <c:if test="${sessionScope.acc.isAdmin == 1}">
                     <li class="nav-item">
                         <a class="nav-link" href="statistic">Statistic</a>
                     </li>
                 </c:if> --%>
            </ul>

            <form action="search" method="post" class="form-inline my-2 my-lg-0">

                <a class="btn btn-success btn-sm ml-3" href="managerCart">
                    <i class="fa fa-shopping-cart"></i> <span style="font-size: 14px;">Cart</span>
                    <b><span id="amountCart" class="badge badge-light" style="color:black; font-size: 12px;">0</span></b>

                </a>
            </form>
        </div>
    </div>
</nav>



<!--end of menu-->
