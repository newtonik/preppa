  
  /* 
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */



//  window.onload = function() {
//$('votebox').hide();
$('flaglink').observe('click', function() {
    //get position
    var pos = $('main').cumulativeOffset();


    $('votebox').setStyle({

        // display: 'block',
        position: 'absolute',
        fontSize: '12px',
        left: (pos.left+20) + "px",
        top : (pos.top-120)+ "px"
    });
    $('votebox').show();
     $('votebox').clonePosition()

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
function onChangeTestsubject(response) {

    //alert(response.testsubject);
    $('result1').update(response.testsubject);
    $('result1').setStyle({
        backgroundColor: '#FFFF5C'
    });


}
 //    }

//     Event.observe('flagform', 'submit', function(event) {
//         $('flagform').request({
//            onSuccess: function() {
//               //$('votebox').hide();
//                  $('flagZone').setStyle({
//                display: 'block',
//                fontSize: '12px',
//                width: '200px',
//                height: '150px'
//            });
//
//
//         }
//         });
//     });

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
