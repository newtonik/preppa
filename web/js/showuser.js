jQuery(document).ready(function(){
jQuery("ul.css-tabs").tabs("div.realpanes > div", {
  onBeforeClick: function() {

        this.getCurrentPane().addClass("current");
  }
});

});