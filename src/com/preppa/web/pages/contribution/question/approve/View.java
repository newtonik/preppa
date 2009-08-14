  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.pages.contribution.question.approve;

import com.preppa.web.components.questiontypes.QuestionMenu;
import com.preppa.web.data.GridinDAO;
import com.preppa.web.data.QuestionDAO;
import com.preppa.web.data.VoteDAO;
import com.preppa.web.entities.Gridin;
import com.preppa.web.entities.Question;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Jan Jan
 */
public class View {
    @Inject
    private QuestionDAO questionDAO;
    @Inject
    private VoteDAO voteDAO;
    @Inject GridinDAO gridinDAO;
    @Property
    private List<Question> questions;
    @Property
    private List<Gridin> gridins;
    @Property
    private Gridin gridin;
    @Property
    private Question question;
    @Property
    private String qType;
    @Property
    private boolean isGridin;

    Object onActivate(String questiontype) {
        System.out.println("In onActivate");
        this.qType = questiontype;
        List<Question> allquestions = null;
        isGridin = false;
        if(questiontype.contains("SentenceCompletion"))
        {
            System.out.println("Sentence Completion");
            allquestions = questionDAO.findAllByQuestionType("Sentence Completion");
        }
        else if(questiontype.contains("MultipleChoice"))
        {
            System.out.println("Multiple Choice");
            allquestions = questionDAO.findAllByQuestionType("Multiple Choice");
            System.out.println("All questions is " + allquestions);
        }
        else if(questiontype.contains("ShortPassages"))
        {
            System.out.println("Short Passages");
            allquestions = questionDAO.findAllByQuestionType("Short Passages");
        }
        else if(questiontype.contains("LongPassages"))
        {
            System.out.println("Long Passages");
            allquestions = questionDAO.findAllByQuestionType("Long Passages");
        }
        else if(questiontype.contains("ShortDualPassages"))
        {
            System.out.println("ShortDualPassages");
            allquestions = questionDAO.findAllByQuestionType("Short Dual Passages");
        }
        else if(questiontype.contains("LongDualPassages"))
        {
            System.out.println("LongDualPassages");
            allquestions = questionDAO.findAllByQuestionType("Long Dual Passages");
        }
        else if(questiontype.contains("Grid-ins"))
        {
            System.out.println("Grid-ins");
            isGridin = true;
            gridins = gridinDAO.findAll();
        }
        else if(questiontype.contains("SentenceErrors"))
        {
            System.out.println("SentenceErrors");
            allquestions = questionDAO.findAllByQuestionType("Identifying Sentence Errors");
        }
        else if(questiontype.contains("ImprovingSentences"))
        {
            System.out.println("ImprovingSentences");
            allquestions = questionDAO.findAllByQuestionType("Improving Sentences");
        }
        else if(questiontype.contains("ImprovingParagraph"))
        {
            System.out.println("ImprovingParagraph");
            allquestions = questionDAO.findAllByQuestionType("Improving Paragraph");
        }
        else if(questiontype.contains("FreeResponseQuestion"))
        {
            System.out.println("FreeResponseQuestion");
            allquestions = questionDAO.findAllByQuestionType("Free Response Question");
        }
        else {
            allquestions = questionDAO.findAllNoRepeat();
        }


        if (isGridin == false) {
            questions = new ArrayList<Question>();
            for (int i = 0; i < allquestions.size(); i++) {
                //System.out.println("Criteria is " + (voteDAO.findSumByQuestionId(allquestions.get(i).getId()) >= 1));
                //System.out.println("Criteria is " + (allquestions.get(i).getFlags().isEmpty()));
                //System.out.println("Criteria is " + (questions.contains(allquestions.get(i)) == false));
                if (voteDAO.findSumByQuestionId(allquestions.get(i).getId()) >= 1 && allquestions.get(i).getFlags().isEmpty() && questions.contains(allquestions.get(i)) == false) {
                    questions.add(allquestions.get(i));
                }
            }
        }
        else {
            
        }
        return null;
    }
}
