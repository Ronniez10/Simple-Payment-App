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
    <div id="Wrapper">
      <div id="header">
        <h2>Simple Payment App</h2>
      </div>

      <div id="container">
        <div id="content">
          <!-- Add a Button for Adding Customers -->

          <table>
            <tr>
              <th>Transaction Type(CREDIT/DEBIT)</th>
              <th>Transaction Amount</th>
            </tr>

            <c:forEach var="tempTransaction" items="${transactions}">
              <!--  Constructing an Update Link -->

              <tr>
                <td>${tempTransaction.type}</td>
                <td>${tempTransaction.amount}</td>
              </tr>
            </c:forEach>
          </table>

          <p>
            <a href="${pageContext.request.contextPath}/api/v1"
              >Back to Homepage</a
            >
          </p>
        </div>
      </div>
    </div>
  </body>
</html>
