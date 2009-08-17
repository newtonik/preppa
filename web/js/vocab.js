$('flaglink').observe('click', function() {
    //get position
    var pos = $('main').cumulativeOffset();


    $('votebox').setStyle({

        // display: 'block',
      position: 'absolute',
        fontSize: '12px'
    });
    $('votebox').show();
     $('votebox').makePositioned();
      $('votebox').focus();
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