(function (ng){
    var mod = ng.module("areaModule");
    mod.constant("areaContext","api/areas");
    mod.controller('areasDetailCtrl', ['$scope','$http', 'areaContext', '$state', 
        function($scope,$http,areaContext,$state) {
            
            if(($state.params.areaId !== undefined) && ($state.params.areaId !==null)){
                /**
                 * @ngdoc function
                 * @name getAutorID
                 * @methodOf autores.controller:autorDetailCtrl
                 * @description 
                 * Esta función utiliza el protocolo Http para obtener el recurso
                 * donde se encuentra el libro por ID en formato JSON.
                 * de los autores o API donde se puede consultar. 
                 * @param {json} response 
                 */
                $http.get(areaContext + '/' + $state.params.areaId).then(function(response){
                    $scope.currentArea =response.data;
                });
            }
        }
    ]);
}
)(window.angular);