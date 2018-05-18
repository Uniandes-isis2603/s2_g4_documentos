/**
 * @ngdoc overview
 * @name login.module:loginModule
 * @description 
 * Definición del módulo de Angular de Manejo de sesión.
 * Los estados definidos en este modulo son:
 * ```
 * |Estado              |URL                |Vistas          |
 * |--------------------|-------------------|----------------|
 * |login               |/login             |mainview:       |
 * |                    |                   |login.html      |
 * |--------------------|-------------------|----------------|
 * ```
 */
(function(ng){
    
    var mod = ng.module("loginModule", ['ui.router']);
    
    mod.config(['$stateProvider', '$urlRouterProvider', function($stateProvider,$urlRouterProvider){
            
            var basePath = "src/modules/login/";
            
            $urlRouterProvider.otherwise("/login");
            
            $stateProvider.state('login', {
                url: '/login',
                data: {
                    requireLogin:false
                },
                views:{
                    'mainView':{
                        templateUrl: basePath + 'login.html',
                        controller: 'loginCtrl'
                    }
                }
            });
    }]);
})(window.angular);

