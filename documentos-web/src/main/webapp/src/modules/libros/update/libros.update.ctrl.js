(function (ng){
    var mod =ng.module("libroModule");
    mod.constant("LibroContext","api/libros");
    mod.controller('libroUpdateCtrl',['$scope', '$http', 'LibroContext', '$state', '$rootScope',
    function ($scope,$http, LibroContext,$state,$rootScope){
        
         $scope.data = {};
         
         var idLibro = $state.params.libroId;
         
         $http.get(LibroContext + '/' + idLibro).then(function(response){
             var libro = response.data;
             $scope.data.nombre = libro.nombre;
             $scope.data.descripcion = libro.descripcion;
             $scope.data.caratula = libro.caratula;
             $scope.data.ISBN = libro.ISBN;
             $scope.data.precio = libro.precio;
             $scope.data.pdf = libro.pdf;
         });
         
         
         $scope.createLibro = function(){
             $http.put(LibroContext + "/" + idLibro, $scope.data).then(function(response)
             {
                 
                 $state.go('librosList',{libroId: response.data.id},{reload:true});
             });
         };
         
         $http.get("api/autores").then(function (responseAutores){
                $scope.autoresRecords = responseAutores.data;
            });
            
        $http.get("api/areas").then(function (responseAreas){
                $scope.areasRecords = responseAreas.data;
            });
            
        $http.get("api/cursos").then(function (responseCursos){
                $scope.cursosRecords = responseCursos.data;
            });
            
        $http.get("api/editoriales").then(function (responseEditoriales){
            $scope.editorialesRecords = responseEditoriales.data;
        });
        
        $scope.asociarAutor = function (id) {
            
            var autores;
            
            if(typeof $scope.data.autores === "undefined")
            {
                autores = []; 
            } else
            {
                 autores = $scope.data.autores;
            }
            
            
            
            $http.get("api/autores/" + id).then(function (responseAutor){
                
                var autor = {id:responseAutor.data.id, nombre:responseAutor.data.nombre};
               
                autores.push(autor);
                
                $scope.data.autores = autores;
               
            });
        };
        
        $scope.asociarArea = function (id) {
            
            var areas;
            
            if(typeof $scope.data.areas === "undefined")
            {
                areas = []; 
            } else
            {
                 areas = $scope.data.areas;
            }
            
            
            
            $http.get("api/areas/" + id).then(function (responseArea){
                
                
                var area = {id:responseArea.data.id, nombre:responseArea.data.tipo};
                
                
                areas.push(area);
                
                $scope.data.areas = areas;
               
            });
        };
        
        $scope.asociarCurso = function (id) {
            
            if(typeof $scope.data.cursos === "undefined")
            {
               var cursos = []; 
            } else
            {
                 cursos = $scope.data.cursos;
            }
            
            $http.get("api/cursos/" + id).then(function (responseCurso){
                
                
                var curso = {id:responseCurso.data.id, nombre:responseCurso.data.nombre, codigo:responseCurso.data.codigo, departamento:responseCurso.data.departamento};
                
                
                cursos.push(curso);
                
                $scope.data.cursos = cursos;
               
            });
        };
        
        $scope.asociarEditorial = function(id) {
            if(typeof $scope.data.editorial === "undefined")
            {
                var editorial= {};
            }
            else{
                editorial = $scope.data.editorial;
            }
            
            $http.get("api/editoriales/" + id).then(function(responseEditorial){
                var editorial = {id:responseEditorial.data.id, nombre:responseEditorial.data.nombre};
                
                $scope.data.editorial = editorial;
            });
        };
    }]);
})(window.angular);
