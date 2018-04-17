(function (ng) {

    var mod = ng.module("editorialesModule");

    mod.controller("editorialesCtrl", ['$scope', '$state', '$stateParams', '$http', 'citiesContext', function ($scope, $state, $stateParams, $http, context) {

            $scope.records = {};
            $http.get("http://localhost:8080/documentos-web/api/editoriales").then(function (response) 
            {
                $scope.editoriales = response.data;
            });
        }]);
})(window.angular);
