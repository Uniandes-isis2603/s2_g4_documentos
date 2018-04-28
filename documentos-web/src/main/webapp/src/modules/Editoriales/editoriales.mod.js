(function (ng) {
var mod = ng.module("editorialesModule", ['ui.router']);
 
    mod.config(['$stateProvider','$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/Editoriales/';
            $urlRouterProvider.otherwise("/");

            $stateProvider.state('editoriales', {
                url: '/editoriales',
                views: {
                    'mainView': {
                        controller: 'editorialesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'editoriales-list.html'
                    }
                }
          
            });
        }]);

})(window.angular);


