<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Wojciech
  Date: 02.11.2019
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${chat}" var="text">
    <p>${text}</p>
</c:forEach>
<form method="post"><input type="text" placeholder="wiadomość"><button type="submit">Wyślij</button></form>
</body>
</html>
