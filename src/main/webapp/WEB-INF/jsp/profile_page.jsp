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

	    <jsp:param name="title" value="Profile" />

    </jsp:include>

</head>

<body>

    <jsp:include page="/WEB-INF/blocks/authorized_user_header.jsp" />

    <div>

        <c:if test="${not (param.editAnswer eq null) }">

            <c:if test="${ (param.editAnswer eq '117') }">

                <p> <fmt:message key="profile_error_117"/> </p>

            </c:if>

            <c:if test="${ (param.editAnswer eq '118') }">

                <p> <fmt:message key="profile_error_118"/> </p>

            </c:if>

        </c:if>

    </div>

    <div id="userInfo">

        <c:set var="infoUser" value="${requestScope.infoUser}" />

        <h2> <fmt:message key="profile_text_name"/> ${infoUser.name} </h2>
        <p > <fmt:message key="profile_text_birthday"/> ${infoUser.birthday} </p>
        <p> <fmt:message key="profile_text_country"/> ${infoUser.country} </p>

        <div id="functionProfileEdit">

            <button id="btnEditProfile"> <fmt:message key="profile_button_edit"/> </button><br><br>

        </div>

        <a href="urlToServlet?command=user_logout"> <fmt:message key="profile_button_log"/> </a>

        <div id="editProfile" class="modal">

            <div id="editProfileContent" class="modal-content">

                <span class="close">&times;</span>

                <h2> <fmt:message key="profile_button_edit"/> </h2>

                <form action="urlToServlet" method="post">

                    <input type="hidden" name="command" value="user_info_edit"/>

                    <input type="hidden" name="idUser" value="${sessionScope.user.idUser}"/>

                    <label for="name"> <fmt:message key="profile_text_name"/> </label>
                    <input type="text" id="nameEditProfile" name="name" value="${infoUser.name}" required><br><br>

                    <label for="date"> <fmt:message key="profile_text_birthday"/> </label>
                    <input type="date" class="form-control" placeholder="Date of Birth" id="dob" name="birthday" value="${infoUser.birthday}" required><br><br>

                    <label for="content"> <fmt:message key="profile_text_country"/> </label>
                    <select class="form-control" id="country" placeholder="Country of Residence" name="country" value="${infoUser.country}" required>

                        <option value="russia">Russia</option>
                        <option value="usa">USA</option>
                        <option value="germany">Germany</option>
                        <option value="france">France</option>
                        <option value="belarus">Belarus</option>

                    </select><br><br>

                    <button type="submit"> <fmt:message key="profile_button_save"/> </button>

                </form>

            </div>

        </div>

	</div>

    <jsp:include page="/WEB-INF/blocks/footer.jsp" />

    <script>

            var modal = document.getElementById("editProfile");

            var btn = document.getElementById("btnEditProfile");

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