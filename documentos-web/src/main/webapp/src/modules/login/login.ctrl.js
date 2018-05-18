(function(ng){
    var mod = ng.module("loginModule");
    mod.controller('loginCtrl', ['$scope', '$http','$state','$rootScope',
        /**
         * @ngdoc controller
         * @name login.controller:loginCtrl
         * @description 
         * Definición del controlador de Angular del módulo login.
         * Se crea el controlador con el cual se maneja el módulo.
         * @param {Object} $scope Referencia inyectada al Scope definida para este controlador
         * el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas desde el HTML.
         * @param {Object} $http Objeto inyectado para manejar consultas HTTP.
         * @param {Object} $state Dependencia inyectada en la que recibe el 
         * estado actual de la navegación definída en el módulo.
         * @param {Object} $rootScope Referencia inyectada al scope
         * para toda la aplicación.    
         */
        function ($scope,$http,$state,$rootScope){
            
            $scope.user= {};
            $scope.data= {};
            
            $http.get('data/users.json').then(function(response){
                $scope.users = response.data;
            });
            
            
            /**
             * @ngdoc function
             * @name autenticar
             * @methodOf login.controller:loginCtrl
             * @description
             * Esta función procesa el inicio de sesión usando los datos del $scope
             */
            $scope.autenticar = function (){
                var flag = false;
                $http.post('api/login',$scope.data).then(function(response){
                    
                    for(var item in $scope.users){
                        if($scope.users[item].user === response.data.username && $scope.users[item].password === response.data.password && $scope.users[item].rol === response.data.rol){
                            flag = true;
                            $scope.user = $scope.users[item];
                            $state.go('librosList',{},{reload:true});
                            break;
                        }
                    }
                    if(!flag) {
                        $rootScope.alerts.push({type: "danger", msg: "Usuario o contraseña incorrectos."});
                        
                    }else{
                        sessionStorage.token = $scope.user.token;
                        sessionStorage.setItem("username", $scope.user.user);
                        sessionStorage.setItem("name", $scope.user.name);
                        sessionStorage.setItem("rol", $scope.user.rol);
                        $rootScope.currentUser = $scope.user.name;
                    }
                });
            };
        }
    ]);
});