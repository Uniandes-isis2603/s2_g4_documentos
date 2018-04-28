(function (ng) {
    var mod = ng.module("comprasModule");
    mod.constant("comprasContext", "api/compras");
    mod.controller('comprasNewCtrl', ['$scope', '$http', 'comprasContext','tarjetadecreditoContext', 'paypalContext' ,'$state', '$rootScope',

               function ($scope, $http, comprasContext, $state, tarjetadecreditoContext, paypalContext, $rootScope) {
            $rootScope.edit = false;

            $scope.data = {};

            $scope.createCompras = function () {
                $http.post(comprasContext, $scope.data).then(function (response) {
                    $state.go('comprasList', {comprasId: response.data.id}, {reload: true});
                });
            };
            
            
            $scope.TDC2 = function() {
                
                $http.get(tarjetadecreditoContext).then(function(response)
                {
                    $scope.TDC = response.data;
                }
            )};

            $scope.PPC2 = function() {
                
                $http.get(paypalContext).then(function(response)
                {
                    $scope.PPC = response.data;
                }
               )};
   
            function darMP(usuario)
            {
                var gaitan = [];
                $scope.PPC2;
                $scope.TDC2;
                for(paypal in PPC)
                {
                    if paypal. = usuario;
                }
            }
            
            function checkTime(i) {
                if (i < 10) {
                  i = "0" + i;
                }
                return i;
              }

              function startTime() {
                var today = new Date();
                var h = today.getHours();
                var m = today.getMinutes();
                var s = today.getSeconds();
                // add a zero in front of numbers<10
                m = checkTime(m);
                s = checkTime(s);
               
                
                return s +":" +m;
              }

              function fecha(){
              var today = new Date();
              var dd = today.getDate();
              var mm = today.getMonth()+1; //January is 0!
              var yyyy = today.getFullYear();

              if(dd<10) {
                  dd = '0'+dd;
              } 

              if(mm<10) {
                  mm = '0'+mm;
              } 

              today = yyyy + '/' + mm + '/' + dd;
              return today;
              }

            
            $scope.fechaTiempo = function() {
                return fecha() + " " + startTime();
            };
        }
    ]);
}
)(window.angular);
