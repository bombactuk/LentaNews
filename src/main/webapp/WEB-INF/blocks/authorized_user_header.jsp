<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<header id="header">

    <h1><span class="logo">Lenta</span>News</h1>

    <nav>

        <ul>

            <li><a href="urlToServlet?command=go_to_updates_page">Update</a></li>
            <li><a href="urlToServlet?command=go_to_about_page">Description</a></li>
            <li><a href="urlToServlet?command=go_to_news_page">News</a></li>

        </ul>

        <c:if test="${(sessionScope.user.role eq 'User')}">

            <div id="regAuth">

                <a href="urlToServlet?command=go_to_profile_page&idUser=${sessionScope.user.idUser}">
                    Профиль пользователя
                </a>

            </div>

        </c:if>

        <c:if test="${(sessionScope.user.role eq 'Admin')}">

            <div id="regAuth">

                <a href="urlToServlet?command=go_to_admin_page">Профиль админестратора</a>

            </div>

        </c:if>

    </nav>

</header>