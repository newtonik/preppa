document.observe("dom:loaded", function() {

    $$('.addtag').invoke('observe', 'click', showTag);

    var store = null;
    // window.onload = function() {

    function showTag(event) {
        //get position
        var element = event.element();
        var pos = element.cumulativeOffset();
        var width  = element.getWidth();
        var i;
        var questiontype;

        var tagbox = $('tagcomp');

        tagbox.setStyle({

            display: 'block',
            position: 'absolute',
            fontSize: '12px',
            left: (pos.left-300) + "px",
            top : (pos.top-500)+ "px"
        });
        tagbox.show();

    // $('tagTextfield').activate();

    }

    $$('.closetagbox').invoke('observe', 'click', closeTag);
    function closeTag(event) {
        var element = event.element();
        var parent = element.ancestors();

        var tagbox = $('tagcomp');

        tagbox.setStyle({

            display: 'none'
        });
        tagcorner.setStyle({
            'background-color': '#e8eefa'
        });
        tagbox.hide();
        $('autoCompletetag').enable();
        $('autoCompletetag').activate();


    }
});