

// Deshabilita el cierre del modal con la tecla Escape
document.addEventListener("keydown", function (event) {
    if (event.key === "Escape") {
        event.preventDefault();
    }
});



// ------------- CAPTURAS ------------------


let cuilFuente = document.getElementById("cuilFuente").innerText;
let idAsocFuente = document.getElementById("idAsocFuente").value;

// ----------- modales y overlay --------------  
let modificarDatosModal = document.getElementById("datosModifDialog");
let modificarFotoModal = document.getElementById("fotoModifDialog");
let modificarHabilitacionModal = document.getElementById("habilitacionModifDialog");
const overlay = document.getElementById('overlay');

// ----------- botones --------------  
const btnModifDatos = document.getElementById("btnActualizarDatos");
const btnActualizarFoto = document.getElementById("btnActualizarFoto");
const btnActualizarHabilitacion = document.getElementById("btnActualizarHabilitacion");


const btnCerrarModalDatos = document.getElementById("cerrarModalDatos");
const btnCerrarModalFoto = document.getElementById("cerrarModalFoto");
const btnCerrarModalhabilitacion = document.getElementById("cerrarModalhabilitacion");



// --------------- Abrir y cerrar modal Datos ------------

btnModifDatos.addEventListener("click", () => {
    capturaDatosParaModal();
    modificarDatosModal.showModal();
    overlay.style.display = 'block';
})

btnCerrarModalDatos.addEventListener("click", () => {
    modificarDatosModal.close();
    overlay.style.display = "none";
    window.location.href = `http://localhost:8080/buscar/unoSolo?nCuil=${cuilFuente}`
})




// --------------- Abrir y cerrar modal Foto ------------

btnActualizarFoto.addEventListener("click", () => {
    capturaDatosParaModal();
    modificarFotoModal.showModal();
    overlay.style.display = 'block';
})

btnCerrarModalFoto.addEventListener("click", () => {
    modificarFotoModal.close();
    overlay.style.display = "none";

    window.location.href = `http://localhost:8080/buscar/unoSolo?nCuil=${cuilFuente}`
})

let formActualizarFoto = document.getElementById("formActualizarFoto");
formActualizarFoto.addEventListener("submit", function (event) {
    event.preventDefault();


    let archivoInput = document.getElementById('fotoActualizarInput');
    let archivo = archivoInput.files[0]; // Obtén el archivo del input

    if (archivo) {

        let tamañoMaximoMB = 1; // Tamaño máximo en MB
        let tamañoMaximoBytes = tamañoMaximoMB * 1024 * 1024; // Convertir MB a bytes

        if (archivo.size > tamañoMaximoBytes) {
            // Mostrar un mensaje de error si el archivo es demasiado grande
            alert(`El archivo es demasiado grande. El límite es de ${tamañoMaximoMB} MB.`)

            //document.getElementById('error-message').textContent = `El archivo es demasiado grande. El límite es de ${tamañoMaximoMB} MB.`;
            return; // Detener el envío del formulario
        } else {

            const form = document.getElementById('formActualizarFoto');
            const formData = new FormData(form);
            
            fetch(form.action, {
                method: 'POST',
                body: formData
            })
                .then(response => {
                    if (response.ok) {
                        modificarFotoModal.close();
                        overlay.style.display = "none";
                        window.location.href = `http://localhost:8080/buscar/unoSolo?nCuil=${cuilFuente}`
                    } else {
                        alert(`Error al modificar foto`)
                    }

                })
        }

    }
})


// --------------- Abrir y cerrar modal habilitación ------------

btnActualizarHabilitacion.addEventListener("click", () => {
    capturaDatosParaModal();
    modificarHabilitacionModal.showModal();
    overlay.style.display = 'block';
})

btnCerrarModalhabilitacion.addEventListener("click", () => {
    modificarHabilitacionModal.close();
    overlay.style.display = "none";

    window.location.href = `http://localhost:8080/buscar/unoSolo?nCuil=${cuilFuente}`
})






function capturaDatosParaModal() {


    let idAsocActualizar = document.getElementById("idAsocActualizar");
    idAsocActualizar.value = idAsocFuente;

    let idAsocFotoHidden = document.getElementById("idAsocFotoHidden");
    idAsocFotoHidden.value = idAsocFuente;    

    let idAsocHabilHidden = document.getElementById("idAsocHabilHidden");
    idAsocHabilHidden.value = idAsocFuente;



    let nombreFuente = document.getElementById("nombreFuente").innerText;

    let nombreActualizar = document.getElementById("nombreActualizar");
    nombreActualizar.value = nombreFuente;

    let nombreFotoHidden = document.getElementById("nombreFotoHidden");
    nombreFotoHidden.value = nombreFuente;

    let nombreHabilHidden = document.getElementById("nombreHabilHidden");
    nombreHabilHidden.value = nombreFuente;




    let apellidoFuente = document.getElementById("apellidoFuente").innerText;

    let apellidoActualizar = document.getElementById("apellidoActualizar");
    apellidoActualizar.value = apellidoFuente;

    let apellidoFotoHidden = document.getElementById("apellidoFotoHidden");
    apellidoFotoHidden.value = apellidoFuente;

    let apellidoHabilHidden = document.getElementById("apellidoHabilHidden");
    apellidoHabilHidden.value = apellidoFuente;




    let dptoLaboralFuente = document.getElementById("dptoLaboralFuente").innerText;
    console.log("dpto laboral "+dptoLaboralFuente)

    let dptoLaboralActualizar = document.getElementById("dptoLaboralActualizar");
    dptoLaboralActualizar.value = dptoLaboralFuente;

    let depatamentoLaboralFotoHidden = document.getElementById("depatamentoLaboralFotoHidden");
    depatamentoLaboralFotoHidden.value = dptoLaboralFuente;

    let depatamentoLaboralHabilHidden = document.getElementById("depatamentoLaboralHabilHidden");
    depatamentoLaboralHabilHidden.value = dptoLaboralFuente;






    let fechaNacFuente = document.getElementById("fechaNacFuente").innerText;
    let parseFecha = fechaNacFuente.split("/");

    let dia = parseFecha[0];
    let mes = parseFecha[1]
    let año = parseFecha[2]

    let fechaFormato = año + "-" + mes + "-" + dia;

    let fechaNacimientoActualizar = document.getElementById("fechaNacimientoActualizar");
    fechaNacimientoActualizar.value = fechaFormato;

    let fechaNacimientoFotoHidden = document.getElementById("fechaNacimientoFotoHidden");
    fechaNacimientoFotoHidden.value = fechaFormato;

    let fechaNacimientoHabilHidden = document.getElementById("fechaNacimientoHabilHidden");
    fechaNacimientoHabilHidden.value = fechaFormato;



    let nacionalidadFuente = document.getElementById("nacionalidadFuente").innerText;

    let nacionalidadActualizar = document.getElementById("nacionalidadActualizar");
    nacionalidadActualizar.value = nacionalidadFuente;

    let nacionalidadFotoHidden = document.getElementById("nacionalidadFotoHidden");
    nacionalidadFotoHidden.value = nacionalidadFuente;

    let nacionalidadHabilHidden = document.getElementById("nacionalidadHabilHidden");
    nacionalidadHabilHidden.value = nacionalidadFuente;



    let dniFuente = document.getElementById("dniFuente").innerText;

    let dniActualizar = document.getElementById("dniActualizar");
    dniActualizar.value = dniFuente;

    let dniFotoHidden = document.getElementById("dniFotoHidden");
    dniFotoHidden.value = dniFuente;

    let dniHabilHidden = document.getElementById("dniHabilHidden");
    dniHabilHidden.value = dniFuente;



    let cuilFuente = document.getElementById("cuilFuente").innerText;

    let cuilActualizar = document.getElementById("cuilActualizar");
    cuilActualizar.value = cuilFuente;

    let cuilFotoHidden = document.getElementById("cuilFotoHidden");
    cuilFotoHidden.value = cuilFuente;

    let cuilHabilHidden = document.getElementById("cuilHabilHidden");
    cuilHabilHidden.value = cuilFuente;



    let estadoCivilFuente = document.getElementById("estadoCivilFuente").innerText;
    console.log("estado civil "+estadoCivilFuente)

    let estadoCivilActualizar = document.getElementById("estadoCivilActualizar");
    estadoCivilActualizar.value = estadoCivilFuente;
    

    let estadoCivilFotoHidden = document.getElementById("estadoCivilFotoHidden");
    estadoCivilFotoHidden.value = estadoCivilFuente;

    let estadoCivilHabilHidden = document.getElementById("estadoCivilHabilHidden");
    estadoCivilHabilHidden.value = estadoCivilFuente;




    let grupoSanguineoFuente = document.getElementById("grupoSanguineoFuente").innerText;

    let grupoSanguineoActualizar = document.getElementById("grupoSanguineoActualizar");
    grupoSanguineoActualizar.value = grupoSanguineoFuente;

    let grupoSanguineoFotoHidden = document.getElementById("grupoSanguineoFotoHidden");
    grupoSanguineoFotoHidden.value = grupoSanguineoFuente;

    let grupoSanguineoHabilHidden = document.getElementById("grupoSanguineoHabilHidden");
    grupoSanguineoHabilHidden.value = grupoSanguineoFuente;




    let telFijoFuente = document.getElementById("telFijoFuente").innerText;

    let telFijoActualizar = document.getElementById("telFijoActualizar");
    telFijoActualizar.value = telFijoFuente;

    let telFijoFotoHidden = document.getElementById("telFijoFotoHidden");
    telFijoFotoHidden.value = telFijoFuente;

    let telFijoHabilHidden = document.getElementById("telFijoHabilHidden");
    telFijoHabilHidden.value = telFijoFuente;




    let telMovilFuente = document.getElementById("telMovilFuente").innerText;

    let telMovilActualizar = document.getElementById("telMovilActualizar");
    telMovilActualizar.value = telMovilFuente;

    let telMovilFotoHidden = document.getElementById("telMovilFotoHidden");
    telMovilFotoHidden.value = telMovilFuente;

    let telMovilHabilHidden = document.getElementById("telMovilHabilHidden");
    telMovilHabilHidden.value = telMovilFuente;




    let emailFuente = document.getElementById("emailFuente").innerText;

    let emailActualizar = document.getElementById("emailActualizar");
    emailActualizar.value = emailFuente;

    let emailFotoHidden = document.getElementById("emailFotoHidden");
    emailFotoHidden.value = emailFuente;

    let emailHabilHidden = document.getElementById("emailHabilHidden");
    emailHabilHidden.value = emailFuente;




    let calleFuente = document.getElementById("calleFuente").innerText;

    let calleActualizar = document.getElementById("calleActualizar");
    calleActualizar.value = calleFuente;

    let calleFotoHidden = document.getElementById("calleFotoHidden");
    calleFotoHidden.value = calleFuente;

    let calleHabilHidden = document.getElementById("calleHabilHidden");
    calleHabilHidden.value = calleFuente;




    let numeroFuente = document.getElementById("numeroFuente").innerText;
    console.log("mi numero fuente es: " + numeroFuente);

    let numeroActualizar = document.getElementById("numeroActualizar");
    numeroActualizar.value = numeroFuente;

    let numeroFotoHidden = document.getElementById("numeroFotoHidden");
    console.log("mi numero antes es: " + numeroFuente);
    numeroFotoHidden.value = numeroFuente;
    console.log("mi numero despues es: " + numeroFuente);

    let numeroHabilHidden = document.getElementById("numeroHabilHidden");
    numeroHabilHidden.value = numeroFuente;




    let pisoFuente = document.getElementById("pisoFuente").innerText;

    let pisoActualizar = document.getElementById("pisoActualizar");
    pisoActualizar.value = pisoFuente;

    let pisoFotoHidden = document.getElementById("pisoFotoHidden");
    pisoFotoHidden.value = pisoFuente;

    let pisoHabilHidden = document.getElementById("pisoHabilHidden");
    pisoHabilHidden.value = pisoFuente;




    let departamentoFuente = document.getElementById("departamentoFuente").innerText;

    let departamentoActualizar = document.getElementById("dptoActualizar");
    departamentoActualizar.value = departamentoFuente;

    let dptoFotoHidden = document.getElementById("dptoFotoHidden");
    dptoFotoHidden.value = departamentoFuente;

    let dptoHabilHidden = document.getElementById("dptoHabilHidden");
    dptoHabilHidden.value = departamentoFuente;





    let barrioFuente = document.getElementById("barrioFuente").innerText;

    let barrioActualizar = document.getElementById("barrioActualizar");
    barrioActualizar.value = barrioFuente;

    let barrioFotoHidden = document.getElementById("barrioFotoHidden");
    barrioFotoHidden.value = barrioFuente;

    let barrioHabilHidden = document.getElementById("barrioHabilHidden");
    barrioHabilHidden.value = barrioFuente;




    let distritoFuente = document.getElementById("distritoFuente").innerText;

    let distritoActualizar = document.getElementById("distritoActualizar");
    distritoActualizar.value = distritoFuente;

    let distritoFotoHidden = document.getElementById("distritoFotoHidden");
    distritoFotoHidden.value = distritoFuente;

    let distritoHabilHidden = document.getElementById("distritoHabilHidden");
    distritoHabilHidden.value = distritoFuente;




    let provinciaFuente = document.getElementById("provinciaFuente").innerText;

    let provinciaActualizar = document.getElementById("provinciaActualizar");
    provinciaActualizar.value = provinciaFuente;

    let provinciaFotoHidden = document.getElementById("provinciaFotoHidden");
    provinciaFotoHidden.value = provinciaFuente;

    let provinciaHabilHidden = document.getElementById("provinciaHabilHidden");
    provinciaHabilHidden.value = provinciaFuente;




    let codigoPostalFuente = document.getElementById("codigoPostalFuente").innerText;

    let codigoPostalActualizar = document.getElementById("codigoPostalActualizar");
    codigoPostalActualizar.value = codigoPostalFuente;

    let codigoPostalFotoHidden = document.getElementById("codigoPostalFotoHidden");
    codigoPostalFotoHidden.value = codigoPostalFuente;

    let codigoPostalHabilHidden = document.getElementById("codigoPostalHabilHidden");
    codigoPostalHabilHidden.value = codigoPostalFuente;

}




//---------------Captura el error y vuelve a mostar el modal de datos ----------

const error = document.getElementsByClassName("error");
//console.log("Número de elementos con clase 'errooor': ", error.length);


for (let i = 0; i < error.length; i++) {

    if (error[i].hasAttribute("data-error")) {

        console.log("estoy en el for");
        modificarDatosModal.showModal();
        overlay.style.display = 'block';
    }
}


//---------------Captura el error y vuelve a mostar el modal de Foto ----------

const errorFoto = document.getElementsByClassName("errorFoto");

for (let i = 0; i < errorFoto.length; i++) {
    if (errorFoto[i].hasAttribute("data-error")) {
        modificarFotoModal.showModal();
        overlay.style.display = 'block';
    }
}


//---------------Captura el error y vuelve a mostar el modal de Habilitación ----------

const errorHabilitacion = document.getElementsByClassName("errorHabilitacion");

for (let i = 0; i < errorHabilitacion.length; i++) {
    if (errorHabilitacion[i].hasAttribute("data-error")) {
        modificarHabilitacionModal.showModal();
        overlay.style.display = 'block';
    }
}















