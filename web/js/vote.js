window.onload = function() {


    }
$$('.downimg').invoke('observe', 'click', voteDown);
function voteDown(event) {
    var element = event.element();
    var parent = element.ancestors()[0];
    var parentname = parent.identify();



    var siblings = new Array();
    var countelement;
    var selectedimage;
    var hform;
    siblings = element.siblings();

    for(i = 0; i < siblings.length; i++) {

        if(siblings[i].hasClassName('count')) {
            countelement = siblings[i];
        }
        if(siblings[i].hasClassName('downimgsel'))
        {
            selectedimage = siblings[i];
        }
        if(siblings[i].hasClassName('hiddenform')) {
            hform = siblings[i];
        }
    }

    var formelements = hform.getElements();
    var hId;
    var hTypeId
    for(i = 0; i < formelements.length; i++) {
        if(formelements[i].hasClassName('hiddenId'))
        {
            hId = formelements[i];
        }
        if(formelements[i].hasClassName('hiddentypeid'))
        {
            hTypeId = formelements[i];
        }
    }
    var path = window.location.pathname;
    var end = path.lastIndexOf("/");
    var url = path.substring(0,end);
    url = url + ".voteseal.votedown?t:ac=1";
    new Ajax.Request(url, {
        method: 'post',
        parameters: {
            contId:(hId.value),
            contTypeId:(hTypeId.value),
            count: (countelement.innerHTML)
        },
        onSuccess: function(transport) {
            var json = transport.responseText.evalJSON();
            //alert(json.count);
            if(json.voted == "true") {
                countelement.update(json.count);
                element.hide();
                selectedimage.show()
            }
            else if(json.voted == "false")
            {
                alert("Sorry! You have already voted.")
            }
        }
    });
}

$$('.upimg').invoke('observe', 'click', voteUp);
function voteUp(event) {
    var element = event.element();
    var parent = element.ancestors()[0];
    var parentname = parent.identify();



    var siblings = new Array();
    var countelement;
    var selectedimage;
    var hform;
    siblings = element.siblings();

    for(i = 0; i < siblings.length; i++) {

        if(siblings[i].hasClassName('count')) {
            countelement = siblings[i];
        }
        if(siblings[i].hasClassName('upimgsel'))
        {
            selectedimage = siblings[i];
        }
        if(siblings[i].hasClassName('hiddenform')) {
            hform = siblings[i];
        }
    }

    var formelements = hform.getElements();
    var hId;
    var hTypeId
    for(i = 0; i < formelements.length; i++) {
        if(formelements[i].hasClassName('hiddenId'))
        {
            hId = formelements[i];
        }
        if(formelements[i].hasClassName('hiddentypeid'))
        {
            hTypeId = formelements[i];
        }
    }
    var path = window.location.pathname;
    var end = path.lastIndexOf("/");
    var url = path.substring(0,end);
    url = url + ".voteseal.voteup?t:ac=1";
    new Ajax.Request(url, {
        method: 'post',
        parameters: {
            contId:(hId.value),
            contTypeId:(hTypeId.value),
            count: (countelement.innerHTML)
        },
        onSuccess: function(transport) {
            var json = transport.responseText.evalJSON();
            //alert(json.count);
            if(json.voted == "true") {
                countelement.update(json.count);
                element.hide();
                selectedimage.show()
            }
            else if(json.voted == "false")
            {
                alert("Sorry! You have already voted.")
            }
        }
    });
}
/*
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

*/