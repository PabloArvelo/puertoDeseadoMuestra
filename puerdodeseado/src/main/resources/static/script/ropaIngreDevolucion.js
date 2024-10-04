
// ------------------ CAPTURAS ------------------------

// selects
const selectPrenda = document.getElementById("prendaSelect");
const selectTalle = document.getElementById("talleSelect");

// botones
const btnAgregar = document.getElementById("agregarItem");
const btnEnviarLista = document.getElementById("enviarLista");
btnEnviarLista.disabled = true;
const btnVolverVistaAnterior = document.getElementById("volverVistaAnterior");

// datos ocultos

//let cuil = document.getElementById("hiddenCUIL").value;

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
class ItemRopaDevolucion {
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

// en este array voy a guardar los objetos ItemRopaDevolucion que se enviarán
//const fecthArrayDevolucion = []; 
const fecthArrayDevolucion = new Array();



function agregarFilaTablaDevolucion() {  
    
    let idAsoc = document.getElementById("hiddenID").value;
    console.log("muestra id 01 "+idAsoc)

    let asociadosMain = {
        asociadosMain: idAsoc,
    }

    const inputcantidad = document.getElementById("cantidadInput");
    const tablaEnviarDevolucion = document.getElementById("tablaEnviarDevolucion");

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
    const itemRopaDevolucion = new ItemRopaDevolucion("devolucion", idAsoc, valorPrendaArray, talle, false, cantidad, fecha);

    console.log("muestra id 02 "+itemRopaDevolucion)



    // valido que no haya valores sin setear antes de agregar a la tabla y al array

    function hasNullOrEmpty(obj) {
        for (let key in obj) {
            if (obj.hasOwnProperty(key)) {
                if (obj[key] === null || obj[key] === '' || obj[key] === undefined || obj[key] === 'Seleccione una prenda'  || obj[key] === 'Seleccione un talle') {
                    return true;
                }
            }
            if (typeof obj[key] === 'object' && hasNullOrEmpty(obj[key])) {
                return true;
            }
        }
        return false;
    }


    if (hasNullOrEmpty(itemRopaDevolucion)) {

        alert("debe completar todos los campos")

    } else {
        // habilito el boton enviarLista
        btnEnviarLista.disabled = false;

        //creo nueva fila
        const nuevaFila = tablaEnviarDevolucion.insertRow();

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
            tablaEnviarDevolucion.deleteRow(rowIndex);
            fecthArrayDevolucion.splice(rowIndex - 1, 1);

            //pregunto si el array está vacio para que en caso que sí, 
            // deshabilito el boton de enviarLista
            if (fecthArrayDevolucion.length === 0) {
                btnEnviarLista.disabled = true;
            }
        };

         // doy estilo a las celdas desde javascript
        // desce css, no lo toma, porque las filas y celtas son creadas dinámicamente desde JS
        celdaTalle.style.textAlign = "center";        
        celdaCantidad.style.textAlign = "center";
        celdaEliminar.style.textAlign = "center";


        // asigno los valores capturados a las celdas
        celdaPrenda.textContent = valorPrenda;
        celdaTalle.textContent = talle;
        celdaCantidad.textContent = cantidad;
        celdaEliminar.appendChild(imgBorrarCelda);

        // agrego la instancia del objeto al array
        fecthArrayDevolucion.push(itemRopaDevolucion);


        // limpio lo seleccionado y vuelvo a cargar el selectPrenda
        selectPrenda.innerHTML = '<option value="">Seleccione una prenda</option>';
        selectTalle.innerHTML = '<option value="">Seleccione un talle</option>';
        inputcantidad.value = "";
        cargarSelects();
    }

}

// asigno la función agregarFilaTablaDevolucion al boton 
btnAgregar.addEventListener("click", agregarFilaTablaDevolucion);


// ------------------------------ ENVIAR A BASE DE DATOS Y REDIRECCIONAR ------------------------------ 

async function guardarPrendasCompradas() {

    //console.log(fecthArrayDevolucion);

    try {
        const response = await fetch(`http://localhost:8080/fetch/movimientoPrenda`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(fecthArrayDevolucion)
        });

        if (response.ok) {
            alert('Devolucion efectuada con éxito');
        } else {
            alert('Error al devolver las prendas');
        }
    } catch (error) {
        alert('Error al devolver las prendas: ' + error.message);
    }

    volverVistaAnterior();
}

btnEnviarLista.addEventListener("click", guardarPrendasCompradas);


// vuelve a la vista asociado
function volverVistaAnterior() {

    let cuil = document.getElementById("hiddenCUIL").value;
    console.log(cuil);
    let nCuil = parseInt(cuil, 10); // 10 representa la base numérica (decimal)
    

    // Crear un formulario y enviarlo automáticamente con método POST
    const form = document.createElement('form');
    
    form.method = 'GET';
    //form.action = `/`;
    form.action = `/buscar/unoSolo`;

    const hiddenField = document.createElement('input');
    hiddenField.type = 'hidden';
    hiddenField.name = 'nCuil';
    hiddenField.value = nCuil;

    form.appendChild(hiddenField);
    document.body.appendChild(form);
    form.submit();
}


// regresa a la vista anterior
btnVolverVistaAnterior.addEventListener("click", () => {

    // si intenta salir sin enviar a labase de datos la prenda a asignar, avisa
    if (fecthArrayDevolucion.length > 0) {
        const respuestaUsuario = confirm("¿ desea salir sin guardar la devolución ? ");

        if (respuestaUsuario) {
            volverVistaAnterior();
        }

    } else {
        volverVistaAnterior()
    }

});

