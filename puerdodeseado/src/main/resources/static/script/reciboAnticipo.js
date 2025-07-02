

const recibosArray = [];
const idsLiquiArray = [];


document.addEventListener("DOMContentLoaded", () => {

    let urlFetch = "";
    const tipoFetch = document.getElementById("tipoFetch").value;
    const periodo = document.getElementById("periodo").value;
    const objetivo = document.getElementById("objetivo").value;
    const idAsoc = document.getElementById("idAsoc").value;
    let idLiqui = "";


    // con este switch elijo qué tipo de impresión realizaré

    switch (tipoFetch) {
        case "todos":
            urlFetch = `/imprimirRecibos/todos?periodo=${periodo}`;
            break;
        case "objetivo":
            urlFetch = `/imprimirRecibos/objetivoImprimir?periodo=${periodo}&objetivo=${objetivo}`;
            break;
        case "cubreFranco":
            urlFetch = `/imprimirRecibos/cubreFrancoImprimir?periodo=${periodo}`;
            break;
        case "unoSolo":
            urlFetch = `/imprimirRecibos/unoSoloImprimir?periodo=${periodo}&idAsoc=${idAsoc}`;
            break;
    }


    fetch(urlFetch)
        .then((response) => response.json())
        .then((data) => {

            console.log(data);

            const conceptosExcluir = ["Total Bruto", "Total Deducciones", "Total Neto"];

            data.recibo.forEach(item => {


                //agrego los id de liquidación al array. Se usara el array para cambiar el estado de impresión
                idLiqui = item.idLiqui;
                idsLiquiArray.push(idLiqui);


                // ------------------------------------------------------------------
                // ------------------- completa la liquidación ----------------------
                // ------------------------------------------------------------------



                // -------------------------------tabla conceptos-----------------------------------

                // Crear elementos base
                const tabla1 = document.createElement('table');
                const t1Head = document.createElement('thead');
                const t1Body = document.createElement('tbody');

                tabla1.className = "tabla1";

                const encabezadosT1 = ["concepto", "horas", "unitario", "subtotal", "deduc", "total"];

                encabezadosT1.forEach(encabez => {
                    const encabezadoT1 = document.createElement("th");
                    encabezadoT1.className = "encabezados";

                    encabezadoT1.textContent = encabez;

                    t1Head.appendChild(encabezadoT1);
                });


                // -------------------------------tabla totales-----------------------------------

                // Crear elementos base
                const tabla2 = document.createElement('table');
                const t2Head = document.createElement('thead');
                const t2Body = document.createElement('tbody');

                tabla2.className = "tabla2";
                t2Body.id = "t2Body2";

                const encabezadosT2 = ["", "", "", "Total Bruto", "Total Deduc.", "Total General"];

                encabezadosT2.forEach(encabez => {
                    const encabezadoT2 = document.createElement("th");
                    encabezadoT2.className = "encabezados";

                    encabezadoT2.textContent = encabez;

                    t2Head.appendChild(encabezadoT2);
                });

                tabla2.appendChild(t2Head);


                // Calcular totales acumulados
                let totalBruto = 0;
                let totalDeducciones = 0;
                let totalNeto = 0;

                item.conceptos.forEach(subItem => {

                    if (!conceptosExcluir.includes(subItem.concepto)) {
                        // en esta fila van los conceptos
                        const fila = document.createElement("tr");

                        // creo celdas conceptos
                        const celdaConcepto = document.createElement("td");
                        celdaConcepto.textContent = subItem.concepto;
                        celdaConcepto.className = "concepto";

                        const celdaHoras = document.createElement("td")
                        celdaHoras.textContent = subItem.horas;
                        celdaHoras.className = "horas";

                        const celdaUnitario = document.createElement("td")
                        celdaUnitario.textContent = subItem.unitario > 0 ? subItem.unitario : "";
                        celdaUnitario.className = "unitario";

                        const celdaHaber = document.createElement("td");
                        celdaHaber.textContent = subItem.haberes > 0 ? subItem.haberes : "";
                        celdaHaber.className = "resto";

                        const celdaDebe = document.createElement("td");
                        celdaDebe.textContent = subItem.deducciones > 0 ? subItem.deducciones : "";
                        celdaDebe.className = "resto";

                        const celdaTotal = document.createElement("td");
                        celdaTotal.textContent = "";
                        celdaTotal.className = "resto";

                        fila.appendChild(celdaConcepto);
                        fila.appendChild(celdaHoras);
                        fila.appendChild(celdaUnitario);
                        fila.appendChild(celdaHaber);
                        fila.appendChild(celdaDebe);
                        fila.appendChild(celdaTotal);

                        tabla1.appendChild(t1Head);
                        t1Body.appendChild(fila);
                        tabla1.appendChild(t1Body);
                    }

                    totalBruto += subItem.totalBruto > 0 ? subItem.totalBruto : 0;
                    totalDeducciones += subItem.totalDeducciones > 0 ? subItem.totalDeducciones : 0;
                    totalNeto += subItem.totalNeto > 0 ? subItem.totalNeto : 0;



                })

                const largoTBody1 = t1Body.rows.length;
                const largoTBodyCompensa = 25 - largoTBody1;
                const tBodyCompensa = document.createElement("tbody");
                tBodyCompensa.className = "blanco";



                for (let i = 1; i <= largoTBodyCompensa; i++) {
                    const filaCompensa = document.createElement("tr");
                    const celdaCompensa1 = document.createElement("td");
                    const celdaCompensa2 = document.createElement("td");
                    const celdaCompensa3 = document.createElement("td");
                    const celdaCompensa4 = document.createElement("td");
                    const celdaCompensa5 = document.createElement("td");
                    const celdaCompensa6 = document.createElement("td");

                    celdaCompensa1.textContent = "hola";
                    celdaCompensa2.textContent = "hola";
                    celdaCompensa3.textContent = "hola";
                    celdaCompensa4.textContent = "hola";
                    celdaCompensa5.textContent = "hola";
                    celdaCompensa6.textContent = "hola";

                    filaCompensa.appendChild(celdaCompensa1);
                    filaCompensa.appendChild(celdaCompensa2);
                    filaCompensa.appendChild(celdaCompensa3);
                    filaCompensa.appendChild(celdaCompensa4);
                    filaCompensa.appendChild(celdaCompensa5);
                    filaCompensa.appendChild(celdaCompensa6);

                    tBodyCompensa.appendChild(filaCompensa);
                }

                tabla1.appendChild(tBodyCompensa);


                // en esta fila van los totales
                const ultimaFila = document.createElement("tr");

                // Crear celdas para columnas vacías de la última fila (1, 2, 3)
                const celdaVacia1 = document.createElement('td');
                const celdaVacia2 = document.createElement('td');
                const celdaVacia3 = document.createElement('td');

                celdaVacia1.className = "concepto";
                celdaVacia2.className = "horas";
                celdaVacia3.className = "unitario";

                // creo celdas totales     
                const celdaTotalBruto = document.createElement("td")
                const celdaTotalDeducciones = document.createElement("td")
                const celdaTotalNeto = document.createElement("td")

                celdaTotalBruto.className = "resto";
                celdaTotalDeducciones.className = "resto";
                celdaTotalNeto.className = "resto";

                celdaTotalBruto.textContent = totalBruto;
                celdaTotalDeducciones.textContent = totalDeducciones;
                celdaTotalNeto.textContent = totalNeto;

                ultimaFila.appendChild(celdaVacia1);
                ultimaFila.appendChild(celdaVacia2);
                ultimaFila.appendChild(celdaVacia3);
                ultimaFila.appendChild(celdaTotalBruto);
                ultimaFila.appendChild(celdaTotalDeducciones);
                ultimaFila.appendChild(celdaTotalNeto);

                //tabla2.appendChild(t2Head);
                t2Body.appendChild(ultimaFila);
                tabla2.appendChild(t2Body);

                const liquidacion = document.getElementById("divTabla");

                //limpio el div de residuos anteriores antes de enviar lo nuevo
                liquidacion.innerHTML = "";

                liquidacion.appendChild(tabla1);
                liquidacion.appendChild(tabla2);


                // ------------------------------------------------------------------
                // -------------- completa los datos del asociado -------------------
                // ------------------------------------------------------------------

                const apelligoYNombre = document.getElementById("apelligoYNombre");
                const inicioActividades = document.getElementById("inicioActividades");
                const dni = document.getElementById("dni");
                const cuil = document.getElementById("cuil");
                const tarea = document.getElementById("tarea");
                let domicilioFiscal = document.getElementById("domicilioFiscal");


                apelligoYNombre.innerHTML = item.asociado.apellido + " " + item.asociado.nombre;
                inicioActividades.innerHTML = item.asociado.fechaIngreso;
                dni.innerHTML = item.asociado.dni;
                cuil.innerHTML = item.asociado.cuil;
                let cuilObtener = cuil.textContent;
                let cuilFormateado = formatearConGuiones(cuilObtener);
                cuil.innerHTML = cuilFormateado;
                tarea.innerHTML = item.asociado.depatamentoLaboral;
                domicilioFiscal.innerHTML = "";


                const domicilio = [item.asociado.calle,
                item.asociado.numero,
                item.asociado.dpto,
                item.asociado.barrio,
                item.asociado.distrito,
                item.asociado.provincia]

                domicilio.forEach(domi => {
                    if (!domi == "") {
                        domicilioFiscal.innerHTML = domicilioFiscal.innerHTML + domi + " ";
                    }
                })

                let textoAcumilado = domicilioFiscal.textContent;

                domicilioFiscal.innerHTML = capitalizarCadaPalabra(textoAcumilado)


                // ------------------------------------------------------------------
                // ----------------- convierte numero a letras ----------------------
                // ------------------------------------------------------------------

                const netoEnLetras = document.getElementById("netoEnLetras");
                netoEnLetras.innerHTML = item.totalTexto;


                // ------------------------------------------------------------------
                // ------------------ agrega el periodo liquidado--------------------
                // ------------------------------------------------------------------

                let periodoLiqui = document.getElementById("periodoLiqui");
                periodoLiqui.textContent = item.periodoLiquidacion;


                // ------------------------------------------------------------------
                // ------------------ agrega la forma de pago -----------------------
                // ------------------------------------------------------------------

                let formaPagoCBU = document.getElementById("formaPagoCBU");               

                formaPagoCBU.textContent = (!item.asociado.cbu)
                    ? "efectivo"
                    : "transferencia bancaria CBU destino " + item.asociado.cbu;


                // ------------------------------------------------------------------
                // ----------------------- inserta logos ----------------------------
                // ------------------------------------------------------------------


                //los inserto desde el html

                // ------------------------------------------------------------------
                // ------------------ inserta numero anticipo------------------------
                // ------------------------------------------------------------------



                const numeroRecibo = document.getElementById("numeroRecibo");
                numeroRecibo.innerHTML = item.anticipoNumero;

                // ------------------------------------------------------------------
                // -------------- fecha de pago y fecha recibi conforme---------------
                // ------------------------------------------------------------------


                const fechaPago = document.getElementById("fechaPago");
                fechaPago.innerHTML = item.fechaDePago;

                const recibiFecha = document.getElementById("recibiFecha");
                recibiFecha.innerHTML = item.fechaDePago;



                // ------------------------------------------------------------------
                // ------------------------ capturar HTML----------------------------
                // ------------------------------------------------------------------


                const htmlContentOrig = `<!DOCTYPE html>\n` + document.documentElement.outerHTML;

                const cleanedHtmlContentOrig = htmlContentOrig
                    .replace(/<script.*?reciboAnticipo\.js.*?<\/script>/, '')
                    .replace(/<input\s+.*?id="periodo".*?>/, '')
                    .replace(/<input\s+.*?id="objetivo".*?>/, '')
                    .replace(/<input\s+.*?id="idAsoc".*?>/, '')
                    .replace(/<input\s+.*?id="tipoFetch".*?>/, '');


                const htmlContentDupli = `<!DOCTYPE html>\n` + document.documentElement.outerHTML;

                const cleanedHtmlContentDupli = htmlContentDupli
                    .replace(/<script.*?reciboAnticipo\.js.*?<\/script>/, '')
                    .replace(/<input\s+.*?id="periodo".*?>/, '')
                    .replace(/<input\s+.*?id="objetivo".*?>/, '')
                    .replace(/<input\s+.*?id="idAsoc".*?>/, '')
                    .replace(/<input\s+.*?id="tipoFetch".*?>/, '')
                    .replace(/<span class="textoVertical">Original<\/span>/, '<span class="textoVertical">Duplicado</span>');


                const OrigDupli = cleanedHtmlContentOrig + cleanedHtmlContentDupli;


                recibosArray.push(OrigDupli);

            })

            // ------------------------------------------------------------------
            // ----------------------- Genero los PDFs --------------------------
            // ------------------------------------------------------------------

            procesarRecibos();            

        })
})



function capitalizarCadaPalabra(texto) {
    if (!texto) return ""; // Manejo de texto vacío o null

    return texto
        .split(" ") // Divide el texto en un array de palabras
        .map(palabra => palabra.charAt(0).toUpperCase() + palabra.slice(1).toLowerCase()) // Capitaliza cada palabra
        .join(" "); // Une las palabras de nuevo en un string
}


function formatearConGuiones(numero) {

    if (numero.length !== 11) {
        throw new Error("El número debe tener exactamente 11 dígitos.");
    }

    const parte1 = numero.slice(0, 2); // Primeros 2 dígitos
    const parte2 = numero.slice(2, 10); // Del 3er al 10mo dígito
    const parte3 = numero.slice(10); // Último dígito

    return `${parte1}-${parte2}-${parte3}`;
}



// manda a generar los PDF y si el resultado es OK modifica el estado de impresión el la DDBB
async function procesarRecibos() {
    const resultado = await generarLosPDFs(recibosArray)
    if (!resultado) {
        alert("No se generaron los recibos, no se modificará el estado de impresión.");
        return;
    }
    fetch(`/imprimirRecibos/modificarEstadoImpresion`, {
        method: "POST",
        headers: { 
            'Content-Type': 'application/json',
            [csrfHeader]: csrfToken // Agrega el token CSRF usando las variables del HTML
         },
        body: JSON.stringify(idsLiquiArray)
    })
        .then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.error('Error:', error));

    window.location.href = `/imprimirRecibos/vistaImprimirRecibos`;
}


async function generarLosPDFs(recibosArray) {

    try {

        const response = await fetch('/GenerarPDFs', {
            method: 'POST',
            headers: { 
                'Content-Type': 'application/json',
                [csrfHeader]: csrfToken // Agrega el token CSRF usando las variables del HTML
             },
            body: JSON.stringify({ recibos: recibosArray }),
        })

        if (!response.ok) throw new Error("Error en la API de generación de PDFs");

        const zipFile = await response.blob();

        // Maneja el archivo ZIP recibido del backend
        const link = document.createElement('a');
        const url = URL.createObjectURL(zipFile);
        link.href = url;
        link.download = 'recibos.zip';
        link.click();

        return true // indica generación con la API exitosa.

    } catch (error) {
        alert('Error generando los PDFs: ' + error.message);
        return false; // Indica que hubo un error
    }
}






