app.factory('Auth', function($rootScope){
	
	var user;

	return{
	    setUser : function(aUser){
	    	console.log("user logged in")
	        user = aUser;
	        $rootScope.$broadcast('userLoggedIn');
	    },
	    getUser: function(){
	    	return user;
	    },
	    isLoggedIn : function(){
	        return(user)? user : false;
	    },
	    getUserName: function(){
	    	return user.name;
	    },
	    getUserId: function(){
	    	return user.id;
	    },
	    getUserEmail: function(){
	    	return user.email;
	    },
	    logOut: function(){
	    	user = null;
	    	$rootScope.$broadcast('userLoggedOut');
	    },
	    isAdmin: function(){
	    	return user && user.isAdmin;
	    }
	  }
})