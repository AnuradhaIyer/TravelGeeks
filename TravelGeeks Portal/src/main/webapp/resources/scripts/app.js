var app = angular.module('SeekersApp', [ 'ngRoute', 'ngCookies', 'ngFileUpload','chart.js' ]);

app.config(function($routeProvider) {

	$routeProvider.when('/', {
		templateUrl : '/resources/views/login.jsp',
		controller : 'loginCtrl'
	}).when('/signup', {
		templateUrl : '/resources/views/signUp.jsp',
		controller : 'loginCtrl'
	}).when('/myuploads/list', {
		templateUrl : '/resources/views/myUploadsPage.jsp',
		controller : 'businessPageCtrl'
	}).when('/uploads/listAll', {
		templateUrl : '/resources/views/allUploadsPage.jsp',
		controller : 'businessPageCtrl'
	}).when('/uploads/listInterestedAll', {
		templateUrl : '/resources/views/interestedUploadsPage.jsp',
		controller : 'businessPageCtrl'
	}).when('/pic/upload', {
		templateUrl : '/resources/views/picUploadPage.jsp',
		controller : 'businessPageCtrl'
	}).otherwise({
		redirectTo : "/"
	});
});

app.controller("loginCtrl",
				[
						'$scope',
						'$http',
						'$location',
						'$routeParams',
						'AuthenticationService',
						function($scope, $http, $location, $routeParams, AuthenticationService) {
					
							AuthenticationService.ClearCredentials();
							
							$scope.loginForm = function() {
								AuthenticationService.Login($scope.userName,$scope.password,function(response) {
													if (response.data.statusCode === '200') {					
														AuthenticationService.SetCredentials($scope.userName);
														//TODO : check role and navigate to upload or charity list
														if (response.data.role === 'Traveller') {
														$location.path('/pic/upload');
														}
													} else {
														$scope.showErrorAlert = true;
														$scope.errorTextAlert = "Username/password is incorrect";
													}
												})
							};

							$scope.signUpForm = function() {
								AuthenticationService.signUp($scope.userName,$scope.password,$scope.role,$scope.name,$scope.address,$scope.city,$scope.zipcode,$scope.mountains, $scope.beach, $scope.architecture, $scope.forest, function(response) {
													if (response.data.statusCode === '200') {
														AuthenticationService.SetCredentials($scope.userName, response.data.role, response.data.name, response.data.address,response.data.city,response.data.zipcode);
														$location.path('/login');
													} else {
														$scope.showErrorAlert = true;
														$scope.errorTextAlert = "User already exists";
													}
												})
							};

							$scope.switchBool = function(value) {
								$scope[value] = !$scope[value];
							};
						} ]);

app.factory('AuthenticationService', [
	'$http',
	'$cookieStore',
	'$rootScope',
	'$timeout',
	function($http, $cookieStore, $rootScope, $timeout) {
		var service = {};

		service.Login = function(userName, password, callback) {

			var req = {
				method : 'POST',
				url : "/login",
				data : {
					userName : userName,
					password : password
				},
				headers : {
					'Content-Type' : "application/json"
				}
			};
			$http(req).then(function(response) {
				callback(response);
			}, function(error) {
				callback(error);
			});
		};

		service.SetCredentials = function(userName, role, name,
				address,city,zipcode) {
			var authdata = userName;

			$rootScope.globals = {
				currentUser : {
					userName : userName,
					authdata : authdata
				}
			};

			$http.defaults.headers.common['Authorization'] = 'Basic '+ authdata;
			$cookieStore.put('globals', $rootScope.globals);
		};

		service.ClearCredentials = function() {
			$rootScope.globals = {};
			$cookieStore.remove('globals');
			$http.defaults.headers.common.Authorization = 'Basic ';
		};

		service.signUp = function(userName, password, role, name,
				address, city, zipcode, mountains, beach, architecture, forest,  callback) {

			var req = {
				method : 'POST',
				url : "/signup",
				data : {
					userName : userName,
					password : password,
					role: role,
					name : name,
					address : address,
					city: city,
					zipcode: zipcode,
					mountains: mountains,
					beach: beach,
					architecture: architecture,
					forest: forest

				},
				headers : {
					'Content-Type' : "application/json"
				}
			};
			$http(req).then(function(response) {
				callback(response);
			}, function(error) {
				callback(error);
			});
		};

		return service;
	} ]);


app.controller("businessPageCtrl", [
	'Upload',
	'$scope',
	'$rootScope',
	'$http',
	'$location',
	'$routeParams',
	'$q',
	'$cookieStore',
	function(Upload, $scope,$rootScope, $http, $location, $routeParams, $q, $cookieStore) {
		
		$scope.customerFileOnChange = function(element) {

			var reader = new FileReader();
			reader.readAsDataURL(element.files[0]);
		};
		var init = function() {
				$scope.authData = $cookieStore.get('globals');
			if (!$scope.authData) {
				$location.path('/');
			}
			getPlaceDetails();
			getAllPlacesDetails();
			$scope.getAllInterestedPlacesDetails();			
		};

		$scope.uploadCustomerFile = function(file) {
			
			
			if(file == null) {
				$scope.showErrorAlert = true;
				$scope.errorTextAlert = "No File to upload!";
				return;
			}
			
			if(file.size > 10485760) {
				$scope.showErrorAlert = true;
				$scope.errorTextAlert = "Failed to upload! File Size is more than 10MB!";
				return;
			}
			file.upload = Upload.upload({
				url : "/upload",
				method : "POST",
				data : {
					placeFile : file,
					fileName : file.name,
					userName : $scope.authData.currentUser.userName,
					placeName : $scope.placeName,
					placeDescription : $scope.placeDescription,
					monthVisited : $scope.monthVisited

				},
				headers : {
					'Content-Type' : "application/json"
				}
			});
			
			file.upload.then(function(response) {
				$scope.showSuccessAlert = true;
				$scope.successTextAlert = "Successfully uploaded the file";
				$scope.isDataPresent = true;
				$scope.custFile = null;
				$scope.placeName = "";
				$scope.placeDescription = "";
				$scope.monthVisited = "";
			}, function(response) {
				$scope.showErrorAlert = true;
				$scope.errorTextAlert = "File upload failed! Please try again after sometime!";
			});
		};
		
		var getPlaceDetails = function() {
			var req = {
					url : "/myuploadsList",
					type : "GET",	
					params : {
						userName : $scope.authData.currentUser.userName
						
					},
					headers : {
						'Content-Type' : "application/json"
					}
			}
			$http(req).then(function(response) {
				
				$scope.place = response.data;
				$scope.isDataPresent = true;
			}, function(error) {
				$scope.isDataPresent = false;
			});
		};

		 var getAllPlacesDetails = function() {
			var req = {
					url : "/alluploadslist",
					type : "GET",	
					params : {
						userName : $scope.authData.currentUser.userName
						
					},
					headers : {
						'Content-Type' : "application/json"
					}
			}
			$http(req).then(function(response) {
				$scope.place1 = response.data;
				$scope.isDataPresent = true;
			}, function(error) {
				$scope.isDataPresent = false;
			});
		};
		
		$scope.getAllInterestedPlacesDetails = function() {
			var req = {
					url : "/interestedPlacesList",
					type : "GET",	
					params : {
						userName : $scope.authData.currentUser.userName
						
					},
					headers : {
						'Content-Type' : "application/json"
					}
			}
			$http(req).then(function(response) {
				$scope.place2 = response.data;
				$scope.isDataPresent = true;
			}, function(error) {
				$scope.isDataPresent = false;
			});
		};
		
		init();
		$scope.switchBool = function (value) {
	        $scope[value] = !$scope[value];
	    };
	    
	} ]);


app.controller("charityPageCtrl", [ '$scope','$rootScope', '$http', '$location', '$routeParams',
	'$q', '$cookieStore',
	function($scope, $rootScope,$http, $location, $routeParams, $q, $cookieStore) {

		var init = function() {
			$scope.authData = $cookieStore.get('globals');

			getPlaceDetails();
		};
		var getPlaceDetails = function() {
			var req = {
					method : 'GET',
					url : "/charityList",
					params : {
						userName : $scope.authData.currentUser.userName
					},
					headers : {
						'Content-Type' : "application/json"
					}
			};
			
			$http(req).then(function(response) {
				
				$scope.placeDetails = response.data;
				$scope.isDataPresent = true;
			}, function(error) {
				
				$scope.isDataPresent = false;
			});
		};

		$scope.updateStatus =function(a,status){
		
			var req = {
					method : 'POST',
					url : "/updateStatus",
					data : {
						requestId : a.requestId,
						status : status,
						userName : $scope.authData.currentUser.userName
					},
					headers : {
						'Content-Type' : "application/json"
					}
				};
			$http(req).then(function(response) {
				//$scope.placeDetails = response.data;
				$scope.isDataPresent = true;
			}, function(error) {
				$scope.isDataPresent = false;
			});
		};
		
		$scope.switchBool = function(value) {
			$scope[value] = !$scope[value];
		};
		init();
	} ]);

