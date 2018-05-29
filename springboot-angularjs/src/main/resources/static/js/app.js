var app = angular.module('demo', []);
app.controller("usersController", function($scope,$http) {
	$scope.searchUser = function(name){
				$scope.gitDataflag = true;
		$http({
		    method: "GET",
		    url: "http://localhost:8080/getValue",
		    params: {
		        user : name
		    }
		}).then(function(response){
			if(response.status==200){
				$scope.myVar=false;
			$scope.gitData = response.data;}
			else{
				$scope.gitDataflag = false;
				$scope.gitData=[];
				$scope.myVar=true;
			}
		})
}
	
	
});



