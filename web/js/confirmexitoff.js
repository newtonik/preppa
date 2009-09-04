  
  /* 
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

var UNLOAD_MSG = "You will lose any unsaved changes!";

var IGNORE_UNLOAD = true;

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