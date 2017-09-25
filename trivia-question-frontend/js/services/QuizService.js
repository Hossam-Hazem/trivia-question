app.factory('QuizService', function($http, consts){
	
	var currentQuiz;

	return{
	    'start': function(userId, topicId, success, error){
			return $http({
					  method: 'POST',
					  url: consts.serverUrlPrefix+'quizzes/start',
					  data: {'user_id': parseInt(userId), 'topic_id':parseInt(topicId)}
					}).then(function(response){
						if(response.data.status == "SUCCESS"){
							currentQuiz = response.data.data;
						}
						if(_.isFunction(success)){
							success(response)
						}
					}, function(response){
						if(_.isFunction(error)){
							error(response)
						}
					});
		},
		'setCurrentQuiz': function(quiz){
			currentQuiz = quiz;
		},
		'getCurrentQuiz': function(){
			return currentQuiz;
		},
		'getQuestions': function(){
			return currentQuiz.questions;
		},
		'submitQuestion': function(questionIndex, success, error){
			var choiceId = currentQuiz.answers[questionIndex].choiceId;
			return $http({
					  method: 'POST',
					  url: consts.serverUrlPrefix+'answers/submit',
					  data: {'quiz_id': parseInt(currentQuiz.id), 'choice_id':parseInt(choiceId)}
					}).then(function(response){
						if(_.isFunction(success)){
							success(response.data)
						}
					}, function(response){
						if(_.isFunction(error)){
							error(response)
						}
					});
		},
		'submitQuiz': function(success, error){
			return $http({
					  method: 'POST',
					  url: consts.serverUrlPrefix+'quizzes/submit',
					  data: {'quiz_id': parseInt(currentQuiz.id)}
					}).then(function(response){
						console.log(response)
						response = response.data;
						if(response.status == "FINISHED"){
							currentQuiz.score = response.data.score;
							currentQuiz.totalQuestions = currentQuiz.questions.length;
							currentQuiz.isSubmitted = true;
							if(_.isFunction(success)){
								success(response.data)
							}
						}
					}, function(response){
						if(_.isFunction(error)){
							error(response)
						}
					});
		},
		'isGoodScore': function(){
			return currentQuiz.score/currentQuiz.totalQuestions >= 0.7;
		}
	  }
})