//list question
var questions = null;
//index of question
var selectedQ = null;
//exam time
var timer = 0;
//set list question
if (questions === null) {
    questions = document.getElementsByClassName("question");
}
//set index of question = index of first question
if (selectedQ === null) {
    selectedQ = 0;
}

//handle next button
function next() {
    //index is end of list question then submit 
    if (selectedQ === questions.length && timer !== 0) {
        document.getElementById("doquiz").submit();
        return;
    }
    //hidden all question in list question
    for (var i = 0; i < questions.length; i++) {
        questions[i].setAttribute("hidden", "true");
    }
    //display question with index of question
    questions[selectedQ++].removeAttribute("hidden");
}

var myInterval;

//handle time remaining
function startTimer(display, realTimeExam) {
    var now = new Date();
    var submitTime = new Date(realTimeExam);
    //handle exam time with real time exam
    timer = (submitTime - now) / 1000;
    if (timer < 0) {
        timer = 0;
    }
    var minutes, seconds;
    myInterval = setInterval(function () {

        timer = timer - 1;
        //handle exam time
        if (timer < 1) {
            document.getElementById("doquiz").submit();
            clearInterval(myInterval);
        }
        //parsing minutes and seconds to 10 base
        minutes = parseInt(timer / 60, 10);
        seconds = parseInt(timer % 60, 10);

        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;
        //display time
        display.textContent = minutes + ":" + seconds;
    }, 1000);//loop after 1 second
}

function clickAnswer() {

}

window.onload = function () {
    next();
    //realtime exam
    realTimeExam = document.getElementById('real-time-exam').value;
    display = document.querySelector('#showTime');
    startTimer(display, realTimeExam);
};




