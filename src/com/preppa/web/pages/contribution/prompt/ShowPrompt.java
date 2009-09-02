  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.pages.contribution.prompt;

import com.preppa.web.data.PromptDAO;
import com.preppa.web.data.VoteDAO;
import com.preppa.web.entities.Flag;
import com.preppa.web.entities.Prompt;
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
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.IncludeStylesheet;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;

/**
 *
 * @author Jan Jan
 */
@IncludeStylesheet(value = {"context:styles/flag.css"})
@IncludeJavaScriptLibrary(value = {"context:js/flagform.js"})
public class ShowPrompt {
    @ApplicationState
    private User user;

    @Property
    private Prompt prompt;
    @Inject
    private PromptDAO promptDAO;
    private Integer pid;
    private List<Flag> flags;
    @Property
    private List<Tag> tags;
    @Property
    @Persist
    private String reason;
    @Inject
    @Property
    private Block flagresponse;
    @Inject
    @Property
    private Block flagblock;
    @Property
    private String reasonDesc;
    @Property
    private String type;
    @Inject
    private HttpServletRequest _request;
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
    @Property
    private ContentType contType;
    
    void onActivate(int id) {
        if (id > 0) {
            prompt = promptDAO.findById(id);
            this.pid = id;
            if (prompt != null) {
                pid = prompt.getId();
                flags = prompt.getFlags();
                tags = prompt.getTaglist();
                this.votes = voteDAO.findVoteByContentId(ContentType.Prompt, prompt.getId());
            }
            type = prompt.getTopic();
        }
        contType = ContentType.Prompt;
    }

    void setprompt(Prompt prompt) {
        this.prompt = prompt;
        this.pid = prompt.getId();
    }

    Integer onPassivate() {
        return this.pid;

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
            f.setContentType(ContentType.Prompt);
            f.setFlagger(user);
            f.setStatus(FlagStatus.NEW);
            f.setPrompt(prompt);



            if (flags == null) {
                flags = new ArrayList<Flag>();
                flags.add(f);
                prompt.setFlags(flags);

            } else {
                //vocabflags.add(f);
                prompt.getFlags().add(f);

            }
            Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());

            f.setUpdatedAt(now);
            f.setCreatedAt(now);

            prompt.setUpdatedAt(now);

        }
        promptDAO.doSave(prompt);
        return flagresponse;
    }
  Block onActionFromCloseFlagBlock() {
      return flagblock;
  }



 Block onActionFromVoteUp() {
     String  hostname = _request.getRemoteHost();
     if(!(voteDAO.checkVoted(ContentType.Prompt, prompt.getId(), user)))
     {
         Vote v = new Vote();
         v.setContentId(prompt.getId());
         v.setSource(hostname);
         if(user != null)
             v.setUser(user);

         v.setValue(1);
          v.setContentTypeId(ContentType.Prompt);

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

     if(!(voteDAO.checkVoted(ContentType.Prompt, prompt.getId(), user)))
     {
         Vote v = new Vote();
         v.setContentId(prompt.getId());
         v.setSource(hostname);
         if(user != null)
             v.setUser(user);

         v.setValue(-1);
         v.setContentTypeId(ContentType.Prompt);

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