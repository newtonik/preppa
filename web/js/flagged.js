 function onChangeFlag(response) {
     // var json = response.evalJSON(true)
     //alert(response.content);
     $('articleviewzone').setStyle(
    {
        display: 'none'
    });
      $('articleviewzone').highlight();
     $('articleviewzone').update(response.content);
     $('articleviewzone').appear({duration:0.5});
     //$('articleviewzone').appear({ duration: 3.0});

 }