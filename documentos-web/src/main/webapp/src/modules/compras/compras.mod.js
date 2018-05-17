/**
 *@ngdoc overview
 *@name compra.modeule:compraModule
 *@description
 *Se define el modulo de Angular de Compra. el modulo encapsula todos los controladores y los templates
 *HTML que estaran relacionados con la compra directamente. En la segunda configuracion se inyecta la 
 *dependencia de ui.router que es la es utilizada para la configuraion de las URLs bajo las cuales se accede
 *al modulo.
 *Por ejemplo, para mostrar las compras en la 
 *URL: 'localhost:8080/compra/list' es necesario configurar el router por medio
 *del stateProvider que informa a AngulaJS de la relación entre la URL,
 *un estado definido (estado de mostrar compras), el controlador y la vista correspondiente.
 *Los estados definidos en este modulo son:
 *|ESTADO              |URL                     |VISTAS               |
 * | comprasList       | /compras               | /listview:          |
 * |                   |                        | compras.list.html   |
 * | Compra            | /id                    | /mainView:          |
 * |                   |                        | compras.html        |
 * |-------------------|------------------------|---------------------|
 */

(function (ng){
    var mod = ng.module("comprasModule", ['ui.router']);
    mod.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider){
            var basePath = 'src/modules/compras/';
            $urlRouterProvider.otherwise("/compras");
            
            $stateProvider.state('compras',{
                url:'/compras',
                abstract: true,
                parent:'usuarios',
                views: {
                    'compras':{
                        templateUrl: basePath + 'compras.html',
                        controller: 'comprasCtrl',
                        controllerAs: 'ctrl'
                    },
                   'menuView':{
                      templateUrl: 'src/modules/usuarios/menu.html',
                      controller: 'usuarioDetailCtrl',
                      controllerAs: 'ctrl'

                    }
                }
            }).state('comprasList',{
                url:'/list',
                parent: 'compras',
                views:{
                    'listView':{
                        templateUrl: basePath + 'compras.list.html'
                    }
                }
            }).state('comprasDetail',{
                url:'/{comprasId: Long}/detail',
                parent:'compras',
                param: {comprasId: null},
                views: {
                    'listView':{
                        templateUrl: basePath + 'compras.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'compras.detail.html',
                        controller: 'comprasDetailCtrl',
                        controllerAs:'ctrl'
                }
            }
            }).state('comprasCreate', {
                url: '/create',
                parent: 'compras',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/compras.new.html',
                        controller: 'comprasNewCtrl'
                    }
                }
                ,
                data: {
                    requireLogin: false,
                }
            }).state('comprasUpdate', {
                url: '/update/{comprasId:int}',
                parent: 'compras',
                param: {
                    editorialId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/compras.new.html',
                        controller: 'comprasUpdateCtrl'
                    }
                }
                ,
                data: {
                    requireLogin: true,
                }
                });
    }]);
})(window.angular);
