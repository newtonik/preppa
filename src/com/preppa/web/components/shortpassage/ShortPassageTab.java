/*
 * Preppa, Inc.
 * 
 * Copyright 2009. All rights reserved.
 * 
 * $Id$
 */


package com.preppa.web.components.shortpassage;

import com.preppa.web.entities.ShortPassage;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;

/**
 *
 * @author newtonik
 */
@IncludeJavaScriptLibrary(value = {"context:js/jquery-1.3.2.js", "context:js/passagetab.js"})
public class ShortPassageTab {
@Parameter(required=true)
private ShortPassage passage;
@Property
private Integer id;

@SetupRender
void setDefault() {
    id = passage.getId();
}
}
