  
/* 
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */
document.observe("dom:loaded", function() {
    $$('.flaglink').invoke('observe', 'click', function() {
        //get position
        var pos = $$('.mainclass')[0].cumulativeOffset();


        $('votebox').setStyle({

            // display: 'block',
            position: 'absolute',
            fontSize: '12px',
            left: (pos.left-300) + "px",
            top : (pos.top)+ "px"
        });
        $('votebox').show();
        $('votebox').makePositioned();
        $('votebox').focus();
        $('flagfield').activate();
        //Effect.ScrollTo('header');
        //$('votebox').absolutize()


        $('closeflagblock').observe('click', function() {
            $('votebox').hide();

        });
    });

    window.scroll(0,  document.height);
    //$('cancelflag').observe('click', function() {
    //    $('votebox').hide();
    //
    //});
    $('closeflagblock').observe('click', function() {
        $('votebox').setStyle( {
            display: 'none'
        })
        $('votebox').hide();

    });

});