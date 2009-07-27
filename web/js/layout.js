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
         
         //alert(response.testsubject);
         $('result1').update(response.testsubject);
         $('result1').setStyle({
            backgroundColor: '#FFFF5C'
            });


     }

     window.onload = function() {

        $('addtag').observe('click', function() {
            //get position
            var pos = $('addtag').cumulativeOffset();
            var width  = $('addtag').getWidth();
            //alert(pos.left)

            $('newtagbox').setStyle({

                display: 'block',
                position: 'absolute',
                fontSize: '12px',
                left: (pos.left-200) + "px",
                top : (pos.top-500)+ "px"
            });
            $('newtagbox').show();
          
            $('tagTextfield').activate();
            $('autoCompletetag').disable();
        });

        $('closetagbox').observe('click', function() {
                 $('newtagbox').hide();
                 $('autoCompletetag').enable();
                 $('autoCompletetag').activate();

        });

     }

//     $('articleform').request({
//        onSuccess: function(){ alert('Form data saved!') }
//      })