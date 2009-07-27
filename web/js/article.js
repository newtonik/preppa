
     window.onload = function() {
        //$('votebox').hide();
        $('flaglink').observe('click', function() {
            //get position
            var pos = $('flagpass').cumulativeOffset();
            var width  = $('flagpass').getWidth();
            //alert(pos.left)
            
            $('votebox').setStyle({

                display: 'block',
                position: 'absolute',
                fontSize: '12px',
                left: (pos.left-200) + "px",
                top : (pos.top-250)+ "px"
            });
            $('votebox').show();
        });

        $('cancelflag').observe('click', function() {
                 $('votebox').hide();

        });
        $('closeflag').observe('click', function() {
               $('votebox').hide();
            
        });
        
     }

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
    
 