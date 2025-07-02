const btnBuscarLiquidacionPorPeriodo = document.getElementById("buscarLiquidacionPorPeriodo");

const divTabla = document.getElementById("divTabla");
divTabla.style.display = "none";



btnBuscarLiquidacionPorPeriodo.addEventListener("click", () => {
    divTabla.style.display = "flex";

    const periodo = document.getElementById("periodo").value;

    if (periodo) {
        buscarLiquidacionesPorPeriodo(periodo);

    } else {
        console.log("debe seleccionar un período")
    }
});


async function buscarLiquidacionesPorPeriodo(periodo) {
    try {
        const respuesta = await fetch(`/editarLiquidacion/liquidacionesPorPeriodo?periodo=${periodo}`)

        if (!respuesta.ok) {
            throw new Error('Error en la respuesta del servidor');
        }
        const data = await respuesta.json();

        console.log(data);

        let tablaBody = document.getElementById("resultadoTablaBody");
        tablaBody.innerHTML = ""; // Limpiar la tabla antes de agregar nuevos resultados

        // recorro el resultado del fetch
        data.forEach(liquidacion => {

            const imgVer = document.createElement("img");
            imgVer.src = "/img/iconos/ver.png";
            imgVer.alt = "ver";
            imgVer.className = "imgVer";


            

            // mando los parametros de busqueda y los almaceno en HttpSession

            imgVer.onclick = function () {             
                
                const idAsoc = liquidacion.idAsoc;

                console.log(idAsoc+" y "+periodo);

                fetch(`/editarLiquidacion/guardarParametrosSesion`,{
                    method:"POST",
                    headers: {
                        "Content-Type" : "application/json",
                        [csrfHeader]: csrfToken // Agrega el token CSRF usando las variables del HTML
                    },
                    credentials: 'include', // Asegura que se envíe la cookie JSESSIONID
                    body: JSON.stringify({idAsoc, periodo})
                })
                .then(()=>{
                    // redirijo a la vista donde visualiza la liquidación buscada
                    window.location.href= `/editarLiquidacion/verLiquidacion`;
                })
                .catch(error => console.error('Error al guardar datos:', error));
                
            }

            // creo la fila
            let row = document.createElement("tr"); // creo la fila

            // creo las celdas
            let apellidoCell = document.createElement("td");
            let nombreCell = document.createElement("td");
            let cuilCell = document.createElement("td");
            let totalAnticipoCell = document.createElement("td");
            let verCell = document.createElement("td");

            //le asigno una clase
            apellidoCell.className = "datos tdDatosApellido";
            nombreCell.className = "datos";
            cuilCell.className = "datos";
            totalAnticipoCell.className = "datos";
            verCell.className = "ojito";

            // agrego el contenido a las celdas
            apellidoCell.textContent = liquidacion.apellido;
            nombreCell.textContent = liquidacion.nombre;
            cuilCell.textContent = liquidacion.cuil;
            totalAnticipoCell.textContent = liquidacion.totalAnticipo;
            verCell.appendChild(imgVer);

            // agrego a la fila cada celda
            row.appendChild(apellidoCell);
            row.appendChild(nombreCell);
            row.appendChild(cuilCell);
            row.appendChild(totalAnticipoCell);
            row.appendChild(verCell);

            // agrego al cuerpo de la tabla la fila
            tablaBody.appendChild(row);


        });











    } catch (error) {
        console.error("Error en la solicitud:", error);
    }

}