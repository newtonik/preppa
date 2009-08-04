
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
});


function showRange() {
     $('singlerow').hide()
    $('rangerow').show();
   $('fRangelow').focus();
   $('fAnswer').writeAttribute("validate", "");
   $('fRangehigh').writeAttribute("validate", "required");
    $('ffRangehigh').writeAttribute("validate", "required");
//
//    $('fRangehigh').show();
//    $('cancelrange').show()
}

function hideRange() {
    $('rangerow').hide()
    $('singlerow').show()
    $('fRangehigh').writeAttribute("validate", "");
    $('ffRangehigh').writeAttribute("validate", "");
    $('fAnswer').writeAttribute("validate", "required");
//    $('fRangelow').hide();
//    $('fRangehigh').hide();
//    $('cancelrange').hide();
    $('noradio').checked = true;
    $('fAnswer').focus();
}

 $('cancelrange').observe('click', hideRange);

 