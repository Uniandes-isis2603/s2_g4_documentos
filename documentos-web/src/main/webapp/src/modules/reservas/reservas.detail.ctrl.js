(function (ng) {
    var mod = ng.module("reservaModule");
    mod.constant("reservaContext", "reservas");
    mod.constant("usuarioContext", "api/usuarios");

    mod.controller('reservaDetailCtrl', ['$scope', '$http', 'usuarioContext', 'reservaContext', '$state',
        /**
         * @ngdoc controller
         * @name reservas.controller:reservaDetailCtrl
         * @description 
         * Definición de un controlador auxiliar del módulo reserva.
         * Se crea el controlador con el cual se manejan las vistas de detalle del módulo.
         * @param {Object} $scope Referencia inyectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o funciones que se definen 
         * en este controlador y que son utilizadas desde el HTML.
         * @param {Object} $http Objeto inyectado para manejar las consultas HTTP
         * @param {Object} reservaContext Constante inyectada que contiene la ruta donde se encuentra
         * el API de reservas en el  back-end.
         * @param {Object} $state  Dependencia inyectada en la que se recibe el
         * estado actual de la navecación definida en el módulo.   
         */
        function ($scope, $http, usuarioContext, reservaContext, $state) {
            if (($state.params.reservaId !== undefined) && ($state.params.reservaId !== null)) {
                /**
                 * @ngdoc function
                 * @name getreservaID
                 * @methodOf reservas.controller:reservaDetailCtrl
                 * @description 
                 * Esta función utiliza el protocolo Http para obtener el recurso
                 * donde se encuentra el reserva por ID en formato JSON.
                 * de los reservas o API donde se puede consultar. 
                 * @param {json} response 
                 */
                $http.get(usuarioContext + '/' + $state.params.usuarioId + '/' + reservaContext + '/' + $state.params.reservaId).then(function (response) {
                    $scope.currentReserva = response.data;
                });
            }
        }
    ]);
})(window.angular);