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

            <small id="currencyHelp" class="form-text text-muted"><fmt:message key="add_template.choose_account"/></small>
                            <label for="fromAccount"></label>
                                <select class="form-control" name="fromAccount" id="fromAccount" placeholder="from">
                                    <c:forEach var = "acc" items = "${accounts}">
                                        <option value="${acc.id}">${acc.name}</option>
                                    </c:forEach>
                                </select>

            <small id="toCardHelp" class="form-text text-muted"><fmt:message key="add_template.card_number_help"/></small>
            <input type="text" class="form-control" name="toCard" id="toCard" aria-describedby="toCardHelp" placeholder=<fmt:message key="add_template.card_number"/>>


            <small id="amountHelp" class="form-text text-muted"><fmt:message key="add_template.amount_help"/></small>
            <input type="text" class="form-control" name="amount" id="amount" aria-describedby="amountHelp" placeholder=<fmt:message key="add_template.amount"/>>


            <button type="submit" class="btn btn-primary " name="command" value="confirm-template"><fmt:message key="add_template.confirm"/></button>
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