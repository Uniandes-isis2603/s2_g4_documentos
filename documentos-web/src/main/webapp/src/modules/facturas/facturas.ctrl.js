(function(ng){
    var mod = ng.module("facturasModule");
    mod.constant("facturasContext", "api/facturas");
    mod.controller('facturasCtrl', ['$scope','$http','facturasContext','$state',
        function($scope,$http,facturasContext,$state){
            $http.get(facturasContext).then(function(response){
                $scope.facturasRecords = response.data;
            });
        }]);
})(window.angular);