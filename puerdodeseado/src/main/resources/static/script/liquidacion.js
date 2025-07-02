
let titulo = document.getElementById("titulo");


let horas1 = document.getElementById("horas1");
let minutos1 = document.getElementById("minutos1");
let valorHora1 = document.getElementById("valorHora1");
let objetivo1 = document.getElementById("objetivo1");

let horas2 = document.getElementById("horas2");
let minutos2 = document.getElementById("minutos2");
let valorHora2 = document.getElementById("valorHora2");
let objetivo2 = document.getElementById("objetivo2");

let horas3 = document.getElementById("horas3");
let minutos3 = document.getElementById("minutos3");
let valorHora3 = document.getElementById("valorHora3");
let objetivo3 = document.getElementById("objetivo3");

let horas4 = document.getElementById("horas4");
let minutos4 = document.getElementById("minutos4");
let valorHora4 = document.getElementById("valorHora4");
let objetivo4 = document.getElementById("objetivo4");

let horas5 = document.getElementById("horas5");
let minutos5 = document.getElementById("minutos5");
let valorHora5 = document.getElementById("valorHora5");
let objetivo5 = document.getElementById("objetivo5");

let horas6 = document.getElementById("horas6");
let minutos6 = document.getElementById("minutos6");
let valorHora6 = document.getElementById("valorHora6");
let objetivo6 = document.getElementById("objetivo6");

let horas7 = document.getElementById("horas7");
let minutos7 = document.getElementById("minutos7");
let valorHora7 = document.getElementById("valorHora7");
let objetivo7 = document.getElementById("objetivo7");

let horas8 = document.getElementById("horas8");
let minutos8 = document.getElementById("minutos8");
let valorHora8 = document.getElementById("valorHora8");
let objetivo8 = document.getElementById("objetivo8");

let horas9 = document.getElementById("horas9");
let minutos9 = document.getElementById("minutos9");
let valorHora9 = document.getElementById("valorHora9");
let objetivo9 = document.getElementById("objetivo9");

let horas10 = document.getElementById("horas10");
let minutos10 = document.getElementById("minutos10");
let valorHora10 = document.getElementById("valorHora10");
let objetivo10 = document.getElementById("objetivo10");

// suman
let bonificaciones = document.getElementById("bonificaciones");
let presentismo = document.getElementById("presentismo");
let nocturnidad = document.getElementById("nocturnidad");
let nocturnidadEventual = document.getElementById("nocturnidadEventual");
let bonificacionFeriados = document.getElementById("bonificacionFeriados");
let reconocimientoHoras = document.getElementById("reconocimientoHoras");
let carpetaMedica = document.getElementById("carpetaMedica");
let hsVacaciones = document.getElementById("hsVacaciones");
let horasAdeudadas = document.getElementById("horasAdeudadas");
let licenciaMaternidad = document.getElementById("licenciaMaternidad");
let horasPractica = document.getElementById("horasPractica");
let reintegroCuotasSociales = document.getElementById("reintegroCuotasSociales");


// restan
let cuotaSocial = document.getElementById("cuotaSocial");
let seguroAccPer_Vida = document.getElementById("seguroAccPer_Vida");
let retenMonotriPer = document.getElementById("retenMonotriPer");
let seguroVidaOblig = document.getElementById("seguroVidaOblig");
let adelanto = document.getElementById("adelanto");
let prestamo = document.getElementById("prestamo");
let cuotaActual = document.getElementById("cuotaActual");
let interes = document.getElementById("interes");
let costoHabilitacion = document.getElementById("costoHabilitacion");
let aptoPsiFi = document.getElementById("aptoPsiFi");
let credisolCS = document.getElementById("credisolCS");
let credisolCC = document.getElementById("credisolCC");
let creditoGUPAServicioSAS = document.getElementById("creditoGUPAServicioSAS");
let almacen = document.getElementById("almacen");
let embargoJudicial = document.getElementById("embargoJudicial");
let depositoExceso = document.getElementById("depositoExceso");
let descuentoRoturaPerdida = document.getElementById("descuentoRoturaPerdida");
let calzado = document.getElementById("calzado");
let adherenteMonotributo = document.getElementById("adherenteMonotributo");

// Totales
let totalBruto = document.getElementById("totalBruto");
let totalDeducciones = document.getElementById("totalDeducciones");
let totalAnticipo = document.getElementById("totalAnticipo");



// idAsoc
let idAsoc = "";



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






fetch(`/editarLiquidacion/buscarLiquidacionPorAsocPeriodo`,{
credentials: "include"
})
    .then((response) => response.json())   // como el resultado es un string, no es necesario que sea .JSON
    .then((data) => {
        //console.log(data);
        //console.log(data.liquidacion.objetivo1)
        //console.log(data.objetivos)

        titulo.innerText = "liquidación: " + data.liquidacion.asociadosMain.apellido + " " + data.liquidacion.asociadosMain.nombre;

        const defaultOption = document.createElement('option');
        defaultOption.value = 0;
        defaultOption.textContent = 'asignar objetivo';
        defaultOption.selected = true; // Marca esta opción como seleccionada por defecto    
        
        

        // LLENO LOS SELECTS DE OBJETIVOS LLAMANDO A LA FUNCIÓN QUE ESTÁ ARRIBA
        llenarSelect(objetivo1, "asignar objetivo", data.liquidacion.objetivo1,data.objetivos);
        llenarSelect(objetivo2, "asignar objetivo", data.liquidacion.objetivo2,data.objetivos);
        llenarSelect(objetivo3, "asignar objetivo", data.liquidacion.objetivo3,data.objetivos);        
        llenarSelect(objetivo4, "asignar objetivo", data.liquidacion.objetivo4,data.objetivos);
        llenarSelect(objetivo5, "asignar objetivo", data.liquidacion.objetivo5,data.objetivos);
        llenarSelect(objetivo6, "asignar objetivo", data.liquidacion.objetivo6,data.objetivos);
        llenarSelect(objetivo7, "asignar objetivo", data.liquidacion.objetivo7,data.objetivos);
        llenarSelect(objetivo8, "asignar objetivo", data.liquidacion.objetivo8,data.objetivos);
        llenarSelect(objetivo9, "asignar objetivo", data.liquidacion.objetivo9,data.objetivos);
        llenarSelect(objetivo10, "asignar objetivo", data.liquidacion.objetivo10,data.objetivos);




        // HORAS 
        horas1.value = data.liquidacion.horas1;
        minutos1.value = data.liquidacion.minutos1;
        valorHora1.value = data.liquidacion.valorHora1;
        
        horas2.value = data.liquidacion.horas2;
        minutos2.value = data.liquidacion.minutos2;
        valorHora2.value = data.liquidacion.valorHora2;
        
        horas3.value = data.liquidacion.horas3;
        minutos3.value = data.liquidacion.minutos3;
        valorHora3.value = data.liquidacion.valorHora3;
        
        horas4.value = data.liquidacion.horas4;
        minutos4.value = data.liquidacion.minutos4;
        valorHora4.value = data.liquidacion.valorHora4;
        
        horas5.value = data.liquidacion.horas5;
        minutos5.value = data.liquidacion.minutos5;
        valorHora5.value = data.liquidacion.valorHora5;
        
        horas6.value = data.liquidacion.horas6;
        minutos6.value = data.liquidacion.minutos6;
        valorHora6.value = data.liquidacion.valorHora6;
        
        horas7.value = data.liquidacion.horas7;
        minutos7.value = data.liquidacion.minutos7;
        valorHora7.value = data.liquidacion.valorHora7;
        
        horas8.value = data.liquidacion.horas8;
        minutos8.value = data.liquidacion.minutos8;
        valorHora8.value = data.liquidacion.valorHora8;
        
        horas9.value = data.liquidacion.horas9;
        minutos9.value = data.liquidacion.minutos9;
        valorHora9.value = data.liquidacion.valorHora9;
        
        horas10.value = data.liquidacion.horas10;
        minutos10.value = data.liquidacion.minutos10;
        valorHora10.value = data.liquidacion.valorHora10;
        

        // SUMAN
        bonificaciones.value = data.liquidacion.bonificaciones;
        presentismo.value = data.liquidacion.presentismo;
        nocturnidad.value = data.liquidacion.nocturnidad;
        nocturnidadEventual.value = data.liquidacion.nocturnidadEventual;
        bonificacionFeriados.value = data.liquidacion.bonificacionFeriados;
        reconocimientoHoras.value = data.liquidacion.reconocimientoHoras;
        carpetaMedica.value = data.liquidacion.carpetaMedica;
        hsVacaciones.value = data.liquidacion.hsVacaciones;
        horasAdeudadas.value = data.liquidacion.horasAdeudadas;
        licenciaMaternidad.value = data.liquidacion.licenciaMaternidad;
        horasPractica.value = data.liquidacion.horasPractica;
        reintegroCuotasSociales.value = data.liquidacion.reintegroCuotasSociales;

        // RESTAN
        cuotaSocial.value = data.liquidacion.cuotaSocial;
        seguroAccPer_Vida.value = data.liquidacion.seguroAccPer_Vida;
        retenMonotriPer.value = data.liquidacion.retenMonotriPer;
        seguroVidaOblig.value = data.liquidacion.seguroVidaOblig;
        adelanto.value = data.liquidacion.adelanto;
        prestamo.value = data.liquidacion.prestamo;
        cuotaActual.value = data.liquidacion.cuotaActual;
        interes.value = data.liquidacion.interes;
        costoHabilitacion.value = data.liquidacion.costoHabilitacion;
        aptoPsiFi.value = data.liquidacion.aptoPsiFi;
        credisolCS.value = data.liquidacion.credisolCS;
        credisolCC.value = data.liquidacion.credisolCC;
        creditoGUPAServicioSAS.value = data.liquidacion.creditoGUPAServicioSAS;
        almacen.value = data.liquidacion.almacen;
        embargoJudicial.value = data.liquidacion.embargoJudicial;
        depositoExceso.value = data.liquidacion.depositoExceso;
        descuentoRoturaPerdida.value = data.liquidacion.descuentoRoturaPerdida;
        calzado.value = data.liquidacion.calzado;
        adherenteMonotributo.value = data.liquidacion.adherenteMonotributo;

        // TOTALES
        totalBruto.value = data.liquidacion.totalBruto;
        totalDeducciones.value = data.liquidacion.totalDeducciones;
        totalAnticipo.value = data.liquidacion.totalAnticipo;

        //IDs        

        const btnGuardarbtnGuardar = document.getElementById("btnGuardar");

        btnGuardar.addEventListener("click", async () => {

            let horas1 = document.getElementById("horas1").value;
            let minutos1 = document.getElementById("minutos1").value;
            let valorHora1 = document.getElementById("valorHora1").value;
            let objetivo1 = document.getElementById("objetivo1").value;
            let horas2 = document.getElementById("horas2").value;
            let minutos2 = document.getElementById("minutos2").value;
            let valorHora2 = document.getElementById("valorHora2").value;
            let objetivo2 = document.getElementById("objetivo2").value;
            let horas3 = document.getElementById("horas3").value;
            let minutos3 = document.getElementById("minutos3").value;
            let valorHora3 = document.getElementById("valorHora3").value;
            let objetivo3 = document.getElementById("objetivo3").value;
            let horas4 = document.getElementById("horas4").value;
            let minutos4 = document.getElementById("minutos4").value;
            let valorHora4 = document.getElementById("valorHora4").value;
            let objetivo4 = document.getElementById("objetivo4").value;
            let horas5 = document.getElementById("horas5").value;
            let minutos5 = document.getElementById("minutos5").value;
            let valorHora5 = document.getElementById("valorHora5").value;
            let objetivo5 = document.getElementById("objetivo5").value;
            let horas6 = document.getElementById("horas6").value;
            let minutos6 = document.getElementById("minutos6").value;
            let valorHora6 = document.getElementById("valorHora6").value;
            let objetivo6 = document.getElementById("objetivo6").value;
            let horas7 = document.getElementById("horas7").value;
            let minutos7 = document.getElementById("minutos7").value;
            let valorHora7 = document.getElementById("valorHora7").value;
            let objetivo7 = document.getElementById("objetivo7").value;
            let horas8 = document.getElementById("horas8").value;
            let minutos8 = document.getElementById("minutos8").value;
            let valorHora8 = document.getElementById("valorHora8").value;
            let objetivo8 = document.getElementById("objetivo8").value;
            let horas9 = document.getElementById("horas9").value;
            let minutos9 = document.getElementById("minutos9").value;
            let valorHora9 = document.getElementById("valorHora9").value;
            let objetivo9 = document.getElementById("objetivo9").value;
            let horas10 = document.getElementById("horas10").value;
            let minutos10 = document.getElementById("minutos10").value;
            let valorHora10 = document.getElementById("valorHora10").value;
            let objetivo10 = document.getElementById("objetivo10").value;

            // suman
            let bonificaciones = document.getElementById("bonificaciones").value;
            let presentismo = document.getElementById("presentismo").value;
            let nocturnidad = document.getElementById("nocturnidad").value;
            let nocturnidadEventual = document.getElementById("nocturnidadEventual").value;
            let bonificacionFeriados = document.getElementById("bonificacionFeriados").value;
            let reconocimientoHoras = document.getElementById("reconocimientoHoras").value;
            let carpetaMedica = document.getElementById("carpetaMedica").value;
            let hsVacaciones = document.getElementById("hsVacaciones").value;
            let horasAdeudadas = document.getElementById("horasAdeudadas").value;
            let licenciaMaternidad = document.getElementById("licenciaMaternidad").value;
            let horasPractica = document.getElementById("horasPractica").value;
            let reintegroCuotasSociales = document.getElementById("reintegroCuotasSociales").value;


            // restan
            let cuotaSocial = document.getElementById("cuotaSocial").value;
            let seguroAccPer_Vida = document.getElementById("seguroAccPer_Vida").value;
            let retenMonotriPer = document.getElementById("retenMonotriPer").value;
            let seguroVidaOblig = document.getElementById("seguroVidaOblig").value;
            let adelanto = document.getElementById("adelanto").value;
            let prestamo = document.getElementById("prestamo").value;
            let cuotaActual = document.getElementById("cuotaActual").value;
            let interes = document.getElementById("interes").value;
            let costoHabilitacion = document.getElementById("costoHabilitacion").value;
            let aptoPsiFi = document.getElementById("aptoPsiFi").value;
            let credisolCS = document.getElementById("credisolCS").value;
            let credisolCC = document.getElementById("credisolCC").value;
            let creditoGUPAServicioSAS = document.getElementById("creditoGUPAServicioSAS").value;
            let almacen = document.getElementById("almacen").value;
            let embargoJudicial = document.getElementById("embargoJudicial").value;
            let depositoExceso = document.getElementById("depositoExceso").value;
            let descuentoRoturaPerdida = document.getElementById("descuentoRoturaPerdida").value;
            let calzado = document.getElementById("calzado").value;
            let adherenteMonotributo = document.getElementById("adherenteMonotributo").value;


            const liquiActualizada = {
                horas1: horas1,
                minutos1: minutos1,
                valor_hora1: valorHora1,
                objetivo1: objetivo1,
                horas2: horas2,
                minutos2: minutos2,
                valor_hora2: valorHora2,
                objetivo2: objetivo2,
                horas3: horas3,
                minutos3: minutos3,
                valor_hora3: valorHora3,
                objetivo3: objetivo3,
                horas4: horas4,
                minutos4: minutos4,
                valor_hora4: valorHora4,
                objetivo4: objetivo4,
                horas5: horas5,
                minutos5: minutos5,
                valor_hora5: valorHora5,
                objetivo5: objetivo5,
                horas6: horas6,
                minutos6: minutos6,
                valor_hora6: valorHora6,
                objetivo6: objetivo6,
                horas7: horas7,
                minutos7: minutos7,
                valor_hora7: valorHora7,
                objetivo7: objetivo7,
                horas8: horas8,
                minutos8: minutos8,
                valor_hora8: valorHora8,
                objetivo8: objetivo8,
                horas9: horas9,
                minutos9: minutos9,
                valor_hora9: valorHora9,
                objetivo9: objetivo9,
                horas10: horas10,
                minutos10: minutos10,
                valor_hora10: valorHora10,
                objetivo10: objetivo10,

                presentismo: presentismo,
                nocturnidad: nocturnidad,
                nocturnidad_eventual: nocturnidadEventual,
                bonificacion_feriados: bonificacionFeriados,
                reconocimiento_horas: reconocimientoHoras,
                carpeta_medica: carpetaMedica,
                hs_vacaciones: hsVacaciones,
                horas_adeudadas: horasAdeudadas,
                licencia_maternidad: licenciaMaternidad,
                horas_practica: horasPractica,
                reintegro_cuotas_sociales: reintegroCuotasSociales,
                seguro_acc_per_vida: seguroAccPer_Vida,
                reten_monotri_per: retenMonotriPer,
                seguro_vida_oblig: seguroVidaOblig,
                costo_habilitacion: costoHabilitacion,
                apto_psi_fi: aptoPsiFi,
                credisolcs: credisolCS,
                credisolcc: credisolCC,
                almacen: almacen,
                embargo_judicial: embargoJudicial,
                deposito_exceso: depositoExceso,
                descuento_rotura_perdida: descuentoRoturaPerdida,
                calzado: calzado,
                adherente_monotributo: adherenteMonotributo,
                cuota_social: cuotaSocial,
                bonificaciones: bonificaciones,
                adelanto: adelanto,
                prestamo: prestamo,
                cuota_actual: cuotaActual,
                interes: interes,
                creditogupaserviciosas: creditoGUPAServicioSAS,

                idLiquidacion: data.liquidacion.id,
                idAsociado: data.liquidacion.asociadosMain.id,
                periodo: data.liquidacion.periodo

            }

            try {

                for (const [campo, valor] of Object.entries(liquiActualizada)) {
                    if (campo !== "idLiquidacion" && campo !== "idAsociado" && campo !== "periodo" && campo !== "cuota_actual") {
                        // Comprobamos si el valor es un número
                        if (isNaN(valor)) {
                            alert(`El valor del campo ${campo} debe ser un numero válido.`);
                            return false; // Detener el proceso si hay un valor inválido
                        }
                    }
                }

                const respuesta = await fetch(`/editarLiquidacion/guardarEdicion/${data.liquidacion.id}`, {
                    method: "PUT",
                    headers: {
                        "Content-Type": "application/json",
                        [csrfHeader]: csrfToken // Agrega el token CSRF usando las variables del HTML
                    },
                    credentials: 'include', // Asegura que se envíe la cookie JSESSIONID
                    body: JSON.stringify(liquiActualizada)

                })


                if (!respuesta.ok) {
                    // Procesar errores de validación
                    const errores = await respuesta.json();
                    for (const [campo, mensaje] of Object.entries(errores)) {
                        //console.log(`Campo: ${campo}, Error: ${mensaje}`);
                        // Aquí puedes agregar lógica para mostrar los errores en los inputs correspondientes

                        const input = document.querySelector(`[name="${campo}"]`);
                        if (input) {
                            // si ya había un error, primero limpio el mensaje anterior
                            const existingError = input.parentNode.querySelector(".error");
                            if (existingError) {
                                existingError.remove();
                            }
                            const errorSpan = document.createElement("span");
                            errorSpan.className = "error";
                            errorSpan.textContent = mensaje;
                            input.parentNode.appendChild(errorSpan);
                        }
                    }
                    return;
                }

                // Procesar mensaje de éxito
                const resultado = await respuesta.json();
                alert(resultado.mensaje);
                window.location.href = `/editarLiquidacion/verLiquidacion`;

            } catch (error) {
                console.error("Error de red:", error);
                alert("Error de red al intentar actualizar los datos");
            }
        })


    }
    );














