<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<style>
background-color
:
 
gainsboro
;
</style>
<body>
	<div data-ng-app="SeekersApp" data-ng-controller="loginCtrl"
		style="background-image: url(/resources/views/travel-diaries-922x613.jpg); top: -20px; height: 450px;">

		<div class="alert alert-danger" data-ng-show="showErrorAlert">
			<button type="button" class="close"
				data-ng-click="switchBool('showErrorAlert')">×</button>
			<strong>Error!</strong> {{errorTextAlert}}
		</div>

		<div class=""
			style="position: absolute; top: 68px; right: 5%; width: 517px; background-color: white; margin: 395px;">
			<h1 class="welcome text-left col-md-11 multi-horizontal">Create
				an Account</h1>
			<div class="card card-container">
				<!--  <h2 class='login_title text-center title-login'>Login</h2> -->
				<!-- <hr> -->
				<form class="form-signin" name="signup" ng-submit="signUpForm()">


					<span id="reauth-email" class="reauth-email"></span>


					<div class="col-md-11 multi-horizontal" data-for="role"></div>
					<div class="col-md-11 multi-horizontal"
						data-for="Business/Charity Name">
						<div class="form-group">
							<label class="form-control-label mbr-fonts-style display-7"
								for="name-form1-4t">Name</label> <input type='text' name='Name'
								data-ng-model="name" id='Name' class="login_box form-control"
								required>

						</div>
					</div>


					<div class="col-md-11 multi-horizontal" data-for="Address">
						<div class="form-group">
							<label class="form-control-label mbr-fonts-style display-7"
								for="name-form1-4t">Address</label> <input type='text'
								name='address' data-ng-model="address" id='address'
								class="login_box form-control" required>
						</div>
					</div>


					<div class="col-md-11 multi-horizontal" data-for="City">
						<div class="form-group">
							<label class="form-control-label mbr-fonts-style display-7"
								for="name-form1-4t">City</label> <input type='text' name='city'
								data-ng-model="city" id='city' class="login_box form-control"
								required>
						</div>
					</div>


					<div class="col-md-11 multi-horizontal" data-for="Zipcode">
						<div class="form-group">
							<label class="form-control-label mbr-fonts-style display-7"
								for="name-form1-4t">Zipcode</label> <input type='text'
								name='zipcode' data-ng-model="zipcode" id='zipcode'
								class="login_box form-control" required>
						</div>
					</div>



					<div class="col-md-11 multi-horizontal" data-for="Email">
						<div class="form-group">
							<label class="form-control-label mbr-fonts-style display-7"
								for="name-form1-4t">Email</label> <input type='text'
								name='userName' data-ng-model="userName" id='userName'
								class="login_box form-control" required>
						</div>
					</div>


					<div class="col-md-11 multi-horizontal" data-for="Password">
						<div class="form-group">
							<label class="form-control-label mbr-fonts-style display-7"
								for="name-form1-4t">Password</label> <input type='password'
								name='password' data-ng-model="password" id='password'
								class="login_box form-control" required>
						</div>
					</div>
					<div class="col-md-11 multi-horizontal">
						<div class="form-group" style="align: center;" data-for="Interest"">

							<label class="form-control-label mbr-fonts-style display-7"
								for="name-form1-4t">Travel Interests</label><br> <input
								type='checkbox' name='Category1' value="Mountains"
								data-ng-model="mountains" id='mountains'>Mountains<br>
							<input type='checkbox' name='Category2' value="Beach"
								data-ng-model="beach" id='beach'>Beach<br> <input
								type='checkbox' name='Category3' value="Forest"
								data-ng-model="forest" id='forest'>Forest<br> <input
								type='checkbox' name='Category4' value="Architectrure"
								data-ng-model="architecture" id='architecture'>Architecture<br>
						</div>

					</div>

					<br />
					<!--  style="padding: 25px;" -->

					<div class="row col-md-11 multi-horizontal">
						<div class="col-md-4 buttons-login multi-horizontal">
							<input name="submit" type="submit" id="submitForm"
								value="Sign Up" class="btn btn-primary btn-sm" />
						</div>

						<div class="col-md-4 buttons-login multi-horizontal">

							<input name="cancel" type="reset" id="resetForm" value="Cancel"
								class="btn btn-primary btn-sm"
								onclick="window.location='/';return false;" />
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>