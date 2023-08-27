<%-- 
    Document   : products
    Created on : Aug 9, 2023, 1:01:57 AM
    Author     : nguye
--%>

<%@page import="java.util.List"%>
<%@page import="model.Categories"%>
<%@page import="dal.DAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./css/productsStyle.css">
        <link rel="stylesheet" href="./css/navStyle.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Danny Store</title>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <div class="col-md-2 search">
                    <h2 style="margin-top: 7px; font-size: 35px; font-weight: 600;">Search</h2>
                    <form action="searchProducts" method="get">
                        <select name="categoryKey">
                            <option value="0" hidden>${requestScope.option}</option>
                            <option value="0">All</option>
                            <%  List<Categories> CList = (List<Categories>) request.getAttribute("category");
                                for (Categories ca : CList) {
                            %> 
                            <option value="<%= ca.getID()%>"> <%= ca.getName()%> </option>
                            <%
                                }
                            %>
                        </select>
                        <input type="text" name="describe" placeholder="Search" value="${param.describe}"> 
                        <input type="number" name="minPrice" placeholder="Lowest Price" value="${param.minPrice}"> 
                        <input type="number" name="maxPrice" placeholder="Highest Price" value="${param.maxPrice}"> 
                        <input type="submit" value="Search">
                    </form>

                    <form action="receipt" method="post">
                        <h2 style="margin-top: 40px;margin-bottom: 0; font-size: 18px; font-weight: 600;">Search Receipt</h2>
                        <input type="text" name="searchOID" placeholder="Enter Order ID" value="${param.searchOID}"> 
                        <input type="submit" value="Search">
                    </form>
                </div>
                <div class="col-md-4">
                    <h1>Products</h1>
                    <div class="item2">
                        <c:forEach items="${requestScope.productsList}" var="p">
                            <ul>
                                <li><img src="${p.getImg()}"></li>
                                <li>${p.getName()}</li>
                                <li>${p.getDescribe()}</li>
                                <li>${p.getPrice()} VND</li>
                                <li><button onclick="location.href = './addToCart?id=${p.getId()}'"><i class="fa fa-shopping-cart" style="font-size:24px"></i>Add to cart</button></li>
                            </ul>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
