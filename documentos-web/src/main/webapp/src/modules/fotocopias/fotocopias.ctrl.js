(function (ng){
    var mod = ng.module("fotocopiaModule");
    mod.constant("fotocopiaContext","api/fotocopias");
    mod.controller('fotocopiaCtrl', ['$scope','$http', 'fotocopiaContext', '$state',
        /**
         * @ngdoc controller
         * @name fotocopias.controller:fotocopiaCtrl
         * @description 
         * Definición del controlador de Angular del módulo Fotocopia.
         * Se crea el controlador con el cual se maneja el módulo.
         * en el controlador se definen los atributos y métodos que pueden
         * ser accedidos desde el HTML utilizando el $scope.
         * @param {Object} $scope Referencia inyectada al Scope definida en este
         * controlador, el scope es el objeto que contiene las variables p
         * funcionaes que se definen en este controlador y que son utilizadas
         * desde el HTML.
         * @param {Object} $http Objeto inyectado para manejar consultas HTTP.
         * @param {Object} fotocopiaContext Constante inyectada que contiene la ruta
         * donde se encuentra el API de fotocopias en el back end.
         * @param {Object} $state Dependencia inyectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.   
         */
        function($scope,$http,fotocopiaContext,$state) {
            /**
             * @ngdoc function
             * @name getFotocopias
             * @methodOf fotocopias.controller:fotocopiaCtrl
             * @description 
             * Esta función utiliza el protocolo HTTP para obtener el recursp
             * donde se encuentran las fotocopias en formato JSON. El recurso
             * puede ser un archivo o un API Rest. La función se ejecuta
             * automáticamente cuando el controlador es accedido desde el navegador.
             * @param {String} URL Dirección donde se encuentra el recurso
             * de los libros o API donde se puede consultar. 
             */
            $http.get(fotocopiaContext).then(function(response){
                $scope.fotocopiasRecords = response.data;
            });
        }
    ]);
}
)(window.angular);