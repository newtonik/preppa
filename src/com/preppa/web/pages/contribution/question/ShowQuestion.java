/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.pages.contribution.question;

import com.preppa.web.components.questiontypes.multichoice.ShowMultiChoice;
import com.preppa.web.data.QuestionDAO;
import com.preppa.web.entities.Question;
import com.preppa.web.entities.QuestionAnswer;
import com.preppa.web.pages.Index;
import java.util.List;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;


/**
 *
 * @author newtonik
 */
public class ShowQuestion {

    @Property
    private Question ques;
    @Component
    private ShowMultiChoice showquestion;
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

    void onActivate(int id) {
        if (id > 0) {
            this.ques = questionDAO.findById(id);
            if (this.ques != null) {
                /*example = question.getSentence().getSentence();
                vid = question.getId();*/
                qid = id;
            } else {
                // return index;
            }
        }
        //return this;
    }

    Integer onPassivate() {
        return this.qid;
    }

    public List<QuestionAnswer> getAllAnswers() {
        List<QuestionAnswer> returnVal;

        returnVal = ques.getChoices();

        return returnVal;
    }

    public void setquestion(Question question) {
        this.ques = question;
        this.qid = question.getId();
    }
}
