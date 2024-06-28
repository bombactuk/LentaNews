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

	    <jsp:param name="title" value="About" />

    </jsp:include>

</head>

<body>

    <c:if test="${(sessionScope.user eq null)}">

        <jsp:include page="/WEB-INF/blocks/header.jsp" />

    </c:if>

    <c:if test="${not(sessionScope.user eq null)}">

        <jsp:include page="/WEB-INF/blocks/authorized_user_header.jsp" />

    </c:if>

    <div id="about_us">

        <c:set var="aboutInfo" value="${requestScope.aboutInformation}" />

        <p>${aboutInfo.content}</p>

    </div>

    <c:if test="${(sessionScope.user.role eq 'Admin')}">

        <div id="functionEditAboutUs">

            <button id="btnEditAboutUs"><fmt:message key="about_function_edit"/></button><br><br>

        </div>

        <hr/>

        <div class="error-messageFunction" id="error-messageFunction">

            <c:if test="${not (param.functionError eq null) }">

                <c:if test="${ (param.functionError eq '101') }">

                    <p> <fmt:message key="update_error_function"/> <fmt:message key="about_error_101"/> </p>

                </c:if>

                <c:if test="${ (param.functionError eq '102') }">

                    <p> <fmt:message key="update_error_function"/> <fmt:message key="about_error_102"/> </p>

                </c:if>

            </c:if>

        </div>

        <div id="editAboutUs" class="modal">

            <div id="editAboutUsContent" class="modal-content">

                <span class="close">&times;</span>

                <h2> <fmt:message key="about_title_edit"/> </h2>

                <form action="urlToServlet" method="post">

                    <input type="hidden" name="command" value="about_us_edit"/>

                    <input type="hidden" name="idAbout" value="${aboutInfo.idAbout}"/>

                    <label for="content"> <fmt:message key="about_text_edit"/> </label>
                    <textarea type="text" placeholder="Text" id="content" name="content" required>${aboutInfo.content}</textarea><br><br>

                    <button type="edit"> <fmt:message key="about_button_edit"/> </button>

                </form>

            </div>

        </div>

    </c:if>

    <jsp:include page="/WEB-INF/blocks/footer.jsp" />

    <script>

        var modal = document.getElementById("editAboutUs");

        var btn = document.getElementById("btnEditAboutUs");

        var span = document.getElementsByClassName("close")[0];

        btn.onclick = function() {

            modal.style.display = "block";

        }

        span.onclick = function() {

            modal.style.display = "none";

        }

        window.onclick = function(event) {

            if (event.target == modal) {

                modal.style.display = "none";

            }

        }

    </script>

</body>

</html>