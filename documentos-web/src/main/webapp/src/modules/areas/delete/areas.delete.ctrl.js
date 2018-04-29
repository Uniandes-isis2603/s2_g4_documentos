(function (ng) {
    var mod = ng.module("areaModule");
    mod.constant("areaContext", "api/areas");
    mod.controller('areaDeleteCtrl', ['$scope', '$http', 'areaContext', '$state',
        /**
         * @ngdoc controller
         * @name autor.controller:areaDeleteCtrl
         * @description
         * Definición del controlador auxiliar para eliminar Areas. 
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} areaContext Constante injectada que contiene la ruta
         * donde se encuentra el API de Autores en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         */
        function ($scope, $http, areaContext, $state) {
            var idArea = $state.params.areaId;
            /**
             * @ngdoc function
             * @name deleteArea
             * @methodOf area.controller:areaDeleteCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para eliminar el area.
             * @param {String} id El ID del area a eliminar.
             */
            $scope.deleteAutor = function () {
                $http.delete(areaContext + '/' + idArea, {}).then(function (response) {
                    $state.go('areasList', {areaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);