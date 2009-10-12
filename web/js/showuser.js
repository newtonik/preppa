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
    $('sddm').hide();
document.observe("dom:loaded", function() {


    $$("a.tabbutton").invoke("observe", "click", function() {
            $('sddm').hide();
        
    });
     $("getquestions").observe("click", function() {
            $('sddm').show();
   
    });
//    $('getquestions').observe('click', function() {
//        $('sddm').show();
//
//    });
});

function showmultiple() {
    $('multiplezone').setStyle({
        display:'block'
    })
}