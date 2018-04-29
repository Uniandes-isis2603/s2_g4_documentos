(function (ng) {

    var mod = ng.module("deseadoModule");

    mod.constant("deseadosContext","deseados");
    mod.constant("usuariosContext", "api/usuarios");
    
    mod.controller("deseadosCtrl", ['$scope', '$state', '$stateParams', '$http', 'deseadosContext','usuariosContext'
        , function ($scope, $state, $stateParams, $http, deseadosContext,usuariosContext) {
               
               $http.get(usuariosContext + '/' + $state.params.usuarioId + '/' + deseadosContext).then(function(response){
                   $scope.deseadosRecords = response.data;
               });
               
               if($state.params.deseadoId !== undefined){
                   $http.get(usuariosContext +'/'+ $state.params.usuarioId + '/' + deseadosContext + '/' + $state.params.deseadoId).then(function (response){
                       $scope.currentdeseado = response.data;
                  
                   });
               }
        }
    ]);
})(angular);
