

// ------------------------------ CAPTURAS ------------------------------ 
const periodoSelect = document.getElementById("periodoSelect");
const objetivoSelect = document.getElementById("objetivoSelect");

let  btnImprimirRecibos = document.getElementById("btnImprimirRecibos");

const btnBuscar = document.getElementById("btnBuscar");


cargarPeriodoSelect();

// ------------------------------ SELECT PERIODO ------------------------------ 




// consulto y obtengo de la BBDD los periodos disponibles con recibos sin imprimir  para luego listarlas en el periodoSelect

function cargarPeriodoSelect() {
    fetch(`/imprimirRecibos/listarPeriodos`)
        .then((response) => response.json())
        .then((data) => iterar(data))

    // Crear y agregar el resto de las opciones
    function iterar(data) {

        console.log(data);

        data.forEach(item => {
            /* crea un option */
            const option = document.createElement("option");
            option.value = item;
            option.textContent = item;
            periodoSelect.appendChild(option);
        })
    }
}

// ------------------------------ SELECT PERIODO ------------------------------ 

periodoSelect.addEventListener("change", ()=>{
    contenedorTabla.style.display = "none";
})

// cuando cambio el período, completo el select de objetivos, segun el período elejido
periodoSelect.addEventListener("change", () => {

    let periodo = periodoSelect.value;

    fetch(`/imprimirRecibos/listarObjetivos?periodo=${periodo}`)
        .then((reponse) => reponse.json())
        .then((data) => {
            //console.log(data);

            data.forEach(item => {
                //console.log(item.id);
                //console.log(item);
                llenarSelect(objetivoSelect, "seleccione objetivo", item.id, data);
            })
        })
})

// ------------------------------ SELECT OBJETIVO ------------------------------ 

objetivoSelect.addEventListener("change", ()=>{
    contenedorTabla.style.display = "none";
})



// Función para llenar un select con opciones
function llenarSelect(selectElement, defaultText, selectedValue, options) {
    // Limpia el select por si tiene datos anteriores
    selectElement.innerHTML = '';

    // Agrega la opción por defecto
    const defaultOption = document.createElement('option');
    defaultOption.value = 0; // Valor predeterminado
    defaultOption.textContent = defaultText; // Texto de la opción predeterminada
    defaultOption.selected = true; // Opción predeterminada seleccionada
    selectElement.appendChild(defaultOption);

    // Llena el select con las opciones disponibles
    options.forEach(optionData => {
        const option = document.createElement('option');
        option.value = optionData.id; // Valor del ID
        option.textContent = optionData.alias; // Alias como texto

        // Marca como seleccionado el valor actual
        if (optionData.id === selectedValue) {
            option.selected = true;
        }

        selectElement.appendChild(option);
    });
}


btnBuscar.addEventListener("click", () => {

    
    const contenedorTabla = document.getElementById("contenedorTabla");

    contenedorTabla.style.display = "flex";

    

    fetch(`/imprimirRecibos/objetivos?periodo=${periodoSelect.value}&objetivo=${objetivoSelect.value}`)
        .then((response) => response.json())
        .then((data) => iterar(data))

    const body = document.getElementById("tablaBody");
    body.innerHTML = "";

    function iterar(data) {
        console.log(data);
        data.forEach(item => {



            // creo fila 
            const fila = document.createElement("tr");
            // creo celdas
            const celdaAsociado = document.createElement("td");
            const celdaCuil = document.createElement("td");
            const celdaAnticiopo = document.createElement("td");
            // asigno sus valores

            celdaAsociado.textContent = item.asociadosMain.apellido + " " + item.asociadosMain.nombre;
            celdaCuil.textContent = item.asociadosMain.cuil;
            celdaAnticiopo.textContent = "$ "+item.totalAnticipo;
            // agrego las celdas a la fila
            fila.appendChild(celdaAsociado);
            fila.appendChild(celdaCuil);
            fila.appendChild(celdaAnticiopo);
            // agrego la fila al body
            body.appendChild(fila);

        })
    }



})

btnImprimirRecibos.addEventListener("click", ()=>{
    window.location.href = `/imprimirRecibos/imprimirObjetivo?periodo=${periodoSelect.value}&objetivo=${objetivoSelect.value}&tipoFetch=objetivo`;  
            alert("generando recibos, aguarde la descarga")            

})
  