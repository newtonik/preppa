jQuery(document).ready(function(){
    var url = document.location;
    var test = url.toString();

jQuery("ul.css-tabs a:contains('Passage')").addClass("current");
    if(test.indexOf("show") > 0) {
        jQuery("ul.css-tabs a").removeClass("current");
        jQuery("ul.css-tabs a:contains('Passage')").addClass("current");
        jQuery("ul.css-tabs a:contains('Article')").addClass("current");
        jQuery("ul.css-tabs a:contains('Question')").addClass("current");
    }
    if(test.indexOf("talk") > 0) {
        jQuery("ul.css-tabs a").removeClass("current");
        jQuery("ul.css-tabs a:contains('Discuss')").addClass("current");
    }
    if(test.indexOf("revisions") > 0) {
        jQuery("ul.css-tabs a").removeClass("current");
        jQuery("ul.css-tabs a:contains('History')").addClass("current");
    }

    // setup ul.tabs to work as tabs for each div directly under div.panes
   // jQuery("css-tabs").tabs("div.panes");
});