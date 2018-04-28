(function (ng) {

    var mod = ng.module("cursosModule");

    mod.controller("cursosCtrl", ['$scope', '$rootScope','$state', '$stateParams', '$http', 'citiesContext', function ($scope, $rootScope,$state, $stateParams, $http, context) {
            $rootScope.home=1;
            $scope.records = {};
            $http.get("http://localhost:8080/documentos-web/api/cursos").then(function (response) 
            {
                $scope.cursos = response.data;
            });
        }]);
})(window.angular);
