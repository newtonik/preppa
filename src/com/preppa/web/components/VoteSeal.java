package com.preppa.web.components;

import com.preppa.web.data.VoteDAO;
import com.preppa.web.entities.User;
import com.preppa.web.entities.Vote;
import com.preppa.web.utils.ContentType;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.springframework.security.annotation.Secured;

/**
 *
 * @author nwt
 */
@IncludeJavaScriptLibrary(value = {"context:js/vote.js"})
public class VoteSeal {
    @ApplicationState
    private User user;
    @Persist
    @Property
    private Integer count;
    @Property
    @Persist
    private String voted;
    @Parameter(required=true)
    private Integer contentId;
    @Parameter(required=true)
    private ContentType contenttype;
    @Parameter(required=true)
    private String votes;
    @Parameter
    private Boolean hasVoted;
    @Persist
    private Boolean up;
    @Inject
    private VoteDAO voteDAO;
    @Inject
    private HttpServletRequest _request;
    private int ctype;
    @SetupRender
    void setDefault() {


            this.count = voteDAO.findVoteByContentId(contenttype, contentId);

        if(count == null) {
            count = 0;
        }
 

        voted = "false";
        up = false;
    }
    @CommitAfter
    @Secured("ROLE_USER")
    JSONObject onActionFromVoteUp() {
        System.out.println("Clicked");
        JSONObject json = new JSONObject();
        if(voted.equals("false") ) {
            count++;
            voted = "true";
            up = true;
            json.put("voted", "true");
            json.put("count", count);
            Vote v = new Vote();
            v.setContentId(contentId);
            v.setContentTypeId(ContentType.OpenQuestion);
            v.setValue(1);
            v.setUser(user);
            Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
            v.setCreatedAt(now);
            String  hostname = _request.getRemoteHost();
            v.setSource(hostname);
            voteDAO.doSave(v);
        }
        else if(up)
        {
            count--;
            voted = "false";
            json.put("voted", "false");
             json.put("count", count);
        }
        
        return json;
    }
    @CommitAfter
    @Secured("ROLE_USER")
    JSONObject onActionFromVoteDown() {
        System.out.println("Clicked");
        JSONObject json = new JSONObject();
        if(voted.equals("false")) {
            count--;
            voted = "true";
            up = false;
            json.put("voted", "true");
             json.put("count", count);
             Vote v = new Vote();
            v.setContentId(contentId);
            v.setContentTypeId(ContentType.OpenQuestion);
            v.setValue(-1);
            v.setUser(user);
            voteDAO.doSave(v);

        }
        else if(!up)
        {
            count++;
            voted = "false";
            json.put("voted", "false");
             json.put("count", count);
        }
        return json;
    }
}
