
document.observe("dom:loaded", function() {

    $('addanswer').observe('click', function() {

    alert("I have been clicked");
    });

    $('hideanswer').observe('click', function() {
    alert("clicked");
             $('gridinanswer').show();
     if($('hideanswer').innerHTML == "Show")
     {
         $('gridinanswer').setStype(
            {
                position: 'relative 10px',
                display: 'block'
            }
            );
         question.getAnswers().get(0)
        $('hideanswer').innerHTML = "Hide";
     }
     else
         {
             $('gridinanswer').hide();
        $('hideanswer').innerHTML = "Show";
         }

 });

  $('cancelrange').observe('click', hideGridinRange);
});


function showGridinRange() {
     $('gridinsinglerow').hide()
    $('gridinrangerow').show();
   $('fRangelow').focus();
   $('fAnswer').writeAttribute("validate", "");
   $('fRangehigh').writeAttribute("validate", "required");
    $('ffRangehigh').writeAttribute("validate", "required");
//
//    $('fRangehigh').show();
//    $('cancelrange').show()
}

function hideGridinRange() {
    $('gridinrangerow').hide()
    $('gridinsinglerow').show()
    $('fRangehigh').writeAttribute("validate", "");
    $('ffRangehigh').writeAttribute("validate", "");
    $('fAnswer').writeAttribute("validate", "required");
//    $('fRangelow').hide();
//    $('fRangehigh').hide();
//    $('cancelrange').hide();
    $('noradio').checked = true;
    $('fAnswer').focus();
}

function showGridinUpload() {
    $('gridinupload').highlight();
    Effect.BlindDown('gridinupload', { duration: 1 });
}

function hideGridinUpload() {
    $('gridinupload').hide();
}



 function FCKeditor_OnComplete( editorInstance )
{
   
    fckeditor_word_count(editorInstance);
    editorInstance.Events.AttachEvent('OnSelectionChange', fckeditor_word_count);
    

}
var counter = 0;
function updateCounter(editorInstance) {
   alert(1)
    window.document.title = editorInstance.GetData();
}

function DoSomething( editorInstance )
{
    // This is a sample function that shows in the title bar the number of times
    // the "OnSelectionChange" event is called.
    //window.document.title = editorInstance.Name + ' : ' + ( ++counter ) ;
}


function fckeditor_word_count(editorInstance) {

    var matches = editorInstance.GetData().replace(/<[^<|>]+?>|&nbsp;/gi,' ').match(/\b/g);
    var count = 0;
    if(matches) {
        count = matches.length/2;
    }

    $('word_count').innerHTML = count + " word" + (count == 1 ? "" : "s");

}
