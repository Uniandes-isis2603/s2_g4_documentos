(function (ng) {

    var mod = ng.module("carritoModule");

    mod.controller("carritoCtrl", ['$rootScope' ,'$scope',function ( $rootScope,$scope) 
        {
            $scope.carro=[];
          $scope.carro=$rootScope.carrito;
         
          $scope.total=function ()
          {
              var elTotal=0;
              for(var i=0;i<$scope.carro.length;i++ )
              {
                  
                  elTotal+= $scope.carro[i].precio;
              }
                  
          
              return elTotal;
          };
            
        }]);
})(window.angular);