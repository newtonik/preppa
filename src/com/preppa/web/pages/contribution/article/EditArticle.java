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
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.apache.tapestry5.FieldTranslator;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.ValidationException;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.chenillekit.tapestry.core.components.Editor;

/**
 *
 * @author newtonik
 */
@IncludeJavaScriptLibrary("context:js/Chenillekit.js")
public class EditArticle {


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
    @Property
    private List<Topic> addedTopics = new LinkedList<Topic>();
    @Inject
    private TestsubjectDAO testsubjectDAO;
    @Property
    private String topicks;
    @Property
    private String fTitle;
    @Property
    private String fBody;
    @Property
    private String fSource;
    @Property
    private String fTag;




    void Article(Integer id) {

       Set setItems = new LinkedHashSet(testsubjectDAO.findAll());
       testsubjects.addAll(setItems);
            addedTopics =  topicDAO.findAll();

    }

    void setupRender() {

    }
    void onActivate(int id) {
         this.article = articleDAO.findById(id);
       if(this.article != null) {
           this.fBody = article.getBody();
           this.fSource = article.getSources();
           this.fTitle = article.getTitle();
           this.fTag = article.getTags();
           this.addedTopics = article.getTopics();
           this.testsubject = article.getTestsubject();
       }

        this.top = new Topic();
        
    }

    Object onPassivate() {
        return article;
    }


    @CommitAfter
    Object onSuccessFromArticleForm() {
               //article = new Article();
         article.setBody(fBody);
         article.setTitle(fTitle);
         article.setTestsubject(testsubject);
         article.setTeaser(fTitle);
         article.setSources(fSource);
         article.setTags(fTag);

         for(Topic e: addedTopics) {
            if(!(article.getTopics().contains(e)))
            {
                article.getTopics().add(e);
            }
          //  article.setTopics(tset);

         }
         System.out.println(article.getTitle());
            Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());

         article.setCreatedAt(now);
         article.setUpdatedAt(now);

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


       List<Topic> onProvideCompletionsFromAutocomplete(String partial) {
        List<Topic> matches = topicDAO.findByPartialName(partial);
        return matches;

    }

    public FieldTranslator getTranslator()
  {
    return new FieldTranslator<Topic>()
    {
      public String toClient(Topic value)
      {
        String clientValue = "0";
        if (value != null)
          clientValue = String.valueOf(value.getId());

        return clientValue;
      }

      public void render(MarkupWriter writer) { }

      public Class<Topic> getType() { return Topic.class; }

      public Topic parse(String clientValue) throws ValidationException
      {
        Topic serverValue = null;

        if (clientValue != null && clientValue.length() > 0 && !clientValue.equals("0")) {
            System.out.println(clientValue);
          serverValue = topicDAO.findById(new Integer(clientValue));
        }
        return serverValue;
      }
    };
  }

}
