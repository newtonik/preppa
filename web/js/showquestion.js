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