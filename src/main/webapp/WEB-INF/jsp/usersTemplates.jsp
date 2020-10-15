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
            <th><fmt:message key="templates.from"/></th>
            <th><fmt:message key="templates.to"/></th>
            <th><fmt:message key="templates.amount"/></th>
            <th><fmt:message key="templates.currency"/></th>
            <th><fmt:message key="templates.send"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var = "template" items = "${templateList}">
            <tr>
                <td>${template.fromNumber}</td>
                <td>${template.toNumber}</td>
                <td>${template.amount}</td>
                <td>${template.currency}</td>
                <%-- TODO make button send smth --%>
                 <td>
                     <form action="controller" method="POST" role="form" class="form-horizontal">
                         <div class="form-group">

                            <input type="hidden" id="accountId" name="accountId" value="${template.from}"/>
                            <input type="hidden" id="toCardValue" name="toCardValue" value="${template.toNumber}"//>
                            <input type="hidden" id="amountValue" name="amountValue" value="${template.amount}"/>
                            <button type="submit" class="btn btn-primary " name="command" value="make-payment"><fmt:message key="templates.send_button"/></button>
                         </div>
                     </form>

                 </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>

        <a name="Add" id="Add" class="btn btn-primary" href="controller?command=get-insert-template" role="button"><fmt:message key="templates.add_template"/></a>

        <%-- PAGINATION --%>
        <%@ include file="/WEB-INF/jspf/pagination.jspf"%>
        <%-- PAGINATION --%>

        <%-- FOOTER --%>
        <%@ include file="/WEB-INF/jspf/footer.jspf"%>
        <%-- FOOTER --%>
</body>
</html>