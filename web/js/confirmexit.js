  
  /* 
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

window.onbeforeunload = askConfirm;
function askConfirm(){
        return "You have unsaved changes.";
}