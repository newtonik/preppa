package com.preppa.web.pages.openquestion;

import com.preppa.web.data.OpenQuestionDAO;
import com.preppa.web.entities.OpenQuestion;
import com.preppa.web.entities.User;
import java.sql.Timestamp;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.chenillekit.tapestry.core.components.Editor;
import org.springframework.security.annotation.Secured;

/**
 *
 * @author nwt
 */

@IncludeJavaScriptLibrary(value = {"context:js/openquestion.js"})
@Secured("ROLE_USER")
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
    private Editor pass1;

    @Component
    private TextField titleField;
    @InjectPage
    private ShowOpenQuestion showquestion;
    void onActivate() {
        question = new OpenQuestion();
    }

    void onValidateFormQuestionForm() {
        if (fTitle == null) {
            questionForm.recordError(titleField, "You need a Title");
        }


    }

    @CommitAfter
    Object onSuccessFromQuestionForm() {

        question.setTitle(fTitle);
        question.setQuestion(fQuestion);
        question.setOwner(user);
        Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
        question.setUpdatedAt(now);
        question.setCreatedAt(now);

        openDAO.doSave(question);
        showquestion.setOpenQuestion(question);
        return showquestion;
    }
}
