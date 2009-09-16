jQuery(document).ready(function(){
    var url = document.location;
    var test = url.toString();
    
    if(test.indexOf("show") > 0) {
        jQuery("ul.css-tabs a").removeClass("current");
        jQuery("ul.css-tabs a:contains('Article')").addClass("current");

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
// jQuery("ul.css-tabs").tabs("div.panes");
});


function onChangeTestsubject(response) {

    //alert(response.testsubject);
    $('result1').update(response.testsubject);
    $('result1').setStyle({
        backgroundColor: '#FFFF5C'
    });


}



function FCKeditor_OnComplete( editorInstance )
{

    fckeditor_word_count(editorInstance);
    editorInstance.Events.AttachEvent('OnSelectionChange', fckeditor_word_count);


}

function fckeditor_word_count(editorInstance) {

    var matches = editorInstance.GetData().replace(/<[^<|>]+?>|&nbsp;/gi,' ').match(/\b/g);
    var count = 0;
    if(matches) {
        count = matches.length/2;
    }

    $('word_count').innerHTML = count + " word" + (count == 1 ? "" : "s");

}

