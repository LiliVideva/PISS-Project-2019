function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
    document.getElementById("main").style.marginLeft = "250px";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("main").style.marginLeft = "0";
}

$(document).ready(function () {
    $("#subj").children().css("display", "none");
    $("#tasksHardness").children().css("display", "none");
    openNav();
});

$('.dropdown-item').on('click', function () {
    var target = $(this).attr('rel');
    $("#" + target).show().siblings("div").hide();
    $("#myCarousel").css("display", "none");
    document.getElementById("container-subjects").style.marginLeft = "20px";
});

$('.taskType').on('click', function () {
    var target = $(this).attr('rel');
    $("#" + target).show().siblings("div").hide();
});