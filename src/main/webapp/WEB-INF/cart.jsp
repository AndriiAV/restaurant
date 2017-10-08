<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="UTF-8">
    <style><%@include file="/WEB-INF/main.css"%></style>
    <title>Cart</title>
</head>
<body>

<c:if test="${not empty sessionScope.currentUser}">
    <div class="current-user">
        <h1>Hello, ${sessionScope.currentUser.userName}</h1>
    </div>
    <form action="/logout" method="post">
        <button class="logout">Logout</button>
    </form>
</c:if>

<c:choose>
    <c:when test="${not empty orderItems}">
        <h1>Your cart</h1>
        <table class="table-fill">
            <thead>
            <tr>
                <th class="text-left">Product name</th>
                <th class="text-left">Price</th>
                <th class="text-left">Count</th>
                <th class="text-left">Amount</th>
            </tr>
            </thead>
            <tbody class="table-hover">
            <c:forEach items="${orderItems}" var="orderItem">
                <tr>
                    <td class="text-left">${orderItem.dish.name}</td>
                    <td class="text-left">${orderItem.dish.price}</td>
                    <td class="text-left">${orderItem.count}</td>
                    <td class="text-left">${orderItem.amount}</td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
        <br/>
        <form action="/order" method="post" name="order">
            <button type="submit" name="add">Confirm your order</button>
        </form>
    </c:when>
    <c:otherwise>
        <h1>Your cart is empty</h1>
    </c:otherwise>
</c:choose>

<div><a href="/menu"><button type="submit">Go to menu</button></a></div>

</body>
</html>
