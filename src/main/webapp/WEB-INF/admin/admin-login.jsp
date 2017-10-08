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

<c:if test="${illegalCredentialsError}">
    <div class="error">
        <fmt:message key="title.loginIllegalCredentialsError"/>
    </div>
</c:if>

<h1><fmt:message key="title.adminHello"/></h1>

<form method="post" action="/admin/login" name="user">
    <div class="container">
        <label><b><fmt:message key="title.login"/></b></label>
        <input type="text" placeholder="<fmt:message key="title.loginForm"/>" name="login" required>
        <label><b><fmt:message key="title.password"/></b></label>
        <input type="password" placeholder="<fmt:message key="title.passwordEnter"/>" name="psw" required>
        <button type="submit"><fmt:message key="title.submit"/></button>
    </div>
</form>
</body>
</html>
