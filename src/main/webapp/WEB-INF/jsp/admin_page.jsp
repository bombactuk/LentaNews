<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>

<head>

    <jsp:include page="/WEB-INF/blocks/head.jsp">

	    <jsp:param name="title" value="Profile Admin" />

    </jsp:include>

</head>

<body>

    <jsp:include page="/WEB-INF/blocks/authorized_user_header.jsp" />

    <div id="functionAdminProfile">

        <a href="urlToServlet?command=user_logout">Выйти с аккаунта</a>
        <a href="urlToServlet?command=user_token_reset">Обнулить токен пользователей</a>

    </div>

    <div class="error-messageFunction" id="error-messageFunction">

        <c:if test="${not (param.functionError eq null) }">

            <p> <c:out value="${param.functionError}" /> </p>

        </c:if>

    </div>

    <jsp:include page="/WEB-INF/blocks/footer.jsp" />

</body>

</html>