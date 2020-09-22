<html lang="en">
<head>
   <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
   <%@ page isELIgnored="false" %>
   <%@ page contentType="text/html;charset=utf-8" %>
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
                <span>You: ${user.nickName} </span>
                 <span><a href="<%=request.getContextPath()%>/logout"><h3>Logout</h3></a></span>
            </div>
    </header>
    <section style ="display: flex" class="control">
        <div class="container">
            <div class="control-buttons__operation">
                <h2>Действия:</h2>
                <button onclick = "location.href='<%=request.getContextPath()%>/library/author/addauthor'">Добавить автора</button>
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
                                <th>Author Id</th>
                                <th>Author name</th>
                                <th>Author second name</th>
                                <th>Author last name</th>
                                <th>Author dob</th>
                                <c:choose>
                                    <c:when test="${user.role.type == 'Admin'}">
                                        <th style="width: 19em;">Ability</th>
                                    </c:when>
                                </c:choose>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="author" items="${authorlist}">
                                <tr>
                                    <td>
                                        <c:out value="${author.authorId}" />
                                    </td>
                                    <td>
                                        <c:out value="${author.name}" />
                                    </td>
                                    <td>
                                        <c:out value="${author.secondName}" />
                                    </td>
                                    <td>
                                        <c:out value="${author.lastName}" />
                                    </td>
                                    <td>
                                       <c:out value="${author.dob}" />
                                     </td>
                                    <c:choose>
                                        <c:when test="${user.role.type == 'Admin'}">
                                    <td style="display: flex; width: 19em;">
                                        <form style="margin: auto;" method="POST" action="<%=request.getContextPath()%>/library/author/deleteauthor" >
                                            <div>
                                                <input style="display: none" type="number" name="authorId" value="${author.authorId}" />
                                                <input  class="submit-button" type="submit" value="Delete author" />
                                            </div>
                                        </form>
                                        <form style="margin: auto;" method="GET" action="<%=request.getContextPath()%>/library/author/editingauthor" >
                                            <div>
                                                <input style="display: none" type="number" name="authorId" value="${author.authorId}" />
                                                <input  class="submit-button" type="submit" value="Editing author" />
                                            </div>
                                        </form>
                                    </td>
                                        </c:when>
                                    </c:choose>

                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

            </section>
    </body>
</body>
</html>
