package com.preppa.web.pages.openquestion;

import com.preppa.web.components.openquestion.ShowAnswers;
import com.preppa.web.data.OpenQuestionDAO;
import com.preppa.web.entities.OpenAnswer;
import com.preppa.web.entities.OpenQuestion;
import com.preppa.web.entities.User;
import com.preppa.web.utils.ContentType;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.acegisecurity.annotation.Secured;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.chenillekit.tapestry.core.components.Editor;

/**
 *
 * @author nwt
 */
@IncludeJavaScriptLibrary(value = {"context:js/openquestion.js"})
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
    @Property
    private String author;
    @Component(parameters = {"value=fAnswer"})
    private Editor pass1;
    @Property
    private ContentType contType;
    private Long qid;

    void onActivate(Long id)
    {
        author = "unknown";
        if(id > 0) {
            question = openDAO.doRetrieve(id, false);
            openanswers = question.getAnswers();
            qid = id;
            author = question.getOwner().getUsername();
            contType = ContentType.OpenQuestion;
        }
    }

    Long onPassivate() {
        return qid;
    }

    public void setOpenQuestion(OpenQuestion q) {
        if(q != null) {
            question = openDAO.findById(q.getId());
            this.qid = question.getId();
        }
    }

    @CommitAfter
    @Secured("ROLE_USER")
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
