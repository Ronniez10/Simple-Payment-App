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

      <c:url
                var="viewTransactions"
                value="bank/customer/viewTransactions" >
                <c:param name="accountId" value="${accountId}" />
      </c:url>
      <hr>
      <div id="header">
          <h2 style="text-align:center;">Welcome ${username} !</h2>
      </div>
     
      <hr>


       <span>
        <!--<span style="float: left;">
            <h3>Welcome ${username}</h3>
        </span>-->

        <span style="float: right;">
          <h3><a href="${viewTransactions}">View Transactions</a></h3>
      </span>

        <span style="float:left ;">
            <h3> Available Balance:<u> ${accountBalance} INR</u>  </h3>
        </span>
       <span>


      <div id="container">
        <div class="content col-xs-12">
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
          <!--<div class="table-responsive">-->
          <table class="table table-hover">
			<div >
				<thead class="thead-light ">
				<th>Account Holder's Name</th>
				<!--<th>Balance</th>-->
				<th>Phone Number</th>
				<!--<th>Transaction History</th>-->
        <th>Transfer Funds</th>
        <th>Account Details</th>
				</thead>
			</div>

            <c:forEach var="tempAccount" items="${accounts}">
              <!--<c:url
                var="viewTransactions"
                value="bank/customer/viewTransactions"
              >
                <c:param name="accountId" value="${tempAccount.id }" />
              </c:url>-->

              <c:url var="doTransaction" value="bank/customer/doTransaction">
                <c:param name="accountId" value="${tempAccount.id }" />
              </c:url>

              <c:url var="accountDetails" value="bank/customer/">
                <c:param name="accountId" value="${tempAccount.id }" />
              </c:url>
			  <tbody>
              <tr>
                <td>${tempAccount.name}</td>
                <!--<td style="font-weight: bold;">${tempAccount.balance} INR</td>-->
                <td style="font-style: italic;">
                  ${tempAccount.phoneNumber}
                </td>
                <!--<td><a href="${viewTransactions }">View Transactions</a></td> -->
                <td><a href="${doTransaction }">Transfer Money</a></td>
                <td><a href="${accountDetails }">View Account Details</a></td>
                
			  </tr>
			</c:forEach>
			</tbody>
    </table>
    <hr>
          <!-- </div>-->
        </div>
      </div>
    </div>
     <a class="btn btn-danger btn-lg "  href="/logout" style="margin-left:50%;margin-right: 50%;">Logout</a>
  </body>
</html>
