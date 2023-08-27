<%-- 
    Document   : userinfo
    Created on : Aug 8, 2023, 10:08:23 PM
    Author     : nguye
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.Categories"%>
<%@page import="dal.DAO"%>
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
        <div class="container-fluid ">
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
                                <h1 style="margin-top: 7px;">Edit Infomation</h1>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <form action="editUserInfo" method="post">
                                        <div class="row addProductsForm">
                                            <div class="col-md-1">
                                                <inputProducts>Username</inputProducts>
                                                <inputProducts>Name</inputProducts>
                                                <inputProducts>Email</inputProducts>
                                                <inputProducts>Balance</inputProducts>
                                                <inputProducts>Password</inputProducts>
                                            </div>
                                            <div class="col-md-4">
                                                <c:set var="p" value="${sessionScope.account}"/>
                                                <input type="text" name="username" value="${p.getUsername()}" required readonly style="border: none; background-color: #F6F6F6;">
                                                <input type="text" name="name" value="${p.getName()}" required style="margin-top: 11px;">
                                                <input type="text" name="email" value="${p.getEmail()}" required style="margin-top: 7px;">
                                                <input type="text" name="balance" value="${p.getBalance()}" required readonly style="border: none; background-color: #F6F6F6;">
                                                <input type="text" name="password" value="${p.getPassword()}" required>
                                                <div class="col-md-7 status" style="color:red; width: 100%; margin-top: 5px;">${requestScope.errMsg}</div>
                                                <input type="submit" value="SAVE" style="margin-top: -3px;">

                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="col-1"></div>
                    </div>
                </div>

            </div>
    </body>
</html>
