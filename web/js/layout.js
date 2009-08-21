   
    Tapestry.activateZone = function ( zoneId, url ) {
            var zoneManager = Tapestry.findZoneManagerByZoneId( zoneId );
            if ( zoneManager != null ) {
                    zoneManager.updateFromURL( url );
                    
            }
    };
    Tapestry.findZoneManagerByZoneId = function( zoneId ) {
        var zoneElement = $(zoneId);
        if (!zoneElement) {
                Tapestry.ajaxError("Unable to locate Ajax Zone '#{id}' for dynamic update.", { id:zoneId});
                return null;
        }
        var manager = $T(zoneElement).zoneManager;
        if (!manager) {
                Tapestry.ajaxError("Ajax Zone '#{id}' does not have an associated Tapestry.ZoneManager object.", { id :zoneId });
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
     var store = null;
    // window.onload = function() {
       
        $('addtag').observe('click', function() {
            //get position
            var pos = $('addtag').cumulativeOffset();
            var width  = $('addtag').getWidth();
            
            $('newtagbox').setStyle({

                display: 'block',
                position: 'absolute',
                fontSize: '12px',
                left: (pos.left-200) + "px",
                top : (pos.top-500)+ "px"
            });
            $('newtagbox').show();
          
            $('tagTextfield').activate();
         
        });

        $('closetag').observe('click', function() {

                $('newtagbox').setStyle({

                display: 'none'
                });
                $('tagcorner').setStyle({
                    'background-color': '#e8eefa'
                });
                 $('newtagbox').hide();
                 $('autoCompletetag').enable();
                 $('autoCompletetag').activate();
  //               Event.observe('tagform', 'submit', function(event) {
//                 $('tagform').request( {
//                     onSuccess: function(response) {
//                        alert(response.evalJSON().content);
//                        $('formzone').update(response.evalJSON().content);
//                     }
//                 })
//                });
//            Tapestry.activateZone( 'formzone', '../create.tagform' );
        });


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