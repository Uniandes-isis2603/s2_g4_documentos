
(function (ng) {
    var app = angular.module('mainApp', [
      
        'ui.router',
       
        // Internal modules dependencies       

        'citiesModule',
        'editorialesModule',
        'cursosModule',
       'libroModule',
       'fotocopiaModule',
       'usuarioModule',
       'reservaModule',
       'deseadoModule',
       'autoresModule',
       'areasModule',
       'paypalModule',
       'tarjetadecreditoModule',
       'comprasModule',
       'inicioModule', 
       'carritoModule'

        


    ]);
    // Resuelve problemas de las promesas
    app.config(['$qProvider','$urlRouterProvider',function ($qProvider,$urlRouterProvider) {
            $qProvider.errorOnUnhandledRejections(false);
          $urlRouterProvider.otherwise('inicio');
           
        }]);

     app.run( function ($rootScope) 
     {$rootScope.carrito=[];
        
        });
  

   
    
 
})(window.angular);

