(function (ng){
    var mod =ng.module("libroModule");
    mod.constant("LibroContext","api/libros");
    mod.controller('libroUpdateCtrl',['$scope', '$http', 'LibroContext', '$state', '$rootScope',
    function ($scope,$http, LibroContext,$state,$rootScope){
        
         $scope.data = {};
         
         var idLibro = $state.params.libroId;
         
         $http.get(LibroContext + '/' + idLibro).then(function(response){
             var libro = response.data;
             $scope.data.nombre = libro.nombre;
             $scope.data.descripcion = libro.descripcion;
             $scope.data.caratula = libro.caratula;
             $scope.data.ISBN = libro.ISBN;
             $scope.data.precio = libro.precio;
             
         });
         
         
         $scope.createLibro = function(){
             $http.put(LibroContext + "/" + idLibro, $scope.data).then(function(response){
                 $state.go('librosList',{libroId: response.data.id},{reload:true});
             });
         };
    }]);
})(window.angular);