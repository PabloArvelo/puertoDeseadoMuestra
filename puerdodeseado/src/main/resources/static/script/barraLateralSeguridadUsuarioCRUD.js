const btnCrear = document.getElementById("btnCrear");
const btnEditar = document.getElementById("btnEditar");
const btnEliminar = document.getElementById("btnEliminar");
const bntVolver = document.getElementById("bntVolver");



btnCrear.addEventListener("click", ()=>{
    window.location.href="/seguridad/usuarios/alta";

})
btnEditar.addEventListener("click", ()=>{
    window.location.href="/editarLiquidacion/vistaEditarLiquidacion";

})
btnEliminar.addEventListener("click", ()=>{
    window.location.href="/eliminarLiquidacion/vistaEliminarLiquidacion";
})



bntVolver.addEventListener("click", ()=>{    
    window.location.href="/seguridad/";
})