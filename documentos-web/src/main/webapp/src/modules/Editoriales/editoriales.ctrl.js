(function (ng) {

    var mod = ng.module("editorialesModule");

    mod.controller("editorialesCtrl", ['$scope', '$state', '$stateParams', '$http', 'citiesContext', function ($scope, $state, $stateParams, $http, context) {

            $scope.data = {};
            
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
               console.log(id);
            };
        }]);
})(window.angular);
