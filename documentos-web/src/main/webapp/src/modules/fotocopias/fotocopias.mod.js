/**
 * @ngdoc overview
 * @name fotocopias.module:fotocopiaModule
 * @description 
 * Definición del módulo de Angular de Fotocopia. El módulo encapsula todos los controladores
 * y los templates HTML que estén relacionados con la fotocopia directamente. En la
 * configuración del módulo se inyecta la dependencia ui.router que es la que se utiliza para
 * la configuración del las URLs bajo las cuales se accede al módulo.
 * Por ejemplo, para mostrar las fotocopias en la 
 * URL: 'localhost:8080/fotocopias/list' es necesario configurar el router por medio
 * del stateProvider que informa a AngulaJS de la relación entre la URL,
 * un estado definido (estado de mostrar fotocopias), el controlador y la vista correspondiente.
 * Los estados definidos en este modulo son:
 * ```
 * |ESTADO              |URL                      |VISTAS                 |
 * | fotocopias         | /fotocopias             | /mainView:            |
 * |                    |                         | fotocopias.html       |
 * | fotocopiasList     | /list                   | listview:             |
 * |                    |                         | fotocopias.list.html  |
 * | fotocopiaDetail    |/{fotocopiaId:int}/detail| detailView:           |
 * |                    |                         | fotocopias.detail.html|
 * |--------------------|-------------------------|-----------------------|
 * ```
 */
(function(ng) {
    var mod = ng.module("fotocopiaModule", ['ui.router']);
    mod.config(['$stateProvider','$urlRouterProvider', function ($stateProvider,$urlRouterProvider){
            var basePath = 'src/modules/fotocopias/';
            $urlRouterProvider.otherwise("/fotocopiasList");
            
            
            $stateProvider.state('fotocopias', {
                url: '/fotocopias',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'fotocopias.html',
                        controller: 'fotocopiaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('fotocopiasList', {
                url: '/list',
                parent: 'fotocopias',
                views: {
                    'listView':{
                        templateUrl: basePath + 'fotocopias.list.html'
                    }
                }
            }).state('fotocopiaDetail', {
                url:'/{fotocopiaId:int}/detail',
                parent: 'fotocopias',
                param: {
                    libroId: null
                },
                views: {
                    'detailView':{
                        templateUrl: basePath + 'fotocopias.detail.html',
                        controller: 'fotocopiaDetailCtrl',
                        controllerAs:'ctrl'
                    }
                }
            });
    }]);
})(window.angular);