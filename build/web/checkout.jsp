<%-- 
    Document   : checkout
    Created on : Aug 16, 2023, 4:02:17 PM
    Author     : nguye
--%>

<%@page import="model.Item"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./css/checkoutStyle.css">
        <link rel="stylesheet" href="./css/navStyle.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title>Danny Store</title>
    </head>
    <body>
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
        <div class="row ctn">
            <div class="col-md-1"></div>
            <div class="col-md-6"><h3>YOUR ORDER</h3>
                <div class="shoppingcart">
                    <%
                        List<Item> cart = (List<Item>) session.getAttribute("cart");
                        int totalMoney = 0, products = 0;
                        if (cart == null || cart.isEmpty()) {
                    %>
                    <h5 style="margin-left: 39%;">There is no product</h5>
                    <%
                    } else {
                        for (Item i : cart) {
                            totalMoney = totalMoney + i.getProduct().getPrice();
                            products++;
                    %>
                    <div class="ProductContainer">
                        <img src="<%= i.getProduct().getImg()%>">
                        <form action="updateCart" class="product" method="post">
                            <div><%= i.getProduct().getName()%></div>
                            <div><%= i.getQuantity()%> x <%= i.getProduct().getPrice()%> VND</div>
                            <div><%= i.getProduct().getPrice() * i.getQuantity()%> VND</div>
                        </form>
                    </div>
                    <%
                            }
                        }
                    %>
                </div>
            </div>
            <div class="col-md-3 summary">
                <div class="headOrder">ORDER SUMMARY</div>
                <div class="infoContainer">
                    <div>Your balance</div><div> ${sessionScope.account.getBalance()}</div>
                </div>
                <div class="infoContainer">
                    <div>Products</div><div> ${requestScope.cartSize}</div>
                </div>
                <div class="infoContainer" style="font-weight: 200; font-size: 15px; margin-top: 0;">
                    <div>Delivery</div><div> Free</div>
                </div>
                <div class="infoContainer" style="font-weight: 600; font-size: 16px; margin-top: 12px;">
                    <div>Total</div><div> ${requestScope.totalMoney}</div>

                </div>
                    <%
                        session.setAttribute("totalMoney", totalMoney);
                    %>
                <a href="checkOrder" class="orderBTN"><div>ORDER</div><i class="fa fa-arrow-right"></i></a>
                <div style="color: red; font-family: 'Times New Roman'; padding: 8px 0 0 4px;">${requestScope.err}</div>
            </div>
        </div>
    </body>
</html>
