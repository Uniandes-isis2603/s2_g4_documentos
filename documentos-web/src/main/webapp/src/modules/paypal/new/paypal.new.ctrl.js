(function (ng) {
    var mod = ng.module("paypalModule");
    mod.constant("paypalContext", "api/usuario/{usuario: Long}/metodosdepago/paypal");
    mod.controller('paypalNewCtrl', ['$scope', '$http', 'paypalContext', '$state', '$rootScope',

        function ($scope, $http, paypalContext, $state, $rootScope) {
            $rootScope.edit = false;

            $scope.data = {};

            $scope.createPaypal = function () {
                $http.post(paypalContext, $scope.data).then(function (response) {
                    $state.go('paypalList', {paypalId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);