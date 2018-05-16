(function (ng) {
    var mod = ng.module("reservaModule");
  
    mod.constant("reservasContext", "reservas");
        mod.constant("usuariosContext", "api/usuarios");

        mod.controller("reservasNewCtrl", ['$scope', '$state', '$stateParams', '$http', 'reservasContext', 'usuariosContext',
            function ($scope, $state, $stateParams, $http, reservasContext, usuariosContext) {
    $scope.createreserva = function () {
        $http.post( usuariosContext +  $state.params.usuarioId +'/'+ reservasContext, {
            
            costo: $scope.costo,
            fecha: $scope.fecha,
        
        }).then(function (response) {
            $state.go('reservasList', {reservaId: response.data.id}, {reload: true});
        });
    }}
        ]);
    
})(window.angular);