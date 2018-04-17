(function(ng){
    var mod= ng.module("deseadoModule");
    mod.constant("deseadoContext","api/deseados");
    mod.controller('deseadoDetailCtrl', ['$scope', '$http','deseadoContext', '$state',
        /**
         * @ngdoc controller
         * @name deseados.controller:deseadoDetailCtrl
         * @description 
         * Definición de un controlador auxiliar del módulo deseado.
         * Se crea el controlador con el cual se manejan las vistas de detalle del módulo.
         * @param {Object} $scope Referencia inyectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o funciones que se definen 
         * en este controlador y que son utilizadas desde el HTML.
         * @param {Object} $http Objeto inyectado para manejar las consultas HTTP
         * @param {Object} deseadoContext Constante inyectada que contiene la ruta donde se encuentra
         * el API de deseados en el  back-end.
         * @param {Object} $state  Dependencia inyectada en la que se recibe el
         * estado actual de la navecación definida en el módulo.   
         */
        function($scope,$http, deseadoContext,$state) {
            if(($state.params.deseadoId !== undefined) && ($state.params.deseadoId !==null)){
                /**
                 * @ngdoc function
                 * @name getdeseadoID
                 * @methodOf deseados.controller:deseadoDetailCtrl
                 * @description 
                 * Esta función utiliza el protocolo Http para obtener el recurso
                 * donde se encuentra el deseado por ID en formato JSON.
                 * de los deseados o API donde se puede consultar. 
                 * @param {json} response 
                 */
                $http.get(deseadoContext + '/' + $state.params.deseadoId).then(function(response){
                    $scope.currentDeseado =response.data;
                });
            }
        }
    ]);
})(window.angular);


