app.controller('SignInController',
	function SignInController($scope, $http, $cookies, Auth,$location){
		$scope.user = {};
		$scope.signInStatus = false;
		$scope.submit = function(requestData){
			$http(
				{
					method:"POST",
					url:"http://localhost:8080/trivia-question/api/users/signin",
					data: requestData
				}).
			success(function(response){
				console.log(response)
				if(response.status == "SUCCESS"){
					console.log(response.data);
					Auth.setUser(response.data)
					console.log("signin success");
					$location.path('/home');
				}
				else{

					$scope.form.password.$setValidity(response.status, false);
				}
			}).
			error(function(response){
				console.log(response);
				console.log("signin failed")
			})
		}
	})