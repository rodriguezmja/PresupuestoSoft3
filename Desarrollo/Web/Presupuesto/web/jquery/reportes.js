var idtransaccion;
var CuentaId;
var f = new Date();
var dia = f.getDate();
var mes = f.getMonth() + 1;
var ano = f.getFullYear();
dia = dia < 10 ? "0" + dia : dia;
mes = mes < 10 ? "0" + mes : mes;
var fechahoy = dia + "/" + mes + "/" + ano;
//var cuenta_id = 0;

$(document).ready(function () {

});

function  reporteDiario() {
    var UsuarioId = localStorage.getItem("Usuario").split(",")[0];
    var tipoTrans = $("#SeleccionTipoTransaccion option:selected").text();
    $.get("api/controladortransaccion/reportediario", {
        usuario_id: UsuarioId,
        fecha: fechahoy,
        tipotrans: tipoTrans
    }, function (response) {
        listaReportes = $.parseJSON(response.message);        
        var html = "";
                
        for (var i = 0; i < listaReportes.length; i++) {
             html += "<tr>";
             html += "<td class='1'> " + listaReportes[i].id + " </td>";
             html += "<td class='2'>" + listaReportes[i].fecha + "</td>";
             html += "<td class='3'>" + listaReportes[i].tipo + "</td>";
            html += "<td class='4'>" + listaReportes[i].monto + "</td>";
             html += "<td class='5'>" + listaReportes[i].detalle + "</td>";
             html += "<td class='6'>" + listaReportes[i].cuenta + "</td>";
            html += "<td class='7'>" + listaReportes[i].categoria + "</td>";
            html += "<td class='td-actions text-right'>";
            html += "<button title='' class='btn btn-primary btn-simple btn-xs' type='button' data-original-title='Edit Task' rel='tooltip'>";
             html += "<i class='material-icons'>edit</i>";
             html += "<div class='ripple-container'></div></button>";
             html += "<button title='' class='btn btn-danger btn-simple btn-xs' type='button' data-original-title='Remove' rel='tooltip'>";
             html += "<i class='material-icons'>close</i>";
             html += "</button>";
             html += "</td>";
             html += "</tr>";
        }
        $("#filaTipoTransaccion").html(html);
    });
}