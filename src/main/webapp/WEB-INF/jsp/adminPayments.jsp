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
                    <th><fmt:message key="admin.payments.from"/></th>
                    <th><fmt:message key="admin.payments.to"/></th>
                    <th><fmt:message key="admin.payments.amount"/></th>
                    <th><fmt:message key="admin.payments.currency"/></th>
                    <th><fmt:message key="admin.payments.time"/></th>
                    <th><fmt:message key="admin.payments.decline"/></th>
                    <th><fmt:message key="admin.payments.confirm"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var = "payment" items = "${paymentsList}">
                    <tr>
                        <td>${payment.fromNumber}</td>
                        <td>${payment.toNumber}</td>
                        <td>${payment.amount}</td>
                        <td>${payment.currency}</td>
                        <td>
                            <t:formatTime format = "yyyy-MM-dd HH:mm:ss" date = "${payment.time}"/>
                        </td>
                        <td>
                            <form action="controller" method="POST" role="form" class="form-horizontal">
                                <div class="form-group">

                                    <input type="hidden" id="paymentId" name="paymentId" value="${payment.id}"/>
                                    <button type="submit"class="btn btn-primary " name="command" value="decline-payment"><fmt:message key="admin.payments.decline"/></button>

                                </div>
                            </form>
                        </td>
                        <td>
                            <form action="controller" method="POST" role="form" class="form-horizontal">
                                <div class="form-group">

                                    <input type="hidden" id="paymentId" name="paymentId" value="${payment.id}"/>
                                    <button type="submit"class="btn btn-primary " name="command" value="perform-payment"><fmt:message key="admin.payments.confirm"/></button>

                                </div>
                            </form>
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