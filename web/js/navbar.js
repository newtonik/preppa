jQuery(document).ready(function(){

 jQuery("#navbarlogintable").click(function() {

        // perform exposing for the clicked element
        jQuery(this).expose({api: true}).load();

    });

});
