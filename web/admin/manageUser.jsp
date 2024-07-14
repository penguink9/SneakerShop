<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Quản Lý Tài Khoản</title>
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
                                    <h5 class="mb-0 text-left"><strong>Quản lý tài khoản</strong></h5>
                                </div>
                                <div class="col-sm-9 text-right">
                                    <a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">New</i></a>

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
                                            <th scope="col">Username</th>
                                            <th scope="col">Role</th>
                                            <th scope="col">Email</th>
                                            <th scope="col">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${listA}" var="o" varStatus="status">
                                            <tr>
                                                <td>${status.index + 1}</td>
                                                <td>${o.getUserName()}</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${o.getRoleID() == 1}">
                                                            Customer
                                                        </c:when>
                                                        <c:when test="${o.getRoleID() == 0}">
                                                            Admin
                                                        </c:when>
                                                    </c:choose>
                                                </td>
                                                <td>${o.getEmail()}</td>
                                                <td>
                                                    <c:if test="${o.getRoleID() == 1}">
                                                            <a class="" href="deleteuser?username=${o.getUserName()}">
                                                                <button type="button" class="btn btn-danger">
                                                                    <i class="material-icons" data-toggle="tooltip" title="Delete">Delete</i>
                                                                </button>
                                                            </a>
                                                    </c:if>            
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
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
                        <form action="addUser" method="post">
                            <div class="modal-header">
                                <h4 class="modal-title">Thêm tài khoản</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <label>Username</label>
                                    <input name="username" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Fullname</label>
                                    <input name="fullname" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Password</label>
                                    <input name="pass" type="password" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Role ID</label>
                                    <select name="roleid" class="form-control">
                                        <option value="0">Admin</option>
                                        <option value="1">Người dùng</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Email</label>
                                    <input name="email" type="email" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Image URL</label>
                                    <input name="image" type="text" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>Address</label>
                                    <input name="address" type="text" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>Phone</label>
                                    <input name="phone" type="text" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>Date of Birth (YYYY-MM-DD)</label>
                                    <input name="dob" type="text" class="form-control" required>
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
