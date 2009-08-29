/*
 * Preppa, Inc.
 *
 * Copyright 2009. All rights
reserved.
 *
 * $Id$
 */
package com.preppa.web.pages.contribution.prompt;

import com.preppa.web.data.EssayDAO;
import com.preppa.web.entities.Essay;
import com.preppa.web.entities.Prompt;
import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Jan Jan
 */
public class Index {

    @Property
    private List<Essay> newest;
    @Property
    private Essay newEssay;
    @Property
    private Essay featured;
    @Inject
    private EssayDAO essayDAO;
    private int featureInt = 1;
   

    /* Setting featured essay
     *
     * */
    void onActivate() {
        featured = essayDAO.findById(featureInt);

        if (featured == null) {
            featured = new Essay();
            Prompt temp = new Prompt();
            temp.setQuote("");
            temp.setQuestion("");
            featured.setBody("");
            featured.setPrompt(temp);
        } else {
            System.out.println("Featured is " + featured);
            System.out.println("Prompt is " + featured.getPrompt());
            System.out.println("");
        }

        newest = essayDAO.findAllByNewest();
    }
}
