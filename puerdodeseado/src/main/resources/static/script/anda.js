fetch(`/imprimirRecibos/todos?periodo=2024-11`)
        .then((response) => response.json())
        .then((data) => {         
            

            data.recibo.forEach(item => {

                // -------------------------------tabla totales-----------------------------------

                // Crear elementos base
                const tabla2 = document.createElement('table');
                const t2Head = document.createElement('thead');
                const t2Body = document.createElement('tbody');

                const encabezadosT2 = ["", "", "", "Total Bruto", "Total Deduc.", "Total General"];

                encabezadosT2.forEach(encabez => {
                    const encabezadoT2 = document.createElement("th");
                    encabezadoT2.className = "titulos";

                    encabezadoT2.textContent = encabez;

                    t2Head.appendChild(encabezadoT2);
                });

                tabla2.appendChild(t2Head);

                // Calcular totales acumulados
                let totalBruto = 0;
                let totalDeducciones = 0;
                let totalNeto = 0;

                item.conceptos.forEach(subItem => {
                    totalBruto += subItem.totalBruto > 0 ? subItem.totalBruto : 0;
                    totalDeducciones += subItem.totalDeducciones > 0 ? subItem.totalDeducciones : 0;
                    totalNeto += subItem.totalNeto > 0 ? subItem.totalNeto : 0;
                });



                // en esta fila van los totales
                const ultimaFila = document.createElement("tr");

                // Crear celdas para columnas vacías de la última fila (1, 2, 3)
                const celdaVacia1 = document.createElement('td');
                const celdaVacia2 = document.createElement('td');
                const celdaVacia3 = document.createElement('td');

                // creo celdas totales     
                const celdaTotalBruto = document.createElement("td")
                const celdaTotalDeducciones = document.createElement("td")
                const celdaTotalNeto = document.createElement("td")

                celdaTotalBruto.textContent = totalBruto;
                celdaTotalDeducciones.textContent = totalDeducciones;
                celdaTotalNeto.textContent = totalNeto;

               

                ultimaFila.appendChild(celdaVacia1);
                ultimaFila.appendChild(celdaVacia2);
                ultimaFila.appendChild(celdaVacia3);
                ultimaFila.appendChild(celdaTotalBruto);
                ultimaFila.appendChild(celdaTotalDeducciones);
                ultimaFila.appendChild(celdaTotalNeto);

                t2Body.appendChild(ultimaFila);
                tabla2.appendChild(t2Body);


                liquidacion.appendChild(tabla2);

            })

        })