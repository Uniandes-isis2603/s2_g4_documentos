(function (ng) {
    var mod = ng.module("fotocopiaModule");
    mod.constant("FotocopiaContext","api/fotocopias");
    mod.controller('fotocopiaCreateCtrl',['$scope','$http','FotocopiaContext','$state', '$rootScope',
    
    /**
     * @ngdoc controller
     * @name fotocopiass.controller:fotocopiaCreateCtrl
     * @description 
     * Definición del controlador auxiliar para crear fotocopias.
     * @param {Object} $scope Referencia inyectada al Scope definida para este controlador, 
     * el scope, es el objeto que contiene las variables o funciones
     * que se definen en este controlador y que son utilizadas
     * desde el HTML
     * @param {Object} $http Objeto inyectado para manejar consultas HTTP
     * @param {Object} FotocopiaContext Constante inyectada que contiene la ruta donde se 
     * encuentra el API de Fotocopias en el back-end.
     * @param {Object} $state Dependencia inyectada en la que se recibe
     * el estado actual de la navegación en el módulo.
     * @param {Object} $rootScope Referencia inyectada al Scope definida para 
     * toda la aplicación
     */
    function($scope,$http,FotocopiaContext, $state,$rootScope){
        $rootScope.edit =false;
        
        $scope.data ={};
        
         
        
        $scope.createFotocopia = function (){   
            $http.post(FotocopiaContext,$scope.data).then(function(response){
                $state.go('fotocopiasList',{libroId:response.data.id},{reload:true});
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
                 autores = $scope.data.autores;
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
                 areas = $scope.data.areas;
            }
            
            
            
            $http.get("api/areas/" + id).then(function (responseArea){
                
                
                var area = {id:responseArea.data.id, nombre:responseArea.data.tipo};
                
                
                areas.push(area);
                
                $scope.data.areas = areas;
               
            });
        };
            
        
    }]);
})(window.angular);
