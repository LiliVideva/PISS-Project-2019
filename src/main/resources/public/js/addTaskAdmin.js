function offer() {
    $("#offer").css("display", "none");
    $("#publishTask").css("display", "none");
    $("#success").show();
}

$(document).ready(function () {
    $("#success").css("display", "none");
    $("#publishTask").show();
});

$('#offer').on('click', function () {
    $("#offer").css("display", "none");
    $("#publishTask").show();
    $("#success").css("display", "none");
});

