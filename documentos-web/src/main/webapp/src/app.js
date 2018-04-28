(function (ng) {
    var app = angular.module('mainApp', [
      
        'ui.router',
       
        // Internal modules dependencies       

        'libroModule',
       'fotocopiaModule',
        'inicioModule',
        'citiesModule',
        'libroModule',
        'editorialesModule',
        'cursosModule'

    ]);
    // Resuelve problemas de las promesas
    app.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
        }]);
     app.run( function ($rootScope) {
            $rootScope.home=0;
        });
    
})(window.angular);

