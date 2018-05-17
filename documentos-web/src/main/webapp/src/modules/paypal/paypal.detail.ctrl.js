(function (ng) {
    var mod = ng.module("paypalModule");
    mod.constant("paypalContext", "api/usuario/45/metodosdepago/paypal");
    mod.controller('paypalDetailCtrl', ['$scope', '$http', 'paypalContext', '$state',
        function ($scope, $http, paypalContext, $state) {

            if (($state.params.paypalId !== undefined) && ($state.params.paypalId !== null)) {
                $http.get(paypalContext + '/' + $state.params.paypalId).then(function (response) {
                    $scope.currentPaypal = response.data;
                });
            }
        }
    ]);
}
)(window.angular);
