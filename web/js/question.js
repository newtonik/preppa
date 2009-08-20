
document.observe("dom:loaded", function() {

    //$('questiontypes').disable()

    $('QuestiontypeSelect').disable();
// $('multiple-question').hide();

//Tapestry.activateZone("questionzone", "../new.questiontypeselect:internalEvent");
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
    var eBlocks = new Array();
    var i;
    if(response.type == "multichoice") {
        
        eBlocks = $$(".questiontype");
        for(i = 0; i < eBlocks.length; i++) {
            eBlocks[i].hide();
         
        }
        $('multichoice').addClassName("current");
        $('multichoice').show();
        $('multichoice').highlight();
        document.title = "Create New MultiChoice";
    }
    if(response.type == "longpassage") {
         eBlocks = $$(".questiontype");
        for(i = 0; i < eBlocks.length; i++) {
            eBlocks[i].hide();
         
        }
        $('shortdualpassage').addClassName("current");
        $('longpassage').show();
        $('longpassage').highlight();
        document.title = "Create Long Passage";
    }
    if(response.type == "longdualpassage") {
         eBlocks = $$(".questiontype");
        for(i = 0; i < eBlocks.length; i++) {
            eBlocks[i].hide();
         
        }
        $('shortdualpassage').addClassName("current");
        $('longdualpassage').show();
        $('longdualpassage').highlight();
        document.title = "Create Long Dual Passage";

    }
    if(response.type == "shortpassage") {
         eBlocks = $$(".questiontype");
        for(i = 0; i < eBlocks.length; i++) {
            eBlocks[i].hide();
         
        }
        $('shortdualpassage').addClassName("current");
        $('shortpassage').show();
        $('shortpassage').highlight();
        document.title = "Create Short Passage";
    }
    if(response.type == "shortdualpassage") {
         eBlocks = $$(".questiontype");
        for(i = 0; i < eBlocks.length; i++) {
            eBlocks[i].hide();
         
        }
        $('shortdualpassage').addClassName("current");

        $('shortdualpassage').show();
        $('shortdualpassage').highlight();
        document.title = "Create Short Dual Passage";
    }
     if(response.type == "gridin") {
         eBlocks = $$(".questiontype");
        for(i = 0; i < eBlocks.length; i++) {
            eBlocks[i].hide();

        }
        $('gridin').addClassName("current");

        $('gridin').show();
        $('gridin').highlight();
        document.title = "New Gridin";
    }
//alert(response.type);
}


document.observe(Tapestry.ZONE_UPDATED_EVENT, function() {
    alert("update event");
});