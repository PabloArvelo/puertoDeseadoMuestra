const periodoSelect = document.getElementById("periodoSelect");


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


periodoSelect.addEventListener("change", ()=>{
    contenedorTabla.style.display = "none";
})


btnBuscar.addEventListener("click", () => {
    
    const contenedorTabla = document.getElementById("contenedorTabla");

    contenedorTabla.style.display = "flex";
    let periodo = periodoSelect.value;
    

    fetch(`/imprimirRecibos/cubreFranco?periodo=${periodo}`)
        .then((response) => response.json())
        .then((data) => iterar(data))  //iterar(data)

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
    window.location.href = `/imprimirRecibos/imprimirCubreFranco?periodo=${periodoSelect.value}&tipoFetch=cubreFranco`;  
            alert("generando recibos, aguarde la descarga")            

})






// toda esta logica habría que borrarla

/* document.getElementById("btnImprimirCubreFranco").addEventListener("click", async () => {
    const periodo = document.getElementById("periodo").value;

    if (!periodo) {
        alert("Por favor, ingrese un período válido.");
        return;
    }

    try {
        const response = await fetch(`/imprimirRecibos/verRecibo?periodo=${periodo}`);
        const data = await response.json();

        if (data.success) {
            window.location.href = `/imprimirRecibos/imprimir?periodo=${periodo}&tipoFetch=cubreFranco`;
            alert("generando recibos, aguarde la descarga")            

        } else {
            console.error("Error:", data.message);
            alert(data.message);
        }
    } catch (err) {        
        alert.error("Error en la solicitud:", err);
    }
}); */