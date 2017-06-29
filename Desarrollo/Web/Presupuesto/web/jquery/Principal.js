var usuarioID = 0;
$(document).ready(function () {
    if(localStorage.getItem("Usuario")===null){
    window.location.href = "Login.html";}
   $("#usuariologueado").text(localStorage.getItem("Usuario").split(",")[1]);
});

function salirsesion(){
    localStorage.clear();
}
