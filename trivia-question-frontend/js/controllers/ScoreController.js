app.controller('ScoreController',
	function ScoreController($scope, Auth, QuizService, $location){
		$scope.quiz = QuizService.getCurrentQuiz();
		$scope.user = Auth.getUser();
		$scope.newQuiz = function(){
			$location.path('/home');
		}
		$scope.isGoodScore = function(){
			return QuizService.isGoodScore();
		}
	})