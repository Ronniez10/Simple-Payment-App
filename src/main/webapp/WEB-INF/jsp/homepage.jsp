<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@  taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<title>List Customers</title>

	<!-- Refer Your Stylesheet -->
	

		<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

		<c:url value="/css/style.css" var="jstlCss" />
    	<link href="${jstlCss}" rel="stylesheet" /> 

		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


   
   


</head>
<body>

<div id="Wrapper">
	<div id="header">
	<h2>Simple Payment App</h2>

	</div>

	<div id="container">
		<div id="content">

			<c:if test="${param.success != null}">
					<div class="alert alert-success  ">
						Transaction was Successful
					</div>
			</c:if>

			<c:if test="${param.failed != null}">
				<div class="alert alert-danger  ">
				  Transaction Failed due to Insufficient Funds. Please Try Again
				</div>
			  </c:if>

		

		

		<table>
			<tr>
			<th>Name</th>
			<th>Balance</th>
			<th>Phone Number</th>
			<th>Action</th>
			</tr>
			
			
		
			<c:forEach var="tempAccount" items="${accounts}">
			
			
			<!--  Constructing an Update Link -->
			<c:url var="viewTransactions"  value="v1/customer/viewTransactions">
				<c:param name="accountId" value="${tempAccount.id }"/>
			</c:url>
			
			<!--  Constructing an Delete Link -->	
			<c:url var="doTransaction"  value="v1/customer/doTransaction">
				<c:param name="accountId" value="${tempAccount.id }"/>
			</c:url>
			
			
			<tr>
				<td>${tempAccount.name}</td>
				<td>${tempAccount.balance}</td>
				<td>${tempAccount.phoneNumber}</td>
				<td> <a href="${viewTransactions }">View Transactions</a>
				|
				<a href="${doTransaction }"
				>Do a Transaction</a>
			
			</tr>
			</c:forEach>
		
		
		
		</table>





		</div>


	</div>

</div>
</body>
</html>