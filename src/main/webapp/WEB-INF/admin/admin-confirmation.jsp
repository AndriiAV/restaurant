<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="UTF-8">
    <style><%@include file="/WEB-INF/main.css"%></style>
    <title>Admin confirmation</title>
</head>
<body>

<c:if test="${not empty sessionScope.currentAdmin}">
    <div class="current-user">
        <h1>Hello, ${sessionScope.currentAdmin.login}</h1>
    </div>
    <form action="/admin/logout" method="post">
        <button class="logout">Logout</button>
    </form>
</c:if>


<h1>List of order requests</h1>
<table class="table-fill">
    <thead>
    <tr>
        <th class="text-left">Order ID</th>
        <th class="text-left">Used id</th>
        <th class="text-left">Date</th>
        <th class="text-left">Amount</th>
        <%--<th class="text-left">Status of confirmation</th>--%>
        <th class="text-left">Action</th>
    </tr>
    </thead>
    <tbody class="table-hover">
    <c:forEach items="${orders}" var="order">
        <tr>
            <td class="text-left">${order.orderId}</td>
            <td class="text-left">${order.userId}</td>
            <td class="text-left">${order.orderDate}</td>
            <td class="text-left">${order.orderAmount}</td>
            <%--<td class="text-left">${order.confirmed}</td>--%>
            <td class="text-left">
                <form action="/admin/confirmation" method="post" name="confirm">
                    <input name="orderId" value="${order.orderId}" hidden />
                    <button type="submit" name="add">Confirm order request</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
