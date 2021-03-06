app.directive('ngElementReady', function($timeout) {
    return {
        priority: Number.MIN_SAFE_INTEGER, // execute last, after all other directives if any.
        restrict: "A",
        link: function($scope, $element, $attributes) {
        	$timeout(function() {
              	$scope.$eval($attributes.ngElementReady); // execute the expression in the attribute.
          	});
           
        }
    };
});