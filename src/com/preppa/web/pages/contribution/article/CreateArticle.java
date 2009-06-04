/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.pages.contribution.article;

import com.preppa.web.data.ArticleDAO;
import com.preppa.web.data.TestsubjectDAO;
import com.preppa.web.data.TopicDAO;
import com.preppa.web.entities.Article;
import com.preppa.web.entities.Testsubject;
import com.preppa.web.entities.Topic;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.chenillekit.tapestry.core.components.Editor;

/**
 *
 * @author newtonik
 */
public class CreateArticle {

    @Property
    private Article article;
    @Property
    private String title;
    @InjectPage
    private ShowArticle showarticle;
    @Inject
    private ArticleDAO articleDAO;
    @Inject
    private TopicDAO topicDAO;
    @Component(parameters = {"value=FBody"})
    private Editor body;
    private int size;
    @Property
    private Testsubject testsubject;
    private List<Testsubject> testsubjects;
    @Property
    private Topic top;
    @Inject
    private TestsubjectDAO testsubjectDAO;
    @Property
    private String topicks;
    @Property
    private String fTitle;
    @Property
    private String fBody;
   
    void Article() {
       this.article = new Article();
      //  Set setItems = new LinkedHashSet(testsubjectDAO.findAll());
        
    }
    void onActivate(Article article) {
        article.setTitle("");
        this.top = new Topic();
        this.article = article;
    }

    Object onPassivate() {
        return article;
    }

    @CommitAfter
    Object onSuccess() {
         Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
         this.article = new Article();
        article.setCreatedAt(now);
         article.setUpdatedAt(now);
         article.setBody(fBody);
         article.setTitle(fTitle);
         article.setTestsubject(testsubject);
         article.setTeaser(fTitle);
         System.out.println(article.getTitle());

       //  article.setBody(sanitize(article.getBody()));
//         if(article.getBody().length() < 100 )
//             size = article.getBody().length();
//         else
//             size = 100;


//         article.setTeaser(article.getBody().substring(0, size));
         //article.setUser(userDAO.findById(1));

         articleDAO.doSave(article);
         showarticle.setarticle(article);
         return showarticle;
    }
    public static String sanitize(String string) {
    return string
     .replaceAll("(?i)<script.*?>.*?</script.*?>", "")   // case 1
     .replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "") // case 2
     .replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "");     // case 3
    }

        /**
     * @return the testsubjects
     */
    public List<Testsubject> getTestsubjects() {
        this.setTestsubjects(testsubjectDAO.findAll());
        return testsubjects;
    }

    /**
     * @param testsubjects the testsubjects to set
     */
    public void setTestsubjects(List<Testsubject> testsubjects) {
        this.testsubjects = testsubjects;
    }

    List<String> onProvideCompletionsFromTopicks(String partial) {
        List<Topic> matches = topicDAO.findByPartialName(partial);

        List<String> result = new ArrayList<String>();
        for(Topic t : matches)
        {
            result.add(t.getName());
        }
        return result;
    }
}
