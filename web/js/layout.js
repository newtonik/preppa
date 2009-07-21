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

     function onChangeTestsubject(response) {
     
         var result = response;
         $('result1').value = result;

     }