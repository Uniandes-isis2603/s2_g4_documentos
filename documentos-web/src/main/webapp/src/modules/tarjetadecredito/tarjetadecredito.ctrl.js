(function(ng){
    var mod=ng.module("tarjetadecreditoModule");
    mod.constant("tarjetadecreditoContext", "api/usuario/45/metodosdepago/tarjetasdecredito");
    mod.controller('tarjetadecreditoCtrl', ['$scope','$http','tarjetadecreditoContext','$state',
        function($scope,$http,tarjetadecreditoContext,$state){
            $http.get(tarjetadecreditoContext).then(function(response){
                $scope.tarjetadecreditoRecords = response.data; 
                
            });
        }
    ]);
})(window.angular);