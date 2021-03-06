package com.preppa.web.pages.contribution.gridin;

import com.preppa.web.data.GridinDAO;
import com.preppa.web.data.UserObDAO;
import com.preppa.web.data.VoteDAO;
import com.preppa.web.entities.Flag;
import com.preppa.web.entities.Gridin;
import com.preppa.web.entities.GridinAnswer;
import com.preppa.web.entities.Tag;
import com.preppa.web.entities.User;
import com.preppa.web.utils.Constants;
import com.preppa.web.utils.ContentType;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.IncludeStylesheet;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;

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
    private Block voteBlock; */
    @Property
    private Integer votes;
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
    @Property
    private Boolean approved;

    void onActivate(Long id)  {
        if(id > 0) {
            question = gridinDAO.doRetrieve(id, false);
            answer = question.getAnswers().get(0);
            this.gridId = id;
            this.votes = voteDAO.findSumByGridInId(question.getId().intValue());
            //votes = question.getVoteScore();
            contType = ContentType.GridIn;
            tags = question.getTaglist();
            if(votes >= Constants.getApprovalThreshhold())
            {
                approved = true;
            }
            else
                approved = false;
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


}
