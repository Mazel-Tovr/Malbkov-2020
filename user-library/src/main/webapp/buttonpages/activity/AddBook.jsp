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
                <div class="title"><h1>Добавть книгу</h1></div>
                <form method="POST" action="addbook">
                    <div class="form-style">
                        <div>
                            <span>Book name</span>
                            <input type="text" name="bookName" value="" />
                        </div>
                        <div>
                            <span>Release Year</span>
                            <input type="number" name="releaseYear" value="" />
                        </div>

                        <div>
                            <span>Page Count</span>
                            <input type="number" name="pageCount" value="" />
                        </div>

                        <div>
                            <span>ISBN</span>
                            <input type="text" name="bookISBN" value="" />
                        </div>

                        <div>
                            <span>Publisher</span>
                            <input type="text" name="publisher" value="" />
                        </div>

                        <div>
                            <span>Author Id</span>
                            <input type="number" name="authorId" value="" />
                        </div>

                        <div >
                            <input class="submit-button" type="submit" value="Add Book" />
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