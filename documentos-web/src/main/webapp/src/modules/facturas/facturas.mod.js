(function(ng){
    var mod = ng.module("facturasModule",['ui.router']);
    mod.config(['$stateProvider', '$urlRouterProvider', function($stateProvider,$urlRouterProvider){
            var basePath = 'src/modules/facturas/';
            $urlRouterProvider.otherwise('/facturas');
            
           $stateProvider.state('facturas',{
               url:'facturas',
               abstract: true,
               parent:'usuarios',
               views:{
                   'facturas':{
                       templateUrl: basePath + 'facturas.html',
                       controller: 'facturasCtrl',
                       controllerAs: 'ctrl'
                   },
                   'menuView':{
                      templateUrl: 'src/modules/usuarios/menu.html',
                      controller: 'usuarioDetailCtrl',
                      controllerAs: 'ctrl'

                    }
               }
           }).state('facturasList',{
               url: '/list',
               parent: 'facturas',
               views:{
                   'listView':{
                       templateUrl: basePath + 'facturas.list.html'
                   }
               }
           }).state('facturasDetail', {
                url: '/{facturasId: int}/detail',
                parent: 'facturas',
                param: {facturasId: null},
                views: {
                    'detailView': {
                        templateUrl: basePath + 'facturas.detail.html',
                        controller: 'facturasDetailCtrl',
                        controllerAs: 'ctrl'
                    }
                }

            }).state('facturasCreate', {
                url: '/create',
               // parent: 'facturas',
                views: {
                    'mainView': {
                        templateUrl: basePath + '/new/facturas.new.html',
                        controller: 'facturasNewCtrl'
                    }
                }
            }).state('facturasUpdate', {
                url: '/update/{facturasId:int}',
                parent: 'facturas',
                param: {
                    editorialId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/facturas.new.html',
                        controller: 'facturasUpdateCtrl'
                    }
                }
            });
    }]);
})(window.angular);