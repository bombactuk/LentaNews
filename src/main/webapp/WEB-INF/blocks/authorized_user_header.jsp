<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="messages" />

<!DOCTYPE html>

<header id="header">

    <h1><span class="logo">Lenta</span>News</h1>

    <nav>

        <ul>

            <li><a href="urlToServlet?command=go_to_updates_page"><fmt:message key="header.ul.update"/></a></li>
            <li><a href="urlToServlet?command=go_to_about_page"><fmt:message key="header.ul.description"/></a></li>
            <li><a href="urlToServlet?command=go_to_news_page"><fmt:message key="header.ul.news"/></a></li>

        </ul>

        <div class="language-selector" id="language-selector">

            <form id="language-form" action="urlToServlet" method="post">

                <input type="hidden" name="command" value="locale_set"/>

                <select name="language" id="language-select" onchange="submitLanguageForm()">

                    <option value="" selected><fmt:message key="header.option.select"/></option>
                    <option value="en"><fmt:message key="header.option.english"/></option>
                    <option value="ru"><fmt:message key="header.option.russian"/></option>
                    <!-- Add more languages as needed -->

                </select>

            </form>

            <script>

                function submitLanguageForm() {

                    document.getElementById('language-form').submit();

                }

            </script>

        </div>

        <c:if test="${(sessionScope.user.role eq 'User' or sessionScope.user.role eq 'Editor')}">

            <div id="regAuth">

                <a href="urlToServlet?command=go_to_profile_page&idUser=${sessionScope.user.idUser}">
                    <fmt:message key="header.a.profile"/>
                </a>

            </div>

        </c:if>

        <c:if test="${(sessionScope.user.role eq 'Admin')}">

            <div id="regAuth">

                <a href="urlToServlet?command=go_to_admin_page"><fmt:message key="header.a.admin_profile"/></a>

            </div>

        </c:if>

    </nav>

</header>