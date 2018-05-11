(function (ng) {
    var mod = ng.module("libroModule");
    mod.constant("libroContext", "api/libros");
    
 

    mod.filter('range', function () {
        return function (input, total) {
            total = parseInt(total);
            for (var i = 0; i < total; i++)
                input.push(i);
            return input;
        };
    });


    mod.controller('libroDetailCtrl', ['$scope', '$http', 'libroContext', '$state',
        
    
        
        /**
         * @ngdoc controller
         * @name libros.controller:libroDetailCtrl
         * @description 
         * Definición de un controlador auxiliar del módulo Libro.
         * Se crea el controlador con el cual se manejan las vistas de detalle del módulo.
         * @param {Object} $scope Referencia inyectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o funciones que se definen 
         * en este controlador y que son utilizadas desde el HTML.
         * @param {Object} $http Objeto inyectado para manejar las consultas HTTP
         * @param {Object} libroContext Constante inyectada que contiene la ruta donde se encuentra
         * el API de Libros en el  back-end.
         * @param {Object} $state  Dependencia inyectada en la que se recibe el
         * estado actual de la navecación definida en el módulo.   
         */
        function ($scope, $http, libroContext, $state) {
                $scope.comentarioContenido=" ";
        $scope.comentario={};
        $scope.data={};
            if (($state.params.libroId !== undefined) && ($state.params.libroId !== null)) {
                /**
                 * @ngdoc function
                 * @name getLibroID
                 * @methodOf libros.controller:libroDetailCtrl
                 * @description 
                 * Esta función utiliza el protocolo Http para obtener el recurso
                 * donde se encuentra el libro por ID en formato JSON.
                 * de los libros o API donde se puede consultar. 
                 * @param {json} response 
                 */
                $http.get(libroContext + '/' + $state.params.libroId).then(function (response) {
                    $scope.currentLibro = response.data;
                });

                $scope.hola = function (libro)
                {
                 $scope.data.nombre = libro.nombre;
                 $scope.data.descripcion = libro.descripcion;
                 $scope.data.caratula = libro.caratula;
                 $scope.data.ISBN = libro.ISBN;
                 $scope.data.precio = libro.precio;

                $scope.comentarioContenido.fecha="2015-10-28T14:12:59-05:00";

               
                $scope.data.comentarios= libro.comentarios.push($scope.comentario);

            $http.put("http://localhost:8080/documentos-web/api/libros" + "/" + libro.id, $scope.data).then(function(response){
                $state.go('librosList',{libroId: response.data.id},{reload:true});
            });



};
            }

        }
    ]);
})(window.angular);