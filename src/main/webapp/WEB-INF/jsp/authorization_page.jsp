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

        <h2 class="form-signin-heading text-java text-center">

            <fmt:message key="authorization_text_user"/>

                <span id=logoAuthor>

                    <fmt:message key="authorization_text_authorization"/>

                </span>

        </h2>

        <div class="auth-message" id="auth-message">

            <c:if test="${not (param.authMessage eq null) }">

                <c:if test="${ (param.authMessage eq '107') }">

                    <p> <fmt:message key="update_error_function"/> <fmt:message key="authorization_error_107"/> </p>

                </c:if>

                <c:if test="${ (param.authMessage eq '108') }">

                    <p> <fmt:message key="update_error_function"/> <fmt:message key="authorization_error_108"/> </p>

                </c:if>

                <c:if test="${ (param.authMessage eq '109') }">

                    <p> <fmt:message key="update_error_function"/> <fmt:message key="authorization_error_109"/> </p>

                </c:if>

                <c:if test="${ (param.authMessage eq '110') }">

                    <p> <fmt:message key="update_error_function"/> <fmt:message key="authorization_error_110"/> </p>

                </c:if>

            </c:if>

        </div>

		<form id="form-signin" class="form-signin" action="urlToServlet" method="post">

			<input type="hidden" name="command" value="user_authorization" />

			<input type="email" id="inputEmail" class="form-control" placeholder="<fmt:message key="authorization_text_email"/>" name="login" required autofocus>

			<input type="password" id="inputPassword" class="form-control" placeholder="<fmt:message key="authorization_text_password"/>" name="password" required>

            <label id="remember_me"> <fmt:message key="authorization_text_remember"/> </label>

            <input type="checkbox" value="remember-me" name="remember-me">

			<button id="btn" class="btn btn-lg btn-success btn-block" type="submit">

			    <fmt:message key="authorization_text_to"/>

			</button>

		</form>

		<div class="text-center mt-2">

            <a href="urlToServlet?command=go_to_registration_page"> <fmt:message key="authorization_text_registering"/> </a>

        </div>

	</div>

    <jsp:include page="/WEB-INF/blocks/footer.jsp" />

</body>

</html>