<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="messages" />

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

		<h2> <fmt:message key="registration_text_user"/> <span id="logoRegistr"> <fmt:message key="registration_text_registration"/> </span></h2>

		<form action="urlToServlet" method="post">

		    <input type="hidden" name="command" value="user_registration"/>

		    <div class="error-message" id="error-message">

                <c:if test="${not (param.regError eq null) }">

                    <c:if test="${ (param.regError eq '119') }">

                        <p> <fmt:message key="update_error_function"/> <fmt:message key="registration_error_119"/> </p>

                    </c:if>

                    <c:if test="${ (param.regError eq '120') }">

                        <p> <fmt:message key="update_error_function"/> <fmt:message key="registration_error_120"/> </p>

                    </c:if>

                    <c:if test="${ (param.regError eq '121') }">

                        <p> <fmt:message key="update_error_function"/> <fmt:message key="registration_error_121"/> </p>

                    </c:if>

                    <c:if test="${ (param.regError eq '122') }">

                        <p> <fmt:message key="update_error_function"/> <fmt:message key="registration_error_122"/> </p>

                    </c:if>

                    <c:if test="${ (param.regError eq '123') }">

                        <p> <fmt:message key="update_error_function"/> <fmt:message key="registration_error_123"/> </p>

                    </c:if>


                </c:if>

            </div>

			<div class="form-group">
			    <input type="text" class="form-control" placeholder="<fmt:message key="registration_text_email"/>" id="username" name="username" required>
			</div>

			<div class="form-group">
	 			<input type="password" class="form-control" placeholder="<fmt:message key="registration_text_password"/>" id="password" name="password" required>
			</div>

			<div class="form-group">
				<input type="text" class="form-control" placeholder="<fmt:message key="registration_text_name"/>" id="name" name="name" required>
			</div>

			<div class="form-group">
				<input type="date" class="form-control" placeholder="<fmt:message key="registration_text_birth"/>" id="dob" name="dob" required>
			</div>

			<div class="form-group">

			    <select class="form-control" id="country" placeholder="<fmt:message key="registration_text_country"/>" name="country" required>

					<option value=""> <fmt:message key="registration_text_choose"/> </option>
					<option value="russia"> <fmt:message key="registration_text_russia"/> </option>
					<option value="usa"> <fmt:message key="registration_text_usa"/> </option>
					<option value="germany"> <fmt:message key="registration_text_germany"/> </option>
					<option value="france"> <fmt:message key="registration_text_france"/> </option>
					<option value="belarus"> <fmt:message key="registration_text_belarus"/> </option>

				</select>

			</div>

			<button id="btnRegistration" type="submit" class="btn btn-primary"> <fmt:message key="registration_button_register"/> </button>

		</form>

	</div>

    <jsp:include page="/WEB-INF/blocks/footer.jsp" />

</body>

</html>