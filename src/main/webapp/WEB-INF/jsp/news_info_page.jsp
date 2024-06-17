<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

            <c:out value="${param.editAnswer}" />

        </c:if>

    </div>

	<c:if test="${(sessionScope.user.role eq 'Admin')}">

        <div id="functionEditNews">

            <button id="btnEditNews">Editing news</button><br><br>

        </div>

        <div id="editFormNews" class="modal">

            <div id="editNewsContent" class="modal-content">

                <span class="close">&times;</span>

                    <h2>Edit news</h2>

                    <form action="urlToServlet" method="post">

                        <input type="hidden" name="command" value="news_edit"/>

                        <input type="hidden" name="idNews" value="${infoNews.idNews}"/>

                        <label for="title">Title:</label>
                        <input type="text" id="titleEditNews" name="title" value="${infoNews.title}" required><br><br>

                        <label for="shortDescription"> Short Description:</label>
                        <input type="text" id="shortDescriptionEditNews" name="short_description" value="${infoNews.shortDescription}" required><br><br>

                        <label for="content"> Content:</label>
                        <textarea type="text" id="contentEditNews" name="content" required>${infoNews.content}</textarea><br><br>

                        <button type="submit">Save</button>

                    </form>

            </div>

        </div>

        <div id="functionDeleteNews">

            <a href="urlToServlet?command=news_delete&idNews=${infoNews.idNews}">Delete news</a>

        </div>

    </c:if>

    <div id="newsInfo">

        <c:set var="infoNews" value="${requestScope.infoNews}" />

        <h2> ${infoNews.title} </h2>

        <h3> ${infoNews.shortDescription} </h2>

        <p> ${infoNews.content} </p>

        <p> Publication date: ${infoNews.postDate} <p>

    </div>

    <div id="commentsContainer">

        <h2>Comments</h2>

            <div id="functionAddNewsComment">

                <button id="btnAddComment">Add comment</button><br><br>

            </div>

            <div id="newsComment" class="modal1">

                <div id="newsCommentContent" class="modal-content1">

                    <span class="close1">&times;</span>

                    <h2>Comment adding</h2>

                    <form action="urlToServlet" method="post">

                        <input type="hidden" name="command" value="comment_add"/>

                        <input type="hidden" name="idNews" value="${infoNews.idNews}"/>

                        <input type="hidden" name="idUser" value="${sessionScope.user.idUser}"/>

                        <label for="content"> Content:</label>
                        <textarea type="text" id="contentComment" name="content" required></textarea><br><br>

                        <button type="submit">Add</button>

                    </form>

                </div>

            </div>

        <div id="commentsList">

            <c:forEach var="comment" items="${requestScope.listComment}">

                <div class="comment">

                    <p id="commentContent">${comment.content}</p>
                    <p id="commentAuthor">by ${comment.nameUser} on ${comment.datePost}</p>

                    <c:if test="${(sessionScope.user.role eq 'Admin')||(sessionScope.user.idUser eq comment.idUser)}">

                        <div id="functionDeleteComment">

                            <a href="urlToServlet?command=comment_delete&idComment=${comment.idComment}&idNews=${infoNews.idNews}">Delete comment</a>

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