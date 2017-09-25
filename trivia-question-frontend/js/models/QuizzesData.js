app.factory('Quiz', function($resource, consts) {
	var resource = $resource(consts.serverUrlPrefix+'quizzes/:id');
	return {
		'resource': resource

		} 
})