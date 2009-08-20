  
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
import com.preppa.web.data.VoteDAO;
import com.preppa.web.entities.Flag;
import com.preppa.web.entities.Question;
import com.preppa.web.entities.QuestionAnswer;
import com.preppa.web.entities.Vote;
import java.util.List;
import com.preppa.web.utils.ContentType;
import java.util.ArrayList;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Jan Jan
 */
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
    private List<Question> approved;
    @Property
    private Question question;
    @Inject
    private VoteDAO voteDAO;

    void onActivate() {
        //Get flagged questions
        List<Flag> temp = flagDAO.FindAllByContentType(ContentType.Question);
        flags = new ArrayList<Flag>();
        for (int i = 0; i < temp.size(); i++) {
            if(flags.contains(temp.get(i)) != true) {
               flags.add(temp.get(i));
            }
        }


        /*List<Question> qTemp = questionDAO.findAllByNonApproved();
        nonApproved = new ArrayList<Question>();

        for (int i = 0; i < qTemp.size(); i++) {
            if (qTemp.get(i).getFlags().isEmpty()) {
                if (voteDAO.findSumByQuestionId(qTemp.get(i).getId()) < 1) {
                    nonApproved.add(qTemp.get(i));
                }
            }
        }*/
        //Get Approved questions
        List<Question> qTemp = questionDAO.findAll();
        approved = new ArrayList<Question>();

        for (int i = 0; i < qTemp.size(); i++) {

            if (voteDAO.findSumByQuestionId(qTemp.get(i).getId()) >= 1 && qTemp.get(i).getFlags().isEmpty() && approved.contains(qTemp.get(i)) == false) {
                approved.add(qTemp.get(i));
            }
        }


        /*qTemp = questionDAO.findAllByApproved();
        approved = new ArrayList<Question>();

        for (int i = 0; i < qTemp.size(); i++) {
            if (qTemp.get(i).getFlags().isEmpty()) {
                if (voteDAO.findSumByQuestionId(qTemp.get(i).getId()) >= 1) {
                    nonApproved.add(qTemp.get(i));
                }
            }
        }*/
        //Get Non-Approved Questions
        qTemp = questionDAO.findAll();
        nonApproved = new ArrayList<Question>();

        for (int i = 0; i < qTemp.size(); i++) {

            if (voteDAO.findSumByQuestionId(qTemp.get(i).getId()) < 1 && qTemp.get(i).getFlags().isEmpty() && nonApproved.contains(qTemp.get(i)) == false) {
                nonApproved.add(qTemp.get(i));
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
