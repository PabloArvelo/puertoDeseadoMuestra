document.getElementById('recoverForm').addEventListener('submit', function(event) {
    event.preventDefault();
    const email = document.getElementById('email').value;
    const csrfToken = document.querySelector('input[name="_csrf"]').value;
    const divAguarde = document.getElementById('successMessage');
    divAguarde.style.display = 'block';
    divAguarde.textContent= "por favor aguarde..."

    fetch('/recover-account', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: 'email=' + encodeURIComponent(email) + '&_csrf=' + encodeURIComponent(csrfToken)
    })
    .then(response => response.text())
    .then(data => {
        divAguarde.style.display = 'none';
        document.getElementById('successMessage').textContent = data;
        document.getElementById('successMessage').style.display = 'block';
        document.getElementById('errorMessage').style.display = 'none';
        
    })
    .catch(error => {
        divAguarde.style.display = 'none';
        document.getElementById('errorMessage').textContent = 'Error al procesar la solicitud';
        document.getElementById('errorMessage').style.display = 'block';
        document.getElementById('successMessage').style.display = 'none';
        
    });
});