(function (ng) {
var mod = ng.module("editorialesModule", ['ui.router']);
 
    mod.config(['$stateProvider','$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/Editoriales/';
            $urlRouterProvider.otherwise("/editorialesList");
            
    $stateProvider.state('editoriales', {
                url: '/editoriales',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'editoriales.html',
                        controller: 'editorialesCtrl',
                        controllerAs: 'ctrl'
                    }
                }
          }).state('editorialesList', {
                url: '/list',
                parent: 'editoriales',
                views: {
                    'listView':{
                        templateUrl: basePath + 'editoriales-list.html'
                    }
                }
                
                }).state('editorialesDetail', {
                url:'/{editorialId}/detail',
                parent: 'editoriales',
                param: {
                    editorialId: null
                },
                views: {
                    
                     
                    'listView': {
                        templateUrl: basePath + 'editoriales-list.html',
                        controller: 'editorialesCtrl',
                        controllerAs: 'ctrl'
                    },
                    'detailView':{
                        templateUrl: basePath + 'editoriales.detail.html',
                        controller: 'editorialesDetailCtrl',
                        controllerAs:'ctrl'
                    }
                }
          
            });
            
        }]);

})(window.angular);


