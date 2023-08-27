<%-- 
    Document   : home
    Created on : Aug 8, 2023, 9:36:13 PM
    Author     : nguye
--%>

<%@page import="java.util.List"%>
<%@page import="model.Products"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="./css/style.css">
        <link rel="stylesheet" href="./css/navStyle.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Danny Store</title>
    </head>

    <body>
        <div class="container-fluid">
            <div class="row">
                <nav>
                    <div class="logo">Danny Store</div>
                    <ul>
                        <li onclick="location.href = './home'">Home</li>
                        <li onclick="location.href = './products'">Products</li>
                        <li onclick="location.href = './viewCart'"><i class="fa fa-shopping-cart" style="font-size:24px"></i><div style="position: absolute; top: 8px; transform: translateX(20px); background: #FFCB74; color: black; border-radius: 50%; height: 15px; width: 15px; display: flex; justify-content: center; align-items: center; font-size: 14px;">
                                ${sessionScope.cartNumber}</div></li>
                        <li onclick="location.href = './userinfo'"><i class="fa fa-user" style="font-size:24px"></i> ${sessionScope.account.getUsername()} </li>
                    </ul>
                </nav>
            </div>
            <div class="row">
                <div class="col-md-1 blankspace"></div>
                <div class="col-md-10 productContainer">
                    <h1>Adidas</h1>
                    <div class="item">
                        <c:forEach items="${requestScope.adidas}" var="a">
                            <ul>
                                <li><img src="${a.getImg()}"></li>
                                <li>${a.getName()}</li>
                                <li>${a.getDescribe()}</li>
                                <li>${a.getPrice()} VND</li>
                                <li><button onclick="location.href = './addToCart?id=${a.getId()}'"><i class="fa fa-shopping-cart" style="font-size:24px"></i>Add to cart</button></li>
                            </ul>
                        </c:forEach>
                    </div>
                    <h1>Nike</h1>
                    <div class="item">
                        <c:forEach items="${requestScope.nike}" var="a">
                            <ul>
                                <li><img src="${a.getImg()}"></li>
                                <li>${a.getName()}</li>
                                <li>${a.getDescribe()}</li>
                                <li>${a.getPrice()} VND</li>
                                <li><button onclick="location.href = './addToCart?id=${a.getId()}'"><i class="fa fa-shopping-cart" style="font-size:24px"></i>Add to cart</button></li>
                            </ul>
                        </c:forEach>
                    </div>
                    <h1>Puma</h1>
                    <div class="item">
                        <c:forEach items="${requestScope.puma}" var="a">
                            <ul>
                                <li><img src="${a.getImg()}"></li>
                                <li>${a.getName()}</li>
                                <li>${a.getDescribe()}</li>
                                <li>${a.getPrice()} VND</li>
                                <li><button onclick="location.href = './addToCart?id=${a.getId()}'"><i class="fa fa-shopping-cart" style="font-size:24px"></i>Add to cart</button></li>
                            </ul>
                        </c:forEach>
                    </div>
                </div>
                <div class="col-md-1" style="display: flex; align-items: center; justify-content: center; height: 40px; width: auto; background-color: gray; border-radius: 4px 50% 50% 4px; margin: 12px 0 0 0; background-color: #2F2F2F; color: #FFCB74; padding: 4px 10px 8px 10px; font-weight: 600; border: 2px solid #FFCB74;">
                    <a href="./products" style="color: #FFCB74; text-decoration: none;">View more</a></div>
            </div>
        </div>
    </body>
</html>
