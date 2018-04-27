(function(ng){
    var mod = ng.module("paypalModule",['ui.router']);
    mod.config(['$stateProvider', '$urlRouterProvider', function($stateProvider,$urlRouterProvider){
            var basePath = 'src/modules/paypal/';
            $urlRouterProvider.otherwise('/paypalList');
            
           $stateProvider.state('paypal',{
               url:'/paypal',
               abstract: true,
               views:{
                   'mainView':{
                       templateUrl: basePath + 'paypal.html',
                       controller: 'paypalCtrl',
                       controllerAs: 'ctrl'
                   }
               }
           }).state('paypalList',{
               url: '/list',
               parent: 'paypal',
               views:{
                   'listView':{
                       templateUrl: basePath + 'paypal.list.html'
                   }
               }
           }).state('paypalDetail', {
                url: '/{paypalId: long}/detail',
                parent: 'paypal',
                param: {paypalId: null},
                views: {
                    'listView': {
                        templateUrl: basePath + 'paypal.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'paypal.detail.html',
                        controller: 'paypalDetailCtrl',
                        controllerAs: 'ctrl'
                    }
                }

            });
    }]);
})(window.angular);