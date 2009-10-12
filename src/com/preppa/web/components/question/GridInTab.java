  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.components.question;

import com.preppa.web.entities.Gridin;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;

/**
 *
 * @author Jan Jan
 */
@IncludeJavaScriptLibrary(value = {"context:js/jquery-1.3.2.js", "context:js/passagetab.js"})
public class GridInTab {
@Parameter(required=true)
private Gridin question;
@Property
private Integer id;

@SetupRender
void setDefault() {
    id = question.getId().intValue();
}
}
