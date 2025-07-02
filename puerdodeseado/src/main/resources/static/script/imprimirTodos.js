// ------------------------------ CAPTURAS ------------------------------ 
const periodoSelect = document.getElementById("periodoSelect");
const btnBuscar = document.getElementById("btnBuscar");
let  btnImprimirRecibos = document.getElementById("btnImprimirRecibos");

const contenedorTabla= document.getElementById("contenedorTabla");


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

btnBuscar.addEventListener("click", () => {    
    

    contenedorTabla.style.display = "flex";

    

    fetch(`/imprimirRecibos/verTodos?periodo=${periodoSelect.value}`)
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
    window.location.href = `/imprimirRecibos/imprimirTodos?periodo=${periodoSelect.value}&tipoFetch=todos`;  
            alert("generando recibos, aguarde la descarga")            

})

