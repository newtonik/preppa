
     window.onload = function() {

        //$('votebox').hide();
        $('flaglink').observe('click', function() {
            $('votebox').setStyle({
                display: 'block',
                fontSize: '12px'
            });
            $('votebox').show();
        });

        $('cancelflag').observe('click', function() {
                 $('votebox').hide();

        });
        $('removeflagbox"').observe('click', function() {
                 $('votebox').hide();
                 $('votebox').remove();

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
    
 $('removeflagbox"').observe('click', function() {
           $('votebox').hide();
                 $('votebox').remove();


});