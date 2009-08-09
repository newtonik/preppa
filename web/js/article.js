
//  window.onload = function() {
//$('votebox').hide();
$('flaglink').observe('click', function() {
    //get position
    var pos = $('flagpass').cumulativeOffset();
    var width  = $('flagpass').getWidth();
           
    $('votebox').setStyle({

        // display: 'block',
        position: 'absolute',
        fontSize: '12px',
        //    left: (pos.left-200) + "px",
        top : (pos.top-400)+ "px"
    });
    $('votebox').show();
    Effect.ScrollTo('footer');
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
    
