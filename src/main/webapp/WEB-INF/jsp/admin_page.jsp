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

        <a href="urlToServlet?command=user_logout">Log out of your account</a>
        <a href="urlToServlet?command=user_token_reset">Reset user token</a>

    </div>

    <hr/>

    <form action="urlToServlet" method="post">

        <input type="hidden" name="command" value="admin_function_sampling"/>

        <select class="form-control" id="functionAdmin" placeholder="Administrator Functions" name="function" required>

            <option value="">Select function</option>
            <option value="addUpdate">Adding updates</option>
            <option value="addContact"> Adding a connection with us</option>

        </select>

        <button id="functionChoice" type="submit" class="btn btn-primary">Choose</button>

    </form>

    <div class="error-messageFunction" id="error-messageFunction">

        <c:if test="${not (param.functionError eq null) }">

            <p> <c:out value="${param.functionError}" /> </p>

        </c:if>

    </div>

    <c:if test="${(param.functionCommand eq 'addUpdate')}">

        <div id="formFunction">

            <form action="urlToServlet" method="post">

                <input type="hidden" name="command" value="update_add"/>

                <input type="text" placeholder="Heading" id="titleUpdate" name="title" required>

                <input type="text" placeholder="Text" id="content" name="content" required>

                <input type="hidden" name="idAdmin" value="${sessionScope.user.idUser}"/>

                <button id="btnAdminFunction" type="submit" class="btn btn-primary">Add</button>

            </form>

        </div>

    </c:if>

    <c:if test="${(param.functionCommand eq 'addContact')}">

        <div id="formFunction">

            <form action="urlToServlet" method="post">

                <input type="hidden" name="command" value="contact_add"/>

                <input type="text" placeholder="Image" id="imgContact" name="img" required>

                <input type="text" placeholder="Link" id="linkContact" name="link" required>

                <input type="hidden" name="idAdmin" value="${sessionScope.user.idUser}"/>

                <button id="btnAdminFunction" type="submit" class="btn btn-primary">Add</button>

            </form>

        </div>

    </c:if>

    <jsp:include page="/WEB-INF/blocks/footer.jsp" />

</body>

</html>