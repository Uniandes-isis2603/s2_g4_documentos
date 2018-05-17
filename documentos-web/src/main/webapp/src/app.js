
(function (ng) {
    var app = angular.module('mainApp', [
      
        'ui.router',
       
        // Internal modules dependencies       

        'inicioModule',
        'citiesModule',
        'editorialesModule',
        'cursosModule',
       'libroModule',
       'fotocopiaModule',
       'usuarioModule',
       'reservaModule',
       'deseadoModule',
       'autorModule',
       'areaModule',
       'paypalModule',
       'tarjetadecreditoModule',
       'comprasModule',
       'facturasModule'

        


    ]);
    // Resuelve problemas de las promesas
    app.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
        }]);

     app.run( function ($rootScope) {
            $rootScope.home=0;
        });
    

})(window.angular);

