<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<nav id="sidebarMenu" class="collapse d-lg-block sidebar collapse bg-white" style="padding: 0px">
    <div class="position-sticky">
        <div class="list-group list-group-flush mx-3 mt-4 ">
            <a href="viewProfile.jsp" class="nav-link list-group-item list-group-item-action py-2 ripple text-center">
                <img src="${sessionScope.imageUser}" alt="User Image" class="img-thumbnail" style="width: 150px; height: 150px;">
                Admin
            </a> 
            <a href="home" class="list-group-item list-group-item-action py-2 ripple" aria-current="true" style="font-size: 18px ;margin: 10px">
                <i class="fas fa-tachometer-alt fa-fw me-3"></i><span>Home</span>
            </a>
            <a href="statistic" class="list-group-item list-group-item-action py-2 ripple" aria-current="true" style="font-size: 18px ;margin: 10px">
                <i class="fas fa-chart-pie fa-fw me-3"></i><span>Statistic</span>
            </a>
            <a href="OrderManager" class="list-group-item list-group-item-action py-2 ripple" style="font-size: 18px ;margin: 10px">
                <i class="fas fa-file-invoice-dollar fa-fw me-3"></i><span>Quản lý đơn hàng</span>
            </a>
            <a href="manageUser" class="list-group-item list-group-item-action py-2 ripple" style="font-size: 18px ;margin: 10px">
                <i class="fas fa-user-circle fa-fw me-3"></i><span>Quản lý tài khoản</span>
            </a>
            <a href="manageProduct" class="list-group-item list-group-item-action py-2 ripple" style="font-size: 18px ;margin: 10px">
                <i class="fas fa-shoe-prints fa-fw me-3"></i><span>Quản lý sản phẩm</span>
            </a>
            <a href="top10product" class="list-group-item list-group-item-action py-2 ripple" style="font-size: 18px ;margin: 10px">
                <i class="fas fa-shoe-prints fa-fw me-3"></i><span>Top 10 sản phẩm</span>
            </a>
            <a href="top5customer" class="list-group-item list-group-item-action py-2 ripple" style="font-size: 18px ;margin: 10px">
                <i class="fas fa-user-circle fa-fw me-3"></i><span>Top 5 khách hàng</span>
            </a>
        </div>
    </div>
</nav>
