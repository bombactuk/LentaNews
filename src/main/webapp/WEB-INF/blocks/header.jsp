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

        </ul>

        <div id="regAuth"><a href="urlToServlet?command=go_to_registration_page">Registration</a> |
        <a href="urlToServlet?command=go_to_authorization_page">Authorization</a></div>

    </nav>

</header>