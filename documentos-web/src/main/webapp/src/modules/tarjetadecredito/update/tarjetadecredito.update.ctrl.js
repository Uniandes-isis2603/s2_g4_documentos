(function (ng) {
    var mod = ng.module("tarjetadecreditoModule");
    mod.constant("tarjetadecreditoContext", "api/usuario/45/metodosdepago/tarjetadecredito");
    mod.controller('tarjetadecreditoUpdateCtrl', ['$scope', '$http', 'tarjetadecreditoContext', '$state', '$rootScope',
        function ($scope, $http, tarjetadecreditoContext, $state, $rootScope) {

            $rootScope.edit = true;
            
            $scope.data = {};

            var tarjetadecreditoId = $state.params.tarjetadecreditoId;

            $http.get(tarjetadecreditoContext + '/' + tarjetadecreditoId).then(function (response) {
                var tarjetadecredito = response.data;
                $scope.data.nroDeLaTarjeta = tarjetadecredito.nroDeLaTarjeta;
                $scope.data.nombreEnLaTarjeta = tarjetadecredito.nombreEnLaTarjeta;
                $scope.data.numeroDeSeguridad = tarjetadecredito.numeroDeSeguridad;
                $scope.data.tipoDeTarjeta = tarjetadecredito.tipoDeTarjeta;

            });

            $scope.createTarjetadecredito = function () {
                $http.put(tarjetadecreditoContext + '/' + tarjetadecreditoId, $scope.data).then(function (response) {
                    $state.reload();
                });
            };
        }]);
}
)(window.angular);