/*
 * Preppa, Inc.
 * 
 * Copyright 2009. All rights reserved.
 * 
 * $Id$
 */


package com.preppa.web.components.gridin;

import com.preppa.web.entities.Gridin;
import com.preppa.web.entities.GridinAnswer;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;

/**
 *
 * @author newtonik
 */
public class NewAnswer {
    @Parameter
    private Gridin question;
    @Property
    private GridinAnswer answer;
    @Property
    private String fAnswer;
    @Property
    private String fDescription;
    @Property
    private String error;

    @SetupRender
    void initialize () {
        if(question == null) {
            error = "Error: Not associated to a question";
        }
        else
        {
            error = "Add an Answer";
        }
    }
    

}
