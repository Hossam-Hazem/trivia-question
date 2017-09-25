app.factory('Topic', function($resource) {
	var resource = $resource("http://localhost:8080/trivia-question/api/topics/:id");
	return {
		'resource': resource,
		'getTopics': function(success,error){
			return resource.query().$promise.then(success, error);
		}

		} 
})