(function(ng){
    var mod = ng.module("paypalModule");
    mod.constant("paypalContext", "api/usuario/45/metodosdepago/paypal");
    mod.controller('paypalCtrl', ['$scope','$http','paypalContext','$state',
        function($scope,$http,paypalContext,$state){
            $http.get(paypalContext).then(function(response){
                $scope.paypalRecords = response.data;
            });
        }]);
})(window.angular);