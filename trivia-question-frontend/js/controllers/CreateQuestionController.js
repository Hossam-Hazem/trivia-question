app.controller('CreateQuestionController',
	function CreateQuestionController($scope,Topic, Auth){
		$scope.question = {};
		Topic.getTopics(function(response){
			$scope.topics = response;
			$scope.topics.forEach(function(topic, index){
				topic.selected = false;
			})
		},
		function(response){
			console.log(response);
		})

		$scope.selectTopicToggle = function(topicId){
			$scope.topics.forEach(function(topic, index){
				if(topic.id == topicId){
					topic.selected = !topic.selected;
				}
			})
		}

		var choice = {'id':0, 'text': ""};
		$scope.question.choices = [];
		$scope.question.choices.push(choice);

		$scope.question.correctChoice = 0;

		$scope.addChoice = function(){
			var id = $scope.question.choices.length;
			var newChoice = {'id': id, 'text': ""};
			$scope.question.choices.push(newChoice);
		}

		$scope.isAnyTopicSelected = function(){
		  return !$scope.topics.some(function(topics){
		    return topics.selected;
		  });
		}

		$scope.submitQuestion = function(){
			$scope.question.topics = [];
			$scope.topics.forEach(function(topic, index){
				if(topic.selected){
					$scope.question.topics.push({'id': topic.id});
				}
			})

			console.log($scope.question);
			// $http(
			// 	{
			// 		method:"POST",
			// 		url:"http://localhost:8080/trivia-question/api/questions",
			// 		data: $scope.question
			// 	}).
			// success(function(response){
			// 	console.log(response)
			// }).
			// error(function(response){
			// 	console.log(response);
			// })
			
		}
})