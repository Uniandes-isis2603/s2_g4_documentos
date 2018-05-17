(function (ng){
    var mod = ng.module("reservaModule");
    mod.constant("usuarioContex","api/usuarios");
    mod.constant("reservaContext","reservas");

   mod.controller("reservaCtrl", ['$scope','$rootScope' , '$state', '$stateParams', '$http','$timeout', 'reservaContext', 'usuarioContext', function ($scope,$rootScope ,$state, $stateParams, $http, $timeout, reservaContext, usuarioContext) {
           
           $scope.data={};
           $scope.data.documentos=[];
           $scope.data.costo=1;
            

            $http.get(usuarioContext +'/'+ $state.params.usuarioId + '/' + reservaContext).then(function(response){
                $scope.reservasRecords = response.data;
            });
            
                 
          

            
           $scope.get = function(){
                $http.get(usuarioContext +'/'+ $state.params.usuarioId + '/' + reservaContext).then(function (response) 
                {
                    $scope.reservasRecords = response.data;
                });
            };

            
                 $scope.create= function()
            {
           
                $scope.data.fecha=new Date(2019,05,05);
                $http.post(usuarioContext +'/'+ $state.params.usuarioId + '/' + reservaContext,$scope.data).then(function (response) 
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
             $scope.updateAll=function(reserva)
            {
                $scope.data.id=reserva.id;
                
                $scope.data.fecha=new Date(2019,05,05);
             console.log($scope.data.documentos); 
             for( var libro in reserva.documentos)
             {
                 $scope.data.costo+=reserva.documentos[libro].precio;
                 $scope.data.documentos.push(reserva.documentos[libro]);
             }
            console.log($scope.data.documentos); 
                $http.put(usuarioContext +'/'+ $state.params.usuarioId + '/' + reservaContext + '/' + reserva.id ,$scope.data).then(function (response) 
                {
                    $state.reload();
                });
          
               
            };
            $scope.reservarLibro= function (libro)
            {
                    $scope.data.documentos.push(libro);
                    $scope.data.costo+=libro.precio;
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

