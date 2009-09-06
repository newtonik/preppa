
jQuery(document).ready(function(){

    //gets the accordion on the pratice setup page
    jQuery("#accordion").tabs("#accordion div.pane", {tabs: 'h3', effect: 'slide'});
    

    jQuery('#randomsubjects').click(toggleSubjects);


    function toggleSubjects() {
     if(jQuery(this).is(':checked'))
     {
         jQuery("#addmathcheck").attr("disabled", true);
         jQuery("#addwritingcheck").attr("disabled", true);
         jQuery("#addcriticalcheck").attr("disabled", true);
     }
     else
     {
         jQuery("#addmathcheck").attr("disabled", false);
         jQuery("#addwritingcheck").attr("disabled", false);
         jQuery("#addcriticalcheck").attr("disabled", false);
     }

     
    }


});

