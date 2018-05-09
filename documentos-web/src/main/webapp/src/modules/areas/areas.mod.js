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
 * | areas            | /areas                     | mainView:              |
 * |                  |                            | areas.html             |
 * |                  |                            |                        |
 * | areas            | /list                      | listView:              |
 * |                  |                            | areas.list.html        |
 * |                  |                            |                        |
 * | areaDetail       | /{areaId:int}/detail       | listView:              |
 * |                  |                            | areas.list.html        |
 * |                  |                            | detailView:            |
 * |                  |                            | areas.detail.html      |
 * | areaCreate       | /create                    | detailView: (/new)     |
 * |                  |                            | /areas.new.html        |
 * | areaUpdate       | /update/{areaId:int}       | detailView: (/new)     |
 * |                  |                            | /areas.new.html        |
 * | areaDelete       | /delete/{areaId:int}       | detailView: (/delete)  |
 * |                  |                            | /areas.delete.html     |
 * |------------------|----------------------------|------------------------|
 *```
 */
(function(ng) {
    var mod = ng.module("areaModule", ['ui.router']);
    mod.config(['$stateProvider','$urlRouterProvider', function ($stateProvider,$urlRouterProvider){
            var basePath = 'src/modules/areas/';
            $urlRouterProvider.otherwise("/areasList");
            
            $stateProvider.state('areas', {
                url: '/areas',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'areas.list.html',
                        controller: 'areasCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            
            });
        }]);
})(window.angular);