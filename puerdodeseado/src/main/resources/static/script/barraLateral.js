const bntVolver = document.getElementById("bntVolver");
const btnCrear = document.getElementById("btnCrear");
const btnEditar = document.getElementById("btnEditar");
/* const btnEliminar = document.getElementById("btnEliminar"); */
const btnImprimir = document.getElementById("btnImprimir");


btnCrear.addEventListener("click", ()=>{
    window.location.href="/liquidaciones/vistaLiquidaciones";

})
btnEditar.addEventListener("click", ()=>{
    window.location.href="/editarLiquidacion/vistaEditarLiquidacion";

})
/* btnEliminar.addEventListener("click", ()=>{
    window.location.href="/eliminarLiquidacion/vistaEliminarLiquidacion";

}) */


btnImprimir.addEventListener("click", ()=>{
    window.location.href="/imprimirRecibos/vistaImprimirRecibos";

})

bntVolver.addEventListener("click", ()=>{
    window.location.href="/";

})