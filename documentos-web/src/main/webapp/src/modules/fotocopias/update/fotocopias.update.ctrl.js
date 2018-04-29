(function (ng){
    var mod =ng.module("fotocopiaModule");
    mod.constant("FotocopiaContext","api/fotocopias");
    mod.controller('fotocopiaUpdateCtrl',['$scope', '$http', 'FotocopiaContext', '$state', '$rootScope',
    function ($scope,$http, FotocopiaContext,$state,$rootScope){
        
         $scope.data = {};
         
         var idLibro = $state.params.fotocopiaId;
         
         $http.get(FotocopiaContext + '/' + idFotocopia).then(function(response){
             var fotocopia = response.data;
             $scope.data.nombre = fotocopia.nombre;
             $scope.data.descripcion = fotocopia.descripcion;
             $scope.data.caratula = fotocopia.caratula;
             $scope.data.nroPaginas= fotocopia.nroPaginas;
             $scope.data.precio = fotocopia.precio;
             $scope.data.capitulos = fotocopia.capitulos;
             $scope.data.profesor = fotocopia.profesor;
             
         });
         
         
         $scope.createFotocopia = function(){
             $http.put(FotocopiaContext + "/" + idFotocopia, $scope.data).then(function(response){
                 $state.go('fotocopiasList',{fotocopiaId: response.data.id},{reload:true});
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