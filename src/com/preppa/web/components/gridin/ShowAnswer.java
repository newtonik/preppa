/*
 * Preppa, Inc.
 * 
 * Copyright 2009. All rights reserved.
 * 
 * $Id$
 */


package com.preppa.web.components.gridin;

import com.preppa.web.data.GridinDAO;
import com.preppa.web.entities.Gridin;
import com.preppa.web.entities.GridinAnswer;
import java.util.List;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author newtonik
 */
public class ShowAnswer {
    @Property
    @Parameter
    private Gridin question;
    @Property
    @Parameter
    private List<GridinAnswer> answers;
    @Property
    private GridinAnswer answer;
    @Inject
    private GridinDAO gridinDAO;

    @SetupRender
    void initiazeComponent() {
        //check if they are already answers
        if(answers == null) {
            //check if a question was passed in.
            if(question != null) {
                question = gridinDAO.findById(question.getId());
                answers = question.getAnswers();
            }
        }
    }

    void setQuestion (Gridin ques) {
        if(ques != null) {
            question = gridinDAO.findById(question.getId());
            answers = question.getAnswers();
        }
    }

}
