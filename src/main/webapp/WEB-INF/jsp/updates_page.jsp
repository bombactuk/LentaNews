<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>

<head>

    <jsp:include page="/WEB-INF/blocks/head.jsp">

	    <jsp:param name="title" value="Updates" />

    </jsp:include>

</head>

<body>

    <c:if test="${(sessionScope.user eq null)}">

        <jsp:include page="/WEB-INF/blocks/header.jsp" />

    </c:if>

    <c:if test="${not(sessionScope.user eq null)}">

        <jsp:include page="/WEB-INF/blocks/authorized_user_header.jsp" />

    </c:if>

    <div id="update" class="update">

        <c:forEach var="update" items="${requestScope.updates}">

    	    <h2>${update.title}</h2>

    	    <p>${update.content}</p>

            <p>${update.date}</p>

    	    <hr id="divider">

    	</c:forEach>

    </div>

    <jsp:include page="/WEB-INF/blocks/footer.jsp" />

</body>

</html>