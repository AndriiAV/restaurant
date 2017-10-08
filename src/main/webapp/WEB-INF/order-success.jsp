<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <style><%@include file="/WEB-INF/main.css"%></style>
    <title>Order success</title>
</head>
<body>

<div><h1>Successful order</h1></div>

<button onclick="window.location = '/menu'">Make new order</button>
<form action="/logout" method="post">
    <button class="logout">Logout</button>
</form>

</body>
</html>
