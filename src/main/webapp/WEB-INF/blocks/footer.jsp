<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<footer>

    <div id="social">

        <c:forEach var="contactsCommunications" items="${sessionScope.contacts}">

            <a href="${contactsCommunications.link}" title="Связь с нами" target="Связь с нами">

                <img src="${contactsCommunications.img}" alt="Связь с нами" title="Связь с нами" />

            </a>

        </c:forEach>

    </div>

    <div id="right">

        <p id="right">Copyright &copy; Your Website</p>

    </div>

    <script>

        document.getElementById('right').innerHTML = 'Все права защищены &copy; '
        + new Date().getFullYear() + '';

    </script>

</footer>