  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.pages.contribution.prompt.essay;

import com.preppa.web.data.EssayDAO;
import com.preppa.web.data.FeedBackDAO;
import com.preppa.web.entities.Essay;
import com.preppa.web.entities.FeedBack;
import com.preppa.web.entities.User;
import com.preppa.web.pages.Index;
import java.sql.Timestamp;
import java.util.List;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.springframework.security.annotation.Secured;
//import org.chenillekit.tapestry.core.components.Slider;

/**
 *
 * @author Jan Jan
 */
@Secured("ROLE_USER")
public class CreateFeedBack {
    @ApplicationState
    private User user;
    @Property
    private FeedBack feedback;
    @Inject
    private FeedBackDAO feedbackDAO;
    @Property
    private Essay essay;
    @Inject
    private EssayDAO essayDAO;
    @Component
    private Form feedbackform;
    @Property
    private String comment;
    @Property
    private Integer rating;
    @Property
    @Persist
    private Number rate;
    private Integer pid;
    @Component(parameters = {"value=rate", "min=0", "max=6", "inc=1"})
    private Slider slider1;
    
    void onActivate(int id) {
        if (id > 0) {
            this.essay = essayDAO.findById(id);
            this.pid = id;
            System.out.println("Essay is originally" + essay);
        }
        else {
            System.out.println("ELSE");
            rate = 0;
        }
        System.out.println(slider1);
    }

    Integer onPassivate() {
        return this.pid;

    }

    void onValidateForm() {

    }

    public boolean getIsAuthor() {
        if (this.essay != null) {

            List<FeedBack> list = essay.getFeedBack();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) != null) {
                    if (list.get(i).getUser().getId() == user.getId()) {
                        return true;
                    }
                }
            }

            System.out.println(essay.getUser().getId() + " being compared to " + user.getId());
            return essay.getUser().getId() == user.getId();
        }
        else {
            return true;
        }
    }

    @CommitAfter
    Object onSuccessFromFeedbackForm() {
        Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
        /*this.essay = new Essay();
        essay.setCreatedAt(now);
        essay.setUpdatedAt(now);
        essay.setBody(eBody);
        essay.setUser(user);
        essay.setTaglist(addedTags);
        essay.setUpdatedBy(user);
        System.out.println("pid is" + pid);
        System.out.println("Prompt is " + prompt);
        essay.setPrompt(prompt);
        essayDAO.doSave(essay);*/
        this.feedback = new FeedBack();
        feedback.setCreatedAt(now);
        feedback.setComment(comment);
        feedback.setEssay(essay);
        feedback.setUser(user);
        System.out.println("Rate is " + rate);
        rating = rate.intValue();
        feedback.setRating(rating);
        feedbackDAO.doSave(feedback);
        
        return Index.class;
    }

}
