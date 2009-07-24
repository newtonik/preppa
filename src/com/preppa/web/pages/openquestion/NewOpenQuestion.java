package com.preppa.web.pages.openquestion;

import com.preppa.web.data.OpenQuestionDAO;
import com.preppa.web.entities.OpenQuestion;
import com.preppa.web.entities.User;
import java.sql.Timestamp;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.chenillekit.tapestry.core.components.Editor;

/**
 *
 * @author nwt
 */
public class NewOpenQuestion {
    @ApplicationState
    private User user;
    @Property
    private OpenQuestion question;
    @Inject
    private OpenQuestionDAO openDAO;
    @Component
    private Form questionForm;
    @Property
    private String fTitle;
    @Property
    private String fQuestion;
    @Component(parameters = {"value=fQuestion"})
    private Editor questionBody;

    void onActivate() {
        question = new OpenQuestion();
    }
    void onValidate()  {
        System.out.println(question.getTitle());
    }

    void onSuccess() {

        question.setTitle(fTitle);
        question.setQuestion(fQuestion);
        question.setOwner(user);
                    Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
                    question.setUpdatedAt(now);
                    question.setCreatedAt(now);

                    openDAO.doSave(question);
    }

}
