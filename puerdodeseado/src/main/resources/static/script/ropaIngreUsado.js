
// ------------------ CAPTURAS ------------------------

// selects
const selectPrenda = document.getElementById("prendaSelect");
const selectTalle = document.getElementById("talleSelect");

// botones
const btnAgregar = document.getElementById("agregarItem");
const btnEnviarLista = document.getElementById("enviarLista");
btnEnviarLista.disabled = true;
const btnVolverVistaAnterior = document.getElementById("volverVistaAnterior");

function cargarSelects() {

    // lista todas las prendas y los guarda en el selectPrendas
    fetch(`http://localhost:8080/fetchRopa/listarTodos`)
        .then((response) => response.json())
        .then((data) => iterarPrendas(data))

    // Crear y agregar el resto de las opciones
    function iterarPrendas(data) {
        console.log(data);
        data.forEach(item => {
            /* crea un option */
            const option = document.createElement("option");

            option.value = item.id;
            option.textContent = item.prenda;
            selectPrenda.appendChild(option);
        })
    }




    // lista todos los talles y los guarda en el selectTalles
    fetch(`http://localhost:8080/fetchTalles/listarTodos`)
        .then((response) => response.json())
        .then((data) => iterarTalles(data))

    // Crear y agregar el resto de las opciones
    function iterarTalles(data) {
        console.log(data);
        data.forEach(item => {
            /* crea un option */
            const option = document.createElement("option");

            option.value = item.id;
            option.textContent = item.talle;
            selectTalle.appendChild(option);
        })
    }

}

cargarSelects();






// ------------------------------ INTERACCION  ------------------------------ 

// creo un objeto con los atributos a enviar
class ItemRopaCompra {
    constructor(tipoMovimiento, idAsoc, idRopa, talle, usado, cantidad, fechaEntrega) {
        this.tipoMovimiento = tipoMovimiento;
        this.asociadosMain = {
            id: idAsoc
        };
        this.ropa = {
            id: idRopa
        };
        this.talle = talle;
        this.usado = usado;
        this.cantidad = cantidad;
        this.fechaEntrega = fechaEntrega;
    }
}

// en este array voy a guardar los objetos ItemRopaCompra que se enviarán
//const fecthArrayCompra = []; 
const fecthArrayCompra = new Array();



function agregarFilaTablaCompra() {    

    let idAsoc = "a117bd33-bdc8-42bc-96f0-8e5d7050a010"; //PD
    //let idAsoc = "61b55fa3-084c-4599-991e-bd4743e7041e"; //CASA
    //let idAsoc = "73d0d5a5-472d-4e16-ac91-d750cdc50553" // puerto_deseado_prueba
    

    let asociadosMain = {
        asociadosMain: idAsoc,
    }

    const inputcantidad = document.getElementById("cantidadInput");
    const tablaEnviarComnpra = document.getElementById("tablaEnviarCompra");

    let valorPrendaArray = selectPrenda.value; // este tengo que usar para el array

    // capturo los valores que necesito
    let valorPrenda = selectPrenda.options[selectPrenda.selectedIndex].textContent; // captura el texto del selectPrenda

    let ropa = {
        ropa: valorPrenda,
    }
    
    let talle = selectTalle.options[selectTalle.selectedIndex].textContent; // captura el texto del selectTalle
    
    let cantidad = inputcantidad.value;

    let fecha = new Date().toISOString().split('T')[0];    // Solo la fecha en formato 'YYYY-MM-DD'

    //instancio el objeto asignando sus atributos.
    const itemRopaCompra = new ItemRopaCompra("devolusado", idAsoc, valorPrendaArray, talle, false, cantidad, fecha);



    // valido que no haya valores sin setear antes de agregar a la tabla y al array

    function hasNullOrEmpty(obj) {
        for (let key in obj) {
            if (obj.hasOwnProperty(key)) {
                if (obj[key] === null || obj[key] === '' || obj[key] === undefined || obj[key] === 'Seleccione una prenda'  || obj[key] === 'Seleccione un talle') {                    
                    return true;
                }
                if (typeof obj[key] === 'object' && hasNullOrEmpty(obj[key])) {
                    return true;
                }
            }
        }
        return false;
    }


    if (hasNullOrEmpty(itemRopaCompra)) {

        alert("debe completar todos los campos")

    } else {
        // habilito el boton enviarLista
        btnEnviarLista.disabled = false;

        //creo nueva fila
        const nuevaFila = tablaEnviarComnpra.insertRow();

        //creo celdas
        const celdaPrenda = nuevaFila.insertCell();
        const celdaTalle = nuevaFila.insertCell();
        const celdaCantidad = nuevaFila.insertCell();
        const celdaEliminar = nuevaFila.insertCell();

        // creo la imagen cesto basura con su boton de cesto para eliminar la fila
        const imgBorrarCelda = document.createElement("img");
        imgBorrarCelda.className = "imgTacho";
        imgBorrarCelda.src = "/img/iconos/cesto3.png";
        imgBorrarCelda.alt = "eliminar fila";
        imgBorrarCelda.onclick = function () {
            const rowIndex = nuevaFila.rowIndex;
            tablaEnviarComnpra.deleteRow(rowIndex);
            fecthArrayCompra.splice(rowIndex - 1, 1);

            //pregunto si el array está vacio para que en caso que sí, 
            // deshabilito el boton de enviarLista
            if (fecthArrayCompra.length === 0) {
                btnEnviarLista.disabled = true;
            }
        };


        // asigno los valores capturados a las celdas
        celdaPrenda.textContent = valorPrenda;
        celdaTalle.textContent = talle;
        celdaCantidad.textContent = cantidad;
        celdaEliminar.appendChild(imgBorrarCelda);

        // agrego la instancia del objeto al array
        fecthArrayCompra.push(itemRopaCompra);
        console.log(fecthArrayCompra);


        // limpio lo seleccionado y vuelvo a cargar el selectPrenda
        selectPrenda.innerHTML = '<option value="">Seleccione una prenda</option>';
        selectTalle.innerHTML = '<option value="">Seleccione un talle</option>';
        inputcantidad.value = "";
        cargarSelects();
    }

}

// asigno la función agregarFilaTablaCompra al boton 
btnAgregar.addEventListener("click", agregarFilaTablaCompra);


// ------------------------------ ENVIAR A BASE DE DATOS Y REDIRECCIONAR ------------------------------ 

async function guardarPrendasCompradas() {

    console.log(fecthArrayCompra);

    try {
        const response = await fetch(`http://localhost:8080/fetch/movimientoPrenda`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(fecthArrayCompra)
        });

        if (response.ok) {
            alert('Prenda usada sumada al stock con éxito');
        } else {
            console.log("la respuesta es: "+response.toISOString);
            alert('Error al agregar las prendas');
        }
    } catch (error) {
        alert('Error al agregar las prendas: ' + error.message);
    }

    volverVistaAnterior();
}

btnEnviarLista.addEventListener("click", guardarPrendasCompradas);


// vuelve a la vista asociado
function volverVistaAnterior() {

    //let cuil = document.getElementById("hiddenCUIL").value;
    //let nCuil = parseInt(cuil, 10); // 10 representa la base numérica (decimal)

    // Crear un formulario y enviarlo automáticamente con método POST
    const form = document.createElement('form');
    
    form.method = 'GET';
    form.action = `/`;

    const hiddenField = document.createElement('input');
    hiddenField.type = 'hidden';
    //hiddenField.name = 'nCuil';
    //hiddenField.value = nCuil;

    form.appendChild(hiddenField);
    document.body.appendChild(form);
    form.submit();
}


// regresa a la vista anterior
btnVolverVistaAnterior.addEventListener("click", () => {

    // si intenta salir sin enviar a labase de datos la prenda a asignar, avisa
    if (fecthArrayCompra.length > 0) {
        const respuestaUsuario = confirm("¿ desea salir sin cargar las compras ? ");

        if (respuestaUsuario) {
            volverVistaAnterior();
        }

    } else {
        volverVistaAnterior()
    }

});

