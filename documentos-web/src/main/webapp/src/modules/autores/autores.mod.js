/**
 * @ngdoc overview
 * @name authors.module:authorModule
 * @description
 * Definición del módulo de Angular de Autores. El módulo encapsula todos los 
 * controladores y los templates HTML que estén relacionados con los Autores 
 * directamente. En la configuración del módulo se injecta la dependencia de 
 * ui.router que es la que se utiliza para la configuración de las URLs bajo las
 * cuales se accede al módulo. Por ejemplo, para mostrar los autores en la 
 * URL: 'localhost:8080/authors/list' es necesario configurar el router por 
 * medio del stateProvider que informa a AngularJS de la relación entre la URL, 
 * un estado definido (estado de mostrar autores), el controlador y la vista 
 * correspondiente. Los estados definidos en este modulo son:
 * ```
 * | ESTADO           | URL                        | VISTAS                 |
 * |------------------|----------------------------|------------------------|
 * | autores          | /autores                   | mainView:              |
 * |                  |                            | autores.html           |
 * |                  |                            |                        |
 * | autoresList      | /list                      | listView:              |
 * |                  |                            | autores.list.html      |
 * |                  |                            |                        |
 * | autorDetail      | /{autorId:int}/detail      | listView:              |
 * |                  |                            | autores.list.html      |
 * |                  |                            | detailView:            |
 * |                  |                            | autores.detail.html    |
 * | autorCreate      | /create                    | detailView: (/new)     |
 * |                  |                            | /autores.new.html      |
 * | autorUpdate      | /update/{autorId:int}      | detailView: (/new)     |
 * |                  |                            | /autores.new.html      |
 * | autorDelete      | /delete/{autorId:int}      | detailView: (/delete)  |
 * |                  |                            | /autor.delete.html     |
 * |------------------|----------------------------|------------------------|
 *```
 */
(function(ng) {
    var mod = ng.module("autorModule", ['ui.router']);
    mod.config(['$stateProvider','$urlRouterProvider', function ($stateProvider,$urlRouterProvider){
            var basePath = 'src/modules/autores/';
            $urlRouterProvider.otherwise("/autoresList");
            
            $stateProvider.state('autores', {
                url: '/autores',
              
                views: {
                    'mainView': {
                        templateUrl: basePath + 'autores.list.html',
                        controller: 'autoresCtrl',
                        controllerAs: 'ctrl'
                    }
                }
          
            });
        }]);
})(window.angular);