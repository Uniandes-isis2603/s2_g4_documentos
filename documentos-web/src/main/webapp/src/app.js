
(function (ng) {
    var app = angular.module('mainApp', [
      
        'ui.router',
       
        // Internal modules dependencies       

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
       'carritoModule',
        'facturasModule'
        


    ]);
    // Resuelve problemas de las promesas
    app.config(['$qProvider','$urlRouterProvider', '$locationProvider',function ($qProvider,$urlRouterProvider,$locationProvider) {
            $qProvider.errorOnUnhandledRejections(false);
          $urlRouterProvider.otherwise('inicio');
          $locationProvider.hashPrefix('!');
           
        }]);

     app.run( function ($rootScope) 
     {$rootScope.carrito=[];
        
        });
  

   
    
 
})(window.angular);

