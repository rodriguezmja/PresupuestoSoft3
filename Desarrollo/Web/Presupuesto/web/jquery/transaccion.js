var idtransaccion = 0;
var CuentaId = 0;
//var cuenta_id = 0;

$(document).ready(function () {
    
});

function  obtenerCuentaTransaccion() {
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
            html += "<p class='category' id='nomcuenta" + listaCuenta[i].id + "' name='nomcuenta' data-cuenta_id='"+listaCuenta[i].id+"'>" + listaCuenta[i].nombre + "</p>";
            html += "<h3 id='montocuenta" + listaCuenta[i].id + "' class='title' id='monto'>" + listaCuenta[i].monto + "</h3>";
            html += "</div>";
            html += "</div>";
            html += "<div class='card-footer'>";
            html += "<div class='stats'>";
            html += "<div class='button-container'>";
            html += "<button title='' class='btn btn-primary btn-simple btn-xs' type='button' data-original-title='Edit Task' rel='tooltip' onclick='seleccionarCuenta(" + listaCuenta[i].id + ",this)'>";
            html += "<i class='material-icons'>edit</i>";           
            html += "<div class='ripple-container'></div></button>";
            html += "<button title='' class='btn btn-danger btn-simple btn-xs' type='button' data-original-title='Remove' rel='tooltip' onclick='eliminarCuenta(" + listaCuenta[i].id + ",this)'>";
            html += "<i class='material-icons'>close</i>";
            html += "</button>";
             html += "<div class='ripple-container'></div></button>";
            html += "<button id='btnAnadirMontoCuenta' title='' class='btn btn-primary btn-simple btn-xs' type='button' data-original-title='Ingresar Monto' rel='tooltip' data-toggle='collapse' data-target='#demo' onclick='seleccionarCuentaTransaccion(" + listaCuenta[i].id + ",this)'>";
            html += "<i class='material-icons'>add_circle_outline</i>";
            html += "<div class='ripple-container'></div></button>";
            html += "</div>";
            html += "</div>";
            html += "</div>";
            html += "</div>";
            html += "</div>";
        }
        $("#mostrarCuentas").html(html);
    });
}

function  crearTransaccionEgreso() {
    var TipoTransaccion = "Egreso";
    var MontoTransaccion = $("input[name=MontoTransaccion]").val();
    var ConceptoTransaccion = $("input[name=Concepto]").val();
    var UsuarioId = localStorage.getItem("Usuario").split(",")[0];
    var CuentaIdDestino = "";
    var CategoriaId = $("input[name=CategoriaId]").val();

    $.get("api/controladortransaccion/creartransaccion", {
        idtransaccion: idtransaccion,
        tipotransaccion: TipoTransaccion,
        montotransaccion: MontoTransaccion,
        conceptotransaccion: ConceptoTransaccion,
        usuario_id: UsuarioId,
        cuenta_id: CuentaId,
        cuenta_id_destino: CuentaIdDestino,
        categoria_id: CategoriaId},
            function (response) {
                alert(response.message);
                limpiarTransaccion();
//                obtenerCategoria();
            });

}

function  crearTransaccionIngreso() {
    var TipoTransaccion = "Ingreso";
    var MontoTransaccion = $("input[name=MontoTransaccion]").val();
    var ConceptoTransaccion = $("input[name=Concepto]").val();
    var UsuarioId = localStorage.getItem("Usuario").split(",")[0];
    //var CuentaId = $("#nomcuenta").data("cuenta_id");
    //var CuentaIdDestino = "";
    var CategoriaId = document.getElementById("seleccionCategoria").value;;

    $.get("api/controladortransaccion/creartransaccion", {
        idtransaccion: idtransaccion,
        tipotransaccion: TipoTransaccion,
        montotransaccion: MontoTransaccion,
        conceptotransaccion: ConceptoTransaccion,
        user_id: UsuarioId,
        cuenta_id: CuentaId,
        cuenta_id_destino: CuentaId,
        categoria_id: CategoriaId},
            function (response) {
                alert(response.message);
                limpiarTransaccion();
                obtenerCuentaTransaccion();
            });

}

function obtenerTipoProductos() {
    $.get("api/controladorTipoProducto/obtenerTipoProducto", {}, function (response) {
        listaTipoProductos = $.parseJSON(response.message);
        var html = "<option value='0' selected>--Seleccione Tipo Producto--</option>";
        for (var i = 0; i < listaTipoProductos.length; i++) {
            html += "<option value='" + listaTipoProductos[i].id + "'>" + listaTipoProductos[i].descripcion + "</option>";
        }
        $("#ObtenerTipoProducto").html(html);
    });
}

function limpiarTransaccion(){
     idtransaccion = 0;
                $("input[name=MontoTransaccion]").val("");
                $("input[name=Concepto]").val("");
                //$("#btnCategoria").text("Crear Categoria");
}




function eliminarTransaccion(id, elemento) {
    $.get("api/controladortransaccion/eliminartransaccion", {
        transaccion_id: id}, function (response) {
        alert(response.message);
        $(elemento).parent().parent().remove();
        
    });
}

function seleccionarCuentaTransaccion(id, elemento) {
    var nomcuenta = $("#nomcuenta"+id).text();
    var montocuenta = $("#montocuenta"+id).text();
    $("#NombreCuentaForm").text("Ingreso para la Cuenta: "+nomcuenta);
    //var nombre = $(elemento).parent().parent().parent().parent().find("p:eq(1)").html();
   // var descripcion = $(elemento).parent().parent().find("td:eq(2)").html();
    //$("#btnCuenta").text("Modificar Cuenta");
    CuentaId = id;
    //$("input[name=NombreCuenta]").val(nomcuenta);
    //$("input[name=Monto]").val(montocuenta);
    
}