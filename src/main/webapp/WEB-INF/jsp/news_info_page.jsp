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

	    <jsp:param name="title" value="News info" />

    </jsp:include>

</head>

<body>

    <jsp:include page="/WEB-INF/blocks/authorized_user_header.jsp" />

    <div>

        <c:if test="${not (param.editAnswer eq null) }">

            <c:if test="${ (param.editAnswer eq '109' ) }">

                <p> <fmt:message key="news_info_error_109"/> </p>

            </c:if>

            <c:if test="${ (param.editAnswer eq '110' ) }">

                <p> <fmt:message key="news_info_error_110"/> </p>

            </c:if>

            <c:if test="${ (param.editAnswer eq '111') }">

                <p> <fmt:message key="news_info_error_111"/> </p>

            </c:if>

            <c:if test="${ (param.editAnswer eq '112') }">

                <p> <fmt:message key="news_info_error_112"/> </p>

            </c:if>news_info_error_113

            <c:if test="${ (param.editAnswer eq '113') }">

                <p> <fmt:message key="news_info_error_113"/> </p>

            </c:if>

            <c:if test="${ (param.editAnswer eq '114') }">

                <p> <fmt:message key="news_info_error_114"/> </p>

            </c:if>

        </c:if>

    </div>

	<c:if test="${(sessionScope.user.role eq 'Admin' )}">

        <div id="functionEditNews">

            <button id="btnEditNews"> <fmt:message key="news_info_text_edit"/> </button><br><br>

        </div>

        <div id="editFormNews" class="modal">

            <div id="editNewsContent" class="modal-content">

                <span class="close">&times;</span>

                    <h2> <fmt:message key="news_info_text_news"/> </h2>

                    <form action="urlToServlet" method="post">

                        <input type="hidden" name="command" value="news_edit"/>

                        <input type="hidden" name="idNews" value="${infoNews.idNews}"/>

                        <label for="title"> <fmt:message key="news_info_text_title"/> </label>
                        <input type="text" id="titleEditNews" name="title" value="${infoNews.title}" required><br><br>

                        <label for="shortDescription"> <fmt:message key="news_info_text_short"/> </label>
                        <input type="text" id="shortDescriptionEditNews" name="short_description" value="${infoNews.shortDescription}" required><br><br>

                        <label for="content"> <fmt:message key="news_info_text_content"/> </label>
                        <textarea type="text" id="contentEditNews" name="content" required>${infoNews.content}</textarea><br><br>

                        <button type="submit"> <fmt:message key="news_info_button_save"/> </button>

                    </form>

            </div>

        </div>

        <div id="functionDeleteNews">

            <a href="urlToServlet?command=news_delete&idNews=${infoNews.idNews}"> <fmt:message key="news_info_button_delete"/> </a>

        </div>

    </c:if>

    <div id="newsInfo">

        <c:set var="infoNews" value="${requestScope.infoNews}" />

        <h2> ${infoNews.title} </h2>

        <h3> ${infoNews.shortDescription} </h2>

        <p> ${infoNews.content} </p>

        <p> <fmt:message key="news_info_text_publication"/> ${infoNews.postDate} <p>

    </div>

    <div id="commentsContainer">

        <h2> <fmt:message key="news_info_text_comments"/> </h2>

            <div id="functionAddNewsComment">

                <button id="btnAddComment"> <fmt:message key="news_info_button_add_comments"/> </button><br><br>

            </div>

            <div id="newsComment" class="modal1">

                <div id="newsCommentContent" class="modal-content1">

                    <span class="close1">&times;</span>

                    <h2> <fmt:message key="news_info_text_comments_adding"/> </h2>

                    <form action="urlToServlet" method="post">

                        <input type="hidden" name="command" value="comment_add"/>

                        <input type="hidden" name="idNews" value="${infoNews.idNews}"/>

                        <input type="hidden" name="idUser" value="${sessionScope.user.idUser}"/>

                        <label for="content"> <fmt:message key="news_info_text_comments_content"/> </label>
                        <textarea type="text" id="contentComment" name="content" required></textarea><br><br>

                        <button type="submit"> <fmt:message key="news_info_button_add"/> </button>

                    </form>

                </div>

            </div>

        <div id="commentsList">

            <c:forEach var="comment" items="${requestScope.listComment}">

                <div class="comment">

                    <p id="commentContent">${comment.content}</p>
                    <p id="commentAuthor"> <fmt:message key="news_info_text_comments_by"/> ${comment.nameUser} <fmt:message key="news_info_text_comments_on"/> ${comment.datePost}</p>

                    <c:if test="${(sessionScope.user.role eq 'Admin')||(sessionScope.user.idUser eq comment.idUser)}">

                        <div id="functionDeleteComment">

                            <a href="urlToServlet?command=comment_delete&idComment=${comment.idComment}&idNews=${infoNews.idNews}"> <fmt:message key="news_info_button_delete_comment"/> </a>

                        </div>

                    </c:if>

                </div>

            </c:forEach>

        </div>

    </div>

    <jsp:include page="/WEB-INF/blocks/footer.jsp" />

    <script>

        var modal = document.getElementById("editFormNews");

        var btn = document.getElementById("btnEditNews");

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

    <script>

            var modal1 = document.getElementById("newsComment");

            var btn1 = document.getElementById("btnAddComment");

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