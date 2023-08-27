<%-- 
    Document   : login.html
    Created on : Aug 8, 2023, 9:16:12 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./css/style.css">
        <link rel="stylesheet" href="./css/navStyle.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Danny Store</title>
    </head>
    <body>
        <nav>
            <div class="logo">Danny Store</div>
            <ul>
                <li onclick="location.href = './home'" style="margin: 0">Home</li>
                <li onclick="location.href = './products'" style="margin: 0">Products</li>
                <li onclick="location.href = './viewCart'" style="margin: 0;"><i class="fa fa-shopping-cart" style="font-size:24px"></i></li>
                 <li style="margin: 0" onclick="location.href = './userinfo'"><i class="fa fa-user" style="font-size:24px"></i> ${sessionScope.account.getUsername()} </li>
            </ul>
        </nav>
        <div class="container">
            <div class="logincontainer">
                <h3>Login Form</h3>
                <form action="login" method="post">
                    <div class="input"> 
                        <input type="text" name="username" required>
                        <label for="email">Username</label>
                    </div>
                    <div class="input"> 
                        <input type="password" name="password" required>
                        <label for="password">Password</label>
                    </div>
                    <div class="invalidMsg">${requestScope.error}</div>
                    <input type="submit" name="action" value="LOGIN">
                    <a href="register" style="color: #FFCB74; line-height: 30px;">Don't have account yet? click to Register</a>
                </form>
            </div>
        </div>
    </body>
</html>
