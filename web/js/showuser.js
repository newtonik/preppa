jQuery(document).ready(function(){
    jQuery("ul.css-tabs").tabs("div.realpanes > div", {
        current: 'current'

    }).history();

    jQuery('tr').click(function () {
        $(this).toggleClass('highlight_row');
    });

});


function DoClick(theUrl) {
    document.location.href = theUrl;

}

document.observe("dom:loaded", function() {
    $('showq').observe('click', function() {
        alert("clicked");

        $('multiplezone').setStyle({
            display:'block'
        })
    });



});

function showmultiple() {
    $('multiplezone').setStyle({
        display:'block'
    })
}