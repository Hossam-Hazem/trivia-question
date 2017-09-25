app.controller('HomeController',
	function HomeController($scope,Topic, $http, $location, QuizService, Auth){
		$scope.topics = {};
		Topic.getTopics(function(topicsList){
			$scope.topics.data = topicsList;
		},
		function(response){
			console.log(response);
		})
		$scope.selectTopic = function(topicId){
			QuizService.start(Auth.getUserId(), topicId,function(response){
				$location.path('/quiz');
			})
			
		}
	})