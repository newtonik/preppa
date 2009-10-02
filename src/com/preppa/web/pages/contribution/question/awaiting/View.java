  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.pages.contribution.question.awaiting;

import com.preppa.web.components.questiontypes.prompt.ListPrompt;
import com.preppa.web.data.GridinDAO;
import com.preppa.web.data.ImprovingParagraphDAO;
import com.preppa.web.data.LongDualPassageDAO;
import com.preppa.web.data.LongPassageDAO;
import com.preppa.web.data.PromptDAO;
import com.preppa.web.data.QuestionDAO;
import com.preppa.web.data.QuestiontypeDAO;
import com.preppa.web.data.ShortDualPassageDAO;
import com.preppa.web.data.ShortPassageDAO;
import com.preppa.web.data.VoteDAO;
import com.preppa.web.entities.Gridin;
import com.preppa.web.entities.ImprovingParagraph;
import com.preppa.web.entities.LongPassage;
import com.preppa.web.entities.Prompt;
import com.preppa.web.entities.Question;
import com.preppa.web.entities.Questiontype;
import java.util.List;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.IncludeStylesheet;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Jan Jan
 */
@IncludeStylesheet(value = {"context:styles/innerroundcontainer.css"})
public class View {
    @Inject
    private QuestionDAO questionDAO;
    @Inject
    private VoteDAO voteDAO;
    @Inject
    private GridinDAO gridinDAO;
    @Inject
    private ShortPassageDAO shortpassageDAO;
    @Inject
    private LongPassageDAO longpassageDAO;
    @Inject
    private ShortDualPassageDAO shortdualpassageDAO;
    @Inject
    private LongDualPassageDAO longdualpassageDAO;
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
    @Property
    private boolean isPrompt;
    @Property
    private boolean isMultiple;
    @Property
    private Boolean isParagraph;
    @Property
    private List<Prompt> prompts;
    @Inject
    private PromptDAO promptDAO;
    @Inject
    private QuestiontypeDAO questDAO;
    private Questiontype questype;
    @Property
    private List<LongPassage> longpassages;
    @Component
    private ListPrompt listprompt;
    @Property
    private List<ImprovingParagraph> paragraphs;
    @Inject
    private ImprovingParagraphDAO improvingDAO;
    


    Object onActivate(String questiontype) {
        System.out.println("In onActivate");
        System.out.println("Questiontype is " + questiontype);
        this.qType = questiontype;
        
        List<Question> allquestions = null;
        isGridin = false;
        isPrompt = false;
        isMultiple = false;
        isParagraph = false;

        if(questiontype.contains("Grid-ins")) {
          
            isGridin = true;
            gridins = gridinDAO.findAllByAwaiting();
        }
        else  if(questiontype.contains("Free Response Question")) {
            isPrompt = true;
            prompts = promptDAO.findAllByAwaiting();
            listprompt.setPrompts(prompts);
            System.out.println("prompts size in after loop " + prompts.size());
        }
        else  if(questiontype.contains("Improving Paragraphs")) {
            isParagraph = true;
            paragraphs = improvingDAO.findAllByAwaiting();
        }
        else {
            questype = questDAO.findByName(qType);
            questions = questionDAO.findAllByNonApproved(questype);
            isMultiple = true;
            System.out.println("There are questions of size " + questions.size());
        }



        /*if(questiontype.contains("SentenceCompletion"))
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
            List<ShortPassage> passages = new ArrayList<ShortPassage>();
            allquestions = new ArrayList<Question>();
            for (int i = 0; i < passages.size(); i++) {
                if (passages.get(i).getQuestions() != null) {
                    allquestions.addAll(passages.get(i).getQuestions());
                }
            }
        }
        else if(questiontype.contains("LongPassages"))
        {
            System.out.println("Long Passages");
            List<LongPassage> passages = new ArrayList<LongPassage>();
            allquestions = new ArrayList<Question>();
            for (int i = 0; i < passages.size(); i++) {
                if (passages.get(i).getQuestions() != null) {
                    allquestions.addAll(passages.get(i).getQuestions());
                }
            }
        }
        else if(questiontype.contains("ShortDualPassages"))
        {
            System.out.println("ShortDualPassages");
            List<ShortDualPassage> passages = new ArrayList<ShortDualPassage>();
            allquestions = new ArrayList<Question>();
            for (int i = 0; i < passages.size(); i++) {
                if (passages.get(i).getQuestions() != null) {
                    allquestions.addAll(passages.get(i).getQuestions());
                }
            }
        }
        else if(questiontype.contains("LongDualPassages"))
        {
            System.out.println("LongDualPassages");
            List<LongDualPassage> passages = new ArrayList<LongDualPassage>();
            allquestions = new ArrayList<Question>();
            for (int i = 0; i < passages.size(); i++) {
                if (passages.get(i).getQuestions() != null) {
                    allquestions.addAll(passages.get(i).getQuestions());
                }
            }
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
        }*/

/*
        if (isGridin == false) {
            questions = new ArrayList<Question>();
            for (int i = 0; i < allquestions.size(); i++) {
                if (allquestions.get(i) != null) {
                    System.out.println("Criteria is " + (voteDAO.findSumByQuestionId(allquestions.get(i).getId()) < 1));
                    System.out.println("Criteria is " + (allquestions.get(i).getFlags().isEmpty()));
                    //System.out.println("Criteria is " + (questions.contains(allquestions.get(i)) == false));
                    if (voteDAO.findSumByQuestionId(allquestions.get(i).getId()) < Constants.getApprovalThreshhold() && allquestions.get(i).getFlags().isEmpty() && questions.contains(allquestions.get(i)) == false) {
                        questions.add(allquestions.get(i));
                    }
                }
            }
        }*/
        return null;
    }
}
