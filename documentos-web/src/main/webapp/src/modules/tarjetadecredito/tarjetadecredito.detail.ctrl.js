(function (ng) {
    var mod = ng.module("tarjetadecreditoModule");
    mod.constant("tarjetadecreditoContext", "api/usuario/45/metodosdepago/tarjetadecredito");
    mod.controller('tarjetadecreditoDetailCtrl', ['$scope', '$http', 'tarjetadecreditoContext', '$state',
        function ($scope, $http, tarjetadecreditoContext, $state) {

            if (($state.params.tarjetadecreditoId !== undefined) && ($state.params.tarjetadecreditoId !== null)) {
                $http.get(tarjetadecreditoContext + '/' + $state.params.tarjetadecreditoId).then(function (response) {
                    $scope.currentTarjetadecredito = response.data;
                    
                });
            }
        }
    ]);
}
)(window.angular);
