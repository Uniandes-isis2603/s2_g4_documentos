(function (ng) {
    var mod = ng.module("comprasModule");
    mod.constant("comprasContext", "api/compras");
    mod.controller('comprasNewCtrl', ['$scope', '$http', 'comprasContext','tarjetadecreditoContext', 'paypalContext' ,'$state', '$rootScope',

               function ($scope, $http, comprasContext, $state, tarjetadecreditoContext, paypalContext, $rootScope) {
            $rootScope.edit = false;

            $scope.data = {};
            $scope.cosas = null;

            $scope.createCompras = function () {
                $http.post(comprasContext, $scope.data).then(function (response) {
                    $state.reload();
                });
                
             
            };
            
            $http.get("api/usuario/45/metodosdepago/tarjetasdecredito").then(function(TDCresponse)
                {
                    $scope.TDC = TDCresponse.data;
                });
            $http.get("api/usuario/45/metodosdepago/paypal").then(function(response)
                {
                    
                    $scope.PPC = response.data;
                });
                
            $http.get("api/libros").then(function(response)
            {
                $scope.Libros = response.data;
            });
           

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
               
                
                return s +":" +m+ ":"+ "00";
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
                $scope.data.fecha = fecha() + " " + startTime();
                $scope.data.costo = 15000;
                return fecha() + " " + startTime();
                
            };
        }
    ]);
}
)(window.angular);
