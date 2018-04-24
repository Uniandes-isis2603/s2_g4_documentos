(function (ng){
    var mod = ng.module("usuarioModule");
    mod.constant("usuarioContext","api/usuarios");
    mod.controller('usuarioCtrl', ['$scope','$http', 'usuarioContext', '$state',
        /**
         * @ngdoc controller
         * @name usuarios.controller:usuarioCtrl
         * @description 
         * Definición del controlador de Angular del módulo usuario.
         * Se crea el controlador con el cual se maneja el módulo.
         * en el controlador se definen los atributos y métodos que pueden
         * ser accedidos desde el HTML utilizando el $scope.
         * @param {Object} $scope Referencia inyectada al Scope definida en este
         * controlador, el scope es el objeto que contiene las variables p
         * funcionaes que se definen en este controlador y que son utilizadas
         * desde el HTML.
         * @param {Object} $http Objeto inyectado para manejar consultas HTTP.
         * @param {Object} usuarioContext Constante inyectada que contiene la ruta
         * donde se encuentra el API de Librs en el back end.
         * @param {Object} $state Dependencia inyectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.   
         */
        function($scope,$http,usuarioContext,$state) {
            /**
             * @ngdoc function
             * @name getusuarios
             * @methodOf usuarios.controller:usuarioCtrl
             * @description 
             * Esta función utiliza el protocolo HTTP para obtener el recursp
             * donde se encuentran los usuarios en formato JSON. El recurso
             * puede ser un archivo o un API Rest. La función se ejecuta
             * automáticamente cuando el controlador es accedido desde el navegador.
             * @param {String} URL Dirección donde se encuentra el recurso
             * de los usuarios o API donde se puede consultar. 
             */
            $http.get(usuarioContext).then(function(response){
                $scope.usuariosRecords = response.data;
            });
        }
    ]);
}
)(window.angular);

