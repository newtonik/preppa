  
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
import com.preppa.web.data.TagDAO;
import com.preppa.web.entities.Prompt;
import com.preppa.web.entities.Tag;
import com.preppa.web.utils.ContentType;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Jan Jan
 */
public class ViewEssayPrompt {
    @Property
    private Prompt prompt;
    @Inject
    private PromptDAO promptDAO;
    @Inject
    private TagDAO tagDAO;
    @Property
    private List<Prompt> promptList;
    @Property
    private List<Tag> alltags = new ArrayList<Tag>();
    @Property
    private boolean show;
    // The String passed in
    @Property
    private String type;
    @Property
    private ContentType contType;


    void onActivate() {
        System.out.println("I'm here too");
        List<Prompt> allprompts = promptDAO.findAll();
        Prompt temp = null;
        List<Tag> tTemp = null;
        for (int i = 0; i < allprompts.size(); i++) {
            temp = allprompts.get(i);
            tTemp = temp.getTaglist();
            for (int j = 0; j < tTemp.size(); j++) {
                if (alltags.contains(tTemp.get(j)) == false) {
                    alltags.add(tTemp.get(j));
                }
            }
        }
        //this.type = "";
    }

    Object onActivate(String type) {
        System.out.println("I'm here too");
        List<Prompt> allprompts;

        if (type.contains("Respond")) {
            System.out.println("Respond");
            this.type = "Respond to Statement";
            allprompts = promptDAO.findByTopic("Respond to Statement");
            show = true;
        }
        else if (type.contains("Choosing")) {
            System.out.println("Choose");
            this.type = "Choosing Between Contrasting Statements";
            allprompts = promptDAO.findByTopic("Choosing Between Contrasting Statements");
            show = true;
        }
        else if (type.contains("Complete")) {
            System.out.println("Complete");
            this.type = "Complete a Statement";
            allprompts = promptDAO.findByTopic("Complete a Statement");

            show = true;
        }
        else {
            allprompts = promptDAO.findAll();
            show = true;
        }

        //promptList = allprompts;
        promptList = new ArrayList<Prompt>();
        for (int i = 0; i < allprompts.size(); i++) {
            if (allprompts.get(i).getEssays().size() >= 1) {
                promptList.add(allprompts.get(i));
            }
        }

        /*Prompt temp = null;
        List<Tag> tTemp = null;
        for (int i = 0; i < allprompts.size(); i++) {
            temp = allprompts.get(i);
            tTemp = temp.getTaglist();
            for (int j = 0; j < tTemp.size(); j++) {
                if (alltags.contains(tTemp.get(j)) == false) {
                    alltags.add(tTemp.get(j));
                }
            }
        }*/

        return null;
    }
}
