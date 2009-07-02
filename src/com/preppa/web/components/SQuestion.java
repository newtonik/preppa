/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.components;

import com.preppa.web.data.LongDualPassageDAO;
import com.preppa.web.data.QuestionDAO;
import com.preppa.web.entities.LongDualPassage;
import com.preppa.web.entities.Question;
import com.preppa.web.entities.QuestionAnswer;
import com.preppa.web.pages.Index;
import java.util.List;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author newtonik
 */
public class SQuestion {
@Property
@Parameter
private Question question;
@Inject
private QuestionDAO questionDAO;
@Property
private String example;
@InjectPage
private Index index;
private Integer qid;
@Property
@Persist
private QuestionAnswer questionanswer;
@Parameter
private Integer pid;
@Inject
private LongDualPassageDAO passageDAO;

@Property
private List<Question> allQuestions;
private LongDualPassage passage;
private List<QuestionAnswer> returnVal;

void onActivate() {
       
        passage = passageDAO.findById(pid);
        allQuestions = passage.getQuestions();

        question = allQuestions.get(0);

        if(question != null) {
            System.out.println("question is not null");
            /*example = question.getSentence().getSentence();
            vid = question.getId();*/
            returnVal = question.getChoices();
        }
      setquestion(question);

    }
   @SetupRender
void intializeQuestion () {
       if(question == null ) {
        passage = passageDAO.findById(pid);
        allQuestions = passage.getQuestions();

        question = allQuestions.get(0);

        if(question != null) {
            System.out.println("question is not null");
            /*example = question.getSentence().getSentence();
            vid = question.getId();*/
            returnVal = question.getChoices();
        }
       }
}

    public List<QuestionAnswer> getAllAnswers() {
   
    if(returnVal == null) {
        returnVal = question.getChoices();
    }
        return returnVal;
    }

void setquestion(Question question) {
        this.question = question;
        this.qid = question.getId();
    }

}
