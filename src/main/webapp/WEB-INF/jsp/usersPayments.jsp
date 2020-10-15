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
<%@ taglib prefix="t" uri="/WEB-INF/tld/time.tld"  %>

		<%-- HEADER --%>
		<%@ include file="/WEB-INF/jspf/header.jspf"%>
		<%-- HEADER --%>



    <table class="table">
        <thead>
        <tr>
            <th><fmt:message key="payments.from"/></th>
            <th><fmt:message key="payments.to"/></th>
            <th><fmt:message key="payments.amount"/></th>
            <th><fmt:message key="payments.currency"/></th>
            <th><fmt:message key="payments.status"/></th>
            <th><fmt:message key="payments.time"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var = "payment" items = "${paymentsList}">
            <tr>
                <td>${payment.fromNumber}</td>
                <td>${payment.toNumber}</td>
                <td>${payment.amount}</td>
                <td>${payment.currency}</td>
                <td><fmt:message key="payments.status.${payment.status}"/></td>
                <td>
                    <t:formatTime format = "yyyy-MM-dd HH:mm:ss" date = "${payment.time}"/>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>

        <%-- PAGINATION --%>
        <%@ include file="/WEB-INF/jspf/pagination.jspf"%>
        <%-- PAGINATION --%>

        <%-- FOOTER --%>
        <%@ include file="/WEB-INF/jspf/footer.jspf"%>
        <%-- FOOTER --%>
</body>
</html>