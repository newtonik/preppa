  window.onload = function() {


  }
$('downimg').observe('click', function() {
    var path = window.location.pathname;
    var end = path.lastIndexOf("/");
    var url = path.substring(0,end);
    url = url + ".voteseal.votedown?t:ac=1";
    new Ajax.Request(url, {
    method: 'post',
    onSuccess: function(transport) {
        var json = transport.responseText.evalJSON();
        alert(json.count);
        if(json.voted == "true") {
            $('countid').update(json.count);
            $('downimg').hide();
            $('downimgsel').show()
        }
    }
});
});


$('upimg').observe('click', function() {

    var path = window.location.pathname;
    var end = path.lastIndexOf("/");
    var url = path.substring(0,end);
    url = url + ".voteseal.voteup?t:ac=1";
    new Ajax.Request(url, {
    method: 'post',
    onSuccess: function(transport) {
        var json = transport.responseText.evalJSON();
        if(json.voted == "true") {
            $('countid').update(json.count);
            $('upimg').hide();
            $('upimgsel').show();
        }
    }
});

    });


$('downimgsel').observe('click', function() {
    var path = window.location.pathname;
    var end = path.lastIndexOf("/");
    var url = path.substring(0,end);
    url = url + ".voteseal.votedown?t:ac=1";
    new Ajax.Request(url, {
    method: 'post',
    onSuccess: function(transport) {
        var json = transport.responseText.evalJSON();
    if(json.voted == "false") {
        $('countid').update(json.count);
        $('downimgsel').hide();
        $('downimg').show();
    }
    }
});
});

$('upimgsel').observe('click', function() {
    var path = window.location.pathname;
    var end = path.lastIndexOf("/");
    var url = path.substring(0,end);
    url = url + ".voteseal.voteup?t:ac=1";
    new Ajax.Request(url, {
    method: 'post',
    onSuccess: function(transport) {
        var json = transport.responseText.evalJSON();
     if(json.voted == "false") {
        $('countid').update(json.count);
         $('upimgsel').hide();
        $('upimg').show();
     }
    }
});


});