(function (ng) {

    var mod = ng.module("reservaModule");

    mod.constant("reservasContext","reservas");
    mod.constant("usuariosContext", "api/usuarios");
    
    mod.controller("reservasCtrl", ['$scope', '$state', '$stateParams', '$http', 'reservasContext','usuariosContext'
        , function ($scope, $state, $stateParams, $http, reservasContext,usuariosContext) {
               
               $http.get(usuariosContext + '/' + $state.params.usuarioId + '/' + reservasContext).then(function(response){
                   $scope.reservasRecords = response.data;
               });
               
               if($state.params.reservaId !== undefined){
                   $http.get(usuariosContext +'/'+ $state.params.usuarioId + '/' + reservasContext + '/' + $state.params.reservaId).then(function (response){
                       $scope.currentReserva = response.data;
                  
                   });
               }
        }
    ]);
})(angular);
