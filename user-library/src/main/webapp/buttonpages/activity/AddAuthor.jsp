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
                <div class="title"><h1>Добавть автора</h1></div>
                <form method="POST" action="<%=request.getContextPath()%>/library/author/addauthor">
                    <div class="form-style" >
                        <div style="width: 16em;">
                            <span>Author name</span>
                            <input type="text" name="authorName" value="" />
                        </div>
                        <div style="width: 16em;">
                            <span>Author second name</span>
                            <input type="text" name="secondName" value="" />
                        </div>

                        <div style="width: 16em;">
                            <span>Author last name</span>
                            <input type="text" name="lastName" value="" />
                        </div>

                        <div style="width: 16em;">
                            <span>Author day of birth</span>
                            <input type="date" name="dob" value="" />
                        </div>
                        <div style="width: 16em;">
                            <input class="submit-button" type="submit" value="Add Author" />
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