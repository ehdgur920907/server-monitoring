<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link href="<c:url value="/resources/css/index.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/index.js" />"></script>
<title>index</title>
</head>

<body onload="realtimeClock()">
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="/"><span
				class="glyphicon glyphicon-home" aria-hidden="true"></span></a>
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
			</ul>
			<%-- <c:choose>
				<c:when test="${sessionedAdmin == null }">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="/signin">signin</a></li>
					</ul>
				</c:when>
				<c:when test="${sessionedAdmin != null }">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="/signout">signout</a></li>
					</ul>
				</c:when>
			</c:choose> --%>
		</div>

	</div>
	</nav>
	<h2>server list</h2>
	<hr />
	<div class="jumbotron">
		<div class="container">
			<h4><div id="time"></div></h4>
			<br />
			<%-- <c:choose>
				<c:when test="${sessionedAdmin == null }">
				</c:when>
				<c:when test="${sessionedAdmin != null }">
					<a href="/add" class="btn btn-primary pull-right" role="button">add</a>
					<hr />
				</c:when>
			</c:choose> --%>
			<table id="server-list"
				class="table table-hover table-striped table-bordered">
				<thead>
					<tr id="tr-head">
						<th>server id</th>
						<th>host name</th>
						<th>ip address</th>
						<th>os</th>
						<th>status</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="arrayListServerInformation"
						items="${ arrayListServerInformation }">
						<tr style="cursor: pointer;" onclick="location.href='/monitoring/${ arrayListServerInformation.id }'">
							<td>${ arrayListServerInformation.id }</td>
							<td>${ arrayListServerInformation.hostName }</td>
							<td>${ arrayListServerInformation.ipAddress }</td>
							<td>${ arrayListServerInformation.osName }</td>
							<td>${ arrayListServerInformation.status }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<script>
		for (var i = 0; i < $('#server-list tbody tr').length; i++) {
			if ($('#server-list tbody tr td:nth-child(5)')[i].innerHTML === 'danger') {
				$('#server-list tbody tr td:nth-child(5)')[i].innerHTML = null;
				$('#server-list tbody tr td:nth-child(5)')[i].innerHTML = '<div style="border-radius: 50% 50%; -moz-border-radius: 50% 50%; -webkit-border-radius: 50% 50%; background: #FF0000; height: 20px; width: 20px; margin: auto;"></div>';
			} else if ($('#server-list tbody tr td:nth-child(5)')[i].innerHTML === 'warning') {
				$('#server-list tbody tr td:nth-child(5)')[i].innerHTML = null;
				$('#server-list tbody tr td:nth-child(5)')[i].innerHTML = '<div style="border-radius: 50% 50%; -moz-border-radius: 50% 50%; -webkit-border-radius: 50% 50%; background: #FFBB00; height: 20px; width: 20px; margin: auto;"></div>';
			} else {
				$('#server-list tbody tr td:nth-child(5)')[i].innerHTML = null;
				$('#server-list tbody tr td:nth-child(5)')[i].innerHTML = '<div style="border-radius: 50% 50%; -moz-border-radius: 50% 50%; -webkit-border-radius: 50% 50%; background: #1DDB16	; height: 20px; width: 20px; margin: auto;"></div>';
			}
		}
	</script>
</body>

</html>