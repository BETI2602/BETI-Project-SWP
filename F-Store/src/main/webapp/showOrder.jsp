<%-- 
    Document   : showOrder
    Created on : Oct 18, 2023, 3:13:07 PM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show Order</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
        <script>
            $(document).ready(function () {
                $('#example').DataTable();
            });
        </script>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css">
    </head>
    <style>
        body {
            color: #566787;
            background: #f5f5f5;
            font-family: 'Varela Round', sans-serif;
            font-size: 13px;
        }

        .main-title{
            margin: 0;
            padding: 0;
            border: none;
            outline: none;
            box-sizing: border-box;
        }

        #logo_img img{
            width: 5px;
            height: 5px;
        }
        .table-wrapper {
            background: #fff;
            padding: 20px 25px;
            margin: 30px 0;
            border-radius: 3px;
            box-shadow: 0 1px 1px rgba(0,0,0,.05);
        }
        .table-title {
            position: fixed;
            padding-bottom: 15px;
            background: #000;
            color: #fff;
            padding: 16px 30px;
            margin: -20px -25px 10px;
            border-radius: 3px 3px 0 0;

        }
        .table-title h2 {
            margin: 5px 0 0;
            font-size: 24px;
        }
        .table-title .btn-group {
            float: right;
        }
        .table-title .btn {
            color: #fff;
            float: right;
            font-size: 13px;
            border: none;
            min-width: 50px;
            border-radius: 2px;
            border: none;
            outline: none !important;
            margin-left: 10px;
        }
        .table-title .btn i {
            float: left;
            font-size: 21px;
            margin-right: 5px;
        }
        .table-title .btn span {
            float: left;
            margin-top: 2px;
        }
        table.table tr th, table.table tr td {
            border-color: #e9e9e9;
            padding: 12px 15px;
            vertical-align: middle;
        }
        table.table tr th:first-child {
            width: 60px;
        }
        table.table tr th:last-child {
            width: 100px;
        }
        table.table-striped tbody tr:nth-of-type(odd) {
            background-color: #fcfcfc;
        }
        table.table-striped.table-hover tbody tr:hover {
            background: #f5f5f5;
        }
        table.table th i {
            font-size: 13px;
            margin: 0 5px;
            cursor: pointer;
        }
        table.table td:last-child i {
            opacity: 0.9;
            font-size: 22px;
            margin: 0 5px;
        }
        table.table td a {
            font-weight: bold;
            color: #566787;
            display: inline-block;
            text-decoration: none;
            outline: none !important;
        }
        table.table td a:hover {
            color: #2196F3;
        }
        table.table td a.edit {
            color: #FFC107;
        }
        table.table td a.delete {
            color: #F44336;
        }
        table.table td i {
            font-size: 19px;
        }
        table.table .avatar {
            border-radius: 50%;
            vertical-align: middle;
            margin-right: 10px;
        }
        .pagination {
            float: right;
            margin: 0 0 5px;
        }
        .pagination li a {
            border: none;
            font-size: 13px;
            min-width: 30px;
            min-height: 30px;
            color: #999;
            margin: 0 2px;
            line-height: 30px;
            border-radius: 2px !important;
            text-align: center;
            padding: 0 6px;
        }
        .pagination li a:hover {
            color: #666;
        }
        .pagination li.active a, .pagination li.active a.page-link {
            background: #03A9F4;
        }
        .pagination li.active a:hover {
            background: #0397d6;
        }
        .pagination li.disabled i {
            color: #ccc;
        }
        .pagination li i {
            font-size: 16px;
            padding-top: 6px
        }
        .hint-text {
            float: left;
            margin-top: 10px;
            font-size: 13px;
        }
        /* Custom checkbox */
        .custom-checkbox {
            position: relative;
        }
        .custom-checkbox input[type="checkbox"] {
            opacity: 0;
            position: absolute;
            margin: 5px 0 0 3px;
            z-index: 9;
        }
        .custom-checkbox label:before{
            width: 18px;
            height: 18px;
        }
        .custom-checkbox label:before {
            content: '';
            margin-right: 10px;
            display: inline-block;
            vertical-align: text-top;
            background: white;
            border: 1px solid #bbb;
            border-radius: 2px;
            box-sizing: border-box;
            z-index: 2;
        }
        .custom-checkbox input[type="checkbox"]:checked + label:after {
            content: '';
            position: absolute;
            left: 6px;
            top: 3px;
            width: 6px;
            height: 11px;
            border: solid #000;
            border-width: 0 3px 3px 0;
            transform: inherit;
            z-index: 3;
            transform: rotateZ(45deg);
        }
        .custom-checkbox input[type="checkbox"]:checked + label:before {
            border-color: #03A9F4;
            background: #03A9F4;
        }
        .custom-checkbox input[type="checkbox"]:checked + label:after {
            border-color: #fff;
        }
        .custom-checkbox input[type="checkbox"]:disabled + label:before {
            color: #b8b8b8;
            cursor: auto;
            box-shadow: none;
            background: #ddd;
        }
        /* Modal styles */
        .modal .modal-dialog {
            max-width: 400px;
        }
        .modal .modal-header, .modal .modal-body, .modal .modal-footer {
            padding: 20px 30px;
        }
        .modal .modal-content {
            border-radius: 3px;
        }
        .modal .modal-footer {
            background: #ecf0f1;
            border-radius: 0 0 3px 3px;
        }
        .modal .modal-title {
            display: inline-block;
        }
        .modal .form-control {
            border-radius: 2px;
            box-shadow: none;
            border-color: #dddddd;
        }
        .modal textarea.form-control {
            resize: vertical;
        }
        .modal .btn {
            border-radius: 2px;
            min-width: 100px;
        }
        .modal form label {
            font-weight: normal;
        }
        img{
            width: 90px;
            height: 90px;
        }

        /*For menu*/
        body {
            margin: 0;
            padding: 0;
        }

        header {
            position: fixed;
            background: #22242A;
            padding: 20px;
            width: 100%;
            height: 30px;
        }

        .left_area h3 {
            color: #fff;
            margin: 0;
            text-transform: uppercase;
            font-size: 22px;
            font-weight: 900;
        }

        .left_area span {
            color: #19B3D3;
        }

        .logout_btn {
            padding: 5px;
            background: #19B3D3;
            text-decoration: none;
            float: right;
            margin-top: -30px;
            margin-right: 40px;
            border-radius: 2px;
            font-size: 15px;
            font-weight: 600;
            color: #fff;
            transition: 0.5s;
            transition-property: background;
        }

        .logout_btn:hover {
            background: #0B87A6;
        }

        .sidebar {
            background: #2f323a;
            padding-top: 30px;
            position: fixed;
            left: 0;
            width: 240px;
            height: 100%;
            transition: 0.5s;
            transition-property: left;
        }

        .sidebar .profile_image {
            width: 100px;
            height: 100px;
            border-radius: 100px;
            margin-bottom: 10px;
            margin-right: 40px;
        }

        .sidebar h4 {
            color: #ccc;
            margin-top: 0;
            margin-bottom: 20px;
        }

        .sidebar a {
            color: #fff;
            display: block;
            width: 100%;
            line-height: 60px;
            text-decoration: none;
            padding-left: 40px;
            box-sizing: border-box;
            transition: 0.5s;
            transition-property: background;
        }

        .sidebar a:hover {
            background: #19B3D3;
        }

        .sidebar i {
            padding-right: 10px;
        }

        label #sidebar_btn {
            z-index: 1;
            color: #fff;
            position: fixed;
            cursor: pointer;
            left: 300px;
            font-size: 20px;
            margin: 5px 0;
            transition: 0.5s;
            transition-property: color;
        }

        label #sidebar_btn:hover {
            color: #19B3D3;
        }

        #check:checked~.sidebar {
            left: -190px;
        }

        #check:checked~.sidebar a span {
            display: none;
        }

        #check:checked~.sidebar a {
            font-size: 20px;
            margin-left: 170px;
            width: 80px;
        }

        .content {
            margin-left: 250px;
            background: url(background.png) no-repeat;
            background-position: center;
            background-size: cover;
            height: 100vh;
            transition: 0.5s;
        }

        #check:checked~.content {
            margin-left: 60px;
        }

        #check {
            display: none;
        }

        .list-group {
            list-style: none;
            padding: 0;
        }

        .list-group-item.inactive {
            background-color: #22242A;
            border-color: transparent;
            color: #ccc;
        }

        .list-group-item.active {
            border-color: #fd7e14;
            background-color: #fd7e14;
            color: #ccc;
        }


    </style>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
    <body>
        <%


        %>
        <div class="container">
            <!--Menu bar-->
            <div class="sidebar">
                <ul class="list-group category_block">
                    <li class="list-group-item ${mana != null ? 'active' : 'inactive'}">
                        <a href="/F-Store/Manager/manageProduct?type=view">
                            <i class="fab fa-product-hunt fa-lg" style="color: #ffffff;"></i>
                            <span>Quản lý sản phẩm</span> 
                        </a>
                    </li>
                    <li class="list-group-item  ${ord != null ? 'active' : 'inactive'} active">
                        <a href="/F-Store/Manager/ShowOrder" class="active">
                            <i class="far fa-file-alt fa-lg"></i>
                            <span>Quản lý hóa đơn</span>
                        </a>
                    </li>
                    <li class="list-group-item  ${logout != null ? 'active' : 'inactive'}">
                        <a href="/F-Store/Logout">
                            <i class="fas fa-sign-out-alt fa-lg" style="color: #ffffff;"></i>
                            <span>Đăng xuất</span>
                        </a>
                    </li>
                    <!--For admin-->
                    <!-- <li class="list-group-item ">
                        <a href="">
                            <i class="fas fa-table"></i>
                            <span>Quan Ly Tai Khoan</span>
                        </a>
                    </li> -->
                </ul>
            </div>
            <div class="row">
                <div class="col-md-3">
                    <!--Menu bar-->
                    <div class="sidebar">
                        <ul class="list-group category_block">
                            <li class="list-group-item ${mana != null ? 'active' : 'inactive'}">
                                <a href="/F-Store/Manager/manageProduct?type=view">
                                    <i class="fab fa-product-hunt fa-lg" style="color: #ffffff;"></i>
                                    <span>Quản lý sản phẩm</span> 
                                </a>
                            </li>
                            <li class="list-group-item  ${ord != null ? 'active' : 'inactive'} active">
                                <a href="/F-Store/Manager/ShowOrder" class="active">
                                    <i class="far fa-file-alt fa-lg"></i>
                                    <span>Quản lý hóa đơn</span>
                                </a>
                            </li>
                            <li class="list-group-item  ${mth != null ? 'active' : 'inactive'}">
                                <a href="/F-Store/Manager/monthRevenue">
                                    <i class="far fa-file-alt fa-lg"></i>
                                    <span>Quản lý doanh thu </span>
                                </a>
                            </li>
                            <li class="list-group-item  ${logout != null ? 'active' : 'inactive'}">
                                <a href="">
                                    <i class="fas fa-sign-out-alt fa-lg" style="color: #ffffff;"></i>
                                    <span>Đăng xuất</span>
                                </a>
                            </li>
                            <!--For admin-->
                            <!-- <li class="list-group-item ">
                                <a href="">
                                    <i class="fas fa-table"></i>
                                    <span>Quan Ly Tai Khoan</span>
                                </a>
                            </li> -->
                        </ul>
                    </div>
                </div>

                <div class="col-md-9">
                    <h1 class="text-center">HOA DON</h1>
                    <table id="example">
                        <thead>
                            <tr>
                                <th class="align-middle">NO</th>
                                <th class="align-middle">OrderID</th>
                                <th class="align-middle">Account</th>
                                <th class="align-middle">Date</th>
                                <th class="align-middle">Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="stt" value="0" />
                            <c:forEach items="${listOr}" var="o">
                                <c:set var="stt" value="${stt + 1}" />
                                <tr >
                                    <td class="align-middle"><p>${stt}</p></td>
                                    <td class="align-middle"><p>${o.orderID}</p></td>
                                    <td class="align-middle"><p>${o.userID}</p></td>
                                    <td class="align-middle"><p>${o.purchaseDate}</p></td>
                                    <td class="align-middle"><p><fmt:formatNumber value="${o.totalPrice}" type="number" pattern="###,### vnđ" /></p></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <!--                    <a href="/PRJProject/manager">
                                            <button type="button" class="btn btn-primary bg-primary">Back</button>
                                        </a>-->
                </div>
            </div>
        </div>
    </body>
</html>
