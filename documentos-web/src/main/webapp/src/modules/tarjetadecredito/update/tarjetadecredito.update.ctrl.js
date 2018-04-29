(function (ng) {
    var mod = ng.module("tarjetadecreditoModule");
    mod.constant("tarjetadecreditoContext", "/metodosdepago/tarjetadecredito");
    mod.constant("usuariosContext", "api/usuarios/{usuarioId: long}");
    mod.controller('tarjetadecreditoUpdateCtrl', ['$scope', '$http', 'tarjetadecreditoContext', '$state', 'usuariosContext', '$rootScope',

        function ($scope, $http, tarjetadecreditoContext, usuariosContext, $state, $rootScope) {
            $rootScope.edit = true;

            $scope.data = {};

            var idTarjetadecredito = $state.params.tarjetadecreditoId;

            $http.get(tarjetadecreditoContext + '/' + idTarjetadecredito).then(function (response) {
                var tarjetadecredito = response.data;
                $scope.data.nroEnLaTarjeta = tarjetadecredito.nroEnLaTarjeta;
                $scope.data.nombreEnLaTarjeta = tarjetadecredito.nombreEnLaTarjeta;
                $scope.data.numeroDeSeguridad = tarjetadecredito.numeroDeSeguridad;
                $scope.data.tipoDeLaTarjeta = tarjetadecredito.numeroDeSeguridad;

            });

            $scope.createTarjetadecredito = function () {
                $http.put(usuariosContext + tarjetadecreditoContext + "/" + idTarjetadecredito, $scope.data).then(function (response) {
                    $state.go('tarjetadecreditoList', {tarjetadecreditoId: response.data.id}, {reload: true});
                });
            };
        }]);
}
)(window.angular);