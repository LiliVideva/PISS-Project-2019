function check() {
    if (document.getElementById('name').value != '' && document.getElementById('password').value != '') {
        if (document.getElementById('name').value == 'admin') {
            window.open('indexAdmin.html');
        } else window.open('indexRegistered.html');
    }
}