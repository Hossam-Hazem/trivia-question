var signInRoute = '/signin';
var quizRoute = '/quiz';
var homeRoute = '/home';
var scoreRoute = '/score';
var createQuestionRoute = '/createQuestion';
var scoreHistoryRoute = '/history';

var app = angular.module('triviaQuestionApp', ['ngResource','ngRoute','ngCookies', 'ngMessages'])
	.config(function($routeProvider){
		$routeProvider.when(homeRoute,
		{
			templateUrl: 'templates/home.html',
			controller: 'HomeController'
		});
		$routeProvider.when(signInRoute,
		{
			templateUrl: 'templates/SignIn.html',
			controller: 'SignInController'
		});
		$routeProvider.when(quizRoute,
		{
			templateUrl: 'templates/quiz.html',
			controller: 'QuizController'
		})
		$routeProvider.when(scoreRoute,
		{
			templateUrl: 'templates/score.html',
			controller: 'ScoreController'
		})
		$routeProvider.when(createQuestionRoute,
		{
			templateUrl: 'templates/createQuestion.html',
			controller: 'CreateQuestionController'
		})
		$routeProvider.when(scoreHistoryRoute,
		{
			templateUrl: 'templates/scoreHistory.html',
			controller: 'ScoreHistoryController'
		})
		$routeProvider.otherwise({redirectTo:signInRoute})
	})

app.run(function ($rootScope, $location, Auth) {
    $rootScope.$on('$routeChangeStart', function (event, next) {

    	console.log(next.originalPath)
    	console.log(Auth.isAdmin())

        if (next.originalPath != signInRoute && !Auth.isLoggedIn()) {
            console.log('DENY');
            event.preventDefault();
            $location.path('/signin');
        }
        else {
        	if(next.originalPath == "/createQuestion" && !Auth.isAdmin()){
        		console.log('DENY');
           		event.preventDefault();
        	}
            console.log('ALLOW');
        }
    });
    $rootScope.$on('userLoggedOut', function(event){
    	$location.path('/signin');
    })
});

	console.log("bleh");

