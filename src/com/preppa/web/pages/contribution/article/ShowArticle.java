/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.pages.contribution.article;

import com.preppa.web.data.ArticleDAO;
import com.preppa.web.entities.Article;
import com.preppa.web.entities.Flag;
import com.preppa.web.entities.Tag;
import com.preppa.web.entities.Topic;
import com.preppa.web.entities.User;
import com.preppa.web.utils.ContentType;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.IncludeStylesheet;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author newtonik
 */
@IncludeStylesheet(value = {"context:styles/flag.css"})
@IncludeJavaScriptLibrary(value = {"context:js/jquery-1.3.2.js", "context:js/passagetab.js"})
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
    private HttpServletRequest _request;
    @Property
    private String reason;
    @Property
    private String reasonDesc;
    @Property
    @Persist
    private Integer votes;
    private Integer artId;
    private List<Flag> articleflags;
    @Property
    private ContentType contType;

    void onActivate(int id) {
        contType = ContentType.Article;
        if (id > 0) {
            this.article = articleDAO.findById(id);
            this.author = article.getUser();
            this.tags = article.getTaglist();
            this.articleflags = article.getFlags();
            //this.votes = voteDAO.findVoteByContentId(ContentType.Article, article.getId());
            this.topics = article.getTopics();
            
            votes = 0;
            if (author != null) {
                authorname = author.getUsername();
            }
            if (authorname == null) {
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
        if (article != null) {
            this.author = article.getUser();
            if (author != null) {
                authorname = author.getUsername();
            }
            if (authorname == null) {
                authorname = "unknown dude";
            }
            this.artId = article.getId();
        }
    }

    public Date getcreatedAt() {
        return article.getCreatedAt();
    }

    public Date getUpdatedAt() {
        return article.getUpdatedAt();
    }

    /**
     * Sets the article before the page is rendered.
     * @param article
     */
    public void setarticle(Article article) {
        this.article = articleDAO.findById(article.getId());
        if (article != null) {
            this.article = article;
            this.author = article.getUser();
            if (author != null) {
                authorname = author.getUsername();
            }

            if (authorname == null) {
                authorname = "unknown dude";
            }
            this.artId = article.getId();
        }
    }

    /*Block onActionFromVoteUp() {
    String  hostname = _request.getRemoteHost();
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

    String  hostname = _request.getRemoteHost();
    // System.out.println(_request.getRequestURL());

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
    }*/
//  void onValidateFormFromFlagForm() {
//      if(reason == null) {
//          flagform.recordError("?");
//          System.out.println("You didn't select a reason");
//      }
//  }





}
