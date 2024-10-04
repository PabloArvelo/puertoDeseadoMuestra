
//  --------- BOTONES --------------
const btnModal = document.getElementById("btnModal");
const btnModalUniformes = document.getElementById("btnModalUniformes");

const btnCerrarModal = document.getElementById("cerrarModal");
const btnCerraModalUniformes = document.getElementById("cerrarModalUniformes");

const btnGuardarPrenda = document.getElementById("btnGuardarPrenda");
const btnGuardarTalle = document.getElementById("btnGuardarTalle");





//  --------- MODALES --------------
let modal = document.getElementById("modal");
let modalUniformes = document.getElementById("modalIngresoUniformes");

const overlay = document.getElementById('overlay');




//---------------Captura el error y vuelve a mostar el modal ----------
const error = document.getElementsByClassName("error");
console.log("Número de elementos con clase 'error' en index: ", error.length);

for (let i = 0; i < error.length; i++) {
    if (error[i].hasAttribute("data-error")) {             
        modal.showModal();
        overlay.style.display = 'block';
    }
}


//---------------------------------------------------------------------

btnModalUniformes.addEventListener("click", () => {
    modalUniformes.showModal();
    overlay.style.display = 'block';
})

btnCerraModalUniformes.addEventListener("click", () => {
    modalUniformes.close();
    overlay.style.display = "none";
    window.location.href = `http://localhost:8080/`;
})




btnModal.addEventListener("click", () => {
    modal.showModal();
    overlay.style.display = 'block';
})

btnCerrarModal.addEventListener("click", () => {
    modal.close();
    overlay.style.display = "none";
    window.location.href = `http://localhost:8080/`;
})



// Deshabilita el cierre del modal con la tecla Escape
document.addEventListener("keydown", function (event) {
    if (event.key === "Escape") {
        event.preventDefault();
    }
});






//--------------- MOSTRAR LISTA DE ASOIADOS POR APELLIDO ---------------


const divTabla = document.getElementById("divTablaResultadoPorApellido");
divTabla.style.display = "none";


// capturo primero el formulario y le agrego que escuche el evento submit
document.getElementById("buscarPorApellidoForm").addEventListener("submit", function (event) {
    event.preventDefault(); // evito que se recargue el formulario al enviar.
    divTabla.style.display = "flex";


    let input = document.getElementById("apellido").value;



    fetch(`http://localhost:8080/asociadoPorApellido/${input}`)
        .then((response) => response.json())
        .then((data) => {

            let respuestaNoExisteApellido = document.getElementById("pNoExisteApellido");
            let respuestaNoExisteCuil = document.getElementById("pNoExisteCuil");
            respuestaNoExisteApellido.textContent = ""; // limpia el contenido
            respuestaNoExisteCuil.textContent = ""; // limpia el contenido

            if (data.length === 0 ) {                
                respuestaNoExisteApellido.textContent = "El asociado con apellido "+input + " no exite";
            }            


            let tableBody = document.getElementById("resultTableBody");
            tableBody.innerHTML = ""; // Limpiar la tabla antes de agregar nuevos resultados

            // recorro el resultado del fetch
            data.forEach(item => {

                const imgVer = document.createElement("img");
                imgVer.src = "/img/iconos/ver.png";
                imgVer.alt = "ver";
                imgVer.className = "imgVer";

                // llamo al controlador que busca por cuil, le paso por parámtro el cuil y asigno la función a la imagen del ojito
                imgVer.onclick = function () {                    
                    window.location.href = `http://localhost:8080/buscar/unoSoloDesdeApellido/${item.cuil}`                        
                }

                 // creo la fila
                let row = document.createElement("tr"); // creo la fila

                 // creo las celdas
                let apellidoCell = document.createElement("td");
                let nombreCell = document.createElement("td");
                let cuilCell = document.createElement("td");
                let verCell = document.createElement("td");
                verCell.className="imgVerHeadYDatos";

                apellidoCell.className = "datos tdDatosApellido";
                nombreCell.className = "datos";
                cuilCell.className = "datos";
                verCell.className = "ojito";

                // agrego el contenido a las celdas
                apellidoCell.textContent = item.apellido;
                nombreCell.textContent = item.nombre;
                cuilCell.textContent = item.cuil;
                verCell.appendChild(imgVer);

                // agrego a la fila cada celda
                row.appendChild(apellidoCell);
                row.appendChild(nombreCell);
                row.appendChild(cuilCell);
                row.appendChild(verCell);

                // agrego al cuerpo de la tabla la fila
                tableBody.appendChild(row);
            });
        });
})










