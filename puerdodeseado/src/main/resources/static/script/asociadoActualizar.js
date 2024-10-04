
let idAsocFuente = document.getElementById("idAsocFuente").value;
let idAsocActualizar = document.getElementById("idAsocActualizar");
idAsocActualizar.value = idAsocFuente;


let nombreFuente = document.getElementById("nombreFuente").innerText;
let nombreActualizar = document.getElementById("nombreActualizar");
nombreActualizar.value = nombreFuente;

let apellidoFuente = document.getElementById("apellidoFuente").innerText;
let apellidoActualizar = document.getElementById("apellidoActualizar");
apellidoActualizar.value = apellidoFuente;

let grupoSanguineoFuente = document.getElementById("grupoSanguineoFuente").innerText;
console.log("mi grupo sanguineo es "+ grupoSanguineoFuente);
let grupoSanguineoActualizar = document.getElementById("grupoSanguineoActualizar");
grupoSanguineoActualizar.value = grupoSanguineoFuente;


let dptoLaboralFuente = document.getElementById("dptoLaboralFuente").innerText;
let dptoLaboralActualizar = document.getElementById("dptoLaboralActualizar");
dptoLaboralActualizar.value = dptoLaboralFuente;

let dniFuente = document.getElementById("dniFuente").innerText;
let dniActualizar = document.getElementById("dniActualizar");
dniActualizar.value = dniFuente;

let cuilFuente = document.getElementById("cuilFuente").innerText;
let cuilActualizar = document.getElementById("cuilActualizar");
cuilActualizar.value = cuilFuente;

let fechaNacFuente = document.getElementById("fechaNacFuente").innerText;
let parseFecha = fechaNacFuente.split("/");

let dia = parseFecha[0];
let mes = parseFecha[1]
let año = parseFecha[2]

let fechaFormato = año + "-" + mes + "-" + dia;

let fechaNacimientoActualizar = document.getElementById("fechaNacimientoActualizar");
fechaNacimientoActualizar.value = fechaFormato;



let nacionalidadFuente = document.getElementById("nacionalidadFuente").innerText;
let nacionalidadActualizar = document.getElementById("nacionalidadActualizar");
nacionalidadActualizar.value = nacionalidadFuente;

let estadoCivilFuente = document.getElementById("estadoCivilFuente").innerText;
let estadoCivilActualizar = document.getElementById("estadoCivilActualizar");
estadoCivilActualizar.value = estadoCivilFuente;

let telFijoFuente = document.getElementById("telFijoFuente").innerText;
let telFijoActualizar = document.getElementById("telFijoActualizar");
telFijoActualizar.value = telFijoFuente;

let telMovilFuente = document.getElementById("telMovilFuente").innerText;
let telMovilActualizar = document.getElementById("telMovilActualizar");
telMovilActualizar.value = telMovilFuente;

let emailFuente = document.getElementById("emailFuente").innerText;
let emailActualizar = document.getElementById("emailActualizar");
emailActualizar.value = emailFuente;

let calleFuente = document.getElementById("calleFuente").innerText;
let calleActualizar = document.getElementById("calleActualizar");
calleActualizar.value = calleFuente;

let numeroFuente = document.getElementById("numeroFuente").innerText;
let numeroActualizar = document.getElementById("numeroActualizar");
numeroActualizar.value = numeroFuente;

let pisoFuente = document.getElementById("pisoFuente").innerText;
let pisoActualizar = document.getElementById("pisoActualizar");
pisoActualizar.value = pisoFuente;

let departamentoFuente = document.getElementById("departamentoFuente").innerText;
let departamentoActualizar = document.getElementById("dptoActualizar");
departamentoActualizar.value = departamentoFuente;

let barrioFuente = document.getElementById("barrioFuente").innerText;
let barrioActualizar = document.getElementById("barrioActualizar");
barrioActualizar.value = barrioFuente;

let distritoFuente = document.getElementById("distritoFuente").innerText;
let distritoActualizar = document.getElementById("distritoActualizar");
distritoActualizar.value = distritoFuente;

let provinciaFuente = document.getElementById("provinciaFuente").innerText;
let provinciaActualizar = document.getElementById("provinciaActualizar");
provinciaActualizar.value = provinciaFuente;

let codigoPostalFuente = document.getElementById("codigoPostalFuente").innerText;
let codigoPostalActualizar = document.getElementById("codigoPostalActualizar");
codigoPostalActualizar.value = codigoPostalFuente;
