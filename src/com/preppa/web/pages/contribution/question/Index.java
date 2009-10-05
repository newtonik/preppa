  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.pages.contribution.question;

import com.preppa.web.data.FlagDAO;
import com.preppa.web.data.QuestionDAO;
import com.preppa.web.data.QuestiontypeDAO;
import com.preppa.web.data.VoteDAO;
import com.preppa.web.entities.Flag;
import com.preppa.web.entities.Question;
import com.preppa.web.entities.Question;
import com.preppa.web.entities.QuestionAnswer;
import com.preppa.web.entities.Questiontype;
import java.util.List;
import com.preppa.web.utils.ContentType;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.annotations.IncludeStylesheet;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Jan Jan
 */
@IncludeStylesheet(value = {"context:styles/innerroundcontainer.css"})
public class Index {
    @Inject
    private FlagDAO flagDAO;
    @Inject
    private QuestionDAO questionDAO;
    @Property
    private List<Flag> flags;
    @Property
    private Flag flag;
    @Property
    private QuestionAnswer answer;
    @Property
    private List<Question> nonApproved;
    @Property
    private Question question;
    @Inject
    private VoteDAO voteDAO;
    @Inject
    private QuestiontypeDAO questypeDAO;
    private Questiontype questiontype;

    void onActivate() {
        flags = flagDAO.FindAllByContentType(ContentType.Question);

        flags = null;
      
        questiontype = questypeDAO.findByName("Multiple Choice");

        List<Question> qTemp = questionDAO.findAllByNonApproved(questiontype);
        nonApproved = new ArrayList<Question>();

        for (int i = 0; i < qTemp.size(); i++) {
            if (qTemp.get(i).getFlags().isEmpty()) {
                if (voteDAO.findSumByQuestionId(qTemp.get(i).getId()) < 1) {
                    nonApproved.add(qTemp.get(i));
                }
            }
        }

    }


    public String getAnswerFormatted() {
        String returnVal = answer.getAnswer();
        
        //Remove newline tag
        returnVal = returnVal.substring(4, returnVal.length()-4);
        return returnVal;
    }
}
