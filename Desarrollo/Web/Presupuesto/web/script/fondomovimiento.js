
//Ancho (en pixeles)
var sliderwidth = "1200px"
//Alto
var sliderheight = "380px"
//Velocidad 1-10
var slidespeed = 1
//Color de fondo:
slidebgcolor = "#000000"

//Vínculos y enlaces de las imágenes
var leftrightslide = new Array()
var finalslide = ''
leftrightslide[0] = '<a><img border="0" src="imagenes/FondoCabecera2.jpg" height="380" width="1200"></a>'
leftrightslide[1] = '<a><img border="0" src="imagenes/FONDOPRINCIPAL.jpg" height="380" width="1200"></a>'
leftrightslide[2] = '<a><img border="0" src="imagenes/fondoCabecera.jpg" height="380" width="1200"></a>'


var imagegap = ""
var slideshowgap = 3


var copyspeed = slidespeed
leftrightslide = '<nobr>' + leftrightslide.join(imagegap) + '</nobr>'
var iedom = document.all || document.getElementById
if (iedom)
    document.write('<span id="temp" style="visibility:hidden;position:absolute;top:-100px;left:-9000px">' + leftrightslide + '</span>')
var actualwidth = ''
var cross_slide, ns_slide

function fillup() {
    if (iedom) {
        cross_slide = document.getElementById ? document.getElementById("test2") : document.all.test2
        cross_slide2 = document.getElementById ? document.getElementById("test3") : document.all.test3
        cross_slide.innerHTML = cross_slide2.innerHTML = leftrightslide
        actualwidth = document.all ? cross_slide.offsetWidth : document.getElementById("temp").offsetWidth
        cross_slide2.style.left = actualwidth + slideshowgap + "px"
    } else if (document.layers) {
        ns_slide = document.ns_slidemenu.document.ns_slidemenu2
        ns_slide2 = document.ns_slidemenu.document.ns_slidemenu3
        ns_slide.document.write(leftrightslide)
        ns_slide.document.close()
        actualwidth = ns_slide.document.width
        ns_slide2.left = actualwidth + slideshowgap
        ns_slide2.document.write(leftrightslide)
        ns_slide2.document.close()
    }
    lefttime = setInterval("slideleft()", 15)
}
window.onload = fillup

function slideleft() {
    if (iedom) {
        if (parseInt(cross_slide.style.left) > (actualwidth * (-1) + 8))
            cross_slide.style.left = parseInt(cross_slide.style.left) - copyspeed + "px"
        else
            cross_slide.style.left = parseInt(cross_slide2.style.left) + actualwidth + slideshowgap + "px"

        if (parseInt(cross_slide2.style.left) > (actualwidth * (-1) + 8))
            cross_slide2.style.left = parseInt(cross_slide2.style.left) - copyspeed + "px"
        else
            cross_slide2.style.left = parseInt(cross_slide.style.left) + actualwidth + slideshowgap + "px"

    } else if (document.layers) {
        if (ns_slide.left > (actualwidth * (-1) + 8))
            ns_slide.left -= copyspeed
        else
            ns_slide.left = ns_slide2.left + actualwidth + slideshowgap

        if (ns_slide2.left > (actualwidth * (-1) + 8))
            ns_slide2.left -= copyspeed
        else
            ns_slide2.left = ns_slide.left + actualwidth + slideshowgap
    }
}


if (iedom || document.layers) {
    with (document) {
        document.write('<table border="0" cellspacing="0" cellpadding="0"><td>')
        if (iedom) {
            write('<div id="tamanoFondoPrincipal">')
            write('<div id"tamanoFondoPrincipal2"; onmouseout="copyspeed=slidespeed">')
            write('<div id="test2" style="position:absolute;left:0px;top:0px"></div>')
            write('<div id="test3" style="position:absolute;left:-1000px;top:0px"></div>')
            write('</div></div>')
        } else if (document.layers) {
            //write('<ilayer width="+sliderwidth+" height="+sliderheight+" name="ns_slidemenu" bgcolor="+slidebgcolor+">')
            // write('<layer left="0" top="0" " onmouseout="copyspeed=slidespeed" name="ns_slidemenu2"></layer>')
            // write('<layer left="0" top="0" " onmouseout="copyspeed=slidespeed" name="ns_slidemenu3"></layer>')
            // write('</ilayer>')
        }
        document.write('</td></table>')
    }
}

