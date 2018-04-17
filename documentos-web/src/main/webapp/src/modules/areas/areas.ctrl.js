(function (ng){
    var mod = ng.module("areaModule");
    mod.constant("autorContext","api/areas");
    mod.controller('areasCtrl', ['$scope','$http', 'areaContext', '$state',
        
        function($scope,$http,areasContext,$state) {
            
            $http.get(areasContext).then(function(response){
                $scope.areassRecords = response.data;
            });
        }
    ]);
}
)(window.angular);