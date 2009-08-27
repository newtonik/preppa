   
Tapestry.activateZone = function ( zoneId, url ) {
    var zoneManager = Tapestry.findZoneManagerByZoneId( zoneId );
    if ( zoneManager != null ) {
        zoneManager.updateFromURL( url );
                    
    }
};
Tapestry.findZoneManagerByZoneId = function( zoneId ) {
    var zoneElement = $(zoneId);
    if (!zoneElement) {
        Tapestry.ajaxError("Unable to locate Ajax Zone '#{id}' for dynamic update.", {
            id:zoneId
        });
        return null;
    }
    var manager = $T(zoneElement).zoneManager;
    if (!manager) {
        Tapestry.ajaxError("Ajax Zone '#{id}' does not have an associated Tapestry.ZoneManager object.", {
            id :zoneId
        });
        return null;
    }
    return manager;
};

//Tapestry.activateZone( 'formzone', '../create.tagform' );
function countCharacters() {
    // Get the editor instance that we want to interact with.
    var oEditor = FCKeditorAPI.GetInstance('body');
    // Get the Editor Area DOM (Document object).
    var oDOM = oEditor.EditorDocument;
    var iLength;
    // The are two diffent ways to get the text (without HTML markups).
    // It is browser specific.
    iLength = oDOM.body.innerText.length;
        
}


document.observe("dom:loaded", function() {

    $$('.addtag').invoke('observe', 'click', showTag);
    var store = null;
    // window.onload = function() {
       
    function showTag(event) {
        //get position
        var element = event.element();
        var parent = element.ancestors();
        var pos = element.cumulativeOffset();
        var width  = element.getWidth();
        var i;
        var questiontype;

        for(i = 0; i < parent.length; i++) {
            if(parent[i].hasClassName('questiontype')) {
                questiontype = parent[i];
            }
        }

        var siblings = new Array();
        siblings = questiontype.siblings();
        var tagbox = null;

        //alert(siblings.length);
        for(i = 0; i < siblings.length; i++) {

            if(siblings[i].hasClassName('tagboxklass')) {

                tagbox = siblings[i];
            //      alert(tagbox);
            }


        }
        if(tagbox == null) {
            siblings = questiontype.childElements();

            for(i = 0; i < siblings.length; i++) {

                if(siblings[i].hasClassName('tagboxklass')) {
                    tagbox = siblings[i];
                }


            }
        }
        tagbox.setStyle({

            display: 'block',
            position: 'absolute',
            fontSize: '12px',
            left: (pos.left-200) + "px",
            top : (pos.top-500)+ "px"
        });
        tagbox.show();
          
    // $('tagTextfield').activate();
         
    }

    $$('.closetagbox').invoke('observe', 'click', closeTag);
    function closeTag(event) {
        var element = event.element();
        var parent = element.ancestors();

        var tagbox;
        var tagcorner;
        var i;
        for(i = 0; i < parent.length; i++) {

            if(parent[i].hasClassName('tagcornerclass')) {
                tagcorner = parent[i]

            }
            if(parent[i].hasClassName('tagboxklass')) {
                tagbox = parent[i]

            }



        }
        tagbox.setStyle({

            display: 'none'
        });
        tagcorner.setStyle({
            'background-color': '#e8eefa'
        });
        tagbox.hide();
        $('autoCompletetag').enable();
        $('autoCompletetag').activate();


    }


    $('addtopic').observe('click', function() {
        //get position
        var pos = $('addtag').cumulativeOffset();
        var width  = $('addtag').getWidth();
        //alert(pos.left)

        $('newtopicbox').setStyle({

            display: 'block',
            position: 'absolute',
            fontSize: '12px',
            left: (pos.left-200) + "px",
            top : (pos.top-500)+ "px"
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
});
//   }

//     $('articleform').request({
//        onSuccess: function(){ alert('Form data saved!') }
//      })


jQuery(document).ready(function(){
jQuery(".rightsideboxes").draggable({greedy:true,  activeClass: 'ui-state-hover', revert:'invalid'});
    jQuery(".droppable").droppable({
      drop: function() {
            }
    });


       jQuery.updnWatermark.attachAll();
   
});