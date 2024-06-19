<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="messages" />

<!DOCTYPE html>

<footer>

    <div id="social">

        <c:forEach var="contactsCommunications" items="${contacts}">

            <a href="${contactsCommunications.link}" title="Contact us" target="Contact us">

                <img src="${contactsCommunications.img}" alt="Contact us" title="Contact us" />

            </a>

        </c:forEach>

    </div>

    <div id="selectLanguage">

        <a href="urlToServlet?command=locale_set&language=en"> <fmt:message key="header.option.english"/> </a>
        <a href="urlToServlet?command=locale_set&language=ru"> <fmt:message key="header.option.russian"/> </a>

    </div>

    <div id="right">

        <p id="right">Copyright &copy; Your Website</p>

    </div>

    <script>

        document.getElementById('right').innerHTML = '<fmt:message key="footer.text.website"/> '
        + new Date().getFullYear() + '';

    </script>

</footer>