(function (ng) {
    var mod = ng.module("areaModule");
    mod.constant("areaContext", "api/areas");
    mod.controller('areaUpdateCtrl', ['$scope', '$http', 'areaContext', '$state', '$rootScope',
        /**
         * @ngdoc controller
         * @name authors.controller:authorUpdateCtrl
         * @description
         * Definición del controlador auxiliar para actualizar Autores. 
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} autorContext Constante injectada que contiene la ruta
         * donde se encuentra el API de Autores en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         * @param {Object} $filter Dependencia injectada para hacer filtros sobre
         * arreglos.
         */
        function ($scope, $http, areaContext, $state, $rootScope) {
            $rootScope.edit = true;

            $scope.data = {};

            $scope.selectedItems = [];

            $scope.availableItems = [];

            var idArea = $state.params.areaId;

            //Consulto el autor a editar.
            $http.get(areaContext + '/' + idArea).then(function (response) {
                var area = response.data;
                $scope.data.tipo = area.tipo;
                $scope.getDocumentos(area.documentos);
            });

            /**
             * @ngdoc function
             * @name createArea
             * @methodOf authors.controller:authorUpdateCtrl
             * @description
             * Crea un nuevo autor con los libros nuevos y la información del
             * $scope.
             */
            $scope.createArea = function () {
                $http.put(areaContext + "/" + idArea, $scope.data).then(function (response) {
                    if ($scope.selectedItems.length >= 0) {
                        $http.put(areaContext + "/" + response.data.id).then(function (response) {
                        });
                    }
                    //Autor created successfully
                    $state.go('areasList', {areaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);