(function(ng){
    var mod= ng.module("fotocopiaModule");
    mod.constant("fotocopiaContext","api/fotocopias");
    mod.controller('fotocopiaDetailCtrl', ['$scope', '$http','fotocopiaContext', '$state','$rootScope',
        /**
         * @ngdoc controller
         * @name fotocopias.controller:fotocopiaDetailCtrl
         * @description 
         * Definición de un controlador auxiliar del módulo Fotocopia.
         * Se crea el controlador con el cual se manejan las vistas de detalle del módulo.
         * @param {Object} $scope Referencia inyectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o funciones que se definen 
         * en este controlador y que son utilizadas desde el HTML.
         * @param {Object} $http Objeto inyectado para manejar las consultas HTTP
         * @param {Object} fotocopiaContext Constante inyectada que contiene la ruta donde se encuentra
         * el API de Fotocopias en el  back-end.
         * @param {Object} $state  Dependencia inyectada en la que se recibe el
         * estado actual de la navecación definida en el módulo.   
         */
        function($scope,$http, fotocopiaContext,$state,$rootScope) {
            if(($state.params.fotocopiaId !== undefined) && ($state.params.fotocopiaId !==null)){
                /**
                 * @ngdoc function
                 * @name getFotocopiaID
                 * @methodOf fotocopias.controller:fotocopiaDetailCtrl
                 * @description 
                 * Esta función utiliza el protocolo Http para obtener el recurso
                 * donde se encuentra la fotocopia por ID en formato JSON.
                 * de los libros o API donde se puede consultar. 
                 * @param {json} response 
                 */
                  $scope.comentario={};
            $scope.fecha=   new Date();               
            $scope.comentario.comentario="";
            $scope.comentario.fecha=new Date().toJSON()+"";
            $scope.comentario.id=9000;
                $http.get(fotocopiaContext + '/' + $state.params.fotocopiaId).then(function(response){
                    $scope.currentFotocopia =response.data;
                });
                
                $scope.agregar = function (libro)
                {
                    
                  
                  $rootScope.carrito.push(libro);
                    
                };
                
                $scope.agregarC=function (libro) 
                {
                  
                    libro.comentarios.push($scope.comentario);
                    
                    $http.put("http://localhost:8080/documentos-web/api/fotocopias/"+libro.id ,libro).then(function (response) 
                    {
                          $http.get(fotocopiaContext + '/' + $state.params.fotocopiaId).then(function(response){
                    $scope.currentFotocopia =response.data;
                });
               
                    });
                    $scope.comentario={};
                    
                };
                  $scope.borrar=function (comentario,libro) 
                {

                     for (var i = 0; i < libro.comentarios.length; i++) 
                    {
                        if(libro.comentarios[i].id===comentario.id)
                         {
                           
                            libro.comentarios.splice(i,1);  
                         };
                    
                    }
                    $http.put("http://localhost:8080/documentos-web/api/fotocopias/"+libro.id ,libro);
                    
               
                    
                };
             
            }
               
        }
    ]);
})(window.angular);