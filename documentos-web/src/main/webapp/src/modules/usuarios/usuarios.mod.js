/**
 * @ngdoc overview
 * @name usuarios.module:usuarioModule
 * @description 
 * Definición del módulo de Angular de usuario. El módulo encapsula todos los controladores
 * y los templates HTML que estén relacionados con el usuario directamente. En la
 * configuración del módulo se inyecta la dependencia ui.router que es la que se utiliza para
 * la configuración del las URLs bajo las cuales se accede al módulo.
 * Por ejemplo, para mostrar los usuarios en la 
 * URL: 'localhost:8080/usuarios/list' es necesario configurar el router por medio
 * del stateProvider que informa a AngulaJS de la relación entre la URL,
 * un estado definido (estado de mostrar usuarios), el controlador y la vista correspondiente.
 * Los estados definidos en este modulo son:
 * ```
 * |ESTADO              |URL                     |VISTAS               |
 * | usuarios             | /usuarios                | /mainView:          |
 * |                    |                        | usuarios.html         |
 * | usuariosList         | /list                  | listview:           |
 * |                    |                        | usuarios.list.html    |
 * | usuarioDetail        | /{usuarioId:int}/detail  | detailView:         |
 * |                    |                        |usuarios.detail.html   |
 * |--------------------|------------------------|---------------------|
 * ```
 */
(function (ng) {
    var mod = ng.module("usuarioModule", ['ui.router']);
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
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
                    'listView': {
                        templateUrl: basePath + 'usuarios.list.html'
                    }
                }
            }).state('usuarioCreate', {
                url: '/create',
                parent: 'usuarios',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/usuarios.new.html',
                        controller: 'usuariosNewCtrl',
                    }
                }
            }).state('usuarioDetail', {
                url: '/{usuarioId:int}/detail',
                parent: 'usuarios',
                param: {
                    usuarioId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'usuarios.detail.html',
                        controller: 'usuarioDetailCtrl',
                        controllerAs: 'ctrl'
                    },
                    'menuView': {
                        templateUrl: basePath + 'menu.html',
                        controller: 'usuarioDetailCtrl',
                        controllerAs: 'ctrl'
                    }

                }
            }).state('usuarioUpdate', {
                url: '/update/{usuarioId:int}',
                parent: 'usuarios',
                param: {
                    usuarioId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/update/usuarios.update.html',
                        controller: 'usuariosUpdateCtrl'
                    }
                }
               
            });
        }]);
})(window.angular);
