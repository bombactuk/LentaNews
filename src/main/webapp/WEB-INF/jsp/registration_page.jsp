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
			    <input type="text" class="form-control" placeholder="Email address" id="username" name="username" required>
			</div>

			<div class="form-group">
	 			<input type="password" class="form-control" placeholder="Password" id="password" name="password" required>
			</div>

			<div class="form-group">
				<input type="text" class="form-control" placeholder="Name" id="name" name="name" required>
			</div>

			<div class="form-group">
				<input type="date" class="form-control" placeholder="Date of Birth" id="dob" name="dob" required>
			</div>

			<div class="form-group">

			    <select class="form-control" id="country" placeholder="Country of Residence" name="country" required>

					<option value="">Choose the country</option>
					<option value="russia">Russia</option>
					<option value="usa">USA</option>
					<option value="germany">Germany</option>
					<option value="france">France</option>
					<option value="belarus">Belarus</option>

				</select>

			</div>

			<button id="btnRegistration" type="submit" class="btn btn-primary">Register</button>

		</form>

	</div>

    <jsp:include page="/WEB-INF/blocks/footer.jsp" />

</body>

</html>