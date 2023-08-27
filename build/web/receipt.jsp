<%-- 
    Document   : receipt
    Created on : Aug 17, 2023, 2:13:05 AM
    Author     : nguye
--%>

<%@page import="model.Item"%>
<%@page import="java.util.List"%>
<%@page import="model.Orders"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./css/productsStyle.css">
        <link rel="stylesheet" href="./css/navStyle.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title>Danny Store</title>
    </head>
    <body style="font-family: 'Times New Roman'">

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
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-2">
            <form action="receipt" method="post">
                <input type="text" name="searchOID" placeholder="Enter Order ID" value="${param.searchOID}"> 
                <input type="submit" value="Search">
                <div style="color:red; padding: 3px 0 0 0; margin: 0;">${requestScope.err}</div>
            </form>
        </div>
        <div class="col-md-1"></div>
        <div class="col-md-6" style="text-align: center;">
            <div style="border-bottom: 1px gray solid; padding-bottom: 12px; margin: 8px 0 12px 0;"><div style="font-weight: 600; font-size: 20px;">RECEIPT DETAILS</div>${requestScope.order.getOID()}</div>
            <%
                List<Item> orderItems = (List<Item>) request.getAttribute("orderItems");

                if (orderItems == null || orderItems.isEmpty()) {

                } else {
                    for (Item i : orderItems) {
            %>
            <ul style="list-style: none; display: flex; justify-content: space-between; align-items: center; border-bottom: 1px solid gray; padding-bottom: 17px;">
                <li><img src="<%= i.getProduct().getImg()%>" style="width: 90px; height: 90px; border-radius: 5px;"></li>
                <li style="font-weight: 600;"><%= i.getProduct().getName()%></li>
                <li><%= i.getProduct().getPrice()%> x <%= i.getQuantity()%></li>
                <li><%= i.getProduct().getPrice() * i.getQuantity()%></li>
            </ul>
            <%
                    }
                }
            %>
            <div style="display: flex; justify-content: space-between; font-weight: 600; font-size: 17px;"><div>TOTAL</div> <div>${requestScope.order.getTotalMoney()}</div></div>
            <div style="display: flex; justify-content: space-between;  font-size: 15px; margin-top: 12px;"><div>Order Date</div><div> ${requestScope.order.getOrderDate()}</div></div>
            <div style="display: flex; justify-content: space-between;  font-size: 15px;"><div>Username</div><div> ${requestScope.order.getUsername()}</div></div>
            <div style="display: flex; justify-content: space-between;  font-size: 15px;"><div>Email</div><div> ${requestScope.user.getEmail()}</div></div>
        </div>

    </div>
</body>
</html>
