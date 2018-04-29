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
             console.log(response.data);
         });
         
         
         $scope.createLibro = function(){
             $http.put(LibroContext + "/" + idLibro, $scope.data).then(function(response){
                 $state.go('librosList',{libroId: response.data.id},{reload:true});
             });
         };
         
         $http.get("api/autores").then(function (responseAutores){
                $scope.autoresRecords = responseAutores.data;
            });
            
        $http.get("api/areas").then(function (responseAreas){
                $scope.areasRecords = responseAreas.data;
            });
        
        $scope.asociarAutor = function (id) {
            
          
            
            if(typeof $scope.data.autores === "undefined")
            {
               var autores = []; 
            } else
            {
                var autores = $scope.data.autores;
            }
            
            
            
            $http.get("api/autores/" + id).then(function (responseAutor){
                
                var autor = {id:responseAutor.data.id, nombre:responseAutor.data.nombre};
               
                autores.push(autor);
                
                $scope.data.autores = autores;
               
            });
        };
        
        $scope.asociarArea = function (id) {
            
          
            
            if(typeof $scope.data.areas === "undefined")
            {
               var areas = []; 
            } else
            {
                var areas = $scope.data.areas;
            }
            
            
            
            $http.get("api/areas/" + id).then(function (responseArea){
                
                
                var area = {id:responseArea.data.id, nombre:responseArea.data.tipo};
                
                
                areas.push(area);
                
                $scope.data.areas = areas;
               
            });
        };
    }]);
})(window.angular);