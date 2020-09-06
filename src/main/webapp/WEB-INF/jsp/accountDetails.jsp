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
        <h1>Welcome to Account Details Page !!</h1>

        <table>
            <thead>
                <th >Fields</th>
                <th>Account Details</th>
            </thead>
            <tbody>
                <tr>
                    <td style="font-style: sans-serif;">Account Holder's Name</td>
                    <td>${accountDetails.name}</td>
                </tr>
                <tr>
                    <td style="font-style: sans-serif;">Phone Number</td>
                    <td>${accountDetails.phoneNumber}</td>
                </tr>
                <tr>
                    <td style="font-style: sans-serif;">Account Number</td>
                    <td>${accountDetails.accountNumber}</td>
                </tr>
                <tr>
                    <td style="font-style: sans-serif;">Bank Name</td>
                    <td>${accountDetails.bankName}</td>
                </tr>
                <tr>
                    <td style="font-style: sans-serif;">Account Type</td>
                    <td>${accountDetails.accountType}</td>
                </tr>
                <tr>
                    <td style="font-style: sans-serif;">Branch Name</td>
                    <td>${accountDetails.branchName}</td>
                </tr>
            </tbody>
        </table>
        <hr>
        <p>
            <a href="${pageContext.request.contextPath}/api/bank" class="btn btn-primary btn-lg"
              >Back to Homepage</a
            >
          </p>
    </div>
  </body>
</html>
