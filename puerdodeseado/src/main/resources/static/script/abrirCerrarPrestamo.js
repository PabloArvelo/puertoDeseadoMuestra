
document.addEventListener("DOMContentLoaded", function () {
    const idAsocFuente2Elemento = document.getElementById("idAsocFuente2");

    if (idAsocFuente2Elemento) {
        // Estamos en html1
        let idAsocFuente2 = idAsocFuente2Elemento.value;

        const abrirVistaPrestamo = document.getElementById("abrirVistaPrestamo");

        if (abrirVistaPrestamo) {
            abrirVistaPrestamo.addEventListener("click", function (event) {
                event.preventDefault();
                const url = `/prestamo/vistaPrestamo/${idAsocFuente2}`;
                window.open(url, "_blank"); // Abre la nueva pestaña
            });
        }
    } else {
        // Estamos en html2, intentamos cerrar la ventana        
        
        const botonSalir = document.getElementById("botonSalir");

        if (botonSalir) {
            botonSalir.addEventListener("click", function () {
                window.close(); // Cierra la ventana
            });
        }
    }
});