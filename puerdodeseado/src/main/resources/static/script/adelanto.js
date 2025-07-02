let miDiv = document.getElementById("descuentaEnPeriodo");
let miCheckBox = document.getElementById("checkBox");
let miInterruptor = document.getElementById("miInterruptor");

// let formAdelanto = document.getElementById("formAdelanto");


document.addEventListener("DOMContentLoaded", () => {
    miDiv = document.getElementById("descuentaEnPeriodo");
    miDiv.style.display = "none";
})


miInterruptor.addEventListener("change", () => {

    if (miInterruptor.checked) {
        miDiv.style.display = "flex";
        miDiv.style.flexDirection = "column";
        miDiv.style.border = "#1d2124 solid";
    } else {
        miDiv.style.display = "none";
    }
})



   







