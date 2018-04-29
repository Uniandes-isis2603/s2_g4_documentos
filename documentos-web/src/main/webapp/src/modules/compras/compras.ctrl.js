(function(ng) {
    var mod = ng.module("comprasModule");
    mod.constant("comprasContext", "api/compras");
    mod.controller('comprasCtrl', ['$scope','$http','comprasContext', '$state',
    function($scope, $http, comprasContext, $state)
    {
        $http.get(comprasContext).then(function(response){
            $scope.comprasRecords = response.data;
            
        });
                console.log($scope);

    }
    ]);
}
)(window.angular);

