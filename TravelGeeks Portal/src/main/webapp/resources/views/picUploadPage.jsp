<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script type="text/javascript">
</script>

<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<style type="text/css">
...
a {
	text-decoration: underline;
}

h1 {
	font-size: 138.5%;
}

...
blockquote {
	border-left: 2px solid blue;
	margin: 0;
	padding: 0 10px;
}
</style>
</head>
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
						class="glyphicon glyphicon-log-out"></span> LOGOUT</a> <a
						href="#/myuploads/list" class="btn btn-xs btn-primary pull-right"
						style="margin: 15px; margin-top: -1px; background-color: violet; padding: 7px 7px; font-size: 17px; line-height: 1.5; border-radius: 6px;"><span
						class="glyphicon glyphicon-forward"></span>MY BLOGS</a> <a
						href="#/uploads/listAll" class="btn btn-xs btn-primary pull-right"
						style="margin: 15px; margin-top: -1px; background-color: violet; padding: 7px 7px; font-size: 17px; line-height: 1.5; border-radius: 6px;"><span
						class="glyphicon glyphicon-forward"></span> SHOW ALL BLOGS</a> <a
						href="#/uploads/listInterestedAll"
						data-ng-click="getAllInterestedPlacesDetails()"
						class="btn btn-xs btn-primary pull-right"
						style="margin: 15px; margin-top: -1px; background-color: violet; padding: 7px 7px; font-size: 17px; line-height: 1.5; border-radius: 6px;"><span
						class="glyphicon glyphicon-forward"></span> SHOW MY INTERESTS</a>
					<h2 class="fontfamily" align="center" style="margin-left: 302px;">
						<strong>TRAVEL GEEKS</strong>
					</h2>
				</div>
			</nav>


		</div style="background: #efeff0">
		<div class="w3-container" id="about">
			<div class="w3-content"
				style="max-width: 702px; max-width: 702px; margin-lef: 29px; margin-left: 281px;">
				<h5 class="w3-center w3-padding-64" style="font-size: 20px;">
					<span class="w3-tag w3-wide">ABOUT TRAVEL GEEKS</span>
				</h5>
				<p>Travel Geeks is a unique free online travel diary for
					travelers across the world. It works from internet cafes and
					computers world wide, to allow you to update an online travel
					diary, it is free to join and takes just minutes to setup, all you
					need is a working email address.</p>
				<p>Travel Geeks is a collection of tools so that travelers can
					write down a blog, set up automatic mailing lists so that every
					time you add a new entry to your list the signed up users get an
					automatic email. .</p>
				<div class="w3-panel w3-leftbar w3-light-grey">
					<p>
						<i>"The real voyage of discovery consists not in seeking new
							landscapes, but in having new eyes"</i>
					</p>

				</div>
				<div class='Center' align=center right:50%; border="0"
					padding: 6px 12px>
					<iframe width="700" height="450"
						src="https://www.youtube.com/embed/MED4AgH2kk8" frameborder="0"
						allow="autoplay; encrypted-media" allowfullscreen></iframe>
				</div>
		</div>
		</div>


		<form class="form-signin" name="upload"
			onsubmit="uploadPartnerImg(); return false;"
			enctype="multipart/form-data">
			<span id="reauth-email" class="reauth-email"></span>
			<div class="w3-container w3-half w3-margin-top "
				style="margin-left: 148px;">
				<h3>UPLOAD PHOTO</h3>
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="column">
							<div class="col-md-12">
								<div class="form-group row">
									<label class="col-md-12 col-form-label">TITLE</label> <input
										type='text' name='placeName' ng-model='placeName' id='Name'
										class="login_box" required autofocus
										style="margin-left: 15px;">
								</div>
							</div>

							<div class="col-md-12">
								<div class="form-group">
									<label class="col-md-4 col-form-label"
										style="margin-left: -18px;">PLACE DESCRIPTION</label>
									<textarea id=”widgEditor” rows="18" cols="80"
										ng-model='placeDescription' id='placeDescription'
										class="login_box" required></textarea>


								</div>
							</div>

							<div class="col-md-12">
								<div class="form-group">
									<label class="col-md-12 col-form-label"
										style="margin-left: -15px;">MONTH VISITED</label> <input
										type="text" name='monthVisited' ng-model='monthVisited'
										id='monthVisited' class="login_box" required
										style="margin-left: 1px;">
									<!-- <input type="datetime-local" name='pickUpTime' ng-model='pickUpTime' id='pickUpTime' class="login_box"  required> -->
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label class="input_title" style="margin-left: 16px;">Upload
										Place Photo</label> <input type='file' accept="image/*"
										data-ng-model="custFile"
										onchange="angular.element(this).scope().customerFileOnChange(this)"
										data-classButton="btn btn-link" ngf-select required
										data-input="false" name="file" style="margin-left: 16px;" />
								</div>
							</div>
							<div class="col-md-12">
								<a href="javascript:void(0)"
									data-ng-click="uploadCustomerFile(custFile)"
									class="btn btn-sm btn-primary" style="margin-left: 16px;">
									<span class="glyphicon glyphicon-upload"></span>Upload
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>


		<br /> <br />
		<div ng-show="isDataPresent">
			<div class="container-fluid">


				<div class="row">

				</div>


			</div>

		</div>





		<div
			style="position: absolute; top: 164px; right: 0%; margin-left: 10px;">


			<div>
				<img src="http://redhint.com/wp-content/uploads/2017/09/Twitter.jpg"
					alt="Smiley face" width="70" height="70">
			</div>
			<a class="twitter-timeline"
				href="https://twitter.com/search?q=%23travel%20diaries"
				data-widget-id="990362128606314496">Tweets about #travel diaries</a>
			<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+"://platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");</script>

			<div>
				<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+"://platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");</script>
				<br>
			</div>
			</br>
			</br>
			<div>
				<img
					src="http://www.freeiconspng.com/uploads/facebook-transparent-like-us-31.jpg"
					width="250" height="60">
			</div>
			<div>
				<iframe
					src="http://www.facebook.com/plugins/likebox.php?href=https://www.facebook.com/Travel-Diaries-153775005461130/	 &width=400&colorscheme=light&show_faces=true&border_color&stream=true&header=true&height=700"
					scrolling="yes" frameborder="0"
					style="border: none; overflow: hidden; width: 600px; height: 630px; background: white; float: left;"
					allowtransparency="true"></iframe>
			</div>
			<div>
				<iframe width="400px" padding="60px" height="500px"
					src="https://console.dialogflow.com/api-client/demo/embedded/efe5ff56-c626-4127-b78a-d22573e7f976">
				</iframe>
			</div>
		</div>

</body>
</html>
