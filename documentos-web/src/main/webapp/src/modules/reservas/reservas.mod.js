(function(ng) {
    var mod = ng.module("reservaModule", ['usuarioModule','ui.router']);
        mod.constant("usuariosContext", "api/usuarios");

    mod.config(['$stateProvider','$urlRouterProvider', function ($stateProvider,$urlRouterProvider){
            var basePath = 'src/modules/reservas/';
            $urlRouterProvider.otherwise("/reservasList");
                       
            $stateProvider.state('reservas', {
                url: '/{usuarioId: int}/reservas',
                abstract: true,
                parent:'usuarios',
                param: {
                   usuarioId: null
                },
                views: {
                    'reservas': {
                        templateUrl: basePath + 'reservas.html',
                        controller: 'reservasCtrl',
                        controllerAs: 'ctrl'
                    },
                    'menuView':{
                      templateUrl: 'src/modules/usuarios/menu.html',
                      controller: 'usuarioDetailCtrl',
                      controllerAs: 'ctrl'

                    }
                    
                }
            }).state('reservasList', {
                url: '/list',
                parent: 'reservas',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'reservas.list.html',
                        controller: 'reservasCtrl',
                        controllerAs: 'ctrl'
                    }
                 
                    
                }
            }).state('reservaDetail', {
                url: '/{reservaId:int}/detail',
                parent:'reservas',
                param:{
                    reservaId: null
                },
                views: {
                    'detailView':{
                        templateUrl: basePath + 'reservas.detail.html',
                        cotroller: 'reservaDetailCtrl',
                        controllerAs: 'ctrl'
                        
                    }
                }
                
            });
    }]);
})(window.angular);