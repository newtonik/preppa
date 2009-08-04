package com.preppa.web.pages.contribution.gridin;

import com.preppa.web.data.GridinDAO;
import com.preppa.web.entities.Gridin;
import com.preppa.web.entities.GridinAnswer;
import com.preppa.web.entities.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Mixins;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Radio;
import org.apache.tapestry5.corelib.components.RadioGroup;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.chenillekit.tapestry.core.components.Editor;
import org.springframework.security.annotation.Secured;

/**
 *
 * @author nwt
 */
@Secured("ROLE_USER")
@IncludeJavaScriptLibrary(value = {"context:js/gridin.js"})
public class EditGridin {

    @ApplicationState
    private User user;
    @Inject
    private GridinDAO gridinDAO;
    @Property
    private Gridin question;
    @Property
    private String fTitle;
    @Component
    private Form gridinForm;
    @Property
    private String fQuestion;
    @Component(parameters = {"value=fQuestion"})
    private Editor questionBody;
    private List<GridinAnswer> answers;
    private Long gridId;
    @Property
    private String fRangehigh;
    @Property
    private String fRangelow;
    @Component
    private RadioGroup chooserange;
    @Component(parameters = {"event=onclick"})
    @Mixins({"ck/OnEvent"})
    private Radio yesradio;
    @Component(parameters = {"event=onclick"})
    @Mixins({"ck/OnEvent"})
    private Radio noradio;
    @Property
    private String answertype;
    @Property
    private String fSingle;
    @Property
    private String fAnswer;
    @Property
    private String fDescription;
    @Property
    private GridinAnswer gridinanswer;
    @InjectPage
    private ShowGridin showgridin;

    void onActivate(Long id) {
        if (id > 0) {
            this.question = gridinDAO.findById(id);
            this.gridId = id;
            fQuestion = question.getQuestion();
            fTitle = question.getTitle();
            gridinanswer = question.getAnswers().get(0);
            fDescription = gridinanswer.getDescription();
            if(gridinanswer.getRange())
            {
                fRangehigh = gridinanswer.getHighAnswer();
                fRangelow = gridinanswer.getLowAnswer();

            }
            else
            {
                fAnswer = gridinanswer.getAnswer();

            }
        }
    }

    Long onPassivate() {

        return gridId;
    }

    void onValidateFormFromGridinForm() {
        System.out.println("Validating " + answertype);
        if (answertype == null) {
            gridinForm.recordError("You need to select an answer");
        } else {
            if ((answertype.equals("single")) && (fAnswer == null)) {
                gridinForm.recordError("Please enter an answer!");
            }
            if ((answertype.equals("range")) && ((fRangehigh == null) || fRangelow == null)) {
                gridinForm.recordError("Please Enter the ranges for your answer below!");
            }

        }
    }

    @CommitAfter
    Object onSuccess() {

        question.setTitle(fTitle);
        question.setQuestion(fQuestion);
        question.setUser(user);

        if(answertype.equals("range"))  {

             question.getAnswers().get(0).setRange(true);
             question.getAnswers().get(0).setDescription(fDescription);
             question.getAnswers().get(0).setHighAnswer(fRangehigh);
             question.getAnswers().get(0).setLowAnswer(fRangelow);
        }
        else
        {
             question.getAnswers().get(0).setRange(false);
             question.getAnswers().get(0).setAnswer(fAnswer);
             question.getAnswers().get(0).setDescription(fDescription);
        }
         
        Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
        question.setUpdatedAt(now);

        gridinDAO.doSave(question);
        showgridin.setGridin(question);
        return showgridin;
    }
}