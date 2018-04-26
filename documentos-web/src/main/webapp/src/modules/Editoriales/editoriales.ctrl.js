(function (ng) {

    var mod = ng.module("editorialesModule");

    mod.controller("editorialesCtrl", ['$scope','$rootScope' , '$state', '$stateParams', '$http', 'citiesContext', function ($scope,$rootScope ,$state, $stateParams, $http, context) {
$rootScope.home=1;
            $scope.data = {};
            $scope.dataActuaBasico={};
            
            $http.get("http://localhost:8080/documentos-web/api/editoriales").then(function (response) 
            {
                $scope.editoriales = response.data;
            });
            $scope.create= function()
            {
               
               $http.post("http://localhost:8080/documentos-web/api/editoriales",$scope.data).then(function (response) 
            {
              $state.reload();
            });
            
            };
            $scope.update_editorial= function(id)
            {
                $http.put("http://localhost:8080/documentos-web/api/editoriales/"+$scope.dataActuaBasico.id ,$scope.dataActuaBasico).then(function (response) 
            {
              $state.reload();
            });
            
            };
           $scope.delete= function(id)
            {
                $http.delete("http://localhost:8080/documentos-web/api/editoriales/"+id ).then(function (response) 
            {
              $state.reload();
            });
            
            };
          
        }]);
})(window.angular);
