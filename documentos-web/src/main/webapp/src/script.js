/**
 * Metodo para que cambie de flecha el filtro.
 * @type type
 */
$(document).ready(function(){
  $(".input-group").on("hide.bs.dropdown", function(){
    $(".btn.btn-default.dropdown-toggle").html('Filtrar <span class="caret"></span>');
  });
  $(".input-group").on("show.bs.dropdown", function(){
    $(".btn.btn-default.dropdown-toggle").html('Filtrar <span class="caret caret-up"></span>');
  });
});

/**
 * Metodo para que se vea la pagina principal
 */
$(document).ready(function(){
    $("#principal").click(function(){
        $("#navego").hide();
        $(".Intro").remove();
        $("body").append("<div class= 'Intro'><h1>Bienvenidos a Bookzon</h1><center><hr></center></div>");
    });
    });

/**
 * 
 * Metodo para que se vea la pagina de libros.
 */
$(document).ready(function(){
    $("#libros").click(function(){
        $("#navego").show();
        $(".Intro").remove();
        });
    });

$("#input-id").rating();