(function(ng){
    var mod = ng.module("facturasModule");
    mod.constant("facturasContext", "api/facturas");
    mod.controller('facturasCtrl', ['$scope','$http','facturasContext','$state',
        function($scope,$http,facturasContext,$state){
            $http.get(facturasContext).then(function(response){
                $scope.facturasRecords = response.data;
            });
            
            
            
        function ($scope, $http){
            $http({
                action: "https://sandbox.checkout.payulatam.com/ppp-web-gateway-payu/",
                method : 'POST',
                : 
            });
        }
        }]);
})(window.angular);