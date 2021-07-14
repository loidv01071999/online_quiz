var i;

var checkboxes = document.querySelectorAll('input[type=checkbox]');

function save() {

    for (i = 0; i < checkboxes.length; i++) {

        sessionStorage.setItem(checkboxes[i].value, checkboxes[i].checked);

    }
}

function clickAnswer(){
    save();
}

function load() {

    for (i = 0; i < checkboxes.length; i++) {

        checkboxes[i].checked = sessionStorage.getItem(checkboxes[i].value) === 'true' ? true:false;
    }

}
window.onload = function () {
    next();
    if (performance.navigation.type === performance.navigation.TYPE_RELOAD) {
        load();
    }
    //realtime exam
    realTimeExam = document.getElementById('real-time-exam').value;
    display = document.querySelector('#showTime');
    startTimer(display, realTimeExam);
};
window.onbeforeunload = function ()
{
    save();
};