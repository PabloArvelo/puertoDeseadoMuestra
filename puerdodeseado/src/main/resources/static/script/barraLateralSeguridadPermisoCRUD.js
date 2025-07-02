const btnCrear = document.getElementById("btnCrear");
const btnEditar = document.getElementById("btnEditar");
const btnEliminar = document.getElementById("btnEliminar");
const bntVolver = document.getElementById("bntVolver");



btnCrear.addEventListener("click", ()=>{
    window.location.href="/seguridad/permisos/alta";

})
btnEditar.addEventListener("click", ()=>{
    window.location.href="#";

})
btnEliminar.addEventListener("click", ()=>{
    window.location.href="#";
})



bntVolver.addEventListener("click", ()=>{    
    window.location.href="/seguridad/";
})