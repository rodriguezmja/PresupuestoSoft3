$(document).ready(function () {
    obtenerCategoria();
});
function  crearCategoria() {
    var NombreCategoria = $("input[name=NombreCategoria]").val();
    var Descripcion = $("input[name=Descripcion]").val();
    var UsuarioId = localStorage.getItem("Usuario").split(",")[0];

    $.get("api/controladorcategoria/crearcategoria", {
        nombrecategoria: NombreCategoria,
        descripcion: Descripcion,
        usuario_id: UsuarioId},
            function (response) {
                alert(response.message);
                obtenerCategoria();
            });
            
}


function  obtenerCategoria() {
    $.get("api/controladorcategoria/obtenercategoria", function (response) {
        listaCategoria = $.parseJSON(response.message);
        var html ="<tr>";
        var  html = "<td class='1'></td>";
        var   html = "<td class='2'></td>";
        var   html = "<td class='3'></td>";
        var  html ="</tr>";
        

        for (var i = 0; i < listaCategoria.length; i++) {
            html +="<tr>";
            html += "<td id='1'>" + listaCategoria[i].id + "</td>";
            html += "<td id='2'>" + listaCategoria[i].nombre + "</td>";
            html += "<td id='3'>" + listaCategoria[i].descripcion + "</td>";
            html +="</tr>";
        }
        $("#filacat").html(html);
    });
}