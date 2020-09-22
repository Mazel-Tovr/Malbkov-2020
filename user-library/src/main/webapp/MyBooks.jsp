<html lang="en">
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import = "com.epam.library.model.User"  %>
<%@ page import = "com.epam.library.model.EnumRole"  %>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/LibraryStyle.css">
</head>
<body>
    <div class="container">
    <header class="header">
            <h1 class="header-title">Библиотека</h1>
            <ul class="header-list">
                <li><a href="<%=request.getContextPath()%>/library">Главная</a></li>
                <li><a href= "<%=request.getContextPath()%>/library/author"/>Авторы</a></li>
                <li><a href="<%=request.getContextPath()%>/library/bookmark">Мои Закладки</a></li>
                <li><a href="<%=request.getContextPath()%>/library/mybooks">Мои книги</a></li>
                <%  User user = (User)request.getSession().getAttribute("user");
                if(user.getRole().getType().equals(EnumRole.Admin))
                {
                %>
                    <li><a href="<%=request.getContextPath()%>/library/admin">Администрирование</a></li>
                    <%
                }
                %>
            </ul>
            <div class="header-profile">
                <img src="<%=request.getContextPath()%>/images/profile.png" alt="">
                <span>You: ${user.nickName}</span>
                 <span><a href="<%=request.getContextPath()%>/logout"><h3>Logout</h3></a></span>
            </div>
    </header>

    <section>
        <div class="result">
            <c:choose>
                <c:when test="${!empty result}">
                    <div class"table" style="margin: auto;width: 20em;">
                        <h4>Result: ${result}</h4>
                    </div>
                </c:when>
            </c:choose>
        </div>
        <div style="height: 37em;overflow: auto;">
            <table class="table" border=1 style="width: 100%; margin-top: 20px;text-align: center;">
                <thead>
                    <tr>
                        <th>Book id</th>
                        <th>Book name</th>
                        <th>Release Year</th>
                        <th>Page Count</th>
                        <th>ISBN</th>
                        <th>Publisher</th>
                        <th>Author id</th>
                        <th>Ability</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="book" items="${booklist}" >
                        <tr>
                            <td><c:out value="${book.bookId}" /></td>
                            <td><c:out value="${book.bookName}" /></td>
                            <td><c:out value="${book.releaseYear}" /></td>
                            <td><c:out value="${book.pageCount}" /></td>
                            <td><c:out value="${book.ISBN}" /></td>
                            <td><c:out value="${book.publisher}" /></td>
                            <td><c:out value="${book.author.authorId}" /></td>
                            <td class="buttons-size">
                                <c:choose>
                                    <c:when test="${user.role.type == 'Admin'}">
                                        <form style="margin: auto;" method="POST" action="<%=request.getContextPath()%>/library/deletebook" >
                                            <div>
                                                <input style="display: none" type="number" name="bookId" value="${book.bookId}" />
                                                <input  class="submit-button" type="submit" value="Delete book" />
                                            </div>
                                        </form>
                                        <form style="margin: auto;" method="GET" action="<%=request.getContextPath()%>/library/editingbook" >
                                            <div>
                                                <input style="display: none" type="number" name="bookId" value="${book.bookId}" />
                                                <input  class="submit-button" type="submit" value="Editing book" />
                                            </div>
                                        </form>
                                     </c:when>
                                </c:choose>
                                <form style="margin: auto;" method="POST" action="<%=request.getContextPath()%>/library/returnbook" >
                                    <div>
                                        <input style="display: none" type="number" name="bookId" value="${book.bookId}" />
                                        <input  class="submit-button" type="submit" value="Return book" />
                                    </div>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
             </table>
        </div>

    </section>

    </body>
</body>
</html>
