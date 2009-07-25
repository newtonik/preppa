package com.preppa.web.pages.contribution.gridin;

import com.preppa.web.components.gridin.NewAnswer;
import com.preppa.web.data.GridinDAO;
import com.preppa.web.entities.Gridin;
import com.preppa.web.entities.GridinAnswer;
import com.preppa.web.entities.User;
import java.sql.Timestamp;
import java.util.List;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.chenillekit.tapestry.core.components.Editor;
import org.springframework.security.annotation.Secured;

/**
 *
 * @author nwt
 */
@Secured("ROLE_USER")
public class NewGridin {
    @ApplicationState
    private User user;
    @Inject
    private GridinDAO gridinDAO;
    @Property
    private Gridin question;
    @Property
    private String fTitle;
    @Component
    private Form qridinForm;
    @Property
    private String fQuestion;
    @Component(parameters = {"value=fQuestion"})
    private Editor questionBody;
    private List<GridinAnswer> answers;
    @Component
    private NewAnswer answerComp;
    @Inject
    private Block addanswer;
    @InjectPage
    private ShowGridin showgridin;
    void onActivate() {
        this.question = new Gridin();
    }
    Object onSuccess() {

        question.setTitle(fTitle);
        question.setQuestion(fQuestion);
        question.setUser(user);
                    Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
                    question.setUpdatedAt(now);
                    question.setCreatedAt(now);

                    gridinDAO.doSave(question);
                       showgridin.setGridin(question);
         return showgridin;
    }

    Block onActionFromAddAnswer() {
        

        return addanswer;
    }


}
