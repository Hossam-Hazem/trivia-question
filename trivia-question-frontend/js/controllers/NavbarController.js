app.controller('NavbarController',
	function NavbarController($scope, $location, Auth){
		$scope.isActive = function(location){
			return location == $location.path()
		}

		$scope.user = Auth.getUser();

		$scope.logOut = function(){
			Auth.logOut();
		}

		$scope.$on('userLoggedIn', function(event, args) {

		    $scope.user = Auth.getUser();
		});


		
	})