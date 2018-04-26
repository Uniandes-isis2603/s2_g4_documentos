/**
 * @ngdoc overview
 * @name libros.module:libroModule
 * @description 
 * Definición del módulo de Angular de Libro. El módulo encapsula todos los controladores
 * y los templates HTML que estén relacionados con el libro directamente. En la
 * configuración del módulo se inyecta la dependencia ui.router que es la que se utiliza para
 * la configuración del las URLs bajo las cuales se accede al módulo.
 * Por ejemplo, para mostrar los libros en la 
 * URL: 'localhost:8080/libros/list' es necesario configurar el router por medio
 * del stateProvider que informa a AngulaJS de la relación entre la URL,
 * un estado definido (estado de mostrar libros), el controlador y la vista correspondiente.
 * Los estados definidos en este modulo son:
 * ```
 * |ESTADO              |URL                     |VISTAS               |
 * | libros             | /libros                | /mainView:          |
 * |                    |                        | libros.html         |
 * | librosList         | /list                  | listview:           |
 * |                    |                        | libros.list.html    |
 * | libroDetail        | /{libroId:int}/detail  | detailView:         |
 * |                    |                        |libros.detail.html   |
 * |--------------------|------------------------|---------------------|
 * ```
 */
(function(ng) {
    var mod = ng.module("libroModule", ['ui.router']);
    mod.config(['$stateProvider','$urlRouterProvider', function ($stateProvider,$urlRouterProvider){
            var basePath = 'src/modules/libros/';
            $urlRouterProvider.otherwise("/x");
            
            
            $stateProvider.state('libros', {
                url: '/libros',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'libros.html',
                        controller: 'libroCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('librosList', {
                url: '/list',
                parent: 'libros',
                views: {
                    'listView':{
                        templateUrl: basePath + 'libros.list.html'
                    }
                }
            }).state('libroDetail', {
                url:'/{libroId:int}/detail',
                parent: 'libros',
                param: {
                    libroId: null
                },
                views: {
                    'detailView':{
                        templateUrl: basePath + 'libros.detail.html',
                        controller: 'libroDetailCtrl',
                        controllerAs:'ctrl'
                    }
                }
            });
    }]);
})(window.angular);