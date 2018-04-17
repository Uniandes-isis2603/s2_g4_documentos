
(function(ng) {
    var mod = ng.module("deseadoModule", ['ui.router']);
    mod.config(['$stateProvider','$urlRouterProvider', function ($stateProvider,$urlRouterProvider){
            var basePath = 'src/modules/deseados/';
            $urlRouterProvider.otherwise("/deseadosList");
            
            
            $stateProvider.state('deseados', {
                url: '/deseados',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'deseados.html',
                        controller: 'deseadoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('deseadosList', {
                url: '/list',
                parent: 'deseados',
                views: {
                    'listView':{
                        templateUrl: basePath + 'deseados.list.html'
                    }
                }
            }).state('deseadoDetail', {
                url:'/{deseadoId:int}/detail',
                parent: 'deseados',
                param: {
                    deseadoId: null
                },
                views: {
                    'detailView':{
                        templateUrl: basePath + 'deseados.detail.html',
                        controller: 'deseadoDetailCtrl',
                        controllerAs:'ctrl'
                    }
                }
            });
    }]);
})(window.angular);


