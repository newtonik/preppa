/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.pages.contribution.longpassage;

import com.preppa.web.components.CQuestion;
import com.preppa.web.components.SQuestion;
import com.preppa.web.data.LongPassageDAO;
import com.preppa.web.data.PassageDAO;
import com.preppa.web.data.QuestiontypeDAO;
import com.preppa.web.data.VoteDAO;
import com.preppa.web.entities.Flag;
import com.preppa.web.entities.LongPassage;
import com.preppa.web.entities.Question;
import com.preppa.web.entities.Questiontype;
import com.preppa.web.entities.Tag;
import com.preppa.web.entities.User;
import com.preppa.web.utils.Constants;
import com.preppa.web.utils.ContentFlag;
import com.preppa.web.utils.ContentType;
import com.preppa.web.utils.FlagStatus;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.IncludeStylesheet;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.springframework.security.annotation.Secured;

/**
 *
 * @author newtonik
 */
@IncludeStylesheet(value = {"context:styles/flag.css"})
@IncludeJavaScriptLibrary(value = {"context:js/passage.js"})
public class ShowLongPassage {

    @ApplicationState
    private User user;
    @Property
    private LongPassage passage;
    @Inject
    private LongPassageDAO passageDAO;
    @Inject
    private PassageDAO passDA0;
    private Integer pid;
    @Inject
    @Property
    private Block questionblock;
    private List<Block> questionBlocks = new LinkedList<Block>();
    @InjectComponent
    private Zone questionZone;
    @InjectComponent
    private Zone showquestionZone;
    @Inject
    @Property
    private Block showquestionBlock;
    @Component
    private CQuestion firstquestion;
    @Component
    private SQuestion showquestion;
    @Property
    private Question q1;
    @Property
    @Persist
    private int size;
    @Persist
    private int count;
    @Property
    private boolean questionschanged = false;
    private Integer passageid;
    @Property
    private boolean lastquestion;
    @Property
    private boolean onequestion;
    @Persist
    private List<Question> listquestions;
    @Property
    private List<Tag> tags = new LinkedList<Tag>();
    @Property
    @Persist
    private Integer votes;
    @Property
    private String reason;
    @Property
    private String reasonDesc;
    private Integer artId;
    @Component
    private Form flagform;
    @Inject
    private Block flagresponse;
    private List<Flag> passageflags;
    @Inject
    @Property
    private Block flagblock;
    @Component
    private TextField flagfield;
    @Property
    private ContentType contType;
    @Property
    private Questiontype questiontype;
    @Inject
    private QuestiontypeDAO questiontypeDAO;
    @Property
    private boolean isApproved;
    @Property
    private Integer votecount;
    @Inject
    private VoteDAO voteDAO;


    public String getUsername() {
        if (passage.getUser() != null) {
            return passage.getUser().getUsername();
        }
        else {
            return "";
        }
    }
    
    void onpageLoaded() {
        firstquestion.setPageFalse();

    }

    @SetupRender
    void setDefaults() {
        lastquestion = true;
        onequestion = true;
        questiontype = questiontypeDAO.findByName("Long Passage");
    }

    void onActivate(int id) {
        this.passage = passageDAO.findById(id);
        tags = passage.getTaglist();
        this.pid = passage.getId();
        contType = ContentType.LongPassage;
        votecount = voteDAO.findVoteByContentId(contType, passage.getId());
        if (votecount >= Constants.getApprovalThreshhold()) {
            isApproved = true;
        }
        else {
            isApproved = false;
        }
    }

    Integer onPassivate() {
        return this.pid;
    }

    public void setPassagePage(LongPassage passage) {
        if (passage != null) {
            this.passage = passage;
            this.pid = passage.getId();
        }

    }

    Block onActionFromAddQuestion() {

        return questionblock;
    }

    Block onActionFromShowQuestionlink() {
        count = 0;

        if (count == 0) {
            onequestion = false;
        } else {
            onequestion = true;
        }
        if (count == size - 1) {
            lastquestion = false;
        } else {
            lastquestion = true;
        }
        passage = passageDAO.findById(passage.getId());
        listquestions = passage.getQuestions();
        size = listquestions.size();
        if (size == 0) {
            return null;
        } else if (size == 1) {
            lastquestion = false;
            onequestion = false;
        }
        q1 = listquestions.get(count);

        return showquestionBlock;
    }

    Block onActionFromRemoveShowQuestion() {
        questionschanged = true;
        return null;
    }

    Block onActionFromRemoveNewQuestion() {
        return null;
    }

    Block onActionFromNextShowQuestion() {
        if (questionschanged) {
            System.out.println("questions have been updated");
            passage = passageDAO.findById(passage.getId());
            listquestions = passage.getQuestions();
            size = listquestions.size();
            questionschanged = false;
        }
        System.out.println("Size is " + size);
        if (count < size - 1 && (size != 0)) {
            count++;
        }
        if (count == 0) {
            onequestion = false;
        } else {
            onequestion = true;
        }

        if (count == size - 1) {
            lastquestion = false;
        } else {
            lastquestion = true;
        }


        q1 = listquestions.get(count);
        return showquestionBlock;
    }

    Block onActionFromPrevShowQuestion() {
        if (questionschanged) {

            System.out.println("questions have been updated");
            passage = passageDAO.findById(passage.getId());
            listquestions = passage.getQuestions();
            size = listquestions.size();
            questionschanged = false;
        }
        if (count > 0 && count <= (size - 1)) {
            count--;
        }
        if (count == 0) {
            onequestion = false;
        } else {
            onequestion = true;
        }
        if (count == size - 1) {
            lastquestion = false;
        } else {
            lastquestion = true;
        }

        q1 = listquestions.get(count);
        return showquestionBlock;
    }

    @Secured("ROLE_USER")
    @CommitAfter
    Block onSuccessFromFlagForm() {
        if (reason != null) {
            Flag f = new Flag();
            if (reason.equals("A")) {
                f.setFlagtype(ContentFlag.Inappropriate);
            } else if (reason.equals("B")) {
                f.setFlagtype(ContentFlag.Spam);
            } else if (reason.equals("C")) {
                f.setFlagtype(ContentFlag.Attention);
            } else if (reason.equals("D")) {
                f.setFlagtype(ContentFlag.Incorrect);
            } else if (reason.equals("E")) {

                f.setFlagtype(ContentFlag.Copyright);
            } else {
                System.out.println(reason);
                f.setFlagtype(ContentFlag.Attention);
            }

            f.setDescription(reasonDesc);
            f.setContentType(ContentType.LongPassage);
            f.setFlagger(user);
            f.setStatus(FlagStatus.NEW);
//          f.setArticle(article);
            f.setlongpassage(passage);


            if (passageflags == null) {
                passageflags = new ArrayList<Flag>();
                passageflags.add(f);
                //  article.setFlags(articleflags);
                passage.setFlags(passageflags);
            } else {
                //articleflags.add(f);
                //    article.getFlags().add(f);
                passageflags.add(f);
                passage.getFlags().add(f);
            }
            Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());

            f.setUpdatedAt(now);
            f.setCreatedAt(now);

            //  article.setUpdatedAt(now);
            passage.setUpdatedAt(now);
        }
        //articleDAO.doSave(article);
        this.passageDAO.doSave(passage);
        return flagresponse;
    }

    Block onActionFromRemoveFlagBox() {
        return null;
    }

    Block onActionFromCloseFlagBlock() {
        return flagblock;
    }
}
