(function (ng){
    var mod = ng.module("usuarioModule");
    mod.constant("usuarioContext","api/usuarios");
   mod.controller("usuarioCtrl", ['$scope','$rootScope' , '$state', '$stateParams', '$http','$timeout', 'usuarioContext', function ($scope,$rootScope ,$state, $stateParams, $http, $timeout, usuarioContext) {
            $rootScope.home=1;
            $rootScope.usuarioId;
            $scope.formVisibility=false;
           

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
            

            $scope.showForm=function()
            {
                $scope.formVisibility=true;
            }
              $scope.cambiarA=function()
              {
                $rootScope.estiloBarrita="navbar-inverse2";
                $rootScope.estiloItemsBarrita="navBusqueda2";
                $rootScope.estiloBarraMenu="menuNavUsuario2";
                $rootScope.estiloLiMenu="menuUser2";
                $rootScope.estiloLinkMenu="linkUser2";
                $rootScope.estiloTablaLista="tablaFede2";
                $rootScope.estiloTablaHead="tablaListaU2";

              }
                $scope.cambiarB=function()
              {
                 $rootScope.estiloBarrita="navbar-inverse3";
                 $rootScope.estiloItemsBarrita="navBusqueda3";
                 $rootScope.estiloBarraMenu="menuNavUsuario3";
                 $rootScope.estiloLiMenu="menuUser3";
                 $rootScope.estiloLinkMenu="linkUser3";
                 $rootScope.estiloTablaLista="tablaFede3";
                 $rootScope.estiloTablaHead="tablaListaU3";



              }
                $scope.cambiarC=function()
              {
                $rootScope.estiloBarrita="navbar-inverse4";
                $rootScope.estiloItemsBarrita="navBusqueda4";
                $rootScope.estiloBarraMenu="menuNavUsuario4";
                $rootScope.estiloLiMenu="menuUser4";
                $rootScope.estiloLinkMenu="linkUser4";
                $rootScope.estiloTablaLista="tablaFede4";
                $rootScope.estiloTablaHead="tablaListaU4";



              }
                $scope.cambiarD=function()
              {
                $rootScope.estiloBarrita="navbar-inverse5";
                $rootScope.estiloItemsBarrita="navBusqueda5";
                $rootScope.estiloBarraMenu="menuNavUsuario5";
                $rootScope.estiloLiMenu="menuUser5";
                $rootScope.estiloLinkMenu="linkUser5";
                $rootScope.estiloTablaLista="tablaFede5";
                $rootScope.estiloTablaHead="tablaListaU5";



              }
              
                      $scope.cambiarE=function()
              {
                $rootScope.estiloBarrita="navbar-inverse";
                $rootScope.estiloItemsBarrita="navBusqueda";
                $rootScope.estiloBarraMenu="menuNavUsuario";
                $rootScope.estiloLiMenu="menuUser";
                $rootScope.estiloLinkMenu="linkUser";
                $rootScope.estiloTablaLista="tablaFede";
                $rootScope.estiloTablaHead="tablaListaU";



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

