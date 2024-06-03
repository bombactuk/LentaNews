<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>

<head>

    <jsp:include page="/WEB-INF/blocks/head.jsp">

	    <jsp:param name="title" value="Registration" />

    </jsp:include>

</head>

<body>

    <c:if test="${(sessionScope.user eq null)}">

        <jsp:include page="/WEB-INF/blocks/header.jsp" />

    </c:if>

    <c:if test="${not(sessionScope.user eq null)}">

        <jsp:include page="/WEB-INF/blocks/authorized_user_header.jsp" />

    </c:if>

	<div id="containerRegistr" class="container mt-5">

		<h2>User<span id="logoRegistr">Registration</span></h2>

		<form action="urlToServlet" method="post">

		    <input type="hidden" name="command" value="user_registration"/>

		    <div class="error-message" id="error-message">

                <c:if test="${not (param.regError eq null) }">

                    <c:out value="${param.regError}" />

                </c:if>

            </div>

			<div class="form-group">
			    <input type="text" class="form-control" placeholder="Email адрес" id="username" name="username" required>
			</div>

			<div class="form-group">
	 			<input type="password" class="form-control" placeholder="Пароль" id="password" name="password" required>
			</div>

			<div class="form-group">
				<input type="text" class="form-control" placeholder="Имя" id="name" name="name" required>
			</div>

			<div class="form-group">
				<input type="date" class="form-control" placeholder="Дата рождения" id="dob" name="dob" required>
			</div>

			<div class="form-group">

			    <select class="form-control" id="country" placeholder="Страна Проживания" name="country" required>

					<option value="">Выберите страну</option>
					<option value="russia">Россия</option>
					<option value="usa">США</option>
					<option value="germany">Германия</option>
					<option value="france">Франция</option>
					<option value="belarus">Беларусь</option>
					<!-- Добавьте другие страны по необходимости -->

				</select>

			</div>

			<button id="btnRegistration" type="submit" class="btn btn-primary">Зарегистрироваться</button>

		</form>

	</div>

    <jsp:include page="/WEB-INF/blocks/footer.jsp" />

</body>

</html>