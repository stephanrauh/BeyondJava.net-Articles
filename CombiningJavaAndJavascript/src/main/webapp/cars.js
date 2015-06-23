angular.module("AutoApp", [ "angularfaces", "smart-table" ])
.controller('AutoController', ['$scope', function($scope) {
   initJSFScope($scope);
   
   $scope.neuesFahrzeug = function() {
	  var fahrzeug = {hersteller:$scope.carPool.hersteller, typ:$scope.carPool.typ, baujahr:$scope.carPool.baujahr};
	  $scope.carPool.cars.push(fahrzeug);
   }
    
   // Settings for the SmartTable widget    
   $scope.itemsByPage=15;
}]);