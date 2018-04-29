(function (ng){
    var mod = ng.module("autorModule");
    mod.constant("autorContext","api/autores");
    mod.controller('autoresDetailCtrl', ['$scope','$http', 'autorContext', '$state', 
        function($scope,$http,autorContext,$state) {
            
            if(($state.params.autorId !== undefined) && ($state.params.autorId !==null)){
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
                $http.get(autorContext + '/' + $state.params.autorId).then(function(response){
                    $scope.currentAutor =response.data;
                });
            }
        }
    ]);
}
)(window.angular);