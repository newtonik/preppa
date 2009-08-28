/*
 * Preppa, Inc.
 * 
 * Copyright 2009. All rights reserved.
 * 
 * $Id$
 */


package com.preppa.web.components.shortpassage;

import com.preppa.web.entities.ShortDualPassage;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;

/**
 *
 * @author newtonik
 */
public class ShortDualPassageTab {
@Parameter(required=true)
private ShortDualPassage passage;
@Property
private Integer id;

@SetupRender
void setDefault() {
    id = passage.getId();
}
}
