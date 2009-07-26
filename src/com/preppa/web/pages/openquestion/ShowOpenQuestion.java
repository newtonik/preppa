package com.preppa.web.pages.openquestion;

import com.preppa.web.components.openquestion.ShowAnswers;
import com.preppa.web.data.OpenQuestionDAO;
import com.preppa.web.entities.OpenAnswer;
import com.preppa.web.entities.OpenQuestion;
import com.preppa.web.entities.User;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.acegisecurity.annotation.Secured;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author nwt
 */
public class ShowOpenQuestion {
    @Property
    private OpenQuestion question;
    @Inject
    private OpenQuestionDAO openDAO;
    @Property
    private String fAnswer;
    private OpenAnswer answer;
    @Component
    private Form answerform;
    @ApplicationState
    private User user;
    @Property
    private List<OpenAnswer> openanswers;
    @Component
    private ShowAnswers answerlist;


    private Long qid;

    void onActivate(Long id)
    {
        if(id > 0) {
            question = openDAO.doRetrieve(id, true);
            openanswers = question.getAnswers();
            qid = id;
        }
    }

    Long onPassivate() {
        return qid;
    }

    void setOpenQuestion(OpenQuestion q) {
        if(q != null) {
            question = openDAO.findById(q.getId());
            this.qid = question.getId();
        }
    }
    @Secured("ROLE_USER")
    @CommitAfter
    Object onSuccessFromAnswerForm() {
        answer = new OpenAnswer();
        System.out.println(fAnswer);
        answer.setAnswer(fAnswer);
        answer.setUser(user);
        

        Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
        answer.setCreatedAt(now);
        answer.setUpdatedAt(now);

        
        if(question.getAnswers() == null) {
           List<OpenAnswer> answers = new ArrayList<OpenAnswer>();
           answers.add(answer);
           question.setAnswers(answers);
           openanswers = question.getAnswers();
        }
        else {
            question.getAnswers().add(answer);
            openanswers = question.getAnswers();
        }
        now = new java.sql.Timestamp(System.currentTimeMillis());
        question.setUpdatedAt(now);
        openDAO.doSave(question);
        return null;
    }
}
