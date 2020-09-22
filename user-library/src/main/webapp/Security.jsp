<!DOCTYPE html>
<html lang="en">
<head>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=utf-8" %>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
<h2>Exception occurred while processing the request</h2>
<p>Type: <%= request.getParameter("exception") %> </p>
<p>Message: <%= request.getParameter("message") %></p>
</body>
</body>
</html>