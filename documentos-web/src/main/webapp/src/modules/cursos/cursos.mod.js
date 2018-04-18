(function (ng) {
var mod = ng.module("cursosModule", ['ui.router']);
 
    mod.config(['$stateProvider','$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/cursos/';
            $urlRouterProvider.otherwise("/");

            $stateProvider.state('cursos', {
                url: '/cursos',
                views: {
                    'mainView': {
                        controller: 'cursosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'cursos-list.html'
                    }
                }
          
            });
        }]);

})(window.angular);


