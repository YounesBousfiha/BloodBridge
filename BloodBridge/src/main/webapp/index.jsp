<!DOCTYPE html>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="fr">

<%@ include file="WEB-INF/views/shared/header.jsp" %>
<body>
<%@ include file="WEB-INF/views/shared/navbar.jsp" %>

<main>
    <c:choose>
        <c:when test="${not empty content}">
            <jsp:include page="${content}" />
        </c:when>
        <c:otherwise>
            <div>
                <h2>Welcome to BloodBridge!</h2>
                <p>This is your static homepage content. Replace this with your actual content later.</p>
            </div>
        </c:otherwise>
    </c:choose>
</main>

<%@ include file="WEB-INF/views/shared/footer.jsp" %>
</body>
</html>