
   //  window.onload = function() {
        //$('votebox').hide();
        $('flaglink').observe('click', function() {
            //get position
            var pos = $('flagpass').cumulativeOffset();
            var width  = $('flagpass').getWidth();
           
            $('votebox').setStyle({

                display: 'block',
                position: 'absolute',
                fontSize: '12px',
                left: (pos.left-200) + "px",
                top : (pos.top-250)+ "px"
            });
            $('votebox').show();
             $('closeflag').observe('click', function() {
               $('votebox').hide();

        });
        });

        $('cancelflag').observe('click', function() {
                 $('votebox').hide();

        });
        $('closeflagblock').observe('click', function() {
               $('votebox').setStyle( {
                 display: 'none'
               })
               $('votebox').hide();
            
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
    
 