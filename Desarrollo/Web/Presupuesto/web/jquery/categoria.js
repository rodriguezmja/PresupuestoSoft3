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
    var UsuarioId = localStorage.getItem("Usuario").split(",")[0];
    $.get("api/controladorcategoria/obtenercategoria",  {      
        usuario_id: UsuarioId}, function (response) {
        listaCategoria = $.parseJSON(response.message);
        var html ="<tr>";
        var  html = "<td class='1'></td>";
        var  html = "<td class='2'></td>";
        var  html = "<td class='3'></td>";
        var  html = "<td class='td-actions text-right'>";
        var  html = "<button title='' class='btn btn-primary btn-simple btn-xs' type='button' data-original-title='Edit Task' rel='tooltip'>";
        var  html = "<i class='material-icons'>edit</i>";
        var  html = "<div class='ripple-container'></div></button>";
        var  html = "<button title='' class='btn btn-danger btn-simple btn-xs' type='button' data-original-title='Remove' rel='tooltip'>";
        var  html = "<i class='material-icons'>close</i>";
        var  html = "</button>";
        var  html = "</td>";
        var  html ="</tr>";
        

        for (var i = 0; i < listaCategoria.length; i++) {
            html +="<tr>";
            html += "<td id='1'>" + listaCategoria[i].id + "</td>";
            html += "<td id='2'>" + listaCategoria[i].nombre + "</td>";
            html += "<td id='3'>" + listaCategoria[i].descripcion + "</td>";
            html += "<td class='td-actions text-right'>";
            html += "<button title='' class='btn btn-primary btn-simple btn-xs' type='button' data-original-title='Edit Task' rel='tooltip'>";
            html += "<i class='material-icons'>edit</i>";
            html += "<div class='ripple-container'></div></button>";
            html += "<button title='' class='btn btn-danger btn-simple btn-xs' type='button' data-original-title='Remove' rel='tooltip'>";
            html += "<i class='material-icons'>close</i>";
            html += "</button>";
            html += "</td>";
            
            html +="</tr>";
        }
        $("#filacat").html(html);
    });
}