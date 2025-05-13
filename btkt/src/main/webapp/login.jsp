<%@ taglib prefix="c" uri="http://jakarta.ee/jsp/jstl/core" %>


<html>
<head><title>Login</title></head>
<body>
    <h2>Login Form</h2>
    <form action="login" method="post">
        Username: <input type="text" name="username" /><br/>
        Password: <input type="password" name="password" /><br/>
        <input type="submit" value="Login" />
    </form>
    <c:if test="${not empty param.error}">
        <p style="color:red;">Username not blank!</p>
    </c:if>
</body>
</html>
