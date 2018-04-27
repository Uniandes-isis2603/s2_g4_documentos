(function (ng) {
    var mod = ng.module("paypalModule");
    mod.constant("paypalContext", "api/api/usuario/{usuarioId: long}/metodosdepago/paypal");
    mod.controller('paypalDetailCtrl', ['$scope', '$http', 'paypalContext', '$state', '$filter',
        function ($scope, $http, paypalContext, $state, $filter) {

            if (($state.params.paypalId !== undefined) && ($state.params.paypalId !== null)) {
                $http.get(paypalContext + "/" + $state.params.paypalId).then(function (response) {
                    $scope.currentPaypal = response.data;
                });
            }
        }
    ]);
}
)(window.angular);
