<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Quản Lý Sản Phẩm</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
        <link href="css/manager.css" rel="stylesheet">
        <style>
            body {
                margin: 0;
                padding: 0;
            }
        </style>
        <link rel="stylesheet" type="text/css" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css"><link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&amp;display=swap"><link rel="stylesheet" type="text/css" href="https://mdbootstrap.com/wp-content/themes/mdbootstrap4/css/mdb5/3.8.1/compiled.min.css"><link rel="stylesheet" type="text/css" href="https://mdbootstrap.com/wp-content/themes/mdbootstrap4/css/mdb-plugins-gathered.min.css"><style>body {
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
                        <div class="card">
                            <div class="card-header py-3 row">
                                <div class="col-sm-3">
                                    <h5 class="mb-0 text-left"><strong>Quản lý sản phẩm</strong></h5>
                                </div>
                                <div class="col-sm-9 text-right">
                                    <a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">Add</i></a>

                                </div>
                            </div>
                        <c:if test="${error != null}">
                            <div class="alert alert-danger" role="alert">${error}</div>
                        </c:if>
                        <c:if test="${mess != null}">
                            <div class="alert alert-success" role="alert">${mess}</div>
                        </c:if>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-hover text-nowrap">
                                    <thead>
                                        <tr>
                                            <th scope="col">ID</th>
                                            <th scope="col">Product Name</th>
                                            <th scope="col">Image</th>
                                            <th scope="col">Price</th>
                                            <th scope="col">Action</th>


                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${listP}" var="p">
                                            <tr>
                                                <td>${p.getProductID()}</td>
                                                <td>${p.getProductName()}</td>
                                                <td><img src="${p.getImage()}" alt="Product Image" style="max-width: 100px; max-height: 100px;"> </td>
                                                <td>${String.format("%.0f",p.price)} VNĐ</td>

                                                <td>
                                                    <a   href="loadControl?pid=${p.getProductID()}"><button type="button" class="btn btn-warning"><i class="material-icons" data-toggle="tooltip" title="Edit">Edit</i></button>
                                                    </a>
                                                </td>
                                                <td>
                                                    <a href="deleteProduct?pid=${p.getProductID()}">
                                                        <button type="button" class="btn btn-danger">
                                                            <i class="material-icons" data-toggle="tooltip" title="Delete">Delete</i>
                                                        </button>
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                    <!-- Pagination Controls -->
                                    <nav aria-label="Page navigation">
                                        <ul class="pagination">
                                            <c:if test="${currentPage > 1}">
                                                <li class="page-item">
                                                    <a class="page-link" href="manageProduct?page=${currentPage - 1}" aria-label="Previous">
                                                        <span aria-hidden="true">&laquo;</span>
                                                    </a>
                                                </li>
                                            </c:if>
                                            <c:forEach var="i" begin="1" end="${totalPages}">
                                                <li class="page-item ${i == currentPage ? 'active' : ''}">
                                                    <a class="page-link" href="manageProduct?page=${i}">${i}</a>
                                                </li>
                                            </c:forEach>
                                            <c:if test="${currentPage < totalPages}">
                                                <li class="page-item">
                                                    <a class="page-link" href="manageProduct?page=${currentPage + 1}" aria-label="Next">
                                                        <span aria-hidden="true">&raquo;</span>
                                                    </a>
                                                </li>
                                            </c:if>
                                        </ul>
                                    </nav>
                                </table>
                            </div>
                        </div>
                    </div>
                </section>
            </div>

            <!-- Edit Modal HTML -->
            <div id="addEmployeeModal" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="addProduct" method="post">
                            <div class="modal-header">
                                <h4 class="modal-title">Thêm sản phẩm</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <label>Product Name</label>
                                    <input name="productname" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>CategoryID</label>
                                    <input name="categoryid" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Size</label>
                                    <input name="size" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Price</label>
                                    <input name="price" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Image URL</label>
                                    <input name="image" type="text" class="form-control">
                                </div>

                                <div class="form-group">
                                    <label>Image2 URL</label>
                                    <input name="image2" type="text" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>Image3 URL</label>
                                    <input name="image3" type="text" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>Image4 URL</label>
                                    <input name="image4" type="text" class="form-control">
                                </div>

                                <div class="form-group">
                                    <label>Quantity</label>
                                    <input name="quantity" type="text" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>Quantity Sold</label>
                                    <input name="quantitysold" type="text" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>Discount</label>
                                    <input name="discount" type="text" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>Description</label>
                                    <input name="description" type="text" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label for="status">Status</label>
                                    <select class="form-control" id="status" name="status">
                                        <option value="true">Active</option>
                                        <option value="false">Inactive</option>
                                    </select>
                                </div>  


                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                                <input type="submit" class="btn btn-success" value="Add">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </main>

        <!-- SCRIPTS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </body>
</html>