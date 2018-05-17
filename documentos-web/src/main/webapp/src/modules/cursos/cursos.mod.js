(function (ng) {
var mod = ng.module("cursosModule", ['ui.router']);
 
    mod.config(['$stateProvider','$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/cursos/';
            $urlRouterProvider.otherwise("/cursosList");
            
    $stateProvider.state('cursos', {
                url: '/cursos',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'cursos.html',
                        controller: 'cursosCtrl',
                        controllerAs: 'ctrl'
                    }
                }
          }).state('cursosList', {
                url: '/list',
                parent: 'cursos',
                views: {
                    'listView':{
                        templateUrl: basePath + 'cursos-list.html'
                    }
                }
                
                }).state('cursosDetail', {
                url:'/{cursoId}/detail',
                parent: 'cursos',
                param: {
                    cursoId: null
                },
                views: {
                    
                     
                    'listView': {
                        templateUrl: basePath + 'cursos-list.html',
                        controller: 'cursosCtrl',
                        controllerAs: 'ctrl'
                    },
                    'detailView':{
                        templateUrl: basePath + 'cursos.detail.html',
                        controller: 'cursosDetailCtrl',
                        controllerAs:'ctrl'
                    }
                }
          
            });
            
        }]);

})(window.angular);


