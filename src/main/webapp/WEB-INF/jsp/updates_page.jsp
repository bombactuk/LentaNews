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

    <c:if test="${(sessionScope.user.role eq 'Admin')}">

        <div id="functionAddUpdate">

            <button id="btnAddUpdate"> <fmt:message key="update_button_add"/> </button><br><br>

        </div>

        <hr/>

        <div class="error-messageFunction" id="error-messageFunction">

            <c:if test="${not (param.functionError eq null) }">

                <c:if test="${ (param.functionError eq '123') }">

                    <p> <fmt:message key="update_error_123"/> </p>

                </c:if>

                <c:if test="${ (param.functionError eq '124') }">

                    <p> <fmt:message key="update_error_124"/> </p>

                </c:if>

            </c:if>

        </div>

        <div id="addUpdate" class="modal">

            <div id="addUpdateContent" class="modal-content">

                <span class="close">&times;</span>

                <h2> <fmt:message key="update_button_add"/> </h2>

                <form action="urlToServlet" method="post">

                    <input type="hidden" name="command" value="update_add"/>

                    <input type="hidden" name="idAdmin" value="${sessionScope.user.idUser}"/>

                    <label for="title"> <fmt:message key="update_text_title"/> </label>
                    <input type="text" placeholder="heading" id="titleUpdate" name="title" required><br><br>

                    <label for="content"> <fmt:message key="update_text"/> </label>
                    <textarea type="text" placeholder="Text" id="content" name="content" required></textarea><br><br>

                    <button type="add"> <fmt:message key="update_button_add_update"/> </button>

                </form>

            </div>

        </div>

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

    <script>

        var modal = document.getElementById("addUpdate");

        var btn = document.getElementById("btnAddUpdate");

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