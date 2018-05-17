(function (ng){
    var mod = ng.module("autoresModule");
    mod.constant("autorContext","api/autores");
    mod.controller('autoresCtrl', ['$scope','$http', 'autorContext', '$state', 
        function($scope,$http,autorContext,$state) {
            
            if($scope.autores === undefined){
                $http.get("http://localhost:8080/documentos-web/api/autores").then(function (response) 
                {
                    $scope.autores = response.data;
                });
            }
            $scope.get = function(){
                $http.get("http://localhost:8080/documentos-web/api/autores").then(function (response) 
                {
                    $scope.autores = response.data;
                });
            };
            $scope.create= function()
            {
               $scope.data.id=100;
                $http.post("http://localhost:8080/documentos-web/api/autores",$scope.data).then(function (response) 
                {
                    $scope.get();
                });
            
            };
            $scope.update_editorial= function(id)
            {
                $http.put("http://localhost:8080/documentos-web/api/autores/"+$scope.dataActuaBasico.id ,$scope.dataActuaBasico).then(function (response) 
                {
                    $state.reload();
                });
            
            };
            $scope.delete= function(id)
            {
                $http.delete("http://localhost:8080/documentos-web/api/autores/"+id ).then(function (response) 
                {
                    $scope.get();
                });
            
            };
            $scope.darLibros = function()
            {
            
                $http.get("http://localhost:8080/documentos-web/api/libros").then(function (response) 
                {
                    $scope.libros = response.data;
                    
                });   

            };
            $scope.actualizarLibros=function(editorial)
            {
               
                $scope.data.id=editorial.id;
                $scope.data.nombre=editorial.nombre;
               
                $http.put("http://localhost:8080/documentos-web/api/autores/"+editorial.id ,$scope.data).then(function (response) 
                {
                    $state.reload();
                });
           
               
            };
          
            $scope.bibliografia= function (libro)
            {
                
                    $scope.data.libros.push({id:libro.id});
                    
                    
                    for (var i = 0; i < $scope.libros.length; i++) 
                    {
                        if($scope.libros[i].id===libro.id)
                         {
                           $scope.libros.splice(i,1);  
                         };
                    
                }
               
            };
                        $scope.buscarPorNombre=function(tipo){
            var path = "http://localhost:8080/documentos-web/api/autores";
            
            if (tipo != undefined   ){ path += "/" + tipo };
          
            $http.get(path).then(function (response) 
            {
                $scope.autores = response.data;
                
            });                  
            }
            $scope.actualizar=function(){
                $state.reload();
            };
            $scope.filtroTodo= function(calificacion,precio,profesor) {
            var salida = [];
            var pre = (precio != undefined)? parseInt(precio):10000000;
            var cali = (calificacion != undefined)? parseInt(calificacion):"Todos";
            var top = cali + 1;      
           
               
            angular.forEach($scope.autores, function(autores) {
            angular.forEach(autores.documentos, function(documentos) {

                if (((documentos.calificacionPromedio >= parseInt(calificacion) && documentos.calificacionPromedio < top) || (calificacion === "Todos")) && 
                        documentos.precio<pre) {

                    salida.push(autores);
   
                }
                });

            });
              
            $scope.autores= salida;
            
            
            };            
           
        }
    ]);
}
)(window.angular);