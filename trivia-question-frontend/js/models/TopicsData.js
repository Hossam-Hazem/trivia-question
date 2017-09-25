app.factory('Topic', function($resource, consts) {
	var resource = $resource(consts.serverUrlPrefix+'topics/:id');
	return {
		'resource': resource,
		'getTopics': function(success,error){
			return resource.query().$promise.then(success, error);
		}

		} 
})