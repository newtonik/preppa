  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.pages.contribution.question.approve;

import com.preppa.web.data.QuestionDAO;
import com.preppa.web.data.VoteDAO;
import com.preppa.web.entities.Question;
import com.preppa.web.utils.Constants;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.IncludeStylesheet;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.chenillekit.tapestry.core.components.SlidingPanel;

/**
 *
 * @author Jan Jan
 */
@IncludeStylesheet(value = {"context:styles/dropdown.css"})
@IncludeJavaScriptLibrary(value = {"context:js/dropdown.js"})
public class Index {
    @Property
    private List<Question> approved;
    @Inject
    private QuestionDAO questionDAO;
    @Inject
    private VoteDAO voteDAO;
    @Property
    private Question question;
    @Inject
    private HttpServletRequest _request;
    /*@Component(parameters = {"subject=Critical Reading", "closed=true", "options={duration:0.0}"})
    private SlidingPanel cr;
    @Component(parameters = {"subject=Math", "closed=true", "options={duration:0.0}"})
    private SlidingPanel math;
    @Component(parameters = {"subject=Writing", "closed=true", "options={duration:0.0}"})
    private SlidingPanel writing;*/
    
    void onActivate() {
        List<Question> qTemp = questionDAO.findAll();
        approved = new ArrayList<Question>();

        for (int i = 0; i < qTemp.size(); i++) {

            if (voteDAO.findSumByQuestionId(qTemp.get(i).getId()) >= Constants.getApprovalThreshhold() && qTemp.get(i).getFlags().isEmpty() && approved.contains(qTemp.get(i)) == false) {
                approved.add(qTemp.get(i));
            }
        }
    }


    public boolean getMath() {
        String url = _request.getRequestURL().toString();
        return url.contains("/Math");
    }

    public boolean getCriticalReading() {
        String url = _request.getRequestURL().toString();
        return url.contains("/CriticalReading");

    }

    public boolean getWriting() {
        String url = _request.getRequestURL().toString();
        return url.contains("/Writing");
    }
}
