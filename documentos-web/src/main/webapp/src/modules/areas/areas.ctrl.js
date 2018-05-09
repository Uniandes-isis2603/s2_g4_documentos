(function (ng){
    var mod = ng.module("areaModule");
    mod.constant("areaContext","api/areas");
    mod.controller('areasCtrl', ['$scope','$http', 'areaContext', '$state', '$rootScope', 
                 function($scope,$http,autorContext,$state) {
            
                $http.get("http://localhost:8080/documentos-web/api/areas").then(function (response) 
            {
                $scope.areas = response.data;
            });
            
            $scope.get = function(){
                $http.get("http://localhost:8080/documentos-web/api/areas").then(function (response) 
                {
                    $scope.areas = response.data;
                });
            };
            $scope.create= function()
            {
               
                $http.post("http://localhost:8080/documentos-web/api/areas",$scope.data).then(function (response) 
                {
                    $scope.get();
                });
            
            };
            $scope.update_editorial= function(id)
            {
                $http.put("http://localhost:8080/documentos-web/api/areas/"+$scope.dataActuaBasico.id ,$scope.dataActuaBasico).then(function (response) 
                {
                    $state.reload();
                });
            
            };
            $scope.delete= function(id)
            {
                $http.delete("http://localhost:8080/documentos-web/api/areas/"+id ).then(function (response) 
                {
                    $scope.get();
                });
            
            };
            $scope.darLibros = function()
            {
                console.log("holi");
                $http.get("http://localhost:8080/documentos-web/api/libros").then(function (response) 
                {
                    $scope.libros = response.data;
                    console.log($scope.libros)
                });   

            };
            $scope.actualizarLibros=function(editorial)
            {
               
                $scope.data.id=editorial.id;
                $scope.data.nombre=editorial.nombre;
               
                $http.put("http://localhost:8080/documentos-web/api/areas/"+editorial.id ,$scope.data).then(function (response) 
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
        }
    ]);
}


)(window.angular);