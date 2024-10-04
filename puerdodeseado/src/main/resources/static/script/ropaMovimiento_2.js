// ------------------ CAPTURAS ------------------------

let idAsoc = document.getElementById("hiddenID").value;
let nombreAsociado = document.getElementById("nombreAsociado");


let nombreAsoc = "";
//muestra el nombre del asociado en el encabezado de la vista
fetch(`http://localhost:8080/fetch/buscaAsociado/${idAsoc}`)
    .then((response) => response.text())   // como el resultado es un string, no es necesario que sea .JSON
    .then((data) => nombreAsociado.innerHTML = data);




// botones
const btnMF = document.getElementById("mostrarForm");  //  boton mostrarForm
const btnAgregar = document.getElementById("agregarItem");
const btnEnviarLista = document.getElementById("enviarLista");
btnEnviarLista.disabled = true;
const btnVolverVistaAnterior = document.getElementById("volverVistaAnterior");



// ------------------ LISTENERS ------------------------

// boton mostrarForm
btnMF.addEventListener("click", () => {
    document.getElementById("asignarItemsDiv").style.display = "flex"; // muestro el formulario
    document.getElementById("mostrarForm").style.display = "none"; // oculto el boton MF
    cargarPrendaSelect()
})


// regresa a la vista anterior
btnVolverVistaAnterior.addEventListener("click", () => {

    // si intenta salir sin enviar a labase de datos la prenda a asignar, avisa
    if (fecthArray.length > 0) {
        const respuestaUsuario = confirm("¿ desea salir sin asignar prenda ? ");

        if (respuestaUsuario) {
            volverVistaAsociado();
        }

    } else {
        volverVistaAsociado()
    }

});



// ------------------------------ SELECT PRENDA ------------------------------ 

const prendaSelect = document.getElementById("prendaSelect");


// consulto y obtengo de la BBDD las prendas disponibles en stock para luego listarlas en el selectPrendas

function cargarPrendaSelect() {
    fetch(`http://localhost:8080/fetch/ropaEnStock`)  // aca tengo que cambien la url al nuevo query
        .then((response) => response.json())
        .then((data) => iterar(data))

    // Crear y agregar el resto de las opciones
    function iterar(data) {
        console.log(data);
        data.forEach(item => {
            /* crea un option */
            const option = document.createElement("option");

            option.value = item.id;
            option.textContent = item.prenda;
            prendaSelect.appendChild(option);
        })
    }
}



// ------------------------------ SELECT ESTADO ------------------------------ 

const estadoSelect = document.getElementById("estadoSelect");


// consulto y obtengo de la BBDD los estados disponibles de la prenda seleccionada en stock para luego listarlas en el estadoSelect

prendaSelect.addEventListener("change", () => {


    // si hay contenido prrevio en los selects posterores, borra todo cantes de rellenarlo.
    estadoSelect.innerHTML = '<option value="">Seleccione un talle</option>';
    talleSelect.innerHTML = '<option value="">Seleccione un talle</option>';
    stockInput.value = "";

    let idPrendaSelect = prendaSelect.value;
    console.log("el id de la prenda es: " + idPrendaSelect);

    fetch(`http://localhost:8080/fetch/listadoEstadosPrendaEnStock/${idPrendaSelect}`)
        .then((response) => response.json())
        .then((data) => iterar(data))

    // Crear y agregar el resto de las opciones
    function iterar(data) {
        console.log(data);
        data.forEach(item => {
            /* crea un option */
            const option = document.createElement("option");

            option.textContent = item
            estadoSelect.appendChild(option);
        })
    }

})




// ------------------------------ SELECT TALLE ------------------------------ 

const talleSelect = document.getElementById("talleSelect");

estadoSelect.addEventListener("change", () => {

    console.log("hola desde select estado")

    // si hay contenido prrevio en el select talle, borra todo cantes de rellenarlo.
    talleSelect.innerHTML = '<option value="">Seleccione un talle</option>';
    stockInput.value = "";

    let idPrendaSelect = prendaSelect.value;
    let valorEstado = estadoSelect.options[estadoSelect.selectedIndex].textContent;
    console.log(valorEstado);



    // consulto y obtengo de la BBDD los talles disponibles filtrando por prenda y  estado
    // para luegolistarlas en el talleSelect    

    fetch(`http://localhost:8080/fetch/tallesDisponibles/${idPrendaSelect}/${valorEstado}`)
        .then((response) => response.json())
        .then((data) => iterarTalles(data))

    // Crear y agregar el resto de las opciones
    function iterarTalles(data) {
        console.log(data);
        data.forEach(item => {
            /* crea un option */
            const option = document.createElement("option");

            option.value = item;
            //option.textContent = item.prenda;
            option.textContent = item;
            talleSelect.appendChild(option);
        })
    }
})



// ------------------------------ MUESTRA STOCK ------------------------------ 

const stockInput = document.getElementById("stockInput");

talleSelect.addEventListener("change", () => {

    stockInput.value = "";


    let idPrendaSelect = prendaSelect.value;
    let valorEstado = estadoSelect.options[estadoSelect.selectedIndex].textContent;
    let idTalleSelect = talleSelect.value;

    console.log(" talle " + idTalleSelect);

    // consulto y obtengo de la BBDD el stock disponible filtrando por prenda, estado y talle 
    fetch(`http://localhost:8080/fetch/stockPorPrendaYEstadoYTalle/${idPrendaSelect}/${valorEstado}/${idTalleSelect}`)
        .then((response) => response.json())
        .then((data) => {            
            stockInput.value = '';
            stockInput.value = data;
        })
})



// ------------------------------ INTERACCION  ------------------------------ 

// creo un objeto con los atributos a enviar
class ItemRopaMovimiento {
    constructor(tipoMovimiento, idAsoc, idRopa, talle, nuevo, cantidad, fechaEntrega) {
        this.tipoMovimiento = tipoMovimiento;
        this.asociadosMain = {
            id: idAsoc
        };
        this.ropa = {
            id: idRopa
        };
        this.talle = talle;
        this.nuevo = nuevo;
        this.cantidad = cantidad;
        this.fechaEntrega = fechaEntrega;
    }
}



// en este array voy a guardar los objetos ItemRopaMovimiento que se enviarán
//const fecthArray = []; 
const fecthArray = new Array();


// funcion para agregar items a la tabLa tablaEnviar
function agregarFilaTabla() {

    let idAsoc = document.getElementById("hiddenID").value;

    let asociadosMain = {
        asociadosMain: idAsoc,
    }

    const cantidadInput = document.getElementById("cantidadInput");
    const tablaEnviar = document.getElementById("tablaEnviar");

    let valorPrendaArray = prendaSelect.value; // este tengo que usar para el array

    // capturo los valores que necesito
    let valorPrenda = prendaSelect.options[prendaSelect.selectedIndex].textContent; // captura el texto del selectPrenda

    let ropa = {
        ropa: valorPrenda,
    }


    let talle = talleSelect.value;

    let valorEstado = estadoSelect.options[estadoSelect.selectedIndex].textContent;
    let estado = true;
    if (valorEstado == "usado") {
        estado = false;
    }

    let cantidad = cantidadInput.value;

    let fecha = new Date().toISOString().split('T')[0];    // Solo la fecha en formato 'YYYY-MM-DD'

    //instancio el objeto asignando sus atributos.
    const itemRopaMovimiento = new ItemRopaMovimiento("entrega", idAsoc, valorPrendaArray, talle, estado, cantidad, fecha);



    // valido que no haya valores sin setear antes de agregar a la tabla y al array

    function hasNullOrEmpty(obj) {
        for (let key in obj) {
            if (obj.hasOwnProperty(key)) {
                if (obj[key] === null || obj[key] === '' || obj[key] === undefined) {
                    return true;
                }
            }
        }
        return false;
    }


    if (hasNullOrEmpty(itemRopaMovimiento)) {

        alert("debe completar todos los campos")

    } else {
        // habilito el boton enviarLista
        btnEnviarLista.disabled = false;

        //creo nueva fila
        const nuevaFila = tablaEnviar.insertRow();

        //creo celdas
        const celdaPrenda = nuevaFila.insertCell();
        const celdaTalle = nuevaFila.insertCell();
        const celdaEstado = nuevaFila.insertCell();
        const celdaCantidad = nuevaFila.insertCell();
        const celdaEliminar = nuevaFila.insertCell();

        // creo la imagen cesto basura con su boton de cesto para eliminar la fila
        const imgBorrarCelda = document.createElement("img");
        imgBorrarCelda.className = "imgTacho";
        imgBorrarCelda.src = "/img/iconos/cesto3.png";
        imgBorrarCelda.alt = "eliminar fila";
        imgBorrarCelda.onclick = function () {
            const rowIndex = nuevaFila.rowIndex;
            tablaEnviar.deleteRow(rowIndex);
            fecthArray.splice(rowIndex - 1, 1);

            //pregunto si el array está vacio para que en caso que sí, 
            // deshabilito el boton de enviarLista
            if (fecthArray.length === 0) {
                btnEnviarLista.disabled = true;
            }
        };


        // doy estilo a las celdas desde javascript
        // desce css, no lo toma, porque las filas y celtas son creadas dinámicamente desde JS
        celdaTalle.style.textAlign = "center";
        celdaEstado.style.textAlign = "center";
        celdaCantidad.style.textAlign = "center";
        celdaEliminar.style.textAlign = "center";

        


        // asigno los valores capturados a las celdas
        celdaPrenda.textContent = valorPrenda;

        celdaTalle.textContent = talle;
        if (estado==true) {
            celdaEstado.textContent = "nuevo";
        } else {
            celdaEstado.textContent = "usado";
        }
        
        celdaCantidad.textContent = cantidad;
        celdaEliminar.appendChild(imgBorrarCelda);

        // agrego la instancia del objeto al array
        fecthArray.push(itemRopaMovimiento);


        // limpio lo seleccionado y vuelvo a cargar el prendaSelect
        prendaSelect.innerHTML = '<option value="">Seleccione un talle</option>';
        talleSelect.innerHTML = '<option value="">Seleccione un talle</option>';
        stockInput.value = "";
        cantidadInput.value = "";
        cargarPrendaSelect()
    }

}

// asigno la función al boton 
btnAgregar.addEventListener("click", agregarFilaTabla);



// ------------------------------ ENVIAR A BASE DE DATOS Y REDIRECCIONAR ------------------------------ 

async function guardarPrendasAsignadas() {

    try {
        const response = await fetch(`http://localhost:8080/fetch/movimientoPrenda`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(fecthArray)
        });

        if (response.ok) {
            alert('Prendas agregadas exitosamente');
        } else {
            alert('Error al enviar las prendas');
        }
    } catch (error) {
        alert('Error al enviar las prendas: ' + error.message);
    }







    generarPDF();
    volverVistaAsociado();
}

btnEnviarLista.addEventListener("click", guardarPrendasAsignadas);


// vuelve a la vista asociado
function volverVistaAsociado() {

    let cuil = document.getElementById("hiddenCUIL").value;
    let nCuil = parseInt(cuil, 10); // 10 representa la base numérica (decimal)

    // Crear un formulario y enviarlo automáticamente con método POST
    const form = document.createElement('form');

    form.method = 'GET';
    form.action = `/buscar/unoSolo`;

    const hiddenField = document.createElement('input');
    hiddenField.type = 'hidden';
    hiddenField.name = 'nCuil';
    hiddenField.value = nCuil;

    form.appendChild(hiddenField);
    document.body.appendChild(form);
    form.submit();

}

function generarPDF() {

    // creo el documento
    const doc = new jspdf.jsPDF({
        orientation: "portrait",
        unit: "mm",
        format: "A5"
    });

    const tablaEnviar = document.getElementById("tablaEnviar");

    // Establecer las coordenadas iniciales
    let startY = 10; // eje vertical

    //agrego logo
    doc.addImage("/img/logo.png", "PNG", 60, startY, 15, 13);


    //agrego fecha con formato personalizado.
    function formatDate(date) {
        let year = date.getFullYear();
        let month = String(date.getMonth() + 1).padStart(2, '0'); // Los meses son de 0 a 11
        let day = String(date.getDate()).padStart(2, '0');
        return `${day}-${month}-${year}`;
    }
    let fecha = new Date();
    let fechaFormato = formatDate(fecha);

    doc.setFontSize(10);
    doc.text(fechaFormato, 120, 18);



    //agrego titulo del documento y asociado
    doc.setFontSize(20);
    doc.text("Entrega de uniforme", 10, startY += 22);

    const nomAsoc = document.getElementById("nombreAsociado").innerHTML;
    doc.setFontSize(10);
    doc.text(nomAsoc, 10, startY += 5);


    startY += 10;


    // agrego contenido de la tabla con la ropa asignada al asociado.
    doc.setFontSize(8);

    // Recorrer las filas de la tabla
    for (let i = 0; i < tablaEnviar.rows.length; i++) {
        const row = tablaEnviar.rows[i];

        // Recorrer las celdas de la fila
        for (let j = 0; j < row.cells.length; j++) {
            const cell = row.cells[j];

            // Agregar el texto de la celda al PDF
            doc.text(cell.textContent, 10 + (j * 30), startY);

        }
        // Incrementar la coordenada Y para la siguiente fila
        startY += 5;
    }



    // agrego linea y leyenda de recepción
    doc.line(68, startY += 20, 120, startY);

    doc.text("recibí conforme", 85, startY + 5);


    // guada el docuemento en formato PDF (por defecto en carpeta de descargas)
    doc.save("comprobante.pdf");

}