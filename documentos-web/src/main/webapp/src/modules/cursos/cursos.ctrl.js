(function (ng) {

    var mod = ng.module("cursosModule");

    mod.controller("cursosCtrl", ['$scope', '$rootScope','$state', '$stateParams', '$http', 'citiesContext', function ($scope, $rootScope,$state, $stateParams, $http, context) {
            $rootScope.home=1;
            $scope.records = {};
            $scope.data = {};
            
            $scope.data .bibliografiaDelCurso=[];
            $scope.dataActuaBasico={};
            $http.get("http://localhost:8080/documentos-web/api/cursos").then(function (response) 
            {
                $scope.cursos = response.data;
            });
            
            $scope.get = function(){
                $http.get("http://localhost:8080/documentos-web/api/cursos").then(function (response) 
                {
                    $scope.cursos = response.data;
                });
            };
            
              $scope.create= function()
            {
               
                $http.post("http://localhost:8080/documentos-web/api/cursos",$scope.data).then(function (response) 
                {
                    $scope.get();
                });
            
            };
             $scope.update_cursos= function(id)
            {
                $http.put("http://localhost:8080/documentos-web/api/cursos/"+$scope.dataActuaBasico.id ,$scope.dataActuaBasico).then(function (response) 
                {
                    $state.reload();
                });
            
            };
              $scope.delete= function(id)
            {
                $http.delete("http://localhost:8080/documentos-web/api/cursos/"+id ).then(function (response) 
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
            
            
             $scope.actualizarLibros=function(curso)
            {
               
                 $scope.data.id=curso.id;
                $scope.data.nombre=curso.nombre;
                $scope.data.departamento=curso.departamento;
                $scope.data.codigo=curso.codigo;
                
                
               console.log(curso); 
               
                $http.put("http://localhost:8080/documentos-web/api/cursos/"+curso.id ,$scope.data).then(function (response) 
                {
                    $state.reload();
                });
          
               
            };
            $scope.bibliografia= function (libro)
            {
                
                    $scope.data.bibliografiaDelCurso.push({id:libro.id});
                    
                    
                    for (var i = 0; i < $scope.libros.length; i++) 
                    {
                        if($scope.libros[i].id===libro.id)
                         {
                           $scope.libros.splice(i,1);  
                         };
                    
                }
               
            };
            
            
        }]);
})(window.angular);
