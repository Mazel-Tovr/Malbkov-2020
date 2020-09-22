<html lang="en">
<%@ page import = "java.util.List"  %>
<head>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
   <%@ page isELIgnored="false" %>
   <%@ page contentType="text/html;charset=utf-8"  %>
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
                <li><a href="<%=request.getContextPath()%>/library">Книги</a></li>
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
                <div class="title"><h1>Найти книгу</h1></div>
                <form method="POST" action="<%=request.getContextPath()%>/library/findbyisbn">
                    <div class="form-style">
                        <div>
                            <span>Book ISBN</span>
                            <input type="text" name="bookisbn" value="" />
                        </div>
                        <div >
                            <input class="submit-button" type="submit" value="Find book" />
                        </div>
                    </div>
                </form>
            </div>
        </section>

<c:choose>
   <c:when test="${!empty book}">
           <section>
               <div class="table">
                   <table  border=1 style="width: 100%; margin-top: 20px;text-align: center;">
                       <thead>
                           <tr>
                               <th>Book id</th>
                               <th>Book name</th>
                               <th>Release Year</th>
                               <th>Page Count</th>
                               <th>ISBN</th>
                               <th>Publisher</th>
                               <th>Author id</th>
                           </tr>
                       </thead>
                       <tbody>
                               <tr>
                                   <td><c:out value="${book.bookId}" /></td>
                                   <td><c:out value="${book.bookName}" /></td>
                                   <td><c:out value="${book.releaseYear}" /></td>
                                   <td><c:out value="${book.pageCount}" /></td>
                                   <td><c:out value="${book.ISBN}" /></td>
                                   <td><c:out value="${book.publisher}" /></td>
                                   <td><c:out value="${book.author.authorId}" /></td>
                               </tr>
                       </tbody>
                    </table>
               </div>
           </section>
       </c:when>
    <c:when test="${!empty error}">
          <div class"table" style="margin: auto; width: 50%;">
                <h2>Error: ${error} </h2>
          </div>
    </c:when>
</c:choose>
</body>

</html>