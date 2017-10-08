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
<h3><fmt:message key="title.loginEnter"/></h3>

<form method="post" action="/menu" name="user">
    <div class="container">
        <label><b><fmt:message key="title.login"/></b></label>
        <input type="text" placeholder="<fmt:message key="title.loginForm"/>" name="login" required>
        <label><b><fmt:message key="title.password"/></b></label>
        <input type="password" placeholder="<fmt:message key="title.passwordEnter"/>" name="psw" required>
        <button type="submit"><fmt:message key="title.submit"/></button>
    </div>
</form>

<br>
<h3><fmt:message key="title.registrationEnter"/></h3>
<form action="/menu" name="newUser">
    <div class="container">
        <label><b><fmt:message key="title.login"/></b></label>
        <input type="text" placeholder="<fmt:message key="title.loginForm"/>" name="login" required>

        <label><b><fmt:message key="title.userName"/></b></label>
        <input type="text" placeholder="<fmt:message key="title.userNameEnter"/>" name="user_name" required>

        <label><b><fmt:message key="title.phoneNumber"/></b></label>
        <input type="text" placeholder="<fmt:message key="title.phoneNumberEnter"/>" name="phone number" required>

        <label><b><fmt:message key="title.address"/></b></label>
        <input type="text" placeholder="<fmt:message key="title.addressEnter"/>" name="address" required>

        <label><b><fmt:message key="title.password"/></b></label>
        <input type="password" placeholder="<fmt:message key="title.passwordEnter"/>" name="psw" required>

        <div class="clearfix">
            <button type="button" class="cancelbtn"><fmt:message key="title.cancel"/></button>
            <button type="submit" class="signupbtn"><fmt:message key="title.registration"/></button>
        </div>
    </div>
</form>

</body>
</html>
