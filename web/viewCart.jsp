<%-- 
    Document   : viewCart
    Created on : Aug 13, 2023, 6:13:06 PM
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
        <link rel="stylesheet" href="./css/navStyle.css">
        <link rel="stylesheet" href="./css/cartStyle.css">
        <link rel="stylesheet" href="./css/checkoutStyle.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title>Danny Store</title>
    </head>
    <body>
        <script>
            function askDeleteItem(id, name) {
                if (confirm("Do you want to remove " + name + " from your cart?")) {
                    window.location = "deleteFromCart?id=" + id;
                }
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
            <h1 style="text-align: center; width: 100%;">CART</h1>
            <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-11">
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
                                <div><%= i.getProduct().getPrice()%> VND</div>
                                <input name="id" value="<%= i.getId()%>" hidden>
                                <div><input type="number" min="0" name="quantity" value="<%= i.getQuantity()%>" required></div>
                                <div><%= i.getProduct().getPrice() * i.getQuantity()%> VND</div>
                                <div class="buttonaction">
                                    <input type="submit" value="Update">
                                    <a class="delete" onclick="askDeleteItem('<%= i.getId()%>', '<%= i.getProduct().getName()%>')">Delete</a>
                                </div>
                            </form>
                        </div>
                        <%
                                }
                            }
                        %>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-9"></div>
                <div class="col-md-3">
                    <div class="checkoutBtn" onclick="location.href = './checkout'"><div class="co">CHECK OUT</div> <i class="fa fa-arrow-right"></i></div>
                    <div style="color:red; font-family: 'Times New Roman'; font-size: 14px;">${requestScope.err}</div>
                </div>  
            </div>
        </div>
    </body>
</html>
