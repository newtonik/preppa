    function countCharacters() {
        // Get the editor instance that we want to interact with.
        var oEditor = FCKeditorAPI.GetInstance('body');
        // Get the Editor Area DOM (Document object).
        var oDOM = oEditor.EditorDocument;
        var iLength;
        // The are two diffent ways to get the text (without HTML markups).
        // It is browser specific.
        iLength = oDOM.body.innerText.length;
        
     }

//     var url = '../show.voteup';
//
//     $('voteup').observe('click', function() {
//     new Ajax.Request(url, {
//         method: 'post',
//         onSuccess: function(response) {
//              $('voteupZone').update(response.vote);
//               $('voteupZone').setStyle({
//                    backgroundColor: '#FFFF5C'
//            });
//         }
//     });
//     });

     function onChangeTestsubject(response) {
         
         //alert(response.testsubject);
         $('result1').update(response.testsubject);
         $('result1').setStyle({
            backgroundColor: '#FFFF5C'
            });


     }

//     $('articleform').request({
//        onSuccess: function(){ alert('Form data saved!') }
//      })