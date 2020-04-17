<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ taglib
prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
  <head>
    <title>List Customers</title>

    <!-- Refer Your Stylesheet -->

    <link
      rel="stylesheet"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
    />

    <c:url value="/css/style.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" />

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  </head>
  <body>
    <div id="Wrapper">
      <div id="header">
        <h2>Simple Payment App</h2>
      </div>

      <div id="container">
        <div id="content">
          <c:if test="${param.success != null}">
            <div class="alert alert-success">
              Transaction was Successful
            </div>
          </c:if>

          <c:if test="${param.failed != null}">
            <div class="alert alert-danger">
              Transaction Failed due to Insufficient Funds. Please Try Again
            </div>
          </c:if>

          <table>
            <tr>
              <th>Account Holder's Name</th>
              <th>Balance</th>
              <th>Phone Number</th>
              <th>Transaction History</th>
              <th>Transfer Funds</th>
            </tr>

            <c:forEach var="tempAccount" items="${accounts}">
              <c:url
                var="viewTransactions"
                value="bank/customer/viewTransactions"
              >
                <c:param name="accountId" value="${tempAccount.id }" />
              </c:url>

              <c:url var="doTransaction" value="bank/customer/doTransaction">
                <c:param name="accountId" value="${tempAccount.id }" />
              </c:url>

              <tr>
                <td>${tempAccount.name}</td>
                <td style="font-weight: bold;">${tempAccount.balance} INR</td>
                <td style="font-style: italic;">
                  ${tempAccount.phoneNumber}
                </td>
                <td><a href="${viewTransactions }">View Transactions</a></td>
                <td><a href="${doTransaction }">Transfer</a></td>
              </tr>
            </c:forEach>
          </table>
        </div>
      </div>
    </div>
  </body>
</html>
