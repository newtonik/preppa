//     function onChangeTestsubject(response) {
//
//         //alert(response.testsubject);
//         $('result1').update(response.testsubject);
//         $('result1').setStyle({
//            backgroundColor: '#FFFF5C'
//            });
//
//
//     }

document.observe("dom:loaded", function() {
    $('addquestion').observe('click', bindelements);

    function bindelements() {

    
        $('questionZone').setStyle({
            display: 'block'
            }

        );
        Event.observe('createquestionform', 'submit', function() {
            FCKeditorAPI.Instances['questioneditor'].UpdateLinkedField();
            FCKeditorAPI.Instances['choice1'].UpdateLinkedField();
            FCKeditorAPI.Instances['choice2'].UpdateLinkedField();
            FCKeditorAPI.Instances['choice3'].UpdateLinkedField();
            FCKeditorAPI.Instances['choice4'].UpdateLinkedField();
            FCKeditorAPI.Instances['choice5'].UpdateLinkedField();

        });

    }

     $('removenewquestion').observe('click', remove);

     function remove() {
            $('questionZone').setStyle({
            display: 'none'
            }

        );
     }
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
    if(editorInstance.Name == "passeditorone")
    {
        $('word_count').innerHTML = count + " word" + (count == 1 ? "" : "s");
        
    }
    if(editorInstance.Name == "passeditortwo") {

        $('word_count2').innerHTML = count + " word" + (count == 1 ? "" : "s");
    }
    if(editorInstance.Name == "pass1")
    {
        $('word_count').innerHTML = count + " word" + (count == 1 ? "" : "s");
        
    }
    if(editorInstance.Name == "pass2")
    {
        $('word_count2').innerHTML = count + " word" + (count == 1 ? "" : "s");

    }
}

$('flaglink').observe('click', function() {
    //get position
    var pos = $('main').cumulativeOffset();


    $('votebox').setStyle({

        // display: 'block',
        position: 'absolute',
        fontSize: '12px'
    });
    $('votebox').show();
    $('votebox').makePositioned();
    $('votebox').focus();
    $('flagfield').activate();
    //Effect.ScrollTo('header');
    //$('votebox').absolutize()


    $('closeflag').observe('click', function() {
        $('votebox').hide();

    });
});

window.scroll(0,  document.height);
$('cancelflag').observe('click', function() {
    $('votebox').hide();

});
$('closeflagblock').observe('click', function() {
    $('votebox').setStyle( {
        display: 'none'
    })
    $('votebox').hide();

});
