/**
 * @ngdoc overview
 * @name reservas.module:reservaModule
 * @description 
 * Definición del módulo de Angular de reserva. El módulo encapsula todos los controladores
 * y los templates HTML que estén relacionados con el reserva directamente. En la
 * configuración del módulo se inyecta la dependencia ui.router que es la que se utiliza para
 * la configuración del las URLs bajo las cuales se accede al módulo.
 * Por ejemplo, para mostrar los reservas en la 
 * URL: 'localhost:8080/reservas/list' es necesario configurar el router por medio
 * del stateProvider que informa a AngulaJS de la relación entre la URL,
 * un estado definido (estado de mostrar reservas), el controlador y la vista correspondiente.
 * Los estados definidos en este modulo son:
 * ```
 * |ESTADO              |URL                     |VISTAS               |
 * | reservas             | /reservas                | /mainView:          |
 * |                    |                        | reservas.html         |
 * | reservasList         | /list                  | listview:           |
 * |                    |                        | reservas.list.html    |
 * | reservaDetail        | /{reservaId:int}/detail  | detailView:         |
 * |                    |                        |reservas.detail.html   |
 * |--------------------|------------------------|---------------------|
 * ```
 */
(function(ng) {
    var mod = ng.module("reservaModule", ['usuarioModule','ui.router']);
        mod.constant("usuariosContext", "api/usuarios");

    mod.config(['$stateProvider','$urlRouterProvider', function ($stateProvider,$urlRouterProvider){
            var basePath = 'src/modules/reservas/';
            $urlRouterProvider.otherwise("/reservasList");
            
            
            $stateProvider.state('reservas', {
                url: '/reservas',
                abstract: true,
                parent: 'usuarioDetail',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'reservas.html',
                        controller: 'reservasCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('reservasList', {
                url: '/list',
                parent: 'reservas',
                views: {
                    'listView':{
                        templateUrl: basePath + 'reservas.list.html'
                    }
                }
            }).state('reservaDetail', {
                url:'/{reservaId:int}/detail',
                parent: 'reservas',
                param: {
                    reservaId: null
                },
                views: {
                    'detailView':{
                        templateUrl: basePath + 'reservas.detail.html',
                        controller: 'reservaDetailCtrl',
                        controllerAs:'ctrl'
                    }
                }
            });
    }]);
})(window.angular);



