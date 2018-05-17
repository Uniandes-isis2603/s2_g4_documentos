(function (ng) {
var mod = ng.module("areasModule", ['ui.router']);
 
    mod.config(['$stateProvider','$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
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
                url: '/list',
                parent: 'areas',
                views: {
                    'listView':{
                        templateUrl: basePath + 'areas.list.html'
                    }
                }
                
                }).state('areasDetail', {
                url:'/{areaId}/detail',
                parent: 'areas',
                param: {
                    areaId: null
                },
                views: {
                    
                     
                    'listView': {
                        templateUrl: basePath + 'areas.list.html',
                        controller: 'areasCtrl',
                        controllerAs: 'ctrl'
                    },
                    'detailView':{
                        templateUrl: basePath + 'areas.detail.html',
                        controller: 'areasDetailCtrl',
                        controllerAs:'ctrl'
                    }
                }
          
            });
            
        }]);

})(window.angular);


