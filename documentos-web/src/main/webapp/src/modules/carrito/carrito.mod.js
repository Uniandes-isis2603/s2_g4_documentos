(function (ng) {
var mod = ng.module("carritoModule", ['ui.router']);
 
    mod.config(['$stateProvider','$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/carrito/';
            $urlRouterProvider.otherwise("/inicio");

            $stateProvider.state('carrito', {
                url: '/carrito',
                views: {
                    'mainView': {
                        controller: 'carritoCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'carrito-list.html'
                    }
                }
          
            });
        }]);

})(window.angular);



