  
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
import com.preppa.web.entities.Essay;
import com.preppa.web.entities.FeedBack;
import com.preppa.web.entities.Tag;
import com.preppa.web.entities.User;
import java.util.List;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Jan Jan
 */
public class ShowEssay {
    @ApplicationState
    private User user;
    @Property
    private List<Tag> tags;
    @Property
    private Essay essay;
    @Inject
    private EssayDAO essayDAO;
    private Integer eid;

    @Property
    private FeedBack feedback;
    
    void onActivate(int id) {
        if (id > 0) {
            essay = essayDAO.findById(id);
            this.eid = id;
            if (essay != null) {
                eid = essay.getId();
                tags = essay.getTaglist();
            }
        }
    }

    void setprompt(Essay essay) {
        this.essay = essay;
        this.eid = essay.getId();
    }

    Integer onPassivate() {
        return this.eid;

    }

    public boolean getIsAuthor() {
        if (this.essay != null) {
            System.out.println(essay.getUser().getId() + " being compared to " + user.getId());

            if (user.getId() == 0) {
                return true;
            }

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
}
