<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<nav id="sidebarMenu" class="collapse d-lg-block sidebar collapse bg-white" style="padding: 0px">
    <div class="position-sticky">
      <div class="list-group list-group-flush mx-3 mt-4">
        <a href="admin" class="list-group-item list-group-item-action py-2 ripple" aria-current="true">
          <i class="fas fa-tachometer-alt fa-fw me-3"></i><span>Main dashboard</span>
        </a>
        <a href="manageOrder" class="list-group-item list-group-item-action py-2 ripple"><i class="fas fa-file-invoice-dollar fa-fw me-3"></i><span>Quản lý đơn hàng</span></a>
       
         <a href="managerAccount" class="list-group-item list-group-item-action py-2 ripple">
          <i class="fas fa-user-circle fa-fw me-3"></i><span>Quản lý tài khoản</span>
        </a>
        <a href="managerProduct" class="list-group-item list-group-item-action py-2 ripple">
          <i class="fas fa-shoe-prints fa-fw me-3"></i><span>Quản lý sản phẩm</span>
        </a>
        <a href="top10" class="list-group-item list-group-item-action py-2 ripple">
          <i class="fas fa-shoe-prints fa-fw me-3"></i><span>Top 10 sản phẩm</span>
        </a>
        <a href="top5khachhang" class="list-group-item list-group-item-action py-2 ripple">
          <i class="fas fa-user-circle fa-fw me-3"></i><span>Top 5 khách hàng</span>
        </a>
      </div>
    </div>
  </nav>
