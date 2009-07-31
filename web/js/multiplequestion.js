document.observe("dom:loaded", function() {

    $('multiple-question').highlight();
    Event.observe('createquestionform', 'submit', 'checkform');

});


function checkform() {
    alert("checking form");
}