(function (ng){
    var mod = ng.module("areaModule");
    mod.constant("areaContext","api/areas");
    mod.controller('areasCtrl', ['$scope','$http', 'areaContext', '$state', 
        function($scope,$http,autorContext,$state) {
            
            $http.get(autorContext).then(function(response){
                $scope.autoresRecords = response.data;
            });
        }
    ]);
}
)(window.angular);