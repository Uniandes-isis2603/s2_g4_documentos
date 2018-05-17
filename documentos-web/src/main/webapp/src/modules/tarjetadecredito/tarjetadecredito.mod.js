(function(ng) {
    var mod=ng.module('tarjetadecreditoModule', ['ui.router']);
    mod.config(['$stateProvider','$urlRouterProvider', function($stateProvider, $urlRouterProvider){
            var basePath = 'src/modules/tarjetadecredito/';
            $urlRouterProvider.otherwise('/tarjetadecredito');
            
            $stateProvider.state('tarjetadecredito', {
                url: 'tarjetadecredito',
                abstract: true,
                parent:'usuarios',
                views: {
                    'tarjetas': {
                        templateUrl: basePath+'tarjetadecredito.html',
                        controller: 'tarjetadecreditoCtrl',
                        controllerAs: 'ctrl'
                    },
                    'menuView': {
                      templateUrl: 'src/modules/usuarios/menu.html',
                      controller: 'usuarioDetailCtrl',
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
                        controller: 'tarjetadecreditoDetailCtrl',
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
               
            }).state('tarjetadecreditoUpdate', {
                url: '/update/{tarjetadecreditoId:int}',
                parent: 'tarjetadecredito',
                param: {
                    tarjetadecreditoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/tarjetadecredito.new.html',
                        controller: 'tarjetadecreditoUpdateCtrl'
                    }
                }
               
            });
    }]);
})(window.angular);