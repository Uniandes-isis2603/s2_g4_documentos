(function (ng) {

    var mod = ng.module("inicioModule");

    mod.controller("inicioCtrl", ['$scope','$rootScope' , '$state', '$stateParams', '$http', function ($scope,$rootScope ,$state, $stateParams, $http) {
          $rootScope.home=1;
        }]);
})(window.angular);
