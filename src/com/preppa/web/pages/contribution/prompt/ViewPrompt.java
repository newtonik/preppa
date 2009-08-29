  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.pages.contribution.prompt;

import com.preppa.web.data.PromptDAO;
import com.preppa.web.entities.Prompt;
import com.preppa.web.entities.Tag;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Jan Jan
 */
public class ViewPrompt {
    @Property
    private Prompt prompt;
    @Inject
    private PromptDAO promptDAO;
    @Property
    private List<Prompt> promptList;
    @Property
    private List<Tag> alltags = new ArrayList<Tag>();
    @Property
    private boolean show;

    /*void onActivate() {
        System.out.println("I'm here too");
        promptList = promptDAO.findAll();
    }*/

    Object onActivate(String type) {

        if (type.contains("Respond")) {
            System.out.println("Respond");
            promptList = promptDAO.findByTopic("Respond to Statement");
            show = true;
        }
        else if (type.contains("Choose")) {
            System.out.println("Choose");
            promptList = promptDAO.findByTopic("Choosing Between Contrasting Statements");
            show = true;
        }
        else if (type.contains("Complete")) {
            System.out.println("Complete");
            promptList = promptDAO.findByTopic("Complete a Statement");
            
            show = true;
        }
        else {
            promptList = promptDAO.findAll();
        }
        return null;
    }
}
