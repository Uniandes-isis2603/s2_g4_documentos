(function (ng) {
    var mod = ng.module("facturasModule");
    mod.constant("facturasContext", "api/facturas");
    mod.controller('facturasDetailCtrl', ['$scope', '$http', 'facturasContext', '$state',
        function ($scope, $http, facturasContext, $state) {

            if (($state.params.facturasId !== undefined) && ($state.params.facturasId !== null)) {
                $http.get(facturasContext + '/' + $state.params.facturasId).then(function (response) {
                    $scope.currentFacturas = response.data;
                });
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
                return fecha() + " " + startTime();
                
            };
        }
    ]);
}
)(window.angular);