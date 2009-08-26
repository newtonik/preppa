// select all desired input fields and attach tooltips to them
jQuery(document).ready(function(){
jQuery("#loginform :input").tooltip({

    // place tooltip on the right edge
    position: ['center', 'right'],

    // a little tweaking of the position
    offset: [-2, 10],

    // use a simple show/hide effect
    effect: 'toggle',

    // custom opacity setting
    opacity: 0.7
});
});