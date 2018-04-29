(function (ng){
    var mod = ng.module("autorModule");
    mod.constant("autorContext","api/autores");
    mod.controller('autoresCtrl', ['$scope','$http', 'autorContext', '$state', 
        function($scope,$http,autorContext,$state) {
            
            $http.get(autorContext).then(function(response){
                $scope.autoresRecords = response.data;
            });
        }
    ]);
}
)(window.angular);