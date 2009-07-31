
document.observe("dom:loaded", function() {

        //$('questiontypes').disable()

$('QuestiontypeSelect').disable()
 $('multiple-question').hide();

//Tapestry.activateZone("questionzone", "../new.questiontypeselect:internalEvent");
});

function onChangeTestsubject(response) {
    //alert(response);
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
           populateQuestionsTypes("QuestiontypeSelect", c, ids, vals)
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
    $('multiple-question').highlight();
    $('multiple-question').show();
    
    
}

document.observe(Tapestry.ZONE_UPDATED_EVENT, function() {
            alert("update event");
    });