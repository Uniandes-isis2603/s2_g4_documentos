
 
(function(ng) {
    var mod = ng.module("autorModule", ['ui.router']);
    mod.config(['$stateProvider','$urlRouterProvider', function ($stateProvider,$urlRouterProvider){
            var basePath = 'src/modules/autores/';
            $urlRouterProvider.otherwise("/autoresList");
            
            
            $stateProvider.state('autores', {
                url: '/autores',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'autores.html',
                        controller: 'autoresCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('autoresList', {
                url: '/list/autores',
                parent: 'autores',
                views: {
                    'listView':{
                        templateUrl: basePath + 'autores.list.html'
                    }
                }
            });
    }]);
})(window.angular);