<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>

<head>

    <jsp:include page="/WEB-INF/blocks/head.jsp">

	    <jsp:param name="title" value="Profile" />

    </jsp:include>

</head>

<body>

    <jsp:include page="/WEB-INF/blocks/authorized_user_header.jsp" />

    <div id="userInfo">

        <c:set var="infoUser" value="${requestScope.infoUser}" />

        <h2> Имя: ${infoUser.name} </h2>
        <p >День Рождение: ${infoUser.birthday} </p>
        <p> Страна: ${infoUser.country} </p>
        <a href="urlToServlet?command=user_logout">Выйти с аккаунта</a>

	</div>

    <jsp:include page="/WEB-INF/blocks/footer.jsp" />

</body>

</html>