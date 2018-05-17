(function(ng){
    var mod= ng.module("autoresModule");
 
    mod.controller('autoresDetailCtrl', ['$scope', '$http', '$state',
        /**
         * @ngdoc controller
         * @name fotocopias.controller:fotocopiaDetailCtrl
         * @description 
         * Definición de un controlador auxiliar del módulo Fotocopia.
         * Se crea el controlador con el cual se manejan las vistas de detalle del módulo.
         * @param {Object} $scope Referencia inyectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o funciones que se definen 
         * en este controlador y que son utilizadas desde el HTML.
         * @param {Object} $http Objeto inyectado para manejar las consultas HTTP
         * @param {Object} fotocopiaContext Constante inyectada que contiene la ruta donde se encuentra
         * el API de Fotocopias en el  back-end.
         * @param {Object} $state  Dependencia inyectada en la que se recibe el
         * estado actual de la navecación definida en el módulo.   
         */
        function($scope,$http,$state) {
            if(($state.params.autorId !== undefined) && ($state.params.autorId !==null)){
                /**
                 * @ngdoc function
                 * @name getFotocopiaID
                 * @methodOf fotocopias.controller:fotocopiaDetailCtrl
                 * @description 
                 * Esta función utiliza el protocolo Http para obtener el recurso
                 * donde se encuentra la fotocopia por ID en formato JSON.
                 * de los libros o API donde se puede consultar. 
                 * @param {json} response 
                 */
                
                $http.get('http://localhost:8080/documentos-web/api/autores/' + $state.params.autorId).then(function(response){
                    $scope.currentAutor =response.data;
                });
                
          
             
            }
               
        }
    ]);
})(window.angular);
