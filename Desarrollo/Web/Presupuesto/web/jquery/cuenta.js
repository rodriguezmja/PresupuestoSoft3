var idcuenta = 0;

$(document).ready(function () {
    obtenerCuenta();
});
function  crearCuenta() {
    var NombreCuenta = $("input[name=NombreCuenta]").val();
    var Monto = $("input[name=Monto]").val();
    var UsuarioId = localStorage.getItem("Usuario").split(",")[0];

    $.get("api/controladorcuenta/crearcuenta", {
        idcuenta: idcuenta,
        nombrecuenta: NombreCuenta,
        monto: Monto,
        usuario_id: UsuarioId},
            function (response) {
                alert(response.message);
                obtenerCuenta();
            });
            
}


function  obtenerCuenta() {    
    var UsuarioId = localStorage.getItem("Usuario").split(",")[0];
    $.get("api/controladorcuenta/obtenercuenta", {      
        usuario_id: UsuarioId}, function (response) {
        listaCuenta = $.parseJSON(response.message);
        var html = "<div class='col-lg-3 col-md-6 col-sm-6'>";
        var html = "<div class='card card-stats'>";
        var html = "<div class='card-header' data-background-color='green'>";
        var html = "<i class='material-icons'>store</i>";
        var html = "</div>";
        var html = "<div class='card-content'>";
        var html = "<div id='InformacionCuenta'>";
        var html = "<p class='category' id='nomcuenta'>Revenue</p>";
        var html = "<h3 class='title' id='monto'>$34,245</h3>";
        var html = "</div>";
        var html = "</div>";
        var html = "<div class='card-footer'>";
        var html = "<div class='stats'>";           
        var html = "<div class='button-container'>"                           
        var html = "<button id='btnEditarCuenta' onclick='' title='' class='btn btn-primary btn-simple btn-xs' type='button' data-original-title='Editar Cuenta' rel='tooltip'>"
        var html = "<i class='material-icons'>edit</i>"
        var html = "<div class='ripple-container'></div></button>"
        var html = "<button id='btnAnadirMontoCuenta' onclick='' title='' class='btn btn-primary btn-simple btn-xs' type='button' data-original-title='Editar Cuenta' rel='tooltip' data-toggle='collapse' data-target='#demo'>"
        var html = "<i class='material-icons'>add_circle_outline</i>"
        var html = "<div class='ripple-container'></div></button>"
        var html = "</div>"
        var html = "</div>";
        var html = "</div>";
        var html = "</div>";
        var html = "</div>";

        for (var i = 0; i < listaCuenta.length; i++) {
            html += "<div class='col-lg-3 col-md-6 col-sm-6'>";
            html += "<div class='card card-stats'>";
            html += "<div class='card-header' data-background-color='green'>";
            html += "<i class='material-icons'>store</i>";
            html += "</div>";
            html += "<div class='card-content'>";
            html += "<div id='InformacionCuenta'>";
            html += "<p class='category' id='nomcuenta'>" + listaCuenta[i].nombre + "</p>";
            html += "<h3 class='title' id='monto'>" + listaCuenta[i].monto + "</h3>";
            html += "</div>";
            html += "</div>";
            html += "<div class='card-footer'>";
            html += "<div class='stats'>";
            html += "<div class='button-container'>"                           
            html += "<button id='btnEditarCuenta' onclick='' title='' class='btn btn-primary btn-simple btn-xs' type='button' data-original-title='Editar Cuenta' rel='tooltip' onclick='seleccionarCuenta(" + listaCuenta[i].id + ",this)'>"
            html += "<i class='material-icons'>edit</i>"
            html += "<div class='ripple-container'></div></button>"
            html += "<button id='btnAnadirMontoCuenta' onclick='' title='' class='btn btn-primary btn-simple btn-xs' type='button' data-original-title='Editar Cuenta' rel='tooltip' data-toggle='collapse' data-target='#demo'>"
            html += "<i class='material-icons'>add_circle_outline</i>"
            html += "<div class='ripple-container'></div></button>"
            html += "</div>"
            html += "</div>";
            html += "</div>";
            html += "</div>";
            html += "</div>";
        }
        $("#mostrarCuentas").html(html);
    });
}

function seleccionarCuenta(id, elemento) {
    var nombre = $(elemento).parent().parent().parent().parent().find("p:nomcuenta").html();
   // var descripcion = $(elemento).parent().parent().find("td:eq(2)").html();
    $("#btnCuenta").text("Modificar Cuenta");
    idcuenta = id;
    $("input[name=NombreCuenta]").val(nombre);
    //$("input[name=Descripcion]").val(descripcion);
    
}