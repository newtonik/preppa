/*
 * Preppa, Inc.
 * 
 * Copyright 2009. All rights reserved.
 * 
 * $Id$
 */


package com.preppa.web.components.questiontypes.gridin;

import com.preppa.web.entities.Gridin;
import com.preppa.web.entities.GridinAnswer;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.IncludeStylesheet;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.RadioGroup;

/**
 *
 * @author newtonik
 */
@IncludeJavaScriptLibrary(value = {"context:js/gridinanswer.js"})
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
    @Component
    private Form answerform;
    @Component
    private RadioGroup chooseanswer;
    @Property
    private String answertype;

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
