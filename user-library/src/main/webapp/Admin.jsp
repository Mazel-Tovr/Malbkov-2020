<html lang="en">
<head>
   <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
   <%@ page isELIgnored="false" %>
   <%@ page contentType="text/html;charset=utf-8" %>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/LibraryStyle.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/SignUpStyle.css">
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
    <section class="control">
        <div class="container">
            <div class="control-buttons__operation">
                <h2>Действия:</h2>
                <button onclick = "location.href='<%=request.getContextPath()%>/library/admin/register'">Зарегистрировать нового пользователя</button>
                <button onclick = "location.href='<%=request.getContextPath()%>/library/admin/block'">Заблокировать пользователя</button>
                <button onclick = "location.href='<%=request.getContextPath()%>/library/admin/history'">Смотреть историю действий пользователей</button>
            </div>
        </div>
    </section>
    <c:choose>
    <c:when test="${logs == 'show'}">
     <section>
                <div style="height: 37em;overflow: auto;">
                    <table class="table" border=1 style="width: 100%; margin-top: 20px;text-align: center;">
                        <thead>
                            <tr>
                                <th>Log Id</th>
                                <th>User Id</th>
                                <th>Text</th>    
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="log" items="${loglist}">
                                <tr>
                                    <td>
                                        <c:out value="${log.logId}" />
                                    </td>
                                    <td>
                                        <c:out value="${log.userId}" />
                                    </td>
                                    <td>
                                        <c:out value="${log.text}" />
                                    </td>    
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </section>
        </c:when>
        <c:when test="${adduser =='show'}">
            <section>
                <form class="form" method="POST" action="<%=request.getContextPath()%>/library/admin/register" style="margin: auto;width: 30%;">
                    <div>
                       <span>Your first name</span>
                        <input type="text" name="firstname" value="" />
                    </div>
            
                    <div>
                        <span>Your last name</span>
                        <input type="text" name="lastname" value="" />
                    </div>
            
                    <div>
                        <span>Nick Name</span>
                         <input type="text" name="nickname" value="" />
                     </div>
            
                     <div>
                        <span>Password</span>
                        <input type="password" name="password" value=""/>
                     </div>
                     <div>
                        <span>Role Id</span>
                        <input type="number" name="roleId" value=""/>
                     </div>
                     <div>
                     <input class="button" type="submit"  value="Sign Up"/> 

                    </div>
                </form>
            </section>
        </c:when>
        <c:when test="${blockuser =='show'}">
            <section>
                <form class="form" method="POST" action="<%=request.getContextPath()%>/library/admin/block" style="margin: auto;width: 30%;">
                    <div>
                       <span>User Id</span>
                        <input type="number" name="userId" value="" />
                    </div>
                    <div>
                        <input class="button" type="submit"  value="blockuser"/> 
                       </div>
                   </form>
            </section>
        </c:when>
        <c:when test="${!empty result}">
            <div class"table" style="margin: auto; width: 50%;">
                <h2>Result: ${result} </h2>
            </div>
        </c:when>
     </c:choose>
    </body>
</body>
</html>
