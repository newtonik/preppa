  
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
import com.preppa.web.data.VoteDAO;
import com.preppa.web.entities.Prompt;
import com.preppa.web.entities.Tag;
import com.preppa.web.utils.Constants;
import com.preppa.web.utils.ContentType;
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
    @Inject
    private TagDAO tagDAO;
    @Property
    private List<Prompt> promptList;
    @Property
    private List<Tag> alltags = new ArrayList<Tag>();
    @Property
    private boolean show;
    @Inject
    private VoteDAO voteDAO;
    // The String passed in
    @Property
    private String type;
    @Property
    private boolean approved;
    @Property
    private ContentType contType;
    //Boolean to decide how far trail of links back goes.
    @Property
    private boolean threeLevels;

    public String getApp() {
        if (approved == true) {
            return "Approved";
        }
        else {
            return "Awaiting";
        }
    }

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
        this.type = type;
        List<Prompt> allprompts = promptDAO.findAll();
        contType = ContentType.Prompt;
        threeLevels = true;

        if (type.contains("Approve") || type.contains("true")) {
            approved = true;
            System.out.println("Approve");
        }
        else if (type.contains("Awaiting") || type.contains("false")) {
            approved = false;
        }

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
        else if (type.contains("Approve")) {
            System.out.println("Approve");
            this.type = "Approved";
            allprompts = promptDAO.findAll();
            show = true;
            threeLevels = false;
        }
        else if (type.contains("Awaiting")) {
            System.out.println("Awaiting");
            this.type = "Awaiting";
            allprompts = promptDAO.findAll();
            show = true;
            threeLevels = false;
        }
        else {
            allprompts = promptDAO.findAll();
        }

        this.promptList = new ArrayList<Prompt>();
        if (approved == true) {
            for (int i = 0; i < allprompts.size(); i++) {
                if (voteDAO.findVoteByContentId(contType, allprompts.get(i).getId()) >= Constants.getApprovalThreshhold()) {
                    promptList.add(allprompts.get(i));
                }
            }
        }
        else {
            for (int i = 0; i < allprompts.size(); i++) {
                if (voteDAO.findVoteByContentId(contType, allprompts.get(i).getId()) < Constants.getApprovalThreshhold()) {
                    promptList.add(allprompts.get(i));
                }
            }
        }

        return null;
    }
}
