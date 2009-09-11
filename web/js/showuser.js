jQuery(document).ready(function(){
jQuery("ul.css-tabs").tabs("div.realpanes > div", {
  onBeforeClick: function() {

        this.getCurrentPane().addClass("current");
  }
});

jQuery('tr').click(function () {
  $(this).toggleClass('highlight_row');
});

});


function DoClick(theUrl) {
    document.location.href = theUrl;

}