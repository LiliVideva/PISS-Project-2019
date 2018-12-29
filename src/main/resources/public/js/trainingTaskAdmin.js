var minutesLabel = document.getElementById("minutes");
var secondsLabel = document.getElementById("seconds");
var totalSeconds = 0;
setInterval(setTime, 1000);

function setTime() {
    ++totalSeconds;
    $('#check').on('click', function () {
        secondsLabel = sec;
        minutesLabel = min;
    });
    secondsLabel.innerHTML = pad(totalSeconds % 60);
    minutesLabel.innerHTML = pad(parseInt(totalSeconds / 60));
    var sec = pad(totalSeconds % 60);
    var min = pad(parseInt(totalSeconds / 60));
}

function pad(val) {
    var valString = val + "";
    if (valString.length < 2) {
        return "0" + valString;
    } else {
        return valString;
    }
}

$(document).ready(function () {
    $("#helpTheory").css("display", "none");
    $("#solution").css("display", "none");
    $("#checkAnswer").css("display", "none");
    $("#checkTheory").css("display", "none");
    $("#helpTheoryModify").css("display", "none");
    $("#solutionModify").css("display", "none");
    $("#checkAnswerModify").css("display", "none");
    $("#checkTheoryModify").css("display", "none");
    $("#help").show();
    $("#solve").show();
    $("#check").show();
    $("#submit").show();
    $("#retry").show();
    $("#back").show();
});

$('#help').on('click', function () {
    $("#helpTheory").show();
    $("#helpTheoryModify").show();

});

$('#check').on('click', function () {
    $("#checkAnswer").show();
    $("#checkAnswerModify").show();
});

$('#submit').on('click', function () {
    $("#checkTheory").show();
    $("#checkTheoryModify").show();
});

$('#solve').on('click', function () {
    $("#solution").show();
    $("#solutionModify").show();
});