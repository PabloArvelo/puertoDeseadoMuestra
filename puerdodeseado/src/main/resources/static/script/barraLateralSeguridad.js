const btnUsuarios = document.getElementById("btnUsuarios");
const btnRoles = document.getElementById("btnRoles");
const btnPermisos = document.getElementById("btnPermisos");


btnUsuarios.addEventListener("click", ()=>{
    window.location.href="/seguridad/usuarios";
})
btnRoles.addEventListener("click", ()=>{
    window.location.href="/seguridad/roles";
})
btnPermisos.addEventListener("click", ()=>{
    window.location.href="/seguridad/permisos";
})

bntVolver.addEventListener("click", ()=>{
    window.location.href="/";
})