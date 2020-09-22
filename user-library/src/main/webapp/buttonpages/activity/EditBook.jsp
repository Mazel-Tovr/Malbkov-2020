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
                <form method="POST" action="<%=request.getContextPath()%>/library/editingbook">
                    <div class="form-style">
                        <div>
                            <span>Book name</span>
                            <input type="text" name="bookName" value="${book.bookName}" />
                        </div>
                        <div>
                            <span>Release Year</span>
                            <input type="number" name="releaseYear" value="${book.releaseYear}" />
                        </div>

                        <div>
                            <span>Page Count</span>
                            <input type="number" name="pageCount" value="${book.pageCount}" />
                        </div>

                        <div>
                            <span>ISBN</span>
                            <input type="text" name="bookISBN" value="${book.ISBN}" />
                        </div>

                        <div>
                            <span>Publisher</span>
                            <input type="text" name="publisher" value="${book.publisher}" />
                        </div>

                        <div>
                            <span>Author Id</span>
                            <input type="number" name="authorId" value="${book.author.authorId}" />
                             <input style="display:none" type="number" name="bookId" value="${book.bookId}" />
                        </div>
                        <div>
                            <span>Is Taken</span>
                            <input type="checkbox" id="checkboxIsTaken" onchange="fun1()" name="isTaken" value="${book.isTaken}" style="transform: scale(2.0);">
                        </div>
                        <div >
                            <input class="submit-button" type="submit" value="Save changes" />
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

<script>
fun2();

function fun1() {
var chbox;
chbox=document.getElementById('checkboxIsTaken');
    if (chbox.checked) {
       chbox.value = 'true'
    }
    else {
        chbox.value = 'false'
    }
}
function fun2() {
var chbox;
chbox=document.getElementById('checkboxIsTaken');
    if (chbox.value == 'true') {
      chbox.checked = true
    }
    else {
        chbox.checked = false
    }
}
</script>
</html>