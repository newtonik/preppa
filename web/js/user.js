function onChangeLogin(response) {
    if(response.count == "0") {
        $('usernamestatus').setStyle(
        {
          color: 'green',
          'font-style': 'bold'
        }
        );
        $('usernamestatus').update("Available!");
    }
    else
        {
                $('usernamestatus').setStyle(
        {
          color: 'red',
          'font-style': 'bold'
        }
        );
            $('usernamestatus').update("Taken!");
        }
}