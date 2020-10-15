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


<div class="container">
    <form action="controller" method="POST" role="form" class="form-horizontal">
        <div class="form-group">
            <small id="cardNumberHelp" class="form-text text-muted"><fmt:message key="add_account.card_number_help"/></small>
            <input type="text" class="form-control" name="cardNumber" id="cardNumber" aria-describedby="cardNumberHelp" placeholder=<fmt:message key="add_account.card_number"/>>

            <small id="nameHelp" class="form-text text-muted"><fmt:message key="add_account.name_help"/></small>
            <input type="text" class="form-control" name="name" id="name" aria-describedby="nameHelp" placeholder=<fmt:message key="add_account.name"/>>

            <small id="amountHelp" class="form-text text-muted"><fmt:message key="add_account.amount_help"/></small>
            <input type="text" class="form-control" name="amount" id="amount" aria-describedby="amountHelp" placeholder=<fmt:message key="add_account.amount"/>>

            <small id="currencyHelp" class="form-text text-muted"><fmt:message key="add_account.currency_help"/></small>
                <label for="currency"></label>
                    <select class="form-control" name="currency" id="currency" placeholder="currency">
                        <c:forEach var = "currency" items = "${currencies}">
                            <option>${currency}</option>
                        </c:forEach>
                    </select>


            <button type="submit" class="btn btn-primary " name="command" value="insert-account"><fmt:message key="add_account.add_account"/></button>
        </div>
    </form>
</div>

        <%-- FOOTER --%>
        <%@ include file="/WEB-INF/jspf/footer.jspf"%>
        <%-- FOOTER --%>

        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
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