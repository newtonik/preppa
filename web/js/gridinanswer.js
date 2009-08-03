$('checklink').observe('click', function() {
    var form = $('answerform');


    var buttons = form.getInputs('radio', 'chooseanswer');

    alert(buttons);
});

    

