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

        <a href="urlToServlet?command=user_logout">Выйти с аккаунта</a>
        <a href="urlToServlet?command=user_token_reset">Обнулить токен пользователей</a>

    </div>

    <hr/>

    <form action="urlToServlet" method="post">

        <input type="hidden" name="command" value="admin_function_sampling"/>

        <select class="form-control" id="functionAdmin" placeholder="Функции администратора" name="function" required>

            <option value="">Выберите функцию</option>
            <option value="addUpdate">Добавление обновлений</option>

        </select>

        <button id="functionChoice" type="submit" class="btn btn-primary">Выбрать</button>

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

                <input type="text" placeholder="Заголовок" id="titleUpdate" name="title" required>

                <input type="text" placeholder="Текст" id="content" name="content" required>

                <input type="hidden" name="idAdmin" value="${sessionScope.user.idUser}"/>

                <button id="btnAdminFunction" type="submit" class="btn btn-primary">Добавить</button>

            </form>

        </div>

    </c:if>

    <jsp:include page="/WEB-INF/blocks/footer.jsp" />

</body>

</html>