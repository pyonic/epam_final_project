<%@ page contentType="text/html" language="java" %>
<%@ page import="com.epam.murodil.controller.command.CommandType" %>
<html>
<head>
    <title>Index</title>
</head>
<body>
<jsp:forward page="pharmacy?query=${CommandType.DEFAULT}"/>
</body>
</html>