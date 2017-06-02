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
        var html = "<td>1</td>";
        var html = "<td>Universidad</td>";
        var html = "<td>Gastos de la universidad</td>";
        

        for (var i = 0; i < listaCategoria.length; i++) {
            
            html = "<td>" + listaCategoria[i].categoria_id + "</td>";
            html = "<td>" + listaCategoria[i].nombre + "</td>";
            html = "<td>" + listaCategoria[i].descripcion + "</td>";
            
        }
        $("#filacategoria").html(html);
    });
}