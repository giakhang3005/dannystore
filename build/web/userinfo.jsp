<%-- 
    Document   : userinfo
    Created on : Aug 8, 2023, 10:08:23 PM
    Author     : nguye
--%>

<%@page import="model.Orders"%>
<%@page import="java.util.List"%>
<%@page import="model.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                    <div class="row"><div class="col-2"></div>
                        <div class="col-md-8 mainContainer">
                            <h1>User Info</h1>
                            <div class="userContainer">
                                <div class="row infoContainer">
                                    <div class="col-md-6">
                                        <div><show style="font-weight: 600;">Username</show> <%= u.getUsername()%></div>
                                        <div><show style="font-weight: 600;">Name</show> <%= u.getName()%></div>
                                        <a href="./editUserInfo" class="userAction">Edit</a>
                                        <a href="./MainController?action=logout" class="userAction">Log out</a>
                                    </div>
                                    <div class="col-md-6">
                                        <div><show style="font-weight: 600;">Email</show> <%= u.getEmail()%></div>
                                        <div><show style="font-weight: 600;">Balance</show> <%= u.getBalance()%></div>

                                    </div>
                                </div>
                                <div style="margin-top: 8px">
                                    <div style="font-weight: 600; font-size: 16px;">Your recent receipt ID: </div>
                                    <ol style="column-count: 2;">
                                        <%
                                            List<Orders> orderList = (List<Orders>) request.getAttribute("orderList");
                                            if (orderList == null || orderList.isEmpty()) {

                                            } else {
                                                for (Orders o : orderList) {
                                        %>
                                        <li onclick="location.href = './receipt?searchOID=<%= o.getOID()%>'"style="cursor: pointer; padding: 3px 0 3px 0;">    <%= o.getOID()%>       </li>
                                            <%      }
                                                }
                                            %>
                                    </ol>
                                </div>
                            </div>
                        </div>
                        <div class="col-2"></div>
                    </div>
                </div>

            </div>
    </body>
</html>
