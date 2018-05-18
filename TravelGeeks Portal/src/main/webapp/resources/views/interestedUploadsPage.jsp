<!DOCTYPE html>
<html>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<style>
body, h1 {
	font-family: "Montserrat", sans-serif
}

img {
	margin-bottom: -7px
}

.w3-row-padding img {
	margin-bottom: 12px
}

.grid-item {
	width: 00px;
}

.grid-item--width2 {
	width: 600px;
}

div.ex3 {
	background-color: white;
	height: 300px;
	width: 500px;
	overflow-y: scroll;
}
</style>
<body>
	<div data-ng-app="SeekersApp" data-ng-controller="businessPageCtrl">
		<div class="container-fluid">
			<div class="alert alert-success" ng-show="showSuccessAlert">


				<button type="button" class="close"
					data-ng-click="switchBool('showSuccessAlert')">×</button>
				<strong>Done!</strong> {{successTextAlert}}
			</div>

			<div class="alert alert-danger" ng-show="showErrorAlert">
				<button type="button" class="close"
					data-ng-click="switchBool('showErrorAlert')">×</button>
				<strong>Error!</strong> {{errorTextAlert}}
			</div>


			<nav class="navbar" role="navigation" style="background: #6b5b95">
				<div class="panel-heading" style="color: whitesmoke">

					<a href="#/login" class="btn btn-xs btn-primary pull-right"
						style="margin: 15px; margin-top: -1px; background-color: violet; padding: 7px 7px; font-size: 17px; line-height: 1.5; border-radius: 6px;"><span
						class="glyphicon glyphicon-log-out"></span> Logout</a> <a
						href="#/pic/upload" class="btn btn-xs btn-primary pull-right"
						style="margin: 15px; margin-top: -1px; background-color: violet; padding: 7px 7px; font-size: 17px; line-height: 1.5; border-radius: 6px;"><span
						class="glyphicon glyphicon-backward"></span> Back to Upload Page</a>
					<h2 class="fontfamily" align="center">
						<strong>MY INTERESTS</strong>
					</h2>
				</div>
			</nav>

			<div ng-show="isDataPresent">

				<div style="background: #efeff0; margin-left: 103px;">


					<div class=' col-md-4 ex3' align="center"
						data-masonry='{ "itemSelector": ".grid-item"}'
						ng-repeat="i in place2['placeDetailsDTOList']"
						style="background: white">

						<div>
							<img src={{i[ 'fileName']}} style="height: 400px; width: 500px"
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
</body>
</html>
