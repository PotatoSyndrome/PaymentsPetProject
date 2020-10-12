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
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

		<%-- HEADER --%>
		<%@ include file="/WEB-INF/jspf/header.jspf"%>
		<%-- HEADER --%>



    <table class="table">
        <thead>
        <tr>
            <th>Name</th>
            <th>Card number</th>
            <th>Currency</th>
            <th>Amount</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var = "account" items = "${accountList}">
            <tr>
                <td>${account.name}</td>
                <td>${account.cardNumber}</td>
                <td>${account.currency}</td>
                <td>${account.amount}</td>
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
