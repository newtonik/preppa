/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.pages.contribution.article;

import com.preppa.web.data.ArticleDAO;
import com.preppa.web.data.TagDAO;
import com.preppa.web.data.TestsubjectDAO;
import com.preppa.web.data.TopicDAO;
import com.preppa.web.entities.Article;
import com.preppa.web.entities.Tag;
import com.preppa.web.entities.Testsubject;
import com.preppa.web.entities.Topic;
import com.preppa.web.entities.User;
import com.preppa.web.utils.InjectSelectionModel;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.FieldTranslator;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.ValidationException;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Mixins;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Select;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.util.TextStreamResponse;
import org.chenillekit.tapestry.core.components.Editor;
import org.chenillekit.tapestry.core.components.prototype_ui.AutoComplete;
import org.slf4j.Logger;
import org.springframework.security.annotation.Secured;

/**
 *
 * @author newtonik
 */
@Secured("ROLE_USER")
@IncludeJavaScriptLibrary(value = {"context:js/jquery-1.3.2.js", "context:js/article.js", "context:js/confirmexit.js"})
public class CreateArticle {

    @ApplicationState
    private User user;
    //Article data
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
    @Persist
    private Testsubject testsubject;
    @InjectSelectionModel(labelField = "name", idField = "id")
    private List<Testsubject> testsubjects = new ArrayList<Testsubject>();
    @Property
    private Topic top;
    @Property
    private List<Topic> addedTopics = new LinkedList<Topic>();
    @Property
    private List<Tag> addedTags = new LinkedList<Tag>();
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
    @Component
    private AutoComplete autoComplete;
    @Component
    private AutoComplete autoCompleteTag;
    @Component
    private Form articleform;
    private List<Integer> addTagIds;
    @Persist
    @Property
    private Boolean vote;
    //Topic Form Data
    @Component(parameters = {"value=testsubject", "event=change",
        "onCompleteCallback=literal:onChangeTestsubject"})
    @Mixins({"ck/OnEvent"})
    private Select select1;
    @Inject
    private Logger logger;
    @Inject
    private TagDAO tagDAO;
    @Inject
    private ComponentResources resources;
    @InjectPage
    private com.preppa.web.pages.Index mainindexpage;

    public void onPrepare() {
        Set setItems = new LinkedHashSet(testsubjectDAO.findAll());
        testsubjects.clear();
        testsubjects.addAll(setItems);

    }

    void Article() {
        this.article = new Article();
        Set setItems = new LinkedHashSet(testsubjectDAO.findAll());
        testsubjects.addAll(setItems);



    }

    void setupRender() {
        resources.discardPersistentFieldChanges();
    }

    void onActivate(Article article) {
        article.setTitle("");
        this.top = new Topic();
        this.article = article;

    }

    Object onPassivate() {
        return article;
    }

    void onValidateFormFromArticleForm() {
        if (testsubject == null) {
            articleform.recordError("Articles require a Test Subject");
        }
        if (addedTopics.size() == 0) {
            articleform.recordError("Articles should have a topic.");
        }

    }

    public StreamResponse onChangeFromSelect1(String c) {
        logger.debug("TestSubject Id = " + c);
        //JSONObject json = new JSONObject();
        if (c != null) {

            testsubject = testsubjectDAO.findById(Integer.parseInt(c));
            JSONObject json = new JSONObject();
            json.put("testsubject", testsubject.getName());

            return new TextStreamResponse("text/json", json.toString());

        }
        return null;

    }

    @CommitAfter
    Object onSuccessFromArticleForm() {

        article = new Article();
        article.setBody(fBody);
        article.setTitle(fTitle);
        article.setTestsubject(testsubject);
        article.setTeaser(fTitle);
        article.setSources(fSource);


        //add new topics and verify no duplicates
        for (Topic e : addedTopics) {
            if (!(article.getTopics().contains(e))) {
                article.getTopics().add(e);
            }

        }
        for (Tag t : addedTags) {
            if (!(article.getTaglist().contains(t))) {
                article.getTaglist().add(t);
            }
            //  article.setTopics(tset);

        }
        article.setUser(user);
        article.setUpdatedBy(user);
        System.out.println(article.getTitle());
        Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());

        article.setCreatedAt(now);
        article.setUpdatedAt(now);

        articleDAO.doSave(article);

        showarticle.setarticle(article);
        return showarticle;
    }

    public static String sanitize(String string) {
        return string.replaceAll("(?i)<script.*?>.*?</script.*?>", "") // case 1
                .replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "") // case 2
                .replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "");     // case 3
    }

    void onSave() {
        System.out.println("Submit clicked");
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

//      List<String> onProvideCompletionsFromTags(String partial) {
//        List<Tag> matches = tagDAO.findByPartialName(partial);
//
//        List<String> result = new ArrayList<String>();
//        for(Tag t : matches)
//        {
//            if(addedTags.contains(t))
//            {
//                matches.remove(t);
//            }
//            else
//            {
//                result.add(t.getName());
//            }
//        }
//        return result;
//    }
    List<Topic> onProvideCompletionsFromAutocomplete(String partial) {
        List<Topic> matches = null;
        if (testsubject != null) {
            logger.warn("Test subject is not null");
            matches = topicDAO.findByPartialName(partial, testsubject);
            logger.warn("Size is " + matches.size());
        } else {
            matches = null;
        }
        // matches = topicDAO.findByPartialName(partial);
        return matches;

    }

    List<Tag> onProvideCompletionsFromAutocompleteTag(String partial) {
        List<Tag> matches = tagDAO.findByPartialName(partial);

        return matches;

    }

    public FieldTranslator getTranslator() {
        return new FieldTranslator<Topic>() {

            @Override
            public String toClient(Topic value) {
                String clientValue = "0";
                if (value != null) {
                    clientValue = String.valueOf(value.getId());
                }

                return clientValue;
            }

            @Override
            public void render(MarkupWriter writer) {
            }

            @Override
            public Class<Topic> getType() {
                return Topic.class;
            }

            @Override
            public Topic parse(String clientValue) throws ValidationException {
                Topic serverValue = null;

                if (clientValue != null && clientValue.length() > 0 && !clientValue.equals("0")) {
                    System.out.println(clientValue);
                    serverValue = topicDAO.findById(new Integer(clientValue));
                }
                return serverValue;
            }
        };
    }

    public FieldTranslator getTagTranslator() {
        return new FieldTranslator<Tag>() {

            @Override
            public String toClient(Tag value) {
                String clientValue = "0";
                if (value != null) {
                    clientValue = String.valueOf(value.getName());
                }

                return clientValue;
            }

            @Override
            public void render(MarkupWriter writer) {
            }

            @Override
            public Class<Tag> getType() {
                return Tag.class;
            }

            @Override
            public Tag parse(String clientValue) throws ValidationException {
                Tag serverValue = null;
//            if(clientValue == null) {
//                Tag t = new Tag();
//                t.setName(clientValue);
//            }


                if (clientValue != null && clientValue.length() > 0 && !clientValue.equals("0")) {
                    System.out.println(clientValue);
                    serverValue = tagDAO.findByName(clientValue).get(0);
                }
                return serverValue;
            }
        };
    }
     Object onActionFromCancel() {

        return mainindexpage;
    }
}
