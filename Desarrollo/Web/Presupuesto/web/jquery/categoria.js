var idcategoria = 0;

$(document).ready(function () {
    obtenerCategoria();
});
function  crearCategoria() {

    var TipoCategoria = $("#SeleccionTipoCategoria option:selected").text();
    var NombreCategoria = $("input[name=NombreCategoria]").val();
    var Descripcion = $("input[name=Descripcion]").val();
    var UsuarioId = localStorage.getItem("Usuario").split(",")[0];

    $.get("api/controladorcategoria/crearcategoria", {
        idcategoria: idcategoria,
        tipocategoria: TipoCategoria,
        nombrecategoria: NombreCategoria,
        descripcion: Descripcion,
        usuario_id: UsuarioId},
            function (response) {
                //alert(response.message);
                if (response.message === "insert") {
                    obtenerCategoria();
                    MostrarMsgAddCategoria();
                } else if (response.message === "equal") {
                    obtenerCategoria();
                    MostrarMsgAlertaCategoria();
                } else if (response.message === "error") {
                    obtenerCategoria();
                    MostrarMsgAlertaCategoria();
                } else {
                    obtenerCategoria();
                    MostrarMsgUpdateCategoria();
                }
            });

}

function limpiarCategoria() {
    idcategoria = 0;
    $("");
    $("input[name=NombreCategoria]").val("");
    $("input[name=Descripcion]").val("");
    $("select[id=SeleccionTipoCategoria]").val("");
    $("#btnCategoria").text("Crear Categoria");
}


function obtenerCategoriasEgreso() {
    var UsuarioId = localStorage.getItem("Usuario").split(",")[0];
    $.get("api/controladorcategoria/obtenercategoria", {
        usuario_id: UsuarioId}, function (response) {
        listaCategoria = $.parseJSON(response.message);
        var html = "<option value='0' selected>--Seleccione Categoria--</option>";
        for (var i = 0; i < listaCategoria.length; i++) {
            if (listaCategoria[i].tipocategoria === "Egreso") {
                html += "<option value='" + listaCategoria[i].id + "'>" + listaCategoria[i].nombre + "</option>";
            }

        }
        $("#seleccionCategoria").html(html);
    });
}

function  obtenerCategoria() {
    var UsuarioId = localStorage.getItem("Usuario").split(",")[0];
    $.get("api/controladorcategoria/obtenercategoria", {
        usuario_id: UsuarioId}, function (response) {
        listaCategoria = $.parseJSON(response.message);
        var html = "<tr>";
        var html = "<td class='1'></td>";
        var html = "<td class='2'></td>";
        var html = "<td class='3'></td>";
        var html = "<td class='4'></td>";
        var html = "<td class='td-actions text-right'>";
        var html = "<button title='' class='btn btn-primary btn-simple btn-xs' type='button' data-original-title='Edit Task' rel='tooltip'>";
        var html = "<i class='material-icons'>edit</i>";
        var html = "<div class='ripple-container'></div></button>";
        var html = "<button title='' class='btn btn-danger btn-simple btn-xs' type='button' data-original-title='Remove' rel='tooltip'>";
        var html = "<i class='material-icons'>close</i>";
        var html = "</button>";
        var html = "</td>";
        var html = "</tr>";


        for (var i = 0; i < listaCategoria.length; i++) {
            html += "<tr>";
            html += "<td id='1'>" + listaCategoria[i].id + "</td>";
            html += "<td id='2'>" + listaCategoria[i].tipocategoria + "</td>";
            html += "<td id='3'>" + listaCategoria[i].nombre + "</td>";
            html += "<td id='4'>" + listaCategoria[i].descripcion + "</td>";
            html += "<td class='td-actions text-right'>";
            html += "<button title='' class='btn btn-primary btn-simple btn-xs' type='button' data-original-title='Edit Task' rel='tooltip' onclick='seleccionarCategoria(" + listaCategoria[i].id + ",this)'>";
            html += "<i class='material-icons'>edit</i>";
            html += "<div class='ripple-container'></div></button>";
            html += "<button title='' class='btn btn-danger btn-simple btn-xs' type='button' data-original-title='Remove' rel='tooltip' onclick='eliminarCategoria(" + listaCategoria[i].id + ",this)'>";
            html += "<i class='material-icons'>close</i>";
            html += "</button>";
            html += "</td>";

            html += "</tr>";
        }
        $("#filacat").html(html);
    });
}

function eliminarCategoria(id, elemento) {
    $.get("api/controladorcategoria/eliminarcategoria", {
        categoria_id: id}, function (response) {
        //alert(response.message);
        if (response.message === "delete") {
            MostrarMsgDeleteCategoria();
            $(elemento).parent().parent().remove();
            obtenerCategoria();
        } else if (response.message === "error") {
            MostrarMsgAlertaCategoria();
        }
    });
}

function seleccionarCategoria(id, elemento) {
    MostrarCategoria();
    var tipocategoria = $(elemento).parent().parent().find("td:eq(1)").html();
    var nombre = $(elemento).parent().parent().find("td:eq(2)").html();
    var descripcion = $(elemento).parent().parent().find("td:eq(3)").html();
    $("#btnCategoria").text("Modificar Categoria");
    idcategoria = id;
    $("").val(tipocategoria);
    $("input[name=NombreCategoria]").val(nombre);
    $("input[name=Descripcion]").val(descripcion);

}

function NuevaCategoria() {
    ocultarTodoCategoria();
    limpiarCategoria();
}

function ocultarTodoCategoria() {
    $('#msg-AddCategoria').hide();
    $('#msg-DeleteCategoria').hide();
    $('#msg-UpdateCategoria').hide();
    $('#msg-AlertaCategoria').hide();
}


function limpiarCategoria() {
    idcategoria = 0;
    $("input[name=NombreCategoria]").val("");
    $("input[name=Descripcion]").val("");
    $("#btnCategoria").text("Crear Categoria");
}


/**********************************************/
// para los mensajes emergentes
function MostrarMsgAddCategoria() {
    $(document).ready(function () {
        $('#msg-AddCategoria').toggle('slow');
        limpiarCuenta();
        setTimeout(function () {
            $("#msg-AddCategoria").fadeOut(1500);
        }, 3000);
    });
}

function MostrarMsgDeleteCategoria() {
    $(document).ready(function () {
        $('#msg-DeleteCategoria').toggle('slow');
        setTimeout(function () {
            $("#msg-DeleteCategoria").fadeOut(1500);
        }, 3000);
    });
}

function MostrarMsgUpdateCategoria() {
    $(document).ready(function () {
        $('#msg-UpdateCategoria').toggle('slow');
        limpiarCuenta();
        setTimeout(function () {
            $("#msg-UpdateCategoria").fadeOut(1500);
        }, 3000);
    });
}

function MostrarMsgAlertaCategoria() {
    $(document).ready(function () {
        $('#msg-AlertCategoria').toggle('slow');
        setTimeout(function () {
            $("#msg-AlertCategoria").fadeOut(1500);
        }, 3000);
    });
}

function MostrarMsgErrorCategoria() {
    $(document).ready(function () {
        $('#msg-ErrorCategoria').toggle('slow');
        setTimeout(function () {
            $("#msg-ErrorCategoria").fadeOut(1500);
        }, 3000);
    });
}

function MostrarCategoria() {
    //$('#msg-MostrarFormulario').show();
    $('#msg-MostrarFormularioCategoria').toggle('slow');
    NuevaCategoria()();
}

function CancelarCategoria() {
    $('#msg-MostrarFormularioCategoria').toggle('slow');
    NuevaCategoria();
}


/*****************************************/
// jQuery
$(document).ready(function () {
    $('#alternar-respuesta-ej2').on('click', function () {
        $('#msg-MostrarFormularioCategoria').toggle('slow');
    });

});