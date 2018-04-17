
(function(ng) {
    var mod = ng.module("usuarioModule", ['ui.router']);
    mod.config(['$stateProvider','$urlRouterProvider', function ($stateProvider,$urlRouterProvider){
            var basePath = 'src/modules/usuarios/';
            $urlRouterProvider.otherwise("/usuariosList");
            
            
            $stateProvider.state('usuarios', {
                url: '/usuarios',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'usuarios.html',
                        controller: 'usuarioCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('usuariosList', {
                url: '/list',
                parent: 'usuarios',
                views: {
                    'listView':{
                        templateUrl: basePath + 'usuarios.list.html'
                    }
                }
            }).state('usuarioDetail', {
                url:'/{usuarioId:int}/detail',
                parent: 'usuarios',
                param: {
                    usuarioId: null
                },
                views: {
                    'detailView':{
                        templateUrl: basePath + 'usuarios.detail.html',
                        controller: 'usuarioDetailCtrl',
                        controllerAs:'ctrl'
                    }
                }
            });
    }]);
})(window.angular);
