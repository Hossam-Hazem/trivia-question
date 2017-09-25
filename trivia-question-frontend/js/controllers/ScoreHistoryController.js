app.controller('ScoreHistoryController',
	function ScoreHistoryController($scope, $http, Auth, consts){
		$scope.quizzes = [];
		$scope.quizzes.push({
            "score": -1,
            "questionsCount": -1,
            "topic": "Sports",
            "id": 1
        });
		$http(	
				{
					method:"GET",
					url:consts.serverUrlPrefix+'quizzes?userId='+Auth.getUserId(),
				}).
			success(function(response){
				$scope.quizzes = response.quizzes;
				$scope.quizzes.forEach(function(quiz){
					if(quiz.score == -1){
						quiz.status = "under review";
					}
					else{
						quiz.status = "completed"
					}
				})
			}).
			error(function(response){
				console.log(response);
			})
	})