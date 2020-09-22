
<html lang="en">
  <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
   <%@ page isELIgnored="false" %>
   <%@ page contentType="text/html;charset=utf-8" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="SignUpStyle.css">
</head>

<body class="registration-form">
    <h1 class="title">Registration</h1>
    <form class="form" method="POST" action="signup">
        
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
         <input class="button" type="submit"  value="Sign Up"/> 
         
         <input class="button" type="button" value="Go back" onclick="location.href='<%=request.getContextPath()%>'"/>
        </div>
    </form>
        <c:choose>
            <c:when test="${!empty result}">
                <div class"table" style="margin: auto; width: 100%;">
                    <h3>Result: ${result} </h3>
                </div>
            </c:when>
        </c:choose>
</body>

</html>