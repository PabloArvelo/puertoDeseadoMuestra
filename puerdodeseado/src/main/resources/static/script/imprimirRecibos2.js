document.getElementById("btnImprimirTodos").addEventListener("click", async () => {
    const periodo = document.getElementById("periodo").value;

    if (!periodo) {
        alert("Por favor, ingrese un período válido.");
        return;
    }

    try {
        const response = await fetch(`/imprimirRecibos/verRecibo?periodo=${periodo}`);
        const data = await response.json();

        if (data.success) {
            window.location.href = `/imprimirRecibos/imprimir?periodo=${periodo}&tipoFetch=todos`;            
            alert("generando recibos, aguarde la descarga")            

        } else {
            console.error("Error:", data.message);
            alert(data.message);
        }
    } catch (err) {        
        alert.error("Error en la solicitud:", err);
    }
});


