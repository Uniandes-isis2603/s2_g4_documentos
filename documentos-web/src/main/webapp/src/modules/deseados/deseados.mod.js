(function(ng) {
    var mod = ng.module("deseadoModule", ['usuarioModule','ui.router']);
        mod.constant("usuariosContext", "api/usuarios");

    mod.config(['$stateProvider','$urlRouterProvider', function ($stateProvider,$urlRouterProvider){
            var basePath = 'src/modules/deseados/';
            $urlRouterProvider.otherwise("/deseadosList");
                       
            $stateProvider.state('deseados', {
                url: '/{usuarioId: int}/deseados',
                abstract: true,
                parent:'usuarios',
                param: {
                   usuarioId: null
                },
                views: {
                    'deseados': {
                        templateUrl: basePath + 'deseados.html',
                        controller: 'deseadoCtrl',
                        controllerAs: 'ctrl'
                    },
                    'menuView':{
                      templateUrl: 'src/modules/usuarios/menu.html',
                      controller: 'usuarioCtrl',
                      controllerAs: 'ctrl'

                    }
                    
                }
            }).state('deseadosList', {
                url: '/list',
                parent: 'deseados',
             
                views: {
                    'detailView': {
                        templateUrl: basePath + 'deseados.list.html',
                        controller: 'deseadoCtrl',
                        controllerAs: 'ctrl'
                    }
                 
                    
                }
            });
    }]);
})(window.angular);