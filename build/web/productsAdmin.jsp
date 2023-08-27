<%-- 
    Document   : userinfo
    Created on : Aug 8, 2023, 10:08:23 PM
    Author     : nguye
--%>

<%@page import="java.util.List"%>
<%@page import="model.Categories"%>
<%@page import="model.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danny Store</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="./css/UserStyle.css"/>
        <link rel="stylesheet" href="./css/navStyle.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body>
        <script>
            function askDeleteProduct(id, name) {
                if (confirm("Do you want to delete this Product? \nID = " + id + "\nName = " + name)) {
                    window.location = "deleteUsers?id=" + id;
                }
            }

            function change() {
                document.getElementById("catForm").submit();
            }
        </script>
        <div class="row">
            <nav>
                <div class="logo" style="padding-left: 10px;">Danny Store</div>
                <ul>
                    <li onclick="location.href = './home'">Home</li>
                    <li onclick="location.href = './products'">Products</li>
                    <li onclick="location.href = './viewCart'"><i class="fa fa-shopping-cart" style="font-size:24px"></i><div style="position: absolute; top: 8px; transform: translateX(20px); background: #FFCB74; color: black; border-radius: 50%; height: 15px; width: 15px; display: flex; justify-content: center; align-items: center; font-size: 14px;">
                            ${sessionScope.cartNumber}</div></li>
                    <li onclick="location.href = './userinfo'"><i class="fa fa-user" style="font-size:24px"></i> ${sessionScope.account.getUsername()} </li>
                </ul>
            </nav>
        </div>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2 sidebar">
                    <div onclick="location.href = './userinfo'">Personal Information</div>
                    <%
                        Users u = (Users) session.getAttribute("account");
                        String role = "";
                        if (u.getRole() == 0) {
                    %>
                    <div onclick="location.href = './productsAdmin'">Edit Products</div>
                    <div onclick="location.href = './categoriesAdmin'">Edit Categories</div>
                    <div onclick="location.href = './usersAdmin'">Edit Users</div>
                    <%
                        }
                    %>
                </div>

                <div class="col-md-10 animationAdd">
                    <div class="row"><div class="col-1"></div>
                        <div class="col-md-10">
                            <div class="row">
                                <h1 style="margin-top: 10px;">Products Admin</h1>
                                <a href="./addProducts" class="addProducts col-md-5">Add Products</a>
                            </div>
                            <div class="row"> 
                                <div class="col-md-10">
                                    <div class="col-md-7 status" style="color:red; width: 100%;">${sessionScope.err}</div>
                                    <div class="col-md-7 status" style="width: 100%;">${sessionScope.msg}</div>
                                </div>
                                <div class="col-md-2">
                                    <form id="catForm" action="productsAdmin" onchange="change()" method="post">
                                        <select name="categoryKey" style="height: 24px; margin-bottom: 5px; border: 2px solid black; padding-left: 6px; border-radius: 14px 2px 14px 2px;">
                                            <option value="0" hidden>${requestScope.option}</option>
                                            <option value="0">All</option>
                                            <c:forEach items="${requestScope.category}" var="ca">
                                                <option value="${ca.getID()}"> ${ca.getName()}</option>
                                            </c:forEach>
                                        </select>
                                    </form>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">

                                    <table style="width: 100%;">
                                        <tr>
                                            <th>ID</th>
                                            <th>Name</th>
                                            <th>Describe</th>
                                            <th>Quantity</th>
                                            <th>Price</th>
                                            <th>Image</th>
                                            <th>CID</th>
                                            <th>Action</th>
                                        </tr>
                                        <c:forEach items="${requestScope.productsList}" var="p">
                                            <tr>
                                                <td>${p.getId()}</td>
                                                <td>${p.getName()}</td>
                                                <td>${p.getDescribe()}</td>
                                                <td>${p.getQuantity()}</td>
                                                <td>${p.getPrice()} VND</td>
                                                <td><img src="${p.getImg()}" style="height: 50px; width:50px;"></td>   
                                                <td>${p.getCid()}</td>
                                                <td>
                                                    <a href="./editProducts?id=${p.getId()}" style="cursor: pointer;">Edit</a>
                                                    <a onclick="askDeleteProduct('${p.getId()}', '${p.getName()}')" style="cursor: pointer;">Delete</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </table>

                                </div>
                            </div>
                        </div>
                        <div class="col-1"></div>
                    </div>
                </div>

            </div>
    </body>
</html>
