




document.observe("dom:loaded", function() {


    $('QuestiontypeSelect').disable();
    //alert($('vhidden').value);
    var res ={type:$('vhidden').value, title: null };
    showQuestionBlock(res);
       
   });

function onChangeTestsubject(response) {
    //aleart(response);
    var ids = new Object();
    ids = response.ids;
    if (ids != null) {
    //alert(ids[0]);
    }
    var vals = new Object();
    vals = response.qt;
    //var idsarray = ids.split(',');
    //alert(idarray[1]);
    if (vals != null) {
    //alert(vals[0]);
    }
    var c = new Object();
    c = response.counter;
    var selectId = document.getElementById("QuestiontypeSelect");
    selectId.innerHTML = "";
    if (c != undefined) {
        populateQuestionsTypes("QuestiontypeSelect", c, ids, vals);
    }

}
function populateQuestionsTypes(id, c, ids, vs) {
    var s = document.getElementById(id);
    var len = c.length;
    var count = 0;
    var x = 0;
    var optn = document.createElement("OPTION");
    optn.text = "";
    optn.value = "";
    s.options.add(optn);
    c.each(function(x) {
        var optn = document.createElement("OPTION");
        optn.text = vs[x];
        optn.value = ids[x];
        s.options.add(optn);
    });
    $('QuestiontypeSelect').enable();
}

function onChangeQuestiontype(response) {

    showQuestionBlock(response);
}

function showQuestionBlock( vblock) {
    var eBlocks = new Array();
    var i;
    if(vblock.type == "multichoice") {
        
        eBlocks = $$(".questiontype");
        for(i = 0; i < eBlocks.length; i++) {
            eBlocks[i].hide();
            eBlocks[i].removeClassName("current");
         
        }
        $('multichoice').addClassName("current");
        $('multichoice').show();
        $('multichoice').highlight();
        if(vblock.title != null) {
            document.title =vblock.title;
        }
        else {
            document.title = "Create New MultiChoice";
        }
    }
    if(vblock.type == "longpassage") {
        eBlocks = $$(".questiontype");
        for(i = 0; i < eBlocks.length; i++) {
            eBlocks[i].hide();
            eBlocks[i].removeClassName("current");
        }
        $('longpassage').addClassName("current");
        $('longpassage').show();
        $('longpassage').highlight();
        document.title = "Create Long Passage";
    }
    if(vblock.type == "longdualpassage") {
        eBlocks = $$(".questiontype");
        for(i = 0; i < eBlocks.length; i++) {
            eBlocks[i].hide();
            eBlocks[i].removeClassName("current");
        }
        $('longdualpassage').addClassName("current");
        $('longdualpassage').show();
        $('longdualpassage').highlight();
        document.title = "Create Long Dual Passage";

    }
    if(vblock.type == "shortpassage") {
        eBlocks = $$(".questiontype");
        for(i = 0; i < eBlocks.length; i++) {
            eBlocks[i].hide();
            eBlocks[i].removeClassName("current");
        }
        $('shortpassage').addClassName("current");
        $('shortpassage').show();
        $('shortpassage').highlight();
        document.title = "Create Short Passage";
    }
    if(vblock.type == "shortdualpassage") {
        eBlocks = $$(".questiontype");
        for(i = 0; i < eBlocks.length; i++) {
            eBlocks[i].hide();
         
        }
        $('shortdualpassage').addClassName("current");

        $('shortdualpassage').show();
        $('shortdualpassage').highlight();
        document.title = "Create Short Dual Passage";
    }
    if(vblock.type == "gridin") {
        eBlocks = $$(".questiontype");
        for(i = 0; i < eBlocks.length; i++) {
            eBlocks[i].hide();
            eBlocks[i].removeClassName("current");

        }
        $('gridin').addClassName("current");

        $('gridin').show();
        $('gridin').highlight();
        document.title = "New Gridin";
    }
    if(vblock.type == "newprompt") {
        eBlocks = $$(".questiontype");
        for(i = 0; i < eBlocks.length; i++) {
            eBlocks[i].hide();
            eBlocks[i].removeClassName("current");

        }
        $('prompt').addClassName("current");

        $('prompt').show();
        $('prompt').highlight();
        document.title = "New Prompt";
    }

     if(vblock.type == "improving") {
            eBlocks = $$(".questiontype");
            for(i = 0; i < eBlocks.length; i++) {
                eBlocks[i].hide();
                eBlocks[i].removeClassName("current");

            }
            $('improving').addClassName("current");

            $('improving').show();
            $('improving').highlight();
            document.title = "New Improving Paragraph";
        }


//alert(response.type);
}


document.observe(Tapestry.ZONE_UPDATED_EVENT, function() {
    alert("update event");
});