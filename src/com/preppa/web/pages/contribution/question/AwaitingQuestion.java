  
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
public class AwaitingQuestion {
    @Property
    private List<Question> nonApproved;
    @Inject
    private QuestionDAO questionDAO;
    @Property
    private Question question;

    void onActivate() {
        List<Question> qTemp = questionDAO.findAllByNonApproved();
        nonApproved = new ArrayList<Question>();

        for (int i = 0; i < qTemp.size(); i++) {
            //System.out.println("Non approved flags " + nonApproved.get(i).getFlags());
            if (qTemp.get(i).getFlags().isEmpty()) {
                nonApproved.add(qTemp.get(i));
            }
        }
    }
}
