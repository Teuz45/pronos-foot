$(document).ready(function () {
    CountDownTimer($('#dateProchainEvenement').val());
});


function CountDownTimer(dt)
{
    var end = new Date(dt*1000);
    
    var _second = 1000;
    var _minute = _second * 60;
    var _hour = _minute * 60;
    var _day = _hour * 24;
    var timer;

    function showRemaining() {
        var now = new Date();
        var distance = end - now;
        if (distance < 0) {

            clearInterval(timer);
            // ????
            document.getElementById(id).innerHTML = 'EXPIRED!';

            return;
        }
        var days = Math.floor(distance / _day);
        var hours = Math.floor((distance % _day) / _hour);
        var minutes = Math.floor((distance % _hour) / _minute);
        var seconds = Math.floor((distance % _minute) / _second);
        
        document.getElementById('countdown_jours').innerHTML = formatCentaine(days);
        document.getElementById('countdown_heures').innerHTML = formatDizaine(hours);
        document.getElementById('countdown_minutes').innerHTML = formatDizaine(minutes);
        document.getElementById('countdown_secondes').innerHTML = formatDizaine(seconds);
    }

    timer = setInterval(showRemaining, 1000);
}

function formatDizaine(n){
    return n>9 ? n : "0"+n;
}

function formatCentaine(n){
    return n>99 ? n : (n>9 ? "0"+n : "00"+n);
}
