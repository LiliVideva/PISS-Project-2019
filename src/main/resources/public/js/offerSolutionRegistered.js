function offer() {
    if (document.getElementById('solution').value != "" && document.getElementById('theoryOptions').value != "") {
        $("#offer").css("display", "none");
        $("#sol").css("display", "none");
        $("#success").show();
    } else alert("Попълнете всички полета!");
}

$(document).ready(function () {
    $("#sol").css("display", "none");
    $("#success").css("display", "none");
});

$('#offer').on('click', function () {
    $("#offer").css("display", "none");
    $("#sol").show();
    $("#success").css("display", "none");
});