(function(ng){
    var mod= ng.module("usuarioModule");
    mod.constant("usuarioContext","api/usuarios");
    mod.controller('usuarioDetailCtrl', ['$scope', '$http','usuarioContext', '$state',
        /**
         * @ngdoc controller
         * @name usuarios.controller:usuarioDetailCtrl
         * @description 
         * Definición de un controlador auxiliar del módulo usuario.
         * Se crea el controlador con el cual se manejan las vistas de detalle del módulo.
         * @param {Object} $scope Referencia inyectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o funciones que se definen 
         * en este controlador y que son utilizadas desde el HTML.
         * @param {Object} $http Objeto inyectado para manejar las consultas HTTP
         * @param {Object} usuarioContext Constante inyectada que contiene la ruta donde se encuentra
         * el API de usuarios en el  back-end.
         * @param {Object} $state  Dependencia inyectada en la que se recibe el
         * estado actual de la navecación definida en el módulo.   
         */
        function($scope,$http, usuarioContext,$state) {
            if(($state.params.usuarioId !== undefined) && ($state.params.usuarioId !==null)){
                /**
                 * @ngdoc function
                 * @name getusuarioID
                 * @methodOf usuarios.controller:usuarioDetailCtrl
                 * @description 
                 * Esta función utiliza el protocolo Http para obtener el recurso
                 * donde se encuentra el usuario por ID en formato JSON.
                 * de los usuarios o API donde se puede consultar. 
                 * @param {json} response 
                 */
                $http.get(usuarioContext + '/' + $state.params.usuarioId).then(function(response){
                    $scope.currentUsuario =response.data;
                });
            }
        }
    ]);
})(window.angular);


