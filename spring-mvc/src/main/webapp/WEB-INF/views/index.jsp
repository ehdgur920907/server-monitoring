<!DOCTYPE html>
<html>

<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<title>index</title>

<style>
h1 {
	text-align: center;
	color: #5D5D5D;
}

.jumbotron {
	margin: auto;
	text-align: center;
}

.status-normal {
	border-radius: 50% 50%;
	-moz-border-radius: 50% 50%;
	-webkit-border-radius: 50% 50%;
	background: #1DDB16;
	height: 20px;
	width: 20px;
	margin: auto;
}

.status-danger {
	border-radius: 50% 50%;
	-moz-border-radius: 50% 50%;
	-webkit-border-radius: 50% 50%;
	background: #FF0000;
	height: 20px;
	width: 20px;
	margin: auto;
}

.status-warning {
	border-radius: 50% 50%;
	-moz-border-radius: 50% 50%;
	-webkit-border-radius: 50% 50%;
	background: #FFBB00;
	height: 20px;
	width: 20px;
	margin: auto;
}
</style>
</head>

<body>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="/"><span
					class="glyphicon glyphicon-home" aria-hidden="true"></span></a>
			</div>
		</div>
	</nav>
	<h1>server list</h1>
	<br />
	<div class="jumbotron">
		<table class="table table-hover table-striped table-bordered"
			style="width: 500px; margin: auto;">
			<thead>
				<tr style="background-color: white;">
					<th style="text-align: center; vertical-align: middle;">server
						id</th>
					<th style="text-align: center; vertical-align: middle;">host
						name</th>
					<th style="text-align: center; vertical-align: middle;">ip
						address</th>
					<th style="text-align: center; vertical-align: middle;">os</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="arrayListServerInformation" items="${ arrayListServerInformation }" varStatus="status">
					<tr onclick="location.href='/monitoring/${ arrayListServerInformation[0].id }'" style="cursor: pointer;">
						<td style="text-align: center;">${ arrayListServerInformation[0].id }</td>
						<td style="text-align: center;">${ arrayListServerInformation[0].hostName }</td>
						<td style="text-align: center;">${ arrayListServerInformation[0].ipAddress }</td>
						<td style="text-align: center;">${ arrayListServerInformation[0].osName }</td>
					</tr>
					<tr onclick="location.href='/monitoring/${ arrayListServerInformation[1].id }'" style="cursor: pointer;">
						<td style="text-align: center;">${ arrayListServerInformation[1].id }</td>
						<td style="text-align: center;">${ arrayListServerInformation[1].hostName }</td>
						<td style="text-align: center;">${ arrayListServerInformation[1].ipAddress }</td>
						<td style="text-align: center;">${ arrayListServerInformation[1].osName }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>

</html>
