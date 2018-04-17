(function (ng){
    var mod = ng.module("autorModule");
    mod.constant("autorContext","api/autores");
    mod.controller('autoresCtrl', ['$scope','$http', 'autorContext', '$state',
        /**
         * @ngdoc controller
         * @name autor.controller:autorCtrl
         * @description 
         * Definición del controlador de Angular del módulo Autor.
         * Se crea el controlador con el cual se maneja el módulo.
         * en el controlador se definen los atributos y métodos que pueden
         * ser accedidos desde el HTML utilizando el $scope.
         * @param {Object} $scope Referencia inyectada al Scope definida en este
         * controlador, el scope es el objeto que contiene las variables p
         * funcionaes que se definen en este controlador y que son utilizadas
         * desde el HTML.
         * @param {Object} $http Objeto inyectado para manejar consultas HTTP.
         * @param {Object} autorContext Constante inyectada que contiene la ruta
         * donde se encuentra el API de Autores en el back end.
         * @param {Object} $state Dependencia inyectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.   
         */
        function($scope,$http,autorContext,$state) {
            /**
             * @ngdoc function
             * @name getAutores
             * @methodOf autor.controller:AutorCtrl
             * @description 
             * Esta función utiliza el protocolo HTTP para obtener el recurso
             * donde se encuentran los libros en formato JSON. El recurso
             * puede ser un archivo o un API Rest. La función se ejecuta
             * automáticamente cuando el controlador es accedido desde el navegador.
             * @param {String} URL Dirección donde se encuentra el recurso
             * de los libros o API donde se puede consultar. 
             */
            $http.get(autorContext).then(function(response){
                $scope.autoresRecords = response.data;
            });
        }
    ]);
}
)(window.angular);