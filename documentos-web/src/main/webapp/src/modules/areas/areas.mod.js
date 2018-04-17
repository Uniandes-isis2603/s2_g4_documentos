
 
(function(ng) {
    var mod = ng.module("areaModule", ['ui.router']);
    mod.config(['$stateProvider','$urlRouterProvider', function ($stateProvider,$urlRouterProvider){
            var basePath = 'src/modules/areas/';
            $urlRouterProvider.otherwise("/areasList");
            
            
            $stateProvider.state('areas', {
                url: '/areas',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'areas.html',
                        controller: 'areasCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('areasList', {
                url: '/list/areas',
                parent: 'autores',
                views: {
                    'listView':{
                        templateUrl: basePath + 'areas.list.html'
                    }
                }
            });
    }]);
})(window.angular);