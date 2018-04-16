(function(ng){
    var mod= ng.module("libroModule");
    mod.constant("libroContext","api/libros");
    mod.controller('libroDetailCtrl', ['$scope', '$http','libroContext', '$state',
        /**
         * @ngdoc controller
         * @name libros.controller:libroDetailCtrl
         * @description 
         * Definición de un controlador auxiliar del módulo Libro.
         * Se crea el controlador con el cual se manejan las vistas de detalle del módulo.
         * @param {Object} $scope Referencia inyectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o funciones que se definen 
         * en este controlador y que son utilizadas desde el HTML.
         * @param {Object} $http Objeto inyectado para manejar las consultas HTTP
         * @param {Object} libroContext Constante inyectada que contiene la ruta donde se encuentra
         * el API de Libros en el  back-end.
         * @param {Object} $state  Dependencia inyectada en la que se recibe el
         * estado actual de la navecación definida en el módulo.   
         */
        function($scope,$http, libroContext,$state) {
            if(($state.params.libroId !== undefined) && ($state.params.libroId !==null)){
                /**
                 * @ngdoc function
                 * @name getLibroID
                 * @methodOf libros.controller:libroDetailCtrl
                 * @description 
                 * Esta función utiliza el protocolo Http para obtener el recurso
                 * donde se encuentra el libro por ID en formato JSON.
                 * @param {String} URL Dirección donde se encuentra el recurso
                 * de los libros o API donde se puede consultar. 
                 */
                $http.get(libroContext + '/' + $state.params.libroId).then()(function(response){
                    $scope.currentLibro =response.data;
                });
            }
        }
    ]);
})(window.angular);