<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages" />

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <style><%@include file="/WEB-INF/main.css"%></style>
    <title><fmt:message key="title.title"/></title>
</head>
<body>

<form>
    <select id="language" name="language" onchange="submit()">
        <option value="uk_UA" ${language == 'uk_UA' ? 'selected' : ''}>Українська</option>
        <option value="en_EN" ${language == 'en_EN' ? 'selected' : ''}>English</option>
    </select>
</form>

<h1><fmt:message key="title.hello"/></h1>
<h3><fmt:message key="title.choice"/></h3>

<div>
    <table>
        <tr><button onclick="window.location.href = '/login'"><fmt:message key="index.user"/></button></tr>
        <tr><button onclick="window.location.href = '/registration'"><fmt:message key="index.register"/></button></tr>
        <tr><button onclick="window.location.href = '/admin/login'"><fmt:message key="index.admin"/></button></tr>
    </table>
</div>

</body>
</html>
