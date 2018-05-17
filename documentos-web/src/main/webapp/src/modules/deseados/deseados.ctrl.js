(function (ng){
    var mod = ng.module("deseadoModule");
    mod.constant("usuarioContex","api/usuarios");
    mod.constant("deseadoContext","deseados");

   mod.controller("deseadoCtrl", ['$scope' , '$state', '$stateParams', '$http', 'deseadoContext', 'usuarioContext', function ($scope ,$state, $stateParams, $http, deseadoContext, usuarioContext) {
           
           $scope.data={};
           $scope.data.documentos=[];
           $scope.data.cantidad=0;
           $scope.formVisibility=false;

            
            

            $http.get(usuarioContext +'/'+ $state.params.usuarioId + '/' + deseadoContext).then(function(response){
                $scope.deseadosRecords = response.data;
            });
 
           $scope.get = function(){
                $http.get(usuarioContext +'/'+ $state.params.usuarioId + '/' + deseadoContext).then(function (response) 
                {
                    $scope.deseadosRecords = response.data;
                });
            };

            
            $scope.showForm=function()
            {
                $scope.formVisibility=true;
            }
        
                 $scope.create= function()
            {
                $scope.data.cantidad= 0;
                $http.post(usuarioContext +'/'+ $state.params.usuarioId + '/' + deseadoContext,$scope.data).then(function (response) 
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
             $scope.updateAll=function(deseado)
            {
                $scope.data.id=deseado.id;
                 $scope.data.nombre=deseado.nombre;
             console.log($scope.data.documentos); 
             for( var libro in deseado.documentos)
             {
                 $scope.data.cantidad++;
                 $scope.data.documentos.push(deseado.documentos[libro]);
             }
            console.log($scope.data.documentos); 
                $http.put(usuarioContext +'/'+ $state.params.usuarioId + '/' + deseadoContext + '/' + deseado.id ,$scope.data).then(function (response) 
                {
                    $state.reload();
                });
          
               
            };
            $scope.libroDeseado= function (libro)
            {
                    $scope.data.documentos.push(libro);
                    $scope.data.cantidad++;
                    //$scope.data.documentos.push({id:libro.id});
                    
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
    
    
})(window.angular);

