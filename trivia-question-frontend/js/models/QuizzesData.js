app.factory('Quiz', function($resource) {
	var resource = $resource("http://localhost:8080/trivia-question/api/quizzes/:id");
	return {
		'resource': resource

		} 
})