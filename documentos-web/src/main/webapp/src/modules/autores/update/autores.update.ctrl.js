(function (ng) {
    var mod = ng.module("autorModule");
    mod.constant("autorContext", "api/autores");
    mod.controller('autorUpdateCtrl', ['$scope', '$http', 'autorContext', '$state', '$rootScope',
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
        function ($scope, $http, autorContext, $state, $rootScope) {
            $rootScope.edit = true;

            $scope.data = {};

            $scope.selectedItems = [];

            $scope.availableItems = [];

            var idAutor = $state.params.autorId;

            //Consulto el autor a editar.
            $http.get(autorContext + '/' + idAutor).then(function (response) {
                var autor = response.data;
                $scope.data.id = 100004;
                $scope.data.nombre = autor.nombre;
                $scope.getDocumentos(autor.documentos);
            });

            /**
             * @ngdoc function
             * @name createAuthor
             * @methodOf authors.controller:authorUpdateCtrl
             * @description
             * Crea un nuevo autor con los libros nuevos y la información del
             * $scope.
             */
            $scope.createAutor = function () {
                $http.put(autorContext + "/" + idAutor, $scope.data).then(function (response) {
                    if ($scope.selectedItems.length >= 0) {
                        $http.put(autorContext + "/" + response.data.id).then(function (response) {
                        });
                    }
                    //Autor created successfully
                    $state.go('autoresList', {autorId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);