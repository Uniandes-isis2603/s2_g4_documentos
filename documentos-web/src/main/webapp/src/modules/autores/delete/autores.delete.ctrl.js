(function (ng) {
    var mod = ng.module("autorModule");
    mod.constant("autorContext", "api/autores");
    mod.controller('autorDeleteCtrl', ['$scope', '$http', 'autorContext', '$state',
        /**
         * @ngdoc controller
         * @name autor.controller:autorDeleteCtrl
         * @description
         * Definición del controlador auxiliar para eliminar Autores. 
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} autorContext Constante injectada que contiene la ruta
         * donde se encuentra el API de Autores en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         */
        function ($scope, $http, autorContext, $state) {
            var idAutor = $state.params.autorId;
            /**
             * @ngdoc function
             * @name deleteAutor
             * @methodOf authors.controller:autorDeleteCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para eliminar el autor.
             * @param {String} id El ID del autor a eliminar.
             */
            $scope.deleteAutor = function () {
                $http.delete(autorContext + '/' + idAutor, {}).then(function (response) {
                    $state.go('autoresList', {autorId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);