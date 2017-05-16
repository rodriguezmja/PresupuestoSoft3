var usuarioID = 0;


$(document).ready(function () {
    $('#open').click(function () {
        $('#popup').fadeIn('slow');
        $('.popup-overlay').fadeIn('slow');
        $('.popup-overlay').height($(window).height());
        return false;
    });

    $('#close').click(function () {
        $('#popup').fadeOut('slow');
        $('.popup-overlay').fadeOut('slow');
        return false;
    });
});

function  insertarUsuario() {
    var nombreCompleto = $("input[name=NombreCompleto]").val();
    var nombreUsuario = $("input[name=NombreUsuario]").val();
    var password = $("input[name=Contrasena]").val();
    var confirmarPass = $("input[name=ConfirmPassword]").val();
    var email = $("input[name=Email]").val();

    if (nombreCompleto.length == 0) {
        alert("El nombre completo es obligatorio");
        return;
    }
    if (nombreUsuario.length == 0) {
        alert("El nombre de Usuario es obligatorio");
        return;
    }
    if (nombreUsuario.length < 4 || nombreUsuario.length > 20) {
        alert("El nombre de Usuario no debe ser menor a 4 y mayor a 20");
        return;
    }
    if (password.length == 0) {
        alert("La contraseña es obligatoria");
        return;
    }
    if (password.length < 4 || password.length > 20) {
        alert("La Contraseña no debe ser menor a 4 y mayor a 20");
        return;
    }
    if (password != confirmarPass) {
        alert("Las contraseñas no son iguales");
        return;
    }
    if (!validarEmail(email)) {
        alert("Debe colocar un e-mail valido");
        return;
    }
    $.get("api/controladorprincipal/nuevoUsuario", {
        nombreCompleto: nombreCompleto,
        nombreUsuario: nombreUsuario,
        password: password,
        email: email}, function (response) {
        alert(response.message);
        window.location.href = "Login.html";
    });
}

function validarEmail(valor) {
    if (/^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i.test(valor)) {
        return true;
    } else {
        return false;
    }
}

function  Ingresar() {
    var Login = $("input[name=UserNameLogin]").val();
    var password = $("input[name=passLogin]").val();
    $.get("api/controladorprincipal/loggear", {
        cuenta: Login,
        password: password}, function (response) {
        var e = response.message;
        if (e == "-1") {
            alert("Usuario o Contraseña incorrecto");
        } else {
            alert("USUARIO Y CONTRASENA CORRECTOS");
            localStorage.setItem("Usuario", e);
            window.location.href = "index.html";
        }
    });
}
function EnviarEmail() {
    if ($('#EmailAddress').val() != '') {
        var correoelectronico = $("input[name=CorreoElectronico]").val(); //Añadido Angel      
        $.get("api/controladorprincipal/EnvioCorreo", {
            email: correoelectronico}, function (response) {
            var e = response.message;
            if (e == "-1") {
                alert("El correo no existe");
                MostrarMensaje();
            } else {
                alert("Se envio el correo exitosamente");
                localStorage.setItem("Usuario", e);
                OcultarMensaje();
                // window.location.href = "Construccion.html";
            }
        });
    } else
    {
        MostrarMensaje();
    }
}

function MostrarMensaje() {
    $('#MensajeErrorEmail').show();
}

function OcultarMensaje() {
    $('#MensajeErrorEmail').hide();
    $('#popup').fadeOut('slow');
    $('.popup-overlay').fadeOut('slow');
    return false;

}

