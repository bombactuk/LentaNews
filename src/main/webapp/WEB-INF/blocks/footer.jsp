<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<footer>

    <div id="social">

        <c:forEach var="contactsCommunications" items="${contacts}">

            <a href="${contactsCommunications.link}" title="Contact us" target="Contact us">

                <img src="${contactsCommunications.img}" alt="Contact us" title="Contact us" />

            </a>

        </c:forEach>

    </div>

    <div id="right">

        <p id="right">Copyright &copy; Your Website</p>

    </div>

    <script>

        document.getElementById('right').innerHTML = 'All rights reserved &copy; '
        + new Date().getFullYear() + '';

    </script>

</footer>