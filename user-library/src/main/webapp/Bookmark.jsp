<html lang="en">
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=utf-8" %>

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
        <section style ="display: flex" class="control">
            <div class="container">
                <div class="control-buttons__operation">

                </div>
            </div>

            <div class="result" style="margin: auto;">
                <c:choose>
                    <c:when test="${!empty result}">
                        <div class"table" style="margin: auto;width: 20em;">
                            <h4>Result: ${result}</h4>
                        </div>
                    </c:when>
                </c:choose>
            </div>
        </section>
        <section>
            <div style="height: 37em;overflow:  auto;">
                <table class="table" border=1 style="width: 100%; margin-top: 20px;text-align: center;">
                    <thead>
                        <tr>
                            <th>Book id</th>
                            <th>Page number</th>
                            <th style="width: 20em;">Ability</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="bookmark" items="${bookmarklist}">
                            <tr>
                                <td>
                                    <c:out value="${bookmark.bookId}" />
                                </td>
                                <td>
                                    <c:out value="${bookmark.pageNumber}" />
                                </td>
                                <td style="display: flex; width: 20em;">
                                     <form style="margin: auto;" method="POST" action="<%=request.getContextPath()%>/library/bookmark/deletebookmark" >
                                        <div>
                                            <input style="display: none" type="number" name="bookmarkId" value="${bookmark.bookmarkId}" />
                                            <input  class="submit-button" type="submit" value="Delete bookmark" />
                                        </div>
                                     </form>
                                <form style="margin: auto;" method="GET" action="<%=request.getContextPath()%>/library/bookmark/editingbookmark" >
                                    <div>
                                        <input style="display: none" type="number" name="bookmarkId" value="${bookmark.bookmarkId}" />
                                        <input  class="submit-button" type="submit" value="Editing bookmark" />
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

</html>