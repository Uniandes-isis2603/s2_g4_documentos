(function (ng) {
    var mod = ng.module("usuarioModule");
  
    mod.constant("usuariosContext", "api/usuarios");
        mod.controller("usuariosNewCtrl", ['$scope', '$state', '$stateParams', '$http', 'usuariosContext', function ($scope, $state, $stateParams, $http, usuariosContext) {
    $scope.createUsuario = function () {
        $http.post(usuariosContext, {
            
            nombre: $scope.nombre,
            nombreUsuario: $scope.nombreUsuario,
            correo: $scope.correo,
            edad: $scope.edad,
            genero: $scope.genero,
        }).then(function (response) {
            //user created successfully
            $state.go('usuariosList', {usuarioId: response.data.id}, {reload: true});
        });
    }}
        ]);
    
})(window.angular);


