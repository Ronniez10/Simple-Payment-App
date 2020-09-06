<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ taglib
prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
  <head>
    <title>List Customers</title>

    <!-- Refer Your Stylesheet -->
    <c:url value="/css/style.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" />

    <link
      rel="stylesheet"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
    />

    <style>
      table {
        font-family: arial, sans-serif;
        border-collapse: collapse;
        width: 100%;
      }
      
      td, th {
        border: 1px solid #dddddd;
        text-align: left;
        padding: 8px;
      }
      
      tr:nth-child(even) {
        background-color: #dddddd;
      }
      </style>
  </head>
  <body>
    <div id="Wrapper">
      <div id="header">
        <h2>Simple Payment App</h2>
      </div>

      <hr>


      <div id="header">
        <h2 style="text-align:center;">VIEW TRANSACTIONS</h2>
      </div>

      <div id="container">
        <div id="content">
          <div class="row">
            <h3 style="text-align: left;">
              <u>Account Holder's Name:</u> <i>${accountName}</i>
              <span style="float: right;">
                <u> Avaialble Balance:</u><i>${accountBalance} INR</i>
              </span>
            </h3>
          </div>
          <table class="table">
            <thead>
              <th>Transaction Type(CREDIT/DEBIT)</th>
              <th>Transaction Amount</th>
            </thead>

            <tbody>
              <c:forEach var="tempTransaction" items="${transactions}">
                <tr>
                  <!--  Constructing an Update Link -->
                  <td style="font-style: italic;">${tempTransaction.type}</td>
                  <c:if test="${tempTransaction.type == 'CREDIT'}">
                    <td style="color: green;">
                      +${tempTransaction.amount} INR
                    </td>
                  </c:if>
                  <c:if test="${tempTransaction.type == 'DEBIT'}">
                    <td style="color: red;">-${tempTransaction.amount} INR</td>
                  </c:if>
                </tr>
              </c:forEach>
            </tbody>
          </table>
          <p>
            <a href="${pageContext.request.contextPath}/api/bank" class="btn btn-primary btn-lg"
              > Back to Homepage</a
            >
          </p>
        </div>
      </div>
    </div>
  </body>
</html>
