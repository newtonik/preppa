package com.preppa.web.pages.contribution.gridin;

import com.preppa.web.data.FlagDAO;
import com.preppa.web.data.GridinDAO;
import com.preppa.web.data.VoteDAO;
import com.preppa.web.entities.Gridin;
import com.preppa.web.entities.GridinAnswer;
import com.preppa.web.entities.User;
import com.preppa.web.entities.Vote;
import com.preppa.web.utils.ContentType;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;

/**
 *
 * @author nwt
 */
@IncludeJavaScriptLibrary(value = {"context:js/gridin.js"})
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
    @InjectComponent
    private Zone voteupZone;
    @Inject
    private Block voteBlock;
    @Property
    @Persist
    private Integer votes;
    @Inject
    private HttpServletRequest _request;
    @Inject
    private Block upSuccess;
    @Inject
    private Block downSuccess;
    @Inject
    private Block voted;
    @Property
    private ContentType contType;
    void onActivate(Long id)  {
        if(id > 0) {
            question = gridinDAO.doRetrieve(id.intValue(), false);
            answer = question.getAnswers().get(0);
            this.gridId = id;
            //this.votes = voteDAO.findSumByGridInId(question.getId().intValue());
            votes = question.getVoteScore();
            contType = ContentType.GridIn;
        }
    }

    Long onPassivate() {
        return gridId;
    }

    public void setGridin(Gridin question) {
        this.question = question;
        this.gridId = question.getId();
    }

    
    Block onActionFromVoteUp() {
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
 }
}
