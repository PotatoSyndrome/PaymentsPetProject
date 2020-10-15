<!doctype html>
<html lang="ru">
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

		<%-- HEADER --%>
		<%@ include file="/WEB-INF/jspf/header.jspf"%>
		<%-- HEADER --%>



    <table class="table">
        <thead>
        <tr>
            <th><fmt:message key="accounts.name"/></th>
            <th><fmt:message key="accounts.card_number"/></th>
            <th><fmt:message key="accounts.amount"/></th>
            <th><fmt:message key="accounts.currency"/></th>
            <th><fmt:message key="accounts.make_payment"/></th>
            <th><fmt:message key="accounts.block_head"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var = "account" items = "${accountList}">
            <tr>
                <td>${account.name}</td>
                <td>${account.cardNumber}</td>
                <td>${account.amount}</td>
                <td>${account.currency}</td>
                <td>
                    <form action="controller" method="POST" role="form" class="form-horizontal">
                        <div class="form-group">

                            <input type="hidden" id="accountId" name="accountId" value="${account.id}"/>
                            <input type="hidden" id="toCardValue" name="toCardValue" value=""/>
                             <input type="hidden" id="amountValue" name="amountValue" value=""/>
                            <button type="submit" <c:if test = "${account.blocked}">disabled</c:if> class="btn btn-primary " name="command" value="make-payment"><fmt:message key="accounts.make_payment_button"/></button>

                        </div>
                    </form>
                </td>
                <td>
                    <c:choose>
                        <c:when test = "${account.blocked}">
                            <form action="controller" method="POST" role="form" class="form-horizontal">
                                <div class="form-group">

                                    <input type="hidden" id="blockedAccountId" name="blockedAccountId" value="${account.id}"/>
                                    <button type="submit" class="btn btn-primary " name="command" value="user-unblock-account"><fmt:message key="accounts.unblock"/></button>

                                </div>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <form action="controller" method="POST" role="form" class="form-horizontal">
                                <div class="form-group">

                                    <input type="hidden" id="blockedAccountId" name="blockedAccountId" value="${account.id}"/>
                                    <button type="submit" class="btn btn-primary " name="command" value="user-block-account"><fmt:message key="accounts.block"/></button>

                                </div>
                            </form>
                        </c:otherwise>
                     </c:choose>
                </td>
            </tr>
        </c:forEach>


        </tbody>
    </table>

        <a name="Add" id="Add" class="btn btn-primary" href="controller?command=get-insert-account" role="button"><fmt:message key="accounts.add_account"/></a>

        <%-- PAGINATION --%>
        <%@ include file="/WEB-INF/jspf/pagination.jspf"%>
        <%-- PAGINATION --%>

        <%-- FOOTER --%>
        <%@ include file="/WEB-INF/jspf/footer.jspf"%>
        <%-- FOOTER --%>
</body>
</html>
