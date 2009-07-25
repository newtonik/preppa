/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.pages.contribution.article;

import com.preppa.web.data.ArticleDAO;
import com.preppa.web.data.VoteDAO;
import com.preppa.web.entities.Article;
import com.preppa.web.entities.Tag;
import com.preppa.web.entities.Topic;
import com.preppa.web.entities.User;
import com.preppa.web.entities.Vote;
import com.preppa.web.utils.ContentType;
import java.sql.Timestamp;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.RequestGlobals;

/**
 *
 * @author newtonik
 */
public class ShowArticle {
@ApplicationState
private User user;
@Property
private Article article;
@Inject
private ArticleDAO articleDAO;
@Property
private User author;
@Property
private String authorname;
@Property
private List<Tag> tags;
@Property
private List<Topic> topics;
@Inject
private RequestGlobals requestGlobals;
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
private Integer artId;

void onActivate(int id) {
    if(id > 0) {
            this.article = articleDAO.findById(id);
            this.author = article.getUser();
            this.tags = article.getTaglist();
            this.votes = voteDAO.findVoteByContentId(ContentType.Article, article.getId());
            this.topics = article.getTopics();
            
            if(author != null) {
                authorname = author.getUsername();
            }
            if(authorname == null)
            {
                authorname = "unknown dude";
            }
            this.artId = id;
    }
 }
 Integer onPassivate() {
     return artId;
 }
 /**
  * sets the article in the show page by finding the article by Title.
  * @param title
  */
 public void set(String title) {
        this.article = articleDAO.findByTitle(title);
        if(article != null) {
            this.author = article.getUser();
            if(author != null) {
                authorname = author.getUsername();
            }
            if(authorname == null)
            {
                authorname = "unknown dude";
            }
            this.artId = article.getId();
          }
 }

/**
 * Sets the article before the page is rendered.
 * @param article
 */
 public void setarticle(Article article) {
        this.article = articleDAO.findById(article.getId());
        if(article != null) {
            this.article = article;
            this.author = article.getUser();
                if(author != null) {
                authorname = author.getUsername();
            }

             if(authorname == null)
             {
                authorname = "unknown dude";
            }
            this.artId = article.getId();
        }
    }

 Block onActionFromVoteUp() {
     requestGlobals.getHTTPServletRequest();
     String  hostname = _request.getRemoteAddr();

     if(!(voteDAO.checkVoted(ContentType.Article, article.getId(), user)))
     {
         Vote v = new Vote();
         v.setContentId(article.getId());
         v.setSource(hostname);
         if(user != null)
             v.setUser(user);

         v.setValue(1);
          v.setContentTypeId(ContentType.Article);

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
     requestGlobals.getHTTPServletRequest();
     String  hostname = _request.getRemoteAddr();

     if(!(voteDAO.checkVoted(ContentType.Article, article.getId(), user)))
     {
         Vote v = new Vote();
         v.setContentId(article.getId());
         v.setSource(hostname);
         if(user != null)
             v.setUser(user);

         v.setValue(-1);
         v.setContentTypeId(ContentType.Article);

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