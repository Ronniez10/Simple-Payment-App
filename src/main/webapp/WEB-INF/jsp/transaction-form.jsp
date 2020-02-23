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
  </head>
  <body>
    <div id="wrapper">
      <div id="header">
        <h2>Simple Payment App</h2>
      </div>
    </div>

    <c:if test="${param.failed != null}">
      <div class="alert alert-danger  ">
        Transaction Failed due to Insufficient Funds. Please Try Again
      </div>
    </c:if>

    <div id="Container">
      <h3>Save Customer</h3>

      <form:form
        action="doTransaction"
        modelAttribute="transactionForm"
        method="POST"
      >
        <table>
          <tbody>
            <tr>
              <td><label>From Name:</label></td>
              <td><form:input path="from" readonly="true"/></td>
            </tr>

            <tr>
              <td><label>To Name:</label></td>
              <td><form:input path="to"/></td>
            </tr>

            <tr>
              <td><label>Amount:</label></td>
              <td><form:input path="amount"/></td>
            </tr>

            <tr>
              <td><label></label></td>
              <td><input type="submit" value="Submit" class="save"/></td>
            </tr>
          </tbody>
        </table>
      </form:form>

      <div style="clear;both;"></div>

      <p>
        <a href="${pageContext.request.contextPath}/api/v1">Back to Homepage</a>
      </p>
    </div>
  </body>
</html>
