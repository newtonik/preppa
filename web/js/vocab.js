$('flaglink').observe('click', function() {
    //get position
    var pos = $('main').cumulativeOffset();


    $('votebox').setStyle({

        // display: 'block',
        position: 'absolute',
        fontSize: '12px',
        left: (pos.left-20) + "px",
        top : (pos.top-150)+ "px"
    });
    $('votebox').show();
     $('votebox').clonePosition()

    $('flagfield').activate();
    //Effect.ScrollTo('header');
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