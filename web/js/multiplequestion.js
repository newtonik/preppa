document.observe("dom:loaded", function() {

    $('multiple-question').highlight();
    Event.observe('createquestionform', 'submit', 'checkform');

});


function checkform() {
    alert("checking form");
}

function showUpload() {
    $('upload').highlight();
    Effect.BlindDown('upload', { duration: 1 });
}

function hideUpload() {
    $('upload').hide();
}
