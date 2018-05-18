<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<style>
* {
	box-sizing: border-box;
}

body {
	font-family: sans-serif;
}

div.ex3 {
	background-color: white;
	height: 600px;
	width: 500px;
	overflow-y: scroll;
}

/* ---- grid ---- */
.content {
	overflow: hidden;
	height: 750px;
	border: 3px solid black;
	display: block;
	position: relative;
}

.content .showMore {
	position: absolute;
	bottom: 0px;
	background: black;
	width: 100%;
	left: 0px;
	text-align: center;
	font-size: 20px;
}

.content .showLess {
	position: absolute;
	bottom: 0px;
	background: black;
	display: none;
	width: 100%;
	left: 0px;
	text-align: center;
	font-size: 20px;
}

.show {
	height: auto;
	display: block;
	position: relative;
}

.grid {
	max-width: 1200px;
}

/* clearfix */
.grid:after {
	content: '';
	display: block;
	clear: both;
}

/* ---- grid-item ---- */
.grid-item {
	float: left;
}

.grid-item--width2 {
	width: auto;
	margin: 15px;
}

.grid-item--height2 {
	height: 200px;
}

.hidden {
	display: none;
}
</style>

</head>
<body>

	<nav class="navbar" role="navigation" style="background: #6b5b95">
		<div class="panel-heading" style="color: whitesmoke">

			<a href="#/login" class="btn btn-xs btn-primary pull-right"
				style="margin: 15px; margin-top: -1px; background-color: violet; padding: 7px 7px; font-size: 17px; line-height: 1.5; border-radius: 6px;"><span
				class="glyphicon glyphicon-log-out"></span> Logout</a> <a
				href="#/pic/upload" class="btn btn-xs btn-primary pull-right"
				style="margin: 15px; margin-top: -1px; background-color: violet; padding: 7px 7px; font-size: 17px; line-height: 1.5; border-radius: 6px;"><span
				class="glyphicon glyphicon-backward"></span> Back to Upload Page</a>
			<h2 class="fontfamily" align="center" style="margin-left: 302px;">
				<strong>ALL BLOGS</strong>
			</h2>
		</div>
	</nav>

	<div style="background: #efeff0; margin-left: 103px;">


		<div class=' col-md-4 ex3' align="center"
			data-masonry='{ "itemSelector": ".grid-item"}'
			ng-repeat="i in place1['placeDetailsDTOList']"
			style="background: white">

			<div>
				<img src={{i[ 'fileName']}} style="height: 350px; width: 800px"
					alt="No image available"
					class="img-responsive img-box img-thumbnail"></img>
			</div>
			<div class=' col-md-12' align="center"
				style="font-weight: bold; margin-right: 565px">
				<h2>{{i['placeName']}}</h2>
			</div>
			<div align="center">Category:{{i['category']}}</div>
			<div align="center">Month Visited:{{i['monthVisited']}}</div>
			<br>
			<div align="center" style="text-align: left; padding: 36px;">
				{{i['placeDescription']}}	
			</div>

		</div>

		<div align="center">{{i['usersInterested']}}</div>
	</div>
</body>
</html>
