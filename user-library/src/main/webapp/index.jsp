<html>
  <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
   <%@ page isELIgnored="false" %>
   <%@ page contentType="text/html;charset=utf-8" %>
<head>
   <meta charset="UTF-8">
   <title>Login</title>
</head>

<body style="font-size: large;">
   <div style="margin: auto;width: 20em;">
      <h3>Login Page</h3>


      <form method="POST"  action="signin">
         <div style="display: flex; flex-direction: column;">
            <div style="margin-top: 8px;">
               <span>User Name</span>
               <input type="text" name="nickname" value="" />
            </div>
            <div style="margin-top: 8px;">
               <span>Password</span>
               <input type="password" name="password" value="" style="margin-left: 12px;" />
            </div>
            <div style="margin-top: 4px; display: flex;justify-content: space-between; margin-top: 8px;">

               <input type="submit" value="Sign In" />
               <input value="Sign up" type="button" onclick="location.href='<%=request.getContextPath()%>/signup'"style="margin-right: 100px;" />
            </div>
         </div>
      </form>
        <c:choose>
            <c:when test="${!empty result}">
                <div class"table" style="margin: auto; width: 100%;">
                    <h3>Result: ${result} </h3>
                </div>
            </c:when>
        </c:choose>
   </div>
</body>