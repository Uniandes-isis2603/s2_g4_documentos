(function (ng){
    var mod = ng.module("libroModule");
    mod.constant("libroContext","api/libros");
    mod.controller('libroCtrl', ['$scope','$http', 'libroContext', '$state','$rootScope',
        /**
         * @ngdoc controller
         * @name libros.controller:libroCtrl
         * @description 
         * Definición del controlador de Angular del módulo Libro.
         * Se crea el controlador con el cual se maneja el módulo.
         * en el controlador se definen los atributos y métodos que pueden
         * ser accedidos desde el HTML utilizando el $scope.
         * @param {Object} $scope Referencia inyectada al Scope definida en este
         * controlador, el scope es el objeto que contiene las variables p
         * funcionaes que se definen en este controlador y que son utilizadas
         * desde el HTML.
         * @param {Object} $http Objeto inyectado para manejar consultas HTTP.
         * @param {Object} libroContext Constante inyectada que contiene la ruta
         * donde se encuentra el API de Librs en el back end.
         * @param {Object} $state Dependencia inyectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.   
         */
        function($scope,$http,libroContext,$state,$rootScope) {
            /**
             * @ngdoc function
             * @name getLibros
             * @methodOf libros.controller:libroCtrl
             * @description 
             * Esta función utiliza el protocolo HTTP para obtener el recursp
             * donde se encuentran los libros en formato JSON. El recurso
             * puede ser un archivo o un API Rest. La función se ejecuta
             * automáticamente cuando el controlador es accedido desde el navegador.
             * @param {String} URL Dirección donde se encuentra el recurso
             * de los libros o API donde se puede consultar. 
             */
            $http.get(libroContext).then(function(response){
                $scope.librosRecords = response.data;
            });

              $scope.get = function(){
                $http.get("http://localhost:8080/documentos-web/api/libros").then(function (response) 
                {
                    $scope.librosRecords = response.data;
                });
            };
            $scope.delete= function (libro)
            {
                
                 $http.delete("http://localhost:8080/documentos-web/api/libros/"+libro.id ).then(function () 
                {
                    $scope.get();
                });
            };

        }
    ]);
}
)(window.angular);