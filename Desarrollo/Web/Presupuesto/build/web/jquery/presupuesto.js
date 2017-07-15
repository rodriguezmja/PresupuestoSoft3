var presupuesto_id = 0;
var f = new Date();
    var dia = f.getDate();
    var mes = f.getMonth() + 1;
    var ano = f.getFullYear();
    dia = dia < 10 ? "0" + dia : dia;
    mes = mes < 10 ? "0" + mes : mes;
    var fechahoy = dia + "/" + mes + "/" + ano;
$(document).ready(function () {
    obtenerCategoriasCuenta();
    obtenerPresupuesto();
  
    
});
function  crearPresupuesto() {
     
    var Categoria = $("#seleccionCategoria option:selected").val();
    var NombrePresupuesto = $("input[name=NombreCategoria]").val();
    var Monto = $("input[name=Monto]").val();
    var UsuarioId = localStorage.getItem("Usuario").split(",")[0];

    $.get("api/controladorcategoria/crearpresupuesto", {
        presupuestoid:presupuesto_id,
        categoria: Categoria,
        nombrepresupuesto: NombrePresupuesto,
        monto: Monto,
        fecha: fechahoy,
        usuario_id: UsuarioId},
            function (response) {
                if (response.message === "insert") {
                    obtenerPresupuesto();
                    MostrarMsgAddCategoria();
                } else if (response.message === "equal") {
                    obtenerPresupuesto();
                    MostrarMsgAlertaCategoria();
                } else if (response.message === "error") {
                    obtenerPresupuesto();
                    MostrarMsgAlertaCategoria();
                } else {
                    obtenerPresupuesto();
                    MostrarMsgUpdateCategoria();
                }
            });

}

function obtenerPresupuesto(){
    var UsuarioId = localStorage.getItem("Usuario").split(",")[0];
    $.get("api/controladorcategoria/obtenerpresupuesto", {
        usuario_id: UsuarioId}, function (response) {
        listapresupuesto = $.parseJSON(response.message);
        var html="";
        for (var i = 0; i < listapresupuesto.length; i++) {
            html += "<tr>";
            html += "<td >" + listapresupuesto[i].id + "</td>";
            html += "<td >" + listapresupuesto[i].nombre + "</td>";
            html += "<td >" + listapresupuesto[i].monto + "</td>";
            html += "<td >" + listapresupuesto[i].categoria+ "</td>";
            html += "<td >" + listapresupuesto[i].fecha + "</td>";
            html += "<td >" + listapresupuesto[i].user_id + "</td>";
            html += "<td class='td-actions text-right'>";
            html += "<button title='' class='btn btn-primary btn-simple btn-xs' type='button' data-original-title='Edit Task' rel='tooltip' onclick='seleccionarCategoria(" + listapresupuesto[i].id + ",this)'>";
            html += "<i class='material-icons'>edit</i>";
            html += "<div class='ripple-container'></div></button>";
            html += "<button title='' class='btn btn-danger btn-simple btn-xs' type='button' data-original-title='Remove' rel='tooltip' onclick='eliminarCategoria(" + listapresupuesto[i].id + ",this)'>";
            html += "<i class='material-icons'>close</i>";
            html += "</button>";
            html += "</td>";
            html += "</tr>";
        }
        $("#filacat").html(html);
    });

    
}

function obtenerCategoriasCuenta() {
    var UsuarioId = localStorage.getItem("Usuario").split(",")[0];
    $.get("api/controladorcategoria/obtenercategoria", {
        usuario_id: UsuarioId}, function (response) {
        var listaCategoria = $.parseJSON(response.message);
        var html = "<option value='0' selected>--Seleccione Categoria--</option>";
        for (var i = 0; i < listaCategoria.length; i++) {
            if (listaCategoria[i].tipocategoria==="Egreso") {
                html += "<option value='" + listaCategoria[i].id + "'>" + listaCategoria[i].nombre + "</option>";
            }
            
        }
        $("#seleccionCategoria").html(html);
    });
}

function NuevaCuenta(){
    ocultarTodo();
    limpiarCuenta();
}

function ocultarTodo(){
    $('#msg-AddCuenta').hide();
    $('#msg-DeleteCuenta').hide();
    $('#msg-UpdateCuenta').hide();
    $('#msg-AlertaCuenta').hide();
}


function limpiarCuenta() {
    idcuenta = 0;
    $("input[name=NombreCuenta]").val("");
    $("input[name=Monto]").val("");
    $("#btnCuenta").text("Crear Cuenta");
}

function eliminarCuenta(id, elemento) {
    $.get("api/controladorcuenta/eliminarcuenta", {
        cuenta_id: id}, function (response) {
        //alert(response.message);
        if (response.message === "delete") {
            MostrarMsgDeleteCuenta();
            $(elemento).parent().parent().remove();
            obtenerCuenta();
        } else if (response.message === "error") {
            MostrarMsgAlertaCuenta();
        }
        //$(elemento).parent().parent().remove();
    });    
}

function seleccionarCuenta(id, elemento) {
    MostrarCuenta();
    var nomcuenta = $("#nomcuenta" + id).text();
    var montocuenta = $("#montocuenta" + id).text();
    $("#btnCuenta").text("Modificar Cuenta");
    idcuenta = id;
    $("input[name=NombreCuenta]").val(nomcuenta);
    $("input[name=Monto]").val(montocuenta);
}


/**********************************************/
// para los mensajes emergentes
function MostrarMsgAddCuenta() {
    $('#msg-AddCuenta').toggle('slow');
    $(document).ready(function () {
        setTimeout(function () {
            $("#msg-AddCuenta").fadeOut(1500);
        }, 3000);
    });
}

function MostrarMsgDeleteCuenta() {
    $('#msg-DeleteCuenta').toggle('slow');
    $(document).ready(function () {
        setTimeout(function () {
            $("#msg-DeleteCuenta").fadeOut(1500);
        }, 3000);
    });
}

function MostrarMsgUpdateCuenta() {
    $('#msg-UpdateCuenta').toggle('slow');
    $(document).ready(function () {
        setTimeout(function () {
            $("#msg-UpdateCuenta").fadeOut(1500);
        }, 3000);
    });
}

function MostrarMsgAlertaCuenta() {
    $('#msg-AlertCuenta').toggle('slow');
    $(document).ready(function () {
        setTimeout(function () {
            $("#msg-AlertCuenta").fadeOut(1500);
        }, 3000);
    });
}

function MostrarMsgErrorCuenta() {
    $('#msg-ErrorCuenta').toggle('slow');
    $(document).ready(function () {
        setTimeout(function () {
            $("#msg-ErrorCuenta").fadeOut(1500);
        }, 3000);
    });
}


function MostrarCuenta() {
    $('#msg-MostrarFormulario').toggle('slow');
    NuevaCuenta();
}

function CancelarCuenta() {
    $('#msg-MostrarFormulario').toggle('slow');
    NuevaCuenta();
}

/****************************************************************/
// jQuery
$(document).ready(function () {
    $('#alternar-respuesta-ej1').on('click', function () {
        $('#msg-MostrarFormulario').toggle('slow');
    });
});
$(document).ready(function () {
    $('#alternar-respuesta-ej2').on('click', function () {
        $('#msg-MostrarFormularioCategoria').toggle('slow');
    });
});