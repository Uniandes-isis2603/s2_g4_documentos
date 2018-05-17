(function (ng){
    var mod = ng.module("usuarioModule");
    mod.constant("usuarioContext","api/usuarios");
   mod.controller("usuarioCtrl", ['$scope','$rootScope' , '$state', '$stateParams', '$http','$timeout', 'usuarioContext', function ($scope,$rootScope ,$state, $stateParams, $http, $timeout, usuarioContext) {
            $rootScope.home=1;
            $rootScope.usuarioId;
           $scope.col={"color":"green;"};

            $scope.datosUpdate={};
            $scope.data={};
            
            $http.get(usuarioContext).then(function(response){
                $scope.usuariosRecords = response.data;
            });
            
                 
            if(($state.params.usuarioId !== undefined) && ($state.params.usuarioId !==null)){
          
                $http.get(usuarioContext + '/' + $state.params.usuarioId).then(function(response){
                    $scope.currentUsuario =response.data;
                });
            }
        
              
              $scope.cambiarColorAzul=function()
              {
                              $scope.col={"color":"black;"};

              }
            
           $scope.get = function(){
                $http.get(usuarioContext).then(function (response) 
                {
                    $scope.usuariosRecords = response.data;
                });
            };

            
                 $scope.create= function()
            {
               
                $http.post(usuarioContext,$scope.data).then(function (response) 
                {
                    $scope.get();
                });
            
            };
            
            
                  $scope.update= function()
            {
                $http.put(usuarioContext + '/' + $state.params.usuarioId ,$scope.datosUpdate).then(function (response) 
                {
                    $state.reload();
                });
            
            };
            
        
            
        }
    ]);
}
)(window.angular);

