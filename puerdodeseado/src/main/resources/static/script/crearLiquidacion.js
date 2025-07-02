const btnDescargar = document.getElementById("btnDescargar");
const btnAprobar = document.getElementById("btnAprobar");
const periodo = document.getElementById("periodo");

// recupero elvalor del período su antes fue guardado
if(sessionStorage.getItem("periodoSeleccionado")){
    periodo.value = sessionStorage.getItem("periodoSeleccionado");
}


// capturo y guardo el valor del input en un session storage
periodo.addEventListener("change",()=> {
    sessionStorage.setItem("periodoSeleccionado", periodo.value);
})

btnDescargar.addEventListener("click", ()=>{
    window.location.href="/liquidaciones/exportarPadronAsociadosActivos";        
})

// el valor del periodo.value lo recupera gracias al sessionStorage
btnAprobar.addEventListener("click",()=>{
    window.location.href= `/liquidaciones/vistaAprobar?periodo=${periodo.value}`; 
})