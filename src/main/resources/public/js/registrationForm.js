function check() {
    if (document.getElementById('name').value != '' && document.getElementById('email').value != '' &&
        document.getElementById('password').value != '' && document.getElementById('repeatPassword').value != '') {
        if (document.getElementById('password').value != document.getElementById('repeatPassword').value) {
            alert("Двете пароли не съвпадат! Въведи ги отново!");
        } else window.open('loginForm.html');
    }
}