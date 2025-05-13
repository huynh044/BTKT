<%@ taglib prefix="c" uri="http://jakarta.ee/jsp/jstl/core" %>


<html>
<head><title>Results</title></head>
<body>
    <h2>Login result</h2>
    <c:choose>
        <c:when test="${not empty message}">
            <p><c:out value="${message}" /></p>
        </c:when>
        <c:otherwise>
            <p>Error.</p>
        </c:otherwise>
    </c:choose>
</body>
</html>
