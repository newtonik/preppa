/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.pages.contribution.question;

import com.preppa.web.data.QuestionDAO;
import com.preppa.web.entities.Question;
import com.preppa.web.entities.QuestionAnswer;
import com.preppa.web.pages.Index;
import java.util.List;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author newtonik
 */
public class ShowQuestion {
@Persist
@Property
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

Object onActivate(int id) {
    if(id > 0 ) {
        this.question = questionDAO.findById(id);
        if(this.question != null) {
            /*example = question.getSentence().getSentence();
            vid = question.getId();*/
        }
        else
        {
            return index;
        }
    }
     return this;
    }

    public List<QuestionAnswer> getAllAnswers() {
        List<QuestionAnswer> returnVal;

        returnVal = question.getChoices();

        return returnVal;
    }

void setquestion(Question question) {
        this.question = question;
        this.qid = question.getId();
    }

}
