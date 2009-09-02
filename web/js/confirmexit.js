  
  /* 
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   * Found at
   * http://forums.asp.net/p/1014977/2984838.aspx
   */

/*window.onbeforeunload = askConfirm;
function askConfirm(){
        return "You have unsaved changes.";
}*/

var UNLOAD_MSG = "You will lose any unsaved changes!";

var IGNORE_UNLOAD = false;

function doBeforeUnload() {
   if(IGNORE_UNLOAD) return; // Let the page unload

   if(window.event)
      window.event.returnValue = UNLOAD_MSG; // IE
   else
      return UNLOAD_MSG; // FX
}

if(window.body)
   window.body.onbeforeunload = doBeforeUnload; // IE
else
   window.onbeforeunload = doBeforeUnload; // FX

function SetTxtFocus(txtId) {
   var oTxt = document.getElementById(txtId);
   oTxt.focus();
   oTxt.select();
}
