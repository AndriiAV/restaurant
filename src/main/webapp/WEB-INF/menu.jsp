<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="UTF-8">
    <style><%@include file="/WEB-INF/main.css"%></style>
    <title>Menu</title>
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

<c:if test="${not empty param.error}">
    <div>
        <h2>ERROR: ${param.error}</h2>
    </div>
</c:if>

<button onclick="window.location = '/cart'">Go to cart</button>

<h1>Our menu</h1>
<table class="table-fill">
    <thead>
    <tr>
        <th class="text-left">Product name</th>
        <th class="text-left">Price</th>
        <%--<th class="text-left">Photo</th>--%>
        <%--<th class="text-left">Count</th>--%>
        <th class="text-left">Add to cart</th>
    </tr>
    </thead>
    <tbody class="table-hover">
    <c:forEach items="${menu}" var="orderItem">
        <tr>
            <td class="text-left">${orderItem.name}</td>
            <td class="text-left">${orderItem.price}</td>
            <%--<td class="text-left">${dish.photoId}</td>--%>
            <%--<td class="text-left"><form name="count"></form></td>--%>
            <td class="text-left">
                <form action="/cart" method="post" name="add">
                    <input name="dishId" value="${orderItem.id}" hidden />
                    <div>
                        Count:
                        <input type="text" name="count" size="5" value="1">
                    </div>
                    <button type="submit" name="add">Add to cart</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
