app.controller('QuizController',
	function QuizController($scope, $http, QuizService, Auth, $location){
		$scope.quiz = QuizService.getCurrentQuiz();
		$scope.mySlider;
		$scope.bodyIsReady = function(){
			$scope.mySlider = Slider('.questionSlider', $scope);
			console.log("slider is ready")
		}
	  	$scope.ngRepeatCallback = function(){}
		$scope.quiz.answers = [];
		$scope.quiz.submittedQuestions = [];
		$scope.quiz.submittedQuestionsCount = 0;
		console.log($scope.quiz);
		$scope.submitQuestion = function(questionIndex){
			if(!$scope.quiz.submittedQuestions[questionIndex]){
				QuizService.submitQuestion(questionIndex, function(response){
					$scope.quiz.submittedQuestionsCount++;
					$scope.quiz.submittedQuestions[questionIndex] = true;
					var questionButton = angular.element( document.querySelector( '.quizButton#submitButton'+questionIndex ) );
					var questionIcon = angular.element( document.querySelector( '.icon#i'+questionIndex ) );
					if(response.data.isCorrect){
						questionButton.addClass('correct');
						questionButton.html("Correct!");
						questionIcon.addClass('correct');
					}
					else{
						questionButton.addClass('inCorrect');
						questionButton.html("Not correct :(");
						questionIcon.addClass('inCorrect');
					}
				},
				function(response){
					console.log(response);
				});
			}
		}
		$scope.submitQuiz  = function(){
			console.log("submit quizButton")
			QuizService.submitQuiz(function(response){
				$location.path('/score');
			},
			function(response){
				
			});
		}

		$scope.nextQuestion = function(){
			$scope.mySlider.next();
		}
		$scope.prevQuestion = function(){
			$scope.mySlider.previous();
		}

		$scope.isFinished = function(){
			console.log()
			return $scope.quiz.submittedQuestionsCount == $scope.quiz.questions.length
		}
	})
