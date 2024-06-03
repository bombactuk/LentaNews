<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>

<head>

    <jsp:include page="/WEB-INF/blocks/head.jsp">

	    <jsp:param name="title" value="Authorization" />

    </jsp:include>

</head>

<body>

    <c:if test="${(sessionScope.user eq null)}">

        <jsp:include page="/WEB-INF/blocks/header.jsp" />

    </c:if>

    <c:if test="${not(sessionScope.user eq null)}">

        <jsp:include page="/WEB-INF/blocks/authorized_user_header.jsp" />

    </c:if>

    <div id="container" class="container">

		<form id="form-signin" class="form-signin" action="urlToServlet" method="post">

			<input type="hidden" name="command" value="user_authorization" />

			<h2 class="form-signin-heading text-java text-center">User<span id=logoAuthor>Authorization</span></h2>

		    <div class="auth-message" id="auth-message">

			    <c:if test="${not (param.authMessage eq null) }">

                    <c:out value="${param.authMessage}" />

			    </c:if>

			    <input type="email" id="inputEmail" class="form-control" placeholder="Email адрес" name="login" required autofocus>

			    <input type="password" id="inputPassword" class="form-control" placeholder="Пароль" name="password" required>

                <label id="remember_me">Запомнить меня</label>

                <input type="checkbox" value="remember-me" name="remember-me">

			    <button id="btn" class="btn btn-lg btn-success btn-block" type="submit">Войти</button>

			    <div class="text-center mt-2">

				    <a href="urlToServlet?command=go_to_registration_page">Регистрация нового аккаунта</a>

			    </div>

		</form>

	</div>

    <jsp:include page="/WEB-INF/blocks/footer.jsp" />

</body>

</html>