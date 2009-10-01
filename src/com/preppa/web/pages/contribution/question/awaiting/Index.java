  
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
import com.preppa.web.data.QuestiontypeDAO;
import com.preppa.web.data.VoteDAO;
import com.preppa.web.entities.Question;
import com.preppa.web.entities.Questiontype;
import com.preppa.web.utils.Constants;
import java.util.List;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.IncludeStylesheet;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Jan Jan
 */
@IncludeStylesheet(value = {"context:styles/dropdown.css", "context:styles/innerroundcontainer.css"})
@IncludeJavaScriptLibrary(value = {"context:js/dropdown.js"})
public class Index {
    @Property
    private List<Question> nonApproved;
    @Inject
    private QuestionDAO questionDAO;
    @Property
    private Question question;
    @Inject
    private VoteDAO voteDAO;
    @Inject
    private QuestiontypeDAO questypeDAO;
    private Questiontype questiontype;


    /*@Component(parameters = {"subject=Critical Reading", "closed=true", "options={duration:0.0}"})
    private SlidingPanel cr;
    @Component(parameters = {"subject=Math", "closed=true", "options={duration:0.0}"})
    private SlidingPanel math;
    @Component(parameters = {"subject=Writing", "closed=true", "options={duration:0.0}"})
    private SlidingPanel writing;*/
    
    void onActivate() {
        List<Question> qTemp = questionDAO.findAll();
         questiontype = questypeDAO.findByName("Multiple Choice");
        nonApproved = questionDAO.findAllByNonApproved(questiontype);

//        for (int i = 0; i < qTemp.size(); i++) {
//
//            if (voteDAO.findSumByQuestionId(qTemp.get(i).getId()) < Constants.getApprovalThreshhold() && qTemp.get(i).getFlags().isEmpty() && nonApproved.contains(qTemp.get(i)) == false) {
//                nonApproved.add(qTemp.get(i));
//            }
//        }
    }
}
