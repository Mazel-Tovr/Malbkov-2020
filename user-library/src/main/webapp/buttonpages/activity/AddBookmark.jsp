<html lang="en">

<head>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
   <%@ page isELIgnored="false" %>
   <%@ page contentType="text/html;charset=utf-8" %>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/buttonpages/Search/style.css">
</head>

<body>
    <div class="container">
        <header class="header">
            <h1 class="header-title">Библиотека</h1>
            <ul class="header-list">
                <li><a href="<%=request.getContextPath()%>/library">Главная</a></li>
                <li><a href="<%=request.getContextPath()%>/library/author">Авторы</a></li>
                <li><a href="<%=request.getContextPath()%>/library/bookmark">Мои Закладки</a></li>
                 <li><a href="<%=request.getContextPath()%>/library/mybooks">Мои книги</a></li>
                <c:choose>
                    <c:when test="${user.role.type == 'Admin'}">
                    <li><a href="<%=request.getContextPath()%>/library/admin">Администрирование</a></li>
                    </c:when>
                </c:choose>
            </ul>
            <div class="header-profile">
                <img src="<%=request.getContextPath()%>/images/profile.png" alt="">
                <span>You: ${user.nickName}</span>
                 <span><a href="<%=request.getContextPath()%>/logout"><h3>Logout</h3></a></span>
            </div>
        </header>
        <section>
            <div class="form">
                <div class="title"><h1>Добавть закладку</h1></div>
                <form method="POST" action="<%=request.getContextPath()%>/library/bookmark/addbookmark">
                    <div class="form-style" >
                        <div style="width: 16em;">
                            <input style="display:none" type="number" name="bookId" value="${book.bookId}" />
                        </div>
                        <div style="width: 16em;">
                            <span>Page number</span>
                            <input type="text" name="pageNumber" value="" />
                        </div>
                        <div style="width: 16em;">
                            <input class="submit-button" type="submit" value="Add BookMark" />
                        </div>
                    </div>
                </form>
            </div>

        </section>
    <section>  
        <c:choose>
            <c:when test="${!empty result}">
                <div class"table" style="margin: auto; width: 50%;">
                    <h2>Result: ${result} </h2>
                </div>
            </c:when>
        </c:choose>    
    <section>

</body>

</html>