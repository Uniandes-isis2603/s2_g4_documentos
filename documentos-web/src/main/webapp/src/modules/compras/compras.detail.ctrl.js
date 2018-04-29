(function (ng) {
    var mod = ng.module("comprasModule");
    mod.constant("comprasContext", "api/compras");
    mod.controller('comprasDetailCtrl', ['$scope', '$http', 'comprasContext', '$state', '$filter',
        function ($scope, $http, comprasContext, $state, $filter) {

            if (($state.params.comprasId !== undefined) && ($state.params.comprasId !== null)) {
                $http.get(comprasContext + "/" + $state.params.comprasId).then(function (response) {
                    $scope.currentCompra = response.data;
                });
            }
        }
    ]);
}
)(window.angular);