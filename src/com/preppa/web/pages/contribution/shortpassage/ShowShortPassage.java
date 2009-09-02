/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.pages.contribution.shortpassage;

import com.preppa.web.components.SQuestion;
import com.preppa.web.components.questiontypes.multichoice.NewMultiChoice;
import com.preppa.web.data.QuestiontypeDAO;
import com.preppa.web.data.ShortPassageDAO;
import com.preppa.web.data.VoteDAO;
import com.preppa.web.entities.Flag;
import com.preppa.web.entities.Question;
import com.preppa.web.entities.Questiontype;
import com.preppa.web.entities.ShortPassage;
import com.preppa.web.entities.Tag;
import com.preppa.web.entities.User;
import com.preppa.web.entities.Vote;
import com.preppa.web.utils.ContentFlag;
import com.preppa.web.utils.ContentType;
import com.preppa.web.utils.FlagStatus;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.acegisecurity.annotation.Secured;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.IncludeStylesheet;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;

/**
 *
 * @author newtonik
 */
@IncludeStylesheet(value = {"context:styles/flag.css"})
@IncludeJavaScriptLibrary(value = {"context:js/passage.js"})
public class ShowShortPassage {

    @ApplicationState
    private User user;
    @Property
    private ShortPassage passage;
    @Inject
    private ShortPassageDAO passageDAO;
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
    private NewMultiChoice firstquestion;
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
    @Inject
    private VoteDAO voteDAO;
    @InjectComponent
    private Zone voteupZone;
    @Inject
    private Block voteBlock;
    @Inject
    private Block upSuccess;
    @Inject
    private Block downSuccess;
    @Inject
    private Block voted;
    @Property
    @Persist
    private Integer votes;
    @Inject
    private HttpServletRequest _request;
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

    void onActivate(int id) {
        this.passage = passageDAO.findById(id);
        tags = passage.getTaglist();
        this.pid = passage.getId();
        this.votes = voteDAO.findSumByShortPassageId(passage.getId());
        lastquestion = true;
        onequestion = true;
        contType = ContentType.ShortPassage;
        questiontype = questiontypeDAO.findByName("Short Passage");
    }

    Integer onPassivate() {
        return this.pid;
    }

    public void setPassagePage(ShortPassage passage) {
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
        } else if (count == 1) {
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

    Block onActionFromVoteUp() {
        String hostname = _request.getRemoteHost();
        if (!(voteDAO.checkVoted(ContentType.ShortPassage, passage.getId(), user))) {
            Vote v = new Vote();
            v.setContentId(passage.getId());
            v.setSource(hostname);
            if (user != null) {
                v.setUser(user);
            }

            v.setValue(1);
            v.setContentTypeId(ContentType.ShortPassage);

            Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
            v.setCreatedAt(now);

            voteDAO.doSave(v);

            JSONObject json = new JSONObject();
            json.put("vote", "down");
            //decrement the vote
            votes++;

            return upSuccess;
        }//return new TextStreamResponse("text/json", json.toString());
        else {
            return voted;
        }
    }

    Block onActionFromVoteDown() {

        String hostname = _request.getRemoteHost();
        // System.out.println(_request.getRequestURL());

        if (!(voteDAO.checkVoted(ContentType.ShortPassage, passage.getId(), user))) {
            Vote v = new Vote();
            v.setContentId(passage.getId());
            v.setSource(hostname);
            if (user != null) {
                v.setUser(user);
            }

            v.setValue(-1);
            v.setContentTypeId(ContentType.ShortPassage);

            Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
            v.setCreatedAt(now);

            voteDAO.doSave(v);
            //update the vote
            votes--;


            JSONObject json = new JSONObject();
            json.put("vote", "down");

            //return new TextStreamResponse("text/json", json.toString());
            return downSuccess;
        } else {
            return voted;
        }
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
            f.setContentType(ContentType.ShortPassage);

            f.setStatus(FlagStatus.NEW);
//          f.setArticle(article);
            f.setshortpassage(passage);


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
