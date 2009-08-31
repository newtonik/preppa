jQuery(document).ready(function(){
    var url = document.location;
    var test = url.toString();
    
    if(test.indexOf("show") > 0) {
        jQuery("ul.css-tabs a").removeClass("current");
        jQuery("ul.css-tabs a:contains('Article')").addClass("current");

    }
    if(test.indexOf("talk") > 0) {
        jQuery("ul.css-tabs a").removeClass("current");
        jQuery("ul.css-tabs a:contains('Discuss')").addClass("current");
    }
    if(test.indexOf("revisions") > 0) {
        jQuery("ul.css-tabs a").removeClass("current");
        jQuery("ul.css-tabs a:contains('History')").addClass("current");
    }

    // setup ul.tabs to work as tabs for each div directly under div.panes
   // jQuery("ul.css-tabs").tabs("div.panes");
});


function onChangeTestsubject(response) {

    //alert(response.testsubject);
    $('result1').update(response.testsubject);
    $('result1').setStyle({
        backgroundColor: '#FFFF5C'
    });


}
 $('addtopic').observe('click', function() {
            //get position
            alert("clciked");
            var pos = $('addtopic').cumulativeOffset();
            var width  = $('addtopic').getWidth();
            //alert(pos.left)

            $('newtopicbox').setStyle({

                display: 'block',
                position: 'absolute',
                fontSize: '12px',
                left: (pos.left-200) + "px",
                top : (pos.top)+ "px"
            });
            $('newtopicbox').show();
            $('fTopicName').activate();
            //$('autoComplete').disable();
        });

        $('closetopic').observe('click', function() {
              $('newtopicbox').setStyle({

                display: 'none'
                });
                $('topiccorner').setStyle({
                    'background-color': '#e8eefa'
                });
                 $('newtopicbox').hide();
                 $('autoComplete').enable();
                 $('autoComplete').activate();

        });



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

