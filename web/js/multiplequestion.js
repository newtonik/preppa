//jQuery(document).ready(function() {
//    // bind 'myForm' and provide a simple callback function
//    var path = window.location.pathname;
//
//    path = path + '.firstquestion.createquestionform';
//    var options = {
//        url: path,
//        type: 'post',
//        beforeSubmit: checkform
//    }
//    jQuery('#createquestionform').ajaxForm(options);
//});



document.observe("dom:loaded", function() {

    $('multiple-question').highlight();



    Event.observe('createquestionform', 'submit', function() {
    FCKeditorAPI.Instances['questioneditor'].UpdateLinkedField();
    FCKeditorAPI.Instances['choice1'].UpdateLinkedField();
    FCKeditorAPI.Instances['choice2'].UpdateLinkedField();
    FCKeditorAPI.Instances['choice3'].UpdateLinkedField();
    FCKeditorAPI.Instances['choice4'].UpdateLinkedField();
    FCKeditorAPI.Instances['choice5'].UpdateLinkedField();


    });

});



function FCKeditor_OnComplete( editorInstance )
{

    fckeditor_word_count(editorInstance);
    editorInstance.Events.AttachEvent('OnSelectionChange', fckeditor_word_count);


}

function fckeditor_word_count(editorInstance) {

    var matches = editorInstance.GetData().replace(/<[^<|>]+?>|&nbsp;/gi,' ').match(/\b/g);
    var count = 0;
    if(matches) {
        count = matches.length/2;
    }

    $('word_count').innerHTML = count + " word" + (count == 1 ? "" : "s");

}

function checkform() {
    alert("checking form");
}

function showUpload() {
    $('upload').highlight();
    Effect.BlindDown('upload', { 
        duration: 1
    });
}

function hideUpload() {
    $('upload').hide();
}
