<!doctype html>
<html lang="en">
<head>
    <title>WORKS</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix="t" uri="/WEB-INF/tld/time.tld"  %>
		<%-- HEADER --%>
		<%@ include file="/WEB-INF/jspf/adminHeader.jspf"%>
		<%-- HEADER --%>



        <table class="table">
                <thead>
                <tr>
                    <th><fmt:message key="admin.users.user_name"/></th>
                    <th><fmt:message key="admin.users.accounts"/></th>
                    <th><fmt:message key="admin.users.un_block"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var = "user" items = "${usersList}">
                    <tr>
                        <td>${user.login}</td>
                        <td>
                            <form action="controller" method="POST" role="form" class="form-horizontal">
                                <div class="form-group">

                                    <input type="hidden" id="userAccountsId" name="userAccountsId" value="${user.id}"/>
                                    <button type="submit" class="btn btn-primary " name="command" value="adminAccounts"><fmt:message key="admin.users.accounts"/></button>

                                </div>
                            </form>
                        </td>
                        <td>
                        <c:choose>
                            <c:when test = "${!user.blocked}">
                                <form action="controller" method="POST" role="form" class="form-horizontal">
                                    <div class="form-group">

                                        <input type="hidden" id="blockedUserId" name="blockedUserId" value="${user.id}"/>
                                        <button type="submit" class="btn btn-primary " name="command" value="block-user"><fmt:message key="admin.users.block"/></button>

                                    </div>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <form action="controller" method="POST" role="form" class="form-horizontal">
                                    <div class="form-group">

                                        <input type="hidden" id="blockedUserId" name="blockedUserId" value="${user.id}"/>
                                        <button type="submit" class="btn btn-primary " name="command" value="unblock-user"><fmt:message key="admin.users.unblock"/></button>
                                    </div>
                                </form>
                            </c:otherwise>
                        </c:choose>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>

                <%-- PAGINATION --%>
                <%@ include file="/WEB-INF/jspf/pagination.jspf"%>
                <%-- PAGINATION --%>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
                integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
                crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
                integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
                crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
                integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
                crossorigin="anonymous"></script>
</body>
</html>