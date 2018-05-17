(function (ng) {

    var mod = ng.module("inicioModule");

    mod.controller("inicioCtrl", ['$scope', '$state', '$stateParams', '$http', function ($scope,$state, $stateParams, $http) {
          sessionStorage.getItem("home");
        }]);
})(window.angular);
