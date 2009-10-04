package com.preppa.web.pages.contribution.gridin;

import com.preppa.web.data.GridinDAO;
import com.preppa.web.data.QuestionDAO;
import com.preppa.web.data.UserObDAO;
import com.preppa.web.data.VoteDAO;
import com.preppa.web.entities.Flag;
import com.preppa.web.entities.Gridin;
import com.preppa.web.entities.GridinAnswer;
import com.preppa.web.entities.Tag;
import com.preppa.web.entities.User;
import com.preppa.web.entities.Vote;
import com.preppa.web.utils.ContentFlag;
import com.preppa.web.utils.ContentType;
import com.preppa.web.utils.FlagStatus;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;

/**
 *
 * @author nwt
 */
@IncludeStylesheet(value = {"context:styles/flag.css"})
@IncludeJavaScriptLibrary(value = {"context:js/gridin.js", "context:js/showquestion.js"})
public class ShowGridin {
    @ApplicationState
    private User user;
    @Property
    private Gridin question;
    @Inject
    private GridinDAO gridinDAO;
    @Property
    private GridinAnswer answer;
    private Long gridId;
    @Inject
    private VoteDAO voteDAO;
    /*@InjectComponent
    private Zone voteupZone;
    @Inject
    private Block voteBlock;
    @Property
    @Persist 
    private Integer votes;*/
    @Inject
    private HttpServletRequest _request;
    /*@Inject
    private Block upSuccess;
    @Inject
    private Block downSuccess;
    @Inject
    private Block voted;*/
    @Property
    private ContentType contType;
    @Property
    private List<Tag> tags;

    //Flags
    @Component
    private Form flagform;
    @Inject
    private Block flagresponse;
    private List<Flag> questionflags;
    @Inject
    @Property
    private Block flagblock;
    @Property
    private String reason;
    @Property
    private String reasonDesc;
    @Property
    private User author;
    @Inject
    private UserObDAO userDAO;

    void onActivate(Long id)  {
        if(id > 0) {
            question = gridinDAO.doRetrieve(id, false);
            answer = question.getAnswers().get(0);
            this.gridId = id;
            //this.votes = voteDAO.findSumByGridInId(question.getId().intValue());
            //votes = question.getVoteScore();
            contType = ContentType.GridIn;
            tags = question.getTaglist();
        }
    }

    Long onPassivate() {
        return gridId;
    }

    public void setGridin(Gridin question) {
        this.question = question;
        this.gridId = question.getId();
    }

    
    /*Block onActionFromVoteUp() {
     String  hostname = _request.getRemoteHost();
     if(!(voteDAO.checkVoted(ContentType.GridIn, question.getId().intValue(), user)))
     {
         Vote v = new Vote();
         v.setContentId(question.getId().intValue());
         v.setSource(hostname);
         if(user != null)
             v.setUser(user);

         v.setValue(1);
          v.setContentTypeId(ContentType.GridIn);

         Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
         v.setCreatedAt(now);

         voteDAO.doSave(v);

         JSONObject json = new JSONObject();
         json.put("vote", "down");
         //decrement the vote
         votes++;

         return upSuccess;
     }//return new TextStreamResponse("text/json", json.toString());
     else
     {
         return voted;
     }
 }
    Block onActionFromVoteDown() {

     String  hostname = _request.getRemoteHost();
    // System.out.println(_request.getRequestURL());

     if(!(voteDAO.checkVoted(ContentType.GridIn, question.getId().intValue(), user)))
     {
         Vote v = new Vote();
         v.setContentId(question.getId().intValue());
         v.setSource(hostname);
         if(user != null)
             v.setUser(user);

         v.setValue(-1);
         v.setContentTypeId(ContentType.Question);

         Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
         v.setCreatedAt(now);

         voteDAO.doSave(v);
         //update the vote
         votes--;


         JSONObject json = new JSONObject();
         json.put("vote", "down");

     //return new TextStreamResponse("text/json", json.toString());
         return downSuccess;
     }
     else
     {
         return voted;
     }
 }*/

    @CommitAfter
    @Secured("ROLE_USER")
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
            f.setContentType(ContentType.Question);

            author = userDAO.doRetrieve(user.getId(), false);
            System.out.println("author id is " + user.getId());
            f.setFlagger(author);
            f.setStatus(FlagStatus.NEW);
            f.setGridin(question);



            if (questionflags == null) {
                questionflags = new ArrayList<Flag>();
                questionflags.add(f);
                question.setFlags(questionflags);

            } else {
                //questionflags.add(f);
                question.getFlags().add(f);

            }
            Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());

            f.setUpdatedAt(now);
            f.setCreatedAt(now);

            //flagDAO.doSave(f);
            //question.setUpdatedAt(now);
            gridinDAO.doSave(question);

        }

        return flagresponse;
    }

    Block onActionFromRemoveFlagBox() {
        return null;
    }

    Block onActionFromCloseFlagBlock() {
        return flagblock;
    }
}
