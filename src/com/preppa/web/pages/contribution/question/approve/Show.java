  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.pages.contribution.question.approve;

import com.preppa.web.components.questiontypes.multichoice.ShowMultiChoice;
import com.preppa.web.data.QuestionDAO;
import com.preppa.web.data.VoteDAO;
import com.preppa.web.entities.Question;
import com.preppa.web.entities.QuestionAnswer;
import com.preppa.web.entities.User;
import com.preppa.web.entities.Vote;
import com.preppa.web.utils.ContentType;
import java.sql.Timestamp;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;

/**
 *
 * @author Jan Jan
 */
public class Show {
    @ApplicationState
    private User user;
    @Inject
    private QuestionDAO questionDAO;
    @Property
    private Question question;
    @Inject
    private VoteDAO voteDAO;
    @Property
    @Persist
    private Integer votes;
    private Integer qid;
    @Inject
    private HttpServletRequest _request;
    @Inject
    private Block upSuccess;
    @Inject
    private Block voted;
    @Inject
    private Block downSuccess;
    @Component
    private ShowMultiChoice showquestion;
    @Property
    private String qType;

    void onActivate(int id) {
        if (id > 0) {
            question = questionDAO.doRetrieve(id, false);
            if (this.question != null) {
                if (question.getQuestiontype() != null) {
                    qType = question.getQuestiontype().getName();
                }
                qid = id;
                //this.votes = voteDAO.findVoteByContentId(ContentType.Question, question.getId());
                this.votes = voteDAO.findSumByQuestionId(question.getId());
                System.out.println("Votes" + votes);
            }
        }
    }

    Integer onPassivate() {
        return this.qid;
    }

    public List<QuestionAnswer> getAllAnswers() {
        List<QuestionAnswer> returnVal;

        returnVal = question.getChoices();

        return returnVal;
    }

    public void setquestion(Question question) {
        this.question = question;
        this.qid = question.getId();
    }

    Block onActionFromVoteUp() {
        String  hostname = _request.getRemoteHost();
     if(!(voteDAO.checkVoted(ContentType.Question, question.getId(), user)))
        {
           Vote v = new Vote();
           v.setContentId(question.getId());
           v.setSource(hostname);
           if(user != null)
               v.setUser(user);

           v.setValue(1);
            v.setContentTypeId(ContentType.Question);

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

     if(!(voteDAO.checkVoted(ContentType.Question, question.getId(), user)))
     {
         Vote v = new Vote();
         v.setContentId(question.getId());
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
