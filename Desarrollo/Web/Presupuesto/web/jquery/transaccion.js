var idtransaccion;
var CuentaId;
var gastoTotal = 0;
var monto_limite = 0;

var f = new Date();
var dia = f.getDate();
var mes = f.getMonth() + 1;
var ano = f.getFullYear();
dia = dia < 10 ? "0" + dia : dia;
mes = mes < 10 ? "0" + mes : mes;
var fechahoy = dia + "/" + mes + "/" + ano;
//var cuenta_id = 0;

$(document).ready(function () {
    cargarCuentasTransaccion();
    obtenerTransacciones();
    cargarCategoriasTransaccion();
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
            html += "<p class='category' id='nomcuenta" + listaCuenta[i].id + "' name='nomcuenta' data-cuenta_id='" + listaCuenta[i].id + "'>" + listaCuenta[i].nombre + "</p>";
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

function  obtenerTransacciones() {
    var UsuarioId = localStorage.getItem("Usuario").split(",")[0];

    $.get("api/controladortransaccion/obtenertransaccion", {
        usuario_id: UsuarioId}, function (response) {
        listaTransaccion = $.parseJSON(response.message);
        var html = "<tr>";
        var html = "<td class='1'></td>";
        var html = "<td class='2'></td>";
        var html = "<td class='3'></td>";
        var html = "<td class='4'></td>";
        var html = "<td class='5'></td>";
        var html = "<td class='6'></td>";
        var html = "<td class='7'></td>";
        var html = "<td class='8'></td>";
        var html = "<td class='td-actions text-right'>";
        var html = "<button title='' class='btn btn-primary btn-simple btn-xs' type='button' data-original-title='Edit Task' rel='tooltip'>";
        var html = "<i class='material-icons'>edit</i>";
        var html = "<div class='ripple-container'></div></button>";
        var html = "<button title='' class='btn btn-danger btn-simple btn-xs' type='button' data-original-title='Remove' rel='tooltip'>";
        var html = "<i class='material-icons'>close</i>";
        var html = "</button>";
        var html = "</td>";
        var html = "</tr>";


        for (var i = 0; i < listaTransaccion.length; i++) {
            html += "<tr>";
            html += "<td id='a'>" + listaTransaccion[i].id + "</td>";
            html += "<td id='b'>" + listaTransaccion[i].fecha + "</td>";
            html += "<td id='c'>" + listaTransaccion[i].tipo + "</td>";
            html += "<td id='d'>" + listaTransaccion[i].monto + "</td>";
            html += "<td id='e'>" + listaTransaccion[i].detalle + "</td>";
            html += "<td id='f'>" + listaTransaccion[i].cuenta_id + "</td>";
            html += "<td id='g'>" + listaTransaccion[i].cuenta_id_destino + "</td>";
            html += "<td id='h'>" + listaTransaccion[i].categoria_id + "</td>";
            html += "<td class='td-actions text-right'>";
            html += "<button title='' class='btn btn-primary btn-simple btn-xs' type='button' data-original-title='Edit Task' rel='tooltip' onclick='seleccionarCategoria(" + listaTransaccion[i].id + ",this)'>";
            html += "<i class='material-icons'>edit</i>";
            html += "<div class='ripple-container'></div></button>";
            html += "<button title='' class='btn btn-danger btn-simple btn-xs' type='button' data-original-title='Remove' rel='tooltip' onclick='eliminarCategoria(" + listaTransaccion[i].id + ",this)'>";
            html += "<i class='material-icons'>close</i>";
            html += "</button>";
            html += "</td>";

            html += "</tr>";
        }
        $("#filaTransacciones").html(html);
    });
}

function  crearTransaccionEgreso() {
    var TipoTransaccion = "Egreso";
    var MontoTransaccion = $("input[name=MontoGasto]").val();
    var ConceptoTransaccion = $("input[name=ConceptoGasto]").val();
    var UsuarioId = localStorage.getItem("Usuario").split(",")[0];
    var CuentaIdTransaccion = $("#cuentaGasto option:selected").val();
    var CategoriaIdTransaccion = $("#categoriaGasto option:selected").val();

    $.get("api/controladortransaccion/verificarlimite", {
        categoria_id: CategoriaIdTransaccion}, function (response) {
        listaVerificar = $.parseJSON(response.message);

        for (var i = 0; i < listaVerificar.length; i++) {
            var nombre = listaVerificar[i].nombre;
            monto_limite = listaVerificar[i].monto;
            gastoTotal = listaVerificar[i].gastoTotal;
        }

        var aux = 0;
        var limite = parseFloat(monto_limite)
        aux = parseFloat(MontoTransaccion) + parseFloat(gastoTotal);

        if (aux > limite) {
            alert("Esta excediendo el limite del presupuesto")
            return;
        }

        $.get("api/controladortransaccion/creartransaccion", {
            idtransaccion: idtransaccion,
            fecha: fechahoy,
            tipotransaccion: TipoTransaccion,
            montotransaccion: MontoTransaccion,
            conceptotransaccion: ConceptoTransaccion,
            user_id: UsuarioId,
            cuenta_id: CuentaIdTransaccion,
            cuenta_id_destino: CuentaIdTransaccion,
            categoria_id: CategoriaIdTransaccion},
                function (response) {
                    alert(response.message);
                    limpiarTransaccion();
                    obtenerCuentaTransaccion();
                    obtenerTransacciones();
                });
    });



}

function  crearTransaccionIngreso() {

    var TipoTransaccion = "Ingreso";
    var MontoTransaccion = $("input[name=MontoTransaccion]").val();
    var ConceptoTransaccion = $("input[name=Concepto]").val();
    var UsuarioId = localStorage.getItem("Usuario").split(",")[0];
    //var CuentaId = $("#nomcuenta").data("cuenta_id");
    //var CuentaIdDestino = "";
    var CategoriaId = document.getElementById("seleccionCategoria").value;

    $.get("api/controladortransaccion/creartransaccion", {
        idtransaccion: idtransaccion,
        fecha: fechahoy,
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

function cargarCuentasTransaccion() {
    var UsuarioId = localStorage.getItem("Usuario").split(",")[0];
    $.get("api/controladorcuenta/obtenercuenta", {
        usuario_id: UsuarioId}, function (response) {
        listaCuenta = $.parseJSON(response.message);
        var html = "<option value='0' selected>--Seleccione Cuenta--</option>";
        for (var i = 0; i < listaCuenta.length; i++) {
            html += "<option value='" + listaCuenta[i].id + "'>" + listaCuenta[i].nombre + "</option>";
        }
        $("#cuentaGasto").html(html);
    });
}

function cargarCategoriasTransaccion() {
    var UsuarioId = localStorage.getItem("Usuario").split(",")[0];
    $.get("api/controladorcategoria/obtenercategoria", {
        usuario_id: UsuarioId}, function (response) {
        listaCategoria = $.parseJSON(response.message);
        var html = "<option value='0' selected>--Seleccione Categoria--</option>";
        for (var i = 0; i < listaCategoria.length; i++) {

            html += "<option value='" + listaCategoria[i].id + "'>" + listaCategoria[i].nombre + "</option>";

        }
        $("#categoriaGasto").html(html);
    });
}

function limpiarTransaccion() {
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
    var nomcuenta = $("#nomcuenta" + id).text();
    var montocuenta = $("#montocuenta" + id).text();
    $("#NombreCuentaForm").text("Ingreso para la Cuenta: " + nomcuenta);
    //var nombre = $(elemento).parent().parent().parent().parent().find("p:eq(1)").html();
    // var descripcion = $(elemento).parent().parent().find("td:eq(2)").html();
    //$("#btnCuenta").text("Modificar Cuenta");
    CuentaId = id;
    //$("input[name=NombreCuenta]").val(nomcuenta);
    //$("input[name=Monto]").val(montocuenta);

}

function MostrarFomularioGastos() {
    $('#msg-MostrarFormularioGastos').show();

}

function CancelarTransaccion() {
    $('#msg-MostrarFormularioGastos').hide();
}

/**********************************************/
// para los mensajes emergentes
function MostrarMsgAddTransaccion() {
    $('#msg-AddTransaccion').toggle('slow');
    $(document).ready(function () {
        setTimeout(function () {
            $("#msg-AddTransaccion").fadeOut(1500);
        }, 3000);
    });
}

function MostrarMsgDeleteTransaccion() {
    $('#msg-DeleteTransaccion').toggle('slow');
    $(document).ready(function () {
        setTimeout(function () {
            $("#msg-DeleteTransaccion").fadeOut(1500);
        }, 3000);
    });
}

function MostrarMsgUpdateTransaccion() {
    $('#msg-UpdateTransaccion').toggle('slow');
    $(document).ready(function () {
        setTimeout(function () {
            $("#msg-UpdateTransacion").fadeOut(1500);
        }, 3000);
    });
}

function MostrarMsgAlertaTransaccion() {
    $('#msg-AlertTransaccion').toggle('slow');
    $(document).ready(function () {
        setTimeout(function () {
            $("#msg-AlertTransaccion").fadeOut(1500);
        }, 3000);
    });
}

function MostrarMsgErrorTransaccion() {
    $('#msg-ErrorTransaccion').toggle('slow');
    $(document).ready(function () {
        setTimeout(function () {
            $("#msg-ErrorTransaccion").fadeOut(1500);
        }, 3000);
    });
}


function MostrarTransaccion() {
    $('#msg-MostrarFormularioGastos').toggle('slow');
    NuevaTransaccion();
}

function CancelarTransaccion() {
    $('#msg-MostrarFormularioGastos').toggle('slow');
    NuevaTransaccion();
}

/****************************************************************/
// jQuery
$(document).ready(function () {
    $('#alternar-respuesta-ej3').on('click', function () {
        $('#msg-MostrarFormularioGastos').toggle('slow');
    });
});