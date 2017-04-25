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
<title>monitoring</title>

<style>
h1 {
	text-align: center;
	color: #5D5D5D;
}

.jumbotron {
	margin: auto;
	text-align: center;
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
	<h1>server monitoring</h1>
	<br />
	<div class="jumbotron">
		<h1>host name and ip address and os</h1>
		<h4>${ serverInformation.ipAddress }</h4>
		<h4>${ serverInformation.hostName }</h4>
		<h4>${ serverInformation.osName }</h4>
		<br />
		<h1>Memory</h1>
		<h4>${ basicInformation.totalMemory }</h4>
		<h4>${ basicInformation.usedMemory }</h4>
		<h4>${ basicInformation.freeMemory }</h4>
		<br />
		<h1>Disk</h1>
		<h4>${ basicInformation.totalDisk }</h4>
		<h4>${ basicInformation.usedDisk }</h4>
		<h4>${ basicInformation.freeDisk }</h4>
	</div>
</body>

</html>
