const tablaObjetivos = document.getElementById("tablaObjetivos");
const tablaBody = document.getElementById("tablaBody");

fetch(`/objetivos/listar`)
    .then((response) => response.json())
    .then((data) => {
        console.log(data)

        data.forEach(item => {
            const fila = document.createElement("tr");

            const celdaObjId = document.createElement("td");
            const celdaObjNombre = document.createElement("td");
            const celdaObjDomicilio = document.createElement("td");
            const celdaObjTelefono = document.createElement("td");
            const celdaObjAlias = document.createElement("td");

            celdaObjId.className = "datos";
            celdaObjNombre.className = "datos";
            celdaObjDomicilio.className = "datos";
            celdaObjTelefono.className = "datos";
            celdaObjAlias.className = "datos";

            celdaObjId.textContent = item.id;
            celdaObjNombre.textContent = item.nombre;
            celdaObjDomicilio.textContent = item.domicilio;
            celdaObjTelefono.textContent = item.telefono;
            celdaObjAlias.textContent = item.alias;

            fila.appendChild(celdaObjId);
            fila.appendChild(celdaObjNombre);
            fila.appendChild(celdaObjDomicilio);
            fila.appendChild(celdaObjTelefono);
            fila.appendChild(celdaObjAlias);

            tablaBody.appendChild(fila);            
            tablaObjetivos.appendChild(tablaBody);
        });











        objAlias




    });





