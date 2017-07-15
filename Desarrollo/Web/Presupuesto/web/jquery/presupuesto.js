var idcuenta = 0;

$(document).ready(function () {
    obtenerCuenta();
    obtenerCategoriasCuenta();
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
                //alert(response.message);
                if (response.message === "insert") {
                    obtenerCuenta();
                    MostrarMsgAddCuenta();
                } else if (response.message === "equal") {
                    obtenerCuenta();
                    MostrarMsgAlertaCuenta();
                } else if (response.message === "error") {
                    obtenerCuenta();
                    MostrarMsgAlertaCuenta();
                } else {
                    obtenerCuenta();
                    MostrarMsgUpdateCuenta();
                }
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

function obtenerCategoriasCuenta() {
    var UsuarioId = localStorage.getItem("Usuario").split(",")[0];
    $.get("api/controladorcategoria/obtenercategoria", {
        usuario_id: UsuarioId}, function (response) {
        listaCategoria = $.parseJSON(response.message);
        var html = "<option value='0' selected>--Seleccione Categoria--</option>";
        for (var i = 0; i < listaCategoria.length; i++) {
            if (listaCategoria[i].tipocategoria==="Ingreso") {
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