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

	    <jsp:param name="title" value="News" />

    </jsp:include>

</head>

<body>

    <jsp:include page="/WEB-INF/blocks/authorized_user_header.jsp" />

    <div>

        <c:if test="${not (param.messageFunctions eq null) }">

            <c:if test="${ (param.messageFunctions eq '115') }">

                <p> <fmt:message key="news_error_115"/> </p>

            </c:if>

            <c:if test="${ (param.messageFunctions eq '116') }">

                <p> <fmt:message key="news_error_116"/> </p>

            </c:if>

        </c:if>

    </div>

    <div id="search_form_news">

        <form action="urlToServlet" method="post">

            <input type="hidden" name="command" value="news_search"/>
            <input type="search" name="query" placeholder="<fmt:message key="news_search"/>">
            <button type="submit"> <fmt:message key="news_button_find"/> </button>

        </form>

        <form action="urlToServlet" method="post">

            <input type="hidden" name="command" value="news_search_categories"/>

                <select class="form-control" placeholder="Administrator Functions" name="idCategories" required>

                    <option value=""> <fmt:message key="news_text_select"/> </option>
                    <option value="0"> <fmt:message key="news_text_all"/> </option>

                    <c:forEach var="categoriesList" items="${categories}">

                        <option value="${categoriesList.idCategories}">${categoriesList.type}</option>

                    </c:forEach>

                </select>

            <button id="functionChoice" type="submit" class="btn btn-primary"> <fmt:message key="news_button_choose"/> </button>

        </form>

        <c:if test="${(sessionScope.user.role eq 'Admin' )}">

                <button id="btnAddNews"> <fmt:message key="news_button_add"/> </button>

                <div id="addNews" class="modal">

                    <div id="addNewsContent" class="modal-content">

                        <span class="close">&times;</span>

                            <h2> <fmt:message key="news_button_add"/> </h2>

                            <form action="urlToServlet" method="post">

                                <input type="hidden" name="command" value="news_add"/>

                                <input type="hidden" name="idAdmin" value="${sessionScope.user.idUser}"/>

                                <label for="title"> <fmt:message key="news_text_title"/> </label>
                                <input type="text" id="titleMyModal" name="title" required><br><br>

                                <label for="shortDescription"> <fmt:message key="news_text_short"/> </label>
                                <input type="text" id="shortDescriptionMyModal" name="short_description" required><br><br>

                                <label for="content"> <fmt:message key="news_text_content"/> </label>
                                <textarea type="text" id="contentMyModal" name="content" required></textarea><br><br>

                                <label for="categories"> <fmt:message key="news_text_categories"/> </label>
                                <select class="form-control" placeholder="Administrator Functions" name="idCategories" required>

                                    <c:forEach var="categoriesList" items="${categories}">

                                        <option value="${categoriesList.idCategories}">${categoriesList.type}</option>

                                    </c:forEach>

                                </select><br><br>

                                <button type="submit"> <fmt:message key="news_button_add_news"/> </button>

                            </form>

                    </div>

                </div>

            </c:if>

    </div>

    <div id="article" class="article">

        <c:forEach var="news" items="${requestScope.news}">

    	    <h2 class="news-title">${news.title}</h2>

    	    <p class="news-text">${news.shortDescription}</p>

    	    <div id="functionReadNews">

    	        <a class="news-link" href="urlToServlet?command=go_to_news_info_page&idNews=${news.idNews}" /> <fmt:message key="news_button_read_news"/> </a>

    	    </div>

    	    <p class="news-date">${news.postDate}</p>

    	    <hr id="divider">

    	</c:forEach>

    </div>

    <jsp:include page="/WEB-INF/blocks/footer.jsp" />

    <script>

        var modal = document.getElementById("addNews");

        var btn = document.getElementById("btnAddNews");

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