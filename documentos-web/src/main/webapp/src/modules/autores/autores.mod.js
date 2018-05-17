(function (ng) {
var mod = ng.module("autoresModule", ['ui.router']);
 
    mod.config(['$stateProvider','$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
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
                url: '/list',
                parent: 'autores',
                views: {
                    'listView':{
                        templateUrl: basePath + 'autores.list.html'
                    }
                }
                
                }).state('autoresDetail', {
                url:'/{autorId}/detail',
                parent: 'autores',
                param: {
                    cursoId: null
                },
                views: {
                    
                     
                    'listView': {
                        templateUrl: basePath + 'autores.list.html',
                        controller: 'autoresCtrl',
                        controllerAs: 'ctrl'
                    },
                    'detailView':{
                        templateUrl: basePath + 'autores.detail.html',
                        controller: 'autoresDetailCtrl',
                        controllerAs:'ctrl'
                    }
                }
          
            });
            
        }]);

})(window.angular);


