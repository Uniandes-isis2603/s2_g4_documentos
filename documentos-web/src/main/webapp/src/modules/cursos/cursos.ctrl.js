(function (ng) {

    var mod = ng.module("cursosModule");

    mod.controller("cursosCtrl", ['$scope', '$state', '$stateParams', '$http', 'citiesContext', function ($scope, $state, $stateParams, $http, context) {

            $scope.records = {};
            $http.get("http://localhost:8080/documentos-web/api/cursos").then(function (response) 
            {
                $scope.cursos = response.data;
            });
        }]);
})(window.angular);
