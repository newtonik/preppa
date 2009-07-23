/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.pages.contribution.article;

import com.preppa.web.data.ArticleDAO;
import com.preppa.web.data.VoteDAO;
import com.preppa.web.entities.Article;
import com.preppa.web.entities.Tag;
import com.preppa.web.entities.User;
import com.preppa.web.entities.Vote;
import java.sql.Timestamp;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.RequestGlobals;
import org.apache.tapestry5.util.TextStreamResponse;

/**
 *
 * @author newtonik
 */
public class ShowArticle {
@ApplicationState
private User user;
@Property
@Persist
private Article article;
@Inject
private ArticleDAO articleDAO;
@Property
private User author;
@Property
private String authorname;
@Property
private List<Tag> tags;
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

void onActivate(int id) {
            this.article = articleDAO.findById(id);
            this.author = article.getUser();
            this.tags = article.getTaglist();
            
            if(author != null) {
                authorname = author.getUsername();
            }
            if(authorname == null)
            {
                authorname = "unknown dude";
            }
            String  hostname = _request.getRemoteAddr();
            System.out.println("Hostname is " + hostname);
            
 }

 public void set(String title) {
        this.article = articleDAO.findByTitle(title);
        this.author = article.getUser();
        if(author != null) {
            authorname = author.getUsername();
        }
        if(authorname == null)
        {
            authorname = "unknown dude";
        }

 }


 public void setarticle(Article article) {
        this.article = articleDAO.findById(article.getId());

        this.article = article;
        this.author = article.getUser();
            if(author != null) {
            authorname = author.getUsername();
        }
    
         if(authorname == null)
         {
            authorname = "unknown dude";
        }

    }

 StreamResponse onActionFromVoteUp() {
     requestGlobals.getHTTPServletRequest();
     String  hostname = _request.getRemoteAddr();

     Vote v = new Vote();
     v.setContentId(article.getId());
     v.setContentType("article");
     v.setSource(hostname);
     if(user != null)
         v.setUser(user);
    
     v.setValue(1);

     Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
     v.setCreatedAt(now);

     voteDAO.doSave(v);

     return new TextStreamResponse("text/json", hostname);
 }
  StreamResponse onActionFromVoteDown() {
     requestGlobals.getHTTPServletRequest();
     String  hostname = _request.getRemoteAddr();

     Vote v = new Vote();
     v.setContentId(article.getId());
     v.setContentType("article");
     v.setSource(hostname);
     if(user != null)
         v.setUser(user);

     v.setValue(-1);

     Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
     v.setCreatedAt(now);

     voteDAO.doSave(v);

     return new TextStreamResponse("text/json", hostname);
 }
}