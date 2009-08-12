  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.pages.contribution.question;

import com.preppa.web.data.QuestionDAO;
import com.preppa.web.entities.Question;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Jan Jan
 */
public class ApprovedQuestion {
    @Property
    private List<Question> approved;
    @Inject
    private QuestionDAO questionDAO;
    @Property
    private Question question;

    void onActivate() {
        List<Question> qTemp = questionDAO.findAllByApproved();
        approved = new ArrayList<Question>();

        for (int i = 0; i < qTemp.size(); i++) {
            //System.out.println("Non approved flags " + nonApproved.get(i).getFlags());
            if (qTemp.get(i).getFlags().isEmpty()) {
                approved.add(qTemp.get(i));
            }
        }
    }
}
