  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.pages.contribution.question.awaiting;

import com.preppa.web.data.QuestionDAO;
import com.preppa.web.data.VoteDAO;
import com.preppa.web.entities.Question;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Jan Jan
 */
public class Index {
    @Property
    private List<Question> nonApproved;
    @Inject
    private QuestionDAO questionDAO;
    @Property
    private Question question;
    @Inject
    private VoteDAO voteDAO;
    void onActivate() {
        List<Question> qTemp = questionDAO.findAll();
        nonApproved = new ArrayList<Question>();

        for (int i = 0; i < qTemp.size(); i++) {

            if (voteDAO.findSumByQuestionId(qTemp.get(i).getId()) < 1 && qTemp.get(i).getFlags().isEmpty() && nonApproved.contains(qTemp.get(i)) == false) {
                nonApproved.add(qTemp.get(i));
            }
        }
    }
}
