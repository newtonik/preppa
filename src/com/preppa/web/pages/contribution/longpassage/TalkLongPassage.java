package com.preppa.web.pages.contribution.longpassage;

import com.preppa.web.data.LongPassageDAO;
import com.preppa.web.entities.LongPassage;
import com.preppa.web.entities.ReviewComment;
import com.preppa.web.entities.User;
import java.sql.Timestamp;
import java.util.List;
import org.acegisecurity.annotation.Secured;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.chenillekit.tapestry.core.components.Editor;

/**
 *
 * @author nwt
 */
public class TalkLongPassage {


    @ApplicationState
    private User user;
    @Property
    private LongPassage passage;
    @Inject
    private LongPassageDAO passageDAO;
    @Property
    private List<ReviewComment> comments;
    @Property
    private ReviewComment comment;
    @Property
    private String fComment;
    @Component
    private Form commentform;
    @Component(parameters = {"value=fComment", "toolbarSet=Basic"})
    private Editor pass1;



    void onActivate(Integer id) {
        if (id > 0) {
            passage = passageDAO.findById(id);
            comments = passage.getReviewcomments();

        }
    }

    Integer onPassivate() {
        return passage.getId();
    }

    Boolean onValidateFormFromCommentForm() {
         System.out.println("I validation here with comment " + fComment);
        if (fComment == null) {
            commentform.recordError(pass1, "You need to Enter a review");
        }
        else
        {

        }
        if (user == null) {
            commentform.recordError("You must be logged in to post a review");
        }

        return true;
    }

    @CommitAfter
    @Secured("ROLE_USER")
    Boolean onSuccessFromCommentForm() {
        System.out.println("I reached here with comment " + fComment);
        ReviewComment rc = new ReviewComment();
        rc.setComment(fComment);
        rc.setCommenter(user);
        Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
        rc.setCreatedAt(now);
        rc.setUpdatedAt(now);

        passage.getReviewcomments().add(rc);

        passageDAO.doSave(passage);
        return true;
    }

}
