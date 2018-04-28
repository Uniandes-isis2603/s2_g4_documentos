(function (ng) {
    var mod = ng.module("libroModule");
    mod.constant("LibroContext","api/libros");
    mod.controller('libroCreateCtrl',['$scope','$http','LibroContext','$state', '$rootScope',
    
    /**
     * @ngdoc controller
     * @name libros.controller:libroCreateCtrl
     * @description 
     * Definición del controlador auxiliar para crear libros.
     * @param {Object} $scope Referencia inyectada al Scope definida para este controlador, 
     * el scope, es el objeto que contiene las variables o funciones
     * que se definen en este controlador y que son utilizadas
     * desde el HTML
     * @param {Object} $http Objeto inyectado para manejar consultas HTTP
     * @param {Object} LibroContext Constante inyectada que contiene la ruta donde se 
     * encuentra el API de Libros en el back-end.
     * @param {Object} $state Dependencia inyectada en la que se recibe
     * el estado actual de la navegación en el módulo.
     * @param {Object} $rootScope Referencia inyectada al Scope definida para 
     * toda la aplicación
     */
    function($scope,$http,LibroContext, $state,$rootScope){
        $rootScope.edit =false;
        
        $scope.data ={};
        
         
        
        $scope.createLibro = function (){   
            $http.post(LibroContext,$scope.data).then(function(response){
                $state.go('librosList',{libroId:response.data.id},{reload:true});
            });
        };
        
        $http.get("api/autores").then(function (responseAutores){
                $scope.autoresRecords = responseAutores.data;
            });
        
        $scope.obtenerAutor = function () {
            
            var id = $scope.data.id;
            
            if(typeof $scope.data.autores === "undefined")
            {
               var autores = []; 
            } else
            {
                var autores = $scope.data.autores;
            }
            
            
            
            $http.get("api/autores/" + id).then(function (responseAutor){
                console.log(responseAutor.data.nombre);
                
                var autor = {id:responseAutor.data.id, nombre:responseAutor.data.nombre};
                
                console.log(autor);
                console.log(autores);
                
                autores.push(autor);
                
                $scope.data.autores = autores;
               
               console.log($scope.data);
            });
        };
            
        
    }]);
})(window.angular);