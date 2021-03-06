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

    	.error{color:red}
    </style>
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
    <div id="wrapper">
      <div id="header">
        <h2>Simple Payment App</h2>
      </div>
    </div>
    <hr>

    <c:if test="${param.failed != null}">
      <div class="alert alert-danger">
        Transaction Failed due to Insufficient Funds. Please Try Again
      </div>
    </c:if>

    <div id="Container">

      <div id="header">
      <h2 style="text-align:center;">TRANSFER MONEY</h2>
    </div>
      <hr>
      <h4><u>Total Available Balance</u>: ${availableBalance} INR</h2>
      <form:form
        action="doTransaction"
        modelAttribute="transactionForm"
        method="POST"
      >
        <table>
          <tbody>
            <tr>
              <td><label>From Name:</label></td>
              <td><form:input path="from" 
                readonly="true" 
                style="font-family:'Helvetica';font-weight: bold;" /></td>
            </tr>

            <tr>
              <td><label>To Name:</label></td>
              <td>
                <form:input path="to" 
                readonly="true"
                 style="font-family:'Helvetica';font-weight: bold;" />   
              </td>
            </tr>



                <tr>
                  <td><label>Amount:</label></td>
                  <td><form:input path="amount" /></td>
                  <form:errors path="amount" cssClass="error"/>
                </tr>


            <tr>
              <hr>
              <td><label></label></td>
              <td><input type="submit" class="btn btn-primary" value="Make Transaction"  /></td>
            </tr>

          
          </tbody>
        </table>
      </form:form>

      <p>
        <a href="${pageContext.request.contextPath}/api/bank" class="btn btn-primary btn-lg">Back to Homepage</a>
      </p>
    </div>
  </body>
</html>
