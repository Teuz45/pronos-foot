$(document).ready(function () {
    CountDownTimer($('#dateProchainEvenement').val());
});


function CountDownTimer(dt)
{
    var end = new Date(dt*1);
    
    console.log(end);
    
    var _second = 1000;
    var _minute = _second * 60;
    var _hour = _minute * 60;
    var _day = _hour * 24;
    var timer;

    function showRemaining() {
        var now = new Date();
        var diff = end - now;
        if (diff < 0) {

            clearInterval(timer);
            // ????
            //document.getElementById(id).innerHTML = 'EXPIRED!';
            $(".countdown").hide();

            return;
        }
        var days = Math.floor(diff / _day);
        var hours = Math.floor((diff % _day) / _hour);
        var minutes = Math.floor((diff % _hour) / _minute);
        var seconds = Math.floor((diff % _minute) / _second);
        
        $(".countdown").show();
        $("#countdown_jours").html(formatCentaine(days));
        $("#countdown_heures").html(formatDizaine(hours));
        $("#countdown_minutes").html(formatDizaine(minutes));
        $("#countdown_secondes").html(formatDizaine(seconds));
    }

    timer = setInterval(showRemaining, 1000);
}

function formatDizaine(n){
    return n>9 ? n : "0"+n;
}

function formatCentaine(n){
    return n>99 ? n : (n>9 ? "0"+n : "00"+n);
}
