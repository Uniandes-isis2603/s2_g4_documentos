(function (ng) {
    var mod = ng.module("paypalModule");
    mod.constant("paypalContext", "api/usuario/45/metodosdepago/paypal/1");
    mod.controller('paypalUpdateCtrl', ['$scope', '$http', 'paypalsContext', '$state', '$rootScope',

        function ($scope, $http, paypalsContext, $state, $rootScope) {
            $rootScope.edit = true;

            $scope.data = {};

            var idPaypal = $state.params.paypalId;

            $http.get(paypalsContext + '/' + idPaypal).then(function (response) {
                var paypal = response.data;
                $scope.data.id = paypal.id;
            });

            

            $scope.createPaypal = function () {
                $http.put(paypalsContext + "/" + idPaypal, $scope.data).then(function (response) {
                    $state.reload();
                });
            }
        }]);
}
)(window.angular);