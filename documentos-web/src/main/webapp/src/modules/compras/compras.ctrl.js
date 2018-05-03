(function(ng) {
    var mod = ng.module("comprasModule");
    mod.constant("comprasContext", "api/compras");
    mod.controller('comprasCtrl', ['$scope','$http','comprasContext', '$state',
    function($scope, $http, comprasContext, $state)
    {
        $http.get(comprasContext).then(function(response){
            $scope.comprasRecords = response.data;
            
        });
        
            $scope.met = function(met){
                if (met === 'paypal'){
                    return "fa fa-cc-paypal";}
                else if (met === 'tarjetadecredito'){
                    return "fa fa-credit-card";
                }
            };

    }
    
    ]);
}
)(window.angular);

