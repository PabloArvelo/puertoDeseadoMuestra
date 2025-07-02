
const periodo = document.getElementById("periodo");

const tabla = document.getElementById("miTabla");
const encabezado = document.getElementById("encabezado");
const cuerpo = document.getElementById("cuerpo");

const btnAccion = document.getElementById("btnAccion");
const spanTotalAnticipos = document.getElementById("spanTotalAnticipos");

let checkEliminar = document.getElementById("checkEliminar");


fetch(`/liquidaciones/buscarLiqSinAproPorPer?periodo=${periodo.value}`)
    .then((respuesta) => respuesta.json())
    .then((data) => {
        console.log(data);
        generarTabla(data);
        ordenarTabla();

    })

fetch(`/liquidaciones/totalAPagar?periodo=${periodo.value}`)
    .then((respuesta) => respuesta.json())
    .then((data) => {
        spanTotalAnticipos.textContent = "$" + data;
    })




// ORDEN PERSONALIZADO DE LAS COLUMNAS
const ordenColumnas = ["asociado",
    "horas1", "minutos1", "valorHora1", "objetivo1",
    "horas2", "minutos2", "valorHora2", "objetivo2",
    "horas3", "minutos3", "valorHora3", "objetivo3",
    "horas4", "minutos4", "valorHora4", "objetivo4",
    "horas5", "minutos5", "valorHora5", "objetivo5",
    "horas6", "minutos6", "valorHora6", "objetivo6",
    "horas7", "minutos7", "valorHora7", "objetivo7",
    "horas8", "minutos8", "valorHora8", "objetivo8",
    "horas9", "minutos9", "valorHora9", "objetivo9",
    "horas10", "minutos10", "valorHora10", "objetivo10",
    "bonificaciones", "presentismo", "nocturnidad", "nocturnidadEventual",
    "bonificacionFeriados", "reconocimientoHoras", "carpetaMedica", "hsVacaciones",
    "horasAdeudadas", "licenciaMaternidad", "horasPractica", "reintegroCuotasSociales",
    "cuotaSocial", "seguroAccPer_Vida", "retenMonotriPer", "adherenteMonotributo",
    "seguroVidaOblig", "aptoPsiFi", "adelanto", "prestamo", "interes",
    "cuotaActual", "costoHabilitacion", "credisolCS", "credisolCC", "creditoGUPAServicioSAS",
    "almacen", "embargoJudicial", "depositoExceso", "descuentoRoturaPerdida", "calzado",
    "totalBruto", "totalDeducciones", "totalAnticipo"
];


function generarTabla(datos) {

    // Obtener todas las claves que tienen al menos un valor distinto de 0
    const columnas = new Set();
    datos.forEach(obj => {
        Object.entries(obj).forEach(([clave, valor]) => {
            if (clave === "asociadosMain") {
                clave = "asociado";
            }
            if (valor !== 0 &&
                valor !== "" &&
                clave !== "id" &&
                clave !== "anticipoNumero" &&
                clave !== "periodo" &&
                clave !== "impreso") {

                columnas.add(clave);

            }
        });
    });

    // Aplicar el orden personalizado y filtrar columnas que no existen en los datos
    const columnasOrdenadas = ordenColumnas.filter(col => columnas.has(col));

    // Limpiar la tabla antes de agregar contenido
    encabezado.innerHTML = "";
    cuerpo.innerHTML = "";

    // Crear encabezados de tabla según el orden personalizado
    columnasOrdenadas.forEach(clave => {
        const th = document.createElement("th");
        th.textContent = clave;
        encabezado.appendChild(th);
    });

    // Agregar las filas con los datos
    datos.forEach(obj => {
        const fila = document.createElement("tr");

        columnasOrdenadas.forEach(clave => {

            const td = document.createElement("td");

            if (clave.includes("objetivo")) {
                td.classList.add("borde-derecha");
            }

            if (clave === "asociado") {
                // Si la clave es "asociado", usamos nombre y apellido de asociadosMain
                td.textContent = obj.asociadosMain ? `${obj.asociadosMain.apellido} ${obj.asociadosMain.nombre}` : "";
            } else {
                // Para las demás claves, asignamos el valor normalmente
                td.textContent = obj[clave] ?? ""; // Si no existe la clave, poner vacío
            }







            fila.appendChild(td);
        });
        cuerpo.appendChild(fila);
    });

}

function ordenarTabla() {
    // Obtener todas las filas existentes en la tabla
    const filas = Array.from(cuerpo.getElementsByTagName("tr"));

    // Ordenar las filas alfabéticamente por la columna "asociado" (nombre y apellido)
    filas.sort((filaA, filaB) => {
        const nombreApellidoA = filaA.cells[0].textContent.toLowerCase(); // Suponiendo que la columna "asociado" es la primera (índice 0)
        const nombreApellidoB = filaB.cells[0].textContent.toLowerCase();

        if (nombreApellidoA < nombreApellidoB) {
            return -1;
        } else if (nombreApellidoA > nombreApellidoB) {
            return 1;
        }
        return 0;
    });

    // Limpiar el cuerpo de la tabla
    cuerpo.innerHTML = "";

    // Insertar las filas ordenadas en el cuerpo de la tabla
    filas.forEach(fila => {
        cuerpo.appendChild(fila);
    });

}






btnAccion.addEventListener("click", () => {

    

    if (!checkEliminar.checked) {

        let confirmAprueba = confirm("¿está seruro que desea aprobar la liquidación?")

        if(confirmAprueba){

            fetch(`/liquidaciones/aprobarLiquidacion`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded",
                    [csrfHeader]: csrfToken // Agrega el token CSRF usando las variables del HTML
                },
                body: new URLSearchParams({ periodo: periodo.value })
            })
                .then((respuesta) => respuesta.text())
                .then((data) => {
                    alert(data);
                    window.location.href = `/imprimirRecibos/vistaImprimirRecibos`;
                })
                .catch(error => {
                    alert("errorcito: " + error);
                });

        }else{
            alert("apobación cancelada")
        }

        



    } else {


        let confirmEliminar = confirm("¿está seruro que desea eliminar la liquidación?")

        if (confirmEliminar) {

            fetch(`/eliminarLiquidacion/eliminar`, {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded",
                    [csrfHeader]: csrfToken // Agrega el token CSRF usando las variables del HTML
                },
                body: new URLSearchParams({ periodo: periodo.value })
            })
                .then((respuesta) => respuesta.text())
                .then((data) => {
                    alert(data);
                    window.location.href = `/liquidaciones/vistaLiquidaciones`;
                })
                .catch(error => {
                    alert("errorcito: " + error);
                });
            
        } else {
            alert("eliminación cancelada")            
        }



        



    }

    


});

checkEliminar.addEventListener("change", () => {

    if (!checkEliminar.checked) {

        btnAccion.style.backgroundColor = "#6C757D"; // Color de fondo
        btnAccion.style.color = "#FFFFF7"; // Color del texto
        btnAccion.innerText = "aprobar";

        let style = document.createElement("style");
        
        style.innerHTML = `  #btnAccion:hover {
    background-color: #005CA1;
    color: #F8C300;
    box-shadow: 2px 2px 20px #DA261C;
  }`;
        document.head.appendChild(style);




    } else {
        btnAccion.style.backgroundColor = "red"; // Color de fondo
        btnAccion.style.color = "yellow"; // Color del texto
        btnAccion.innerText = "eliminar"
    }

})












