/*
 * Preppa, Inc.
 * 
 * Copyright 2009. All rights reserved.
 * 
 * $Id$
 */
package com.preppa.web.components;

import com.preppa.web.entities.ReviewComment;
import com.preppa.web.entities.User;
import java.util.List;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.chenillekit.tapestry.core.components.Editor;

/**
 *
 * @author newtonik
 */
public class RComment {

    @ApplicationState
    private User user;
    @Parameter(required = true)
    @Property
    private List<ReviewComment> comments;
    @Property
    private ReviewComment comment;
    @Property
    @Parameter
    private String newcomment;
    @Component(parameters = {"value=fComment", "toolbarSet=Basic"})
    private Editor pass1;
    @Component
    private Form commentform;
    @Property
    private String fComment;

    Boolean onValidateFormFromCommentForm() {
         System.out.println("I validation here with comment " + fComment);
        if (fComment == null) {
            commentform.recordError(pass1, "You need to Enter a review");
        }
        else
        {
             newcomment = fComment;
        }
        if (user == null) {
            commentform.recordError("You must be logged in to post a review");
        }

        return true;
    }
}
