(function (ng){
    var mod = ng.module("areaModule");
    mod.constant("areaContext","api/areas");
    mod.controller('areasCtrl', ['$scope','$http', 'areaContext', '$state', '$rootScope', 
        function($scope,$http,areaContext,$state, $rootScope) {
            $rootScope.algo();
            $http.get(areaContext).then(function(response){
                $scope.areasRecords = response.data;
            });
        }
    ]);
}
)(window.angular);