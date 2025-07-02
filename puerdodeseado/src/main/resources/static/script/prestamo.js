
// const checkInteres = document.getElementById("intSiNo");
const checkboxInteres = document.getElementById("intSiNo");
const inputInteres = document.getElementById("interes");
const inputMonto = document.getElementById("monto");
const inputCantidadCuotas = document.getElementById("cantidadCuotas");
const inputPrimeraCuota = document.getElementById("primeraCuota");
const spanErrorInteres = document.getElementById("spanErrorInteres");


inputInteres.disabled = true;

if (document.getElementById("spanErrorInteres")) {
    if (spanErrorInteres.innerText == "*") {
        inputInteres.disabled = false;
    }    
}


function quitarSimboloPorcentaje(input) {
    // Remover el símbolo '%' si existe
    input.value = input.value.replace('%', '').trim();
}


function agregarSimboloPorcentaje(input) {
    if (input.value !== '' && !isNaN(input.value)) {
        input.value = `${input.value}%`;
    }
}

// Función para eliminar el símbolo de porcentaje antes de enviar el formulario
document.getElementById("formPrestamo").addEventListener("submit", function (event) {
    inputInteres.value = inputInteres.value.replace('%', '').trim();
});

checkboxInteres.addEventListener("change", () => {
    // Si el checkbox está marcado, habilita el input; si no, lo deshabilita
    inputInteres.disabled = !checkboxInteres.checked;

})

