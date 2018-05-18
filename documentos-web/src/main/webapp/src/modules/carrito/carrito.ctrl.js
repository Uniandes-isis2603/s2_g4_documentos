(function (ng) {

    var mod = ng.module("carritoModule");

    mod.controller("carritoCtrl", ['$rootScope' ,'$scope','$http','$state',function ( $rootScope,$scope,$http,$state) 
        {
          $scope.carro=[];
            
          $scope.carro=$rootScope.carrito;
         
      
        $scope.data={};
        $scope.data.id=Math.floor((Math.random() * 9000) + 1000);
         $scope.data.costo;
         $scope.data.fecha=new Date().toJSON();
         $scope.data.documentos=[];
         $scope.data.metodoDePagoTDC={};
         
        $scope.data.metodoDePagoTDC.id= 7500;
        $scope.data.metodoDePagoTDC.nombreEnLaTarjeta= "gregorio";
        $scope.data.metodoDePagoTDC.nroDeLaTarjeta="4234567890123456";
        $scope.data.metodoDePagoTDC.numeroDeSeguridad= 123;
        $scope.data.metodoDePagoTDC.tipoDeTarjeta= "Visa";
         $scope.data.usuario={ };
         
         $scope.data.usuario.nombre="gregorio ospina";
         $scope.data.usuario.edad=20;
         $scope.data.usuario.genero=1;
         $scope.data.usuario.correo="g.ospina@uniandes.edu.co";
         $scope.data.usuario.usuario="Grego777";
         $scope.data.usuario.id=45;
         
         $scope.exito=false;
            
          $scope.total=function ()
          {
              var elTotal=0;
              for(var i=0;i<$scope.carro.length;i++ )
              {
                  
                  elTotal+= $scope.carro[i].precio;
              }
                $scope.data.costo=elTotal;  
          
              return elTotal;
          };
          
          
          $scope.crearFactura=function (carrito)
          {
           $scope.data.documentos=carrito;   
           
          
                             
                $http.post("http://localhost:8080/documentos-web/api/facturas",$scope.data).then(function () 
                {
                    $scope.exito=true;
                     $scope.data.id+=1;
                     $scope.carro=[];
                     $rootScope.carrito=[];
                     $state.go('inicio');
                     
                     
                    
                });
                
          };
             $scope.borrar=function (documento)
          {
              
                for (var i = 0; i < $rootScope.carrito.length; i++) 
                {
                if($rootScope.carrito[i].id===documento.id)
                {
                     
                    $rootScope.carrito.splice(i,1);
                    $scope.carro.splice(i,1);
                 
                }
                  
            }
             $state.reload();
          };
          
            
        }]);


})(window.angular);