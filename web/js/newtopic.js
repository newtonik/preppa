/***
 * Javascript to add new topics to content.
 *
 */
document.observe("dom:loaded", function() {


    $$('.addtopic').invoke('observe', 'click', showTopic);


function showTopic(event) {
        //get position
    
       var element = event.element();
        var pos = element.cumulativeOffset();

        //set new style
        $('newtopicbox').setStyle({

            display: 'block',
            position: 'absolute',
            fontSize: '12px',
            left: (pos.left-300) + "px",
            top : (pos.top)+ "px"
        });
        //show the block
        $('newtopicbox').show();
        $('fTopicName').activate();
    
}

$$('.closetopicbox').invoke('observe', 'click', closeTopic);
function closeTopic (event) {
        $('newtopicbox').setStyle({

            display: 'none'
        });
        $('topiccorner').setStyle({
            'background-color': '#e8eefa'
        });
        $('newtopicbox').hide();
        $('autoComplete').enable();
        $('autoComplete').activate();

    }
});

