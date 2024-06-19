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

	    <jsp:param name="title" value="Profile Admin" />

    </jsp:include>

</head>

<body>

    <jsp:include page="/WEB-INF/blocks/authorized_user_header.jsp" />

    <div id="functionAdminProfile">

        <a href="urlToServlet?command=user_logout"> <fmt:message key="admin.button_exit"/> </a>
        <a href="urlToServlet?command=user_token_reset"> <fmt:message key="admin.button_reset"/> </a><br><br>

        <div id="functionAddContact">

            <button id="btnAddContact"> <fmt:message key="admin.button_add"/> </button><br><br>

        </div>

    </div>

    <hr/>

    <div class="error-messageFunction" id="error-messageFunction">

        <c:if test="${not (param.functionError eq null) }">

            <c:if test="${ (param.functionError eq '103') }">

                <p> <fmt:message key="admin_error_103"/> </p>

            </c:if>

            <c:if test="${ (param.functionError eq '104') }">

                <p> <fmt:message key="admin_error_104"/> </p>

            </c:if>

            <c:if test="${ (param.functionError eq '105') }">

                <p> <fmt:message key="admin_error_105"/> </p>

            </c:if>

            <c:if test="${ (param.functionError eq '106') }">

                <p> <fmt:message key="admin_error_106"/> </p>

            </c:if>

        </c:if>

    </div>

    <div id="addContact" class="modal">

        <div id="addContactContent" class="modal-content">

            <span class="close1">&times;</span>

            <h2> <fmt:message key="admin.button_add"/> </h2>

            <form action="urlToServlet" method="post">

                <input type="hidden" name="command" value="contact_add"/>

                <input type="hidden" name="idAdmin" value="${sessionScope.user.idUser}"/>

                <label for="title"> <fmt:message key="admin.text.img"/> </label>
                <input type="text" placeholder="Image" id="imgContact" name="img" required><br><br>

                <label for="content"> <fmt:message key="admin.text.link"/> </label>
                <input type="text" placeholder="Link" id="linkContact" name="link" required><br><br>

                <button type="add"> <fmt:message key="admin.button.add.contact"/> </button>

            </form>

        </div>

    </div>

    <jsp:include page="/WEB-INF/blocks/footer.jsp" />

    <script>

        var modal1 = document.getElementById("addContact");

        var btn1 = document.getElementById("btnAddContact");

        var span1 = document.getElementsByClassName("close1")[0];

        btn1.onclick = function() {

            modal1.style.display = "block";

        }

        span1.onclick = function() {

            modal1.style.display = "none";

        }

        window.onclick = function(event) {

            if (event.target == modal1) {

                modal1.style.display = "none";

            }

        }

    </script>

</body>

</html>