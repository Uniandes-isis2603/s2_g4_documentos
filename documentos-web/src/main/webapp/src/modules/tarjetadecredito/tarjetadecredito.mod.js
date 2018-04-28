(function(ng) {
    var mod=ng.module('tarjetadecreditoModule', ['ui.router']);
    mod.config(['$stateProvider','$urlRouterProvider', function($stateProvider, $urlRouterProvider){
            var basePath = 'src/modules/tarjetadecredito/';
            $urlRouterProvider.otherwise('/tarjetadecredito');
            
            $stateProvider.state('tarjetadecredito', {
                url: 'tarjetadecredito',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath+'tarjetadecredito.html',
                        controller: 'tarjetadecreditoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('tarjetadecreditoList', {
                url: '/tarjetadecredito',
                parent: 'tarjetadecredito',
                views: {
                    'listView':{
                        templateUrl: basePath + 'tarjetadecredito.list.html'
                    }
                }
            }).state('tarjetadecreditoDetail', {
                url: '/{tarjetadecreditoId:int}/detail',
                parent: 'tarjetadecredito',
                param: {
                    tarjetadecreditoId: null},
                views: {
                    'detailView': {
                        templateUrl: basePath + 'tarjetadecredito.detail.html',
                        controller: 'tarjetadecreditotDetailCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            
            }).state('tarjetadecreditoCreate', {
                url: '/create',
               // parent: 'tarjetadecredito',
                views: {
                    'mainView': {
                        templateUrl: basePath + '/new/tarjetadecredito.new.html',
                        controller: 'tarjetadecreditoNewCtrl'
                    }
                }
                ,
                data: {
                    requireLogin: true,
                    roles: ['admin']
                }
            }).state('tarjetadecreditoUpdate', {
                url: '/update/{tarjetadecreditoId:int}',
                parent: 'tarjetadecredito',
                param: {
                    editorialId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/tarjetadecredito.new.html',
                        controller: 'tarjetadecreditoUpdateCtrl'
                    }
                }
                ,
                data: {
                    requireLogin: true,
                    roles: ['admin']
                }
            });
    }]);
})(window.angular);