(function (ng){
    var mod =ng.module("comprasModule");
    mod.constant("ComprasContext","api/compras");
    mod.controller('comprasUpdateCtrl',['$scope', '$http', 'ComprasContext', '$state', '$rootScope',
    function ($scope,$http, ComprasContext,$state,$rootScope){
        
         $scope.data = {};
         
         var idCompras = $state.params.comprasId;
         
         $http.get(ComprasContext + '/' + idCompras).then(function(response){
             var compras = response.data;
             $scope.data.tipoDecompra = compras.tipoDecompra;
             $scope.data.fecha = compras.fecha;
             $scope.data.costo = compras.costo;
             
         });
         
         
         $scope.createCompras = function(){
             $http.put(ComprasContext + "/" + idCompras, $scope.data).then(function(response){
                 $state.reload();
             });
         };
    }]);
})(window.angular);