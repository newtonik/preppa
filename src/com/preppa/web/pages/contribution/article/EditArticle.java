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
import org.apache.tapestry5.Block;
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
@IncludeJavaScriptLibrary(value = {"context:js/article.js", "context:js/confirmexit.js"})
public class EditArticle {

    @ApplicationState
    private User user;
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
    private List<Testsubject> testsubjects;
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
    @Property
    private Integer aid;
    @Component
    private AutoComplete autoCompleteTag;
    @Inject
    private TagDAO tagDAO;
    @Component
    private Form articleform;
    @Component(parameters = {"value=testsubject", "event=change",
        "onCompleteCallback=literal:onChangeTestsubject"})
    @Mixins({"ck/OnEvent"})
    private Select select1;
    @Inject
    private Logger logger;
    @Property
    private String fComment;
    @Property
    private String fname;
    @Property
    private Tag tag;
    @Inject
    @Property
    private Block newtagblock;
    @Inject
    @Property
    private Block newtopicblock;
    @Property
    private Testsubject topicSubject;
    @Component(parameters = {"value=topicSubject"})
    private Select select2;
    @Component
    private Form topicform;
    @Property
    private String fTopic;
    @Property
    private String fTopicName;

    void Article(Integer id) {

        Set setItems = new LinkedHashSet(testsubjectDAO.findAll());
        testsubjects.addAll(setItems);
        addedTopics = topicDAO.findAll();

    }

    void onActivate(int id) {

        this.article = articleDAO.findById(id);
        Set setItems = new LinkedHashSet(testsubjectDAO.findAll());
        testsubjects = new ArrayList<Testsubject>();
        testsubjects.addAll(setItems);

        if (this.article != null) {
            this.fBody = article.getBody();
            this.fSource = article.getSources();
            this.fTitle = article.getTitle();
            this.addedTopics = article.getTopics();
            this.addedTags = article.getTaglist();
            this.testsubject = article.getTestsubject();
            this.aid = article.getId();

        }

        this.top = new Topic();

    }

    Integer onPassivate() {
        return article.getId();
    }

    void onValidateForm() {
        if (testsubject == null) {
            articleform.recordError("Articles require a Test Subject");
        }
        if (addedTopics.size() == 0) {
            articleform.recordError("Articles should have a topic.");
        }
    }

    /**
     * This method respons to the onChange event from the select component
     * @param c
     * @return a response
     */
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
        //article = new Article();
        article.setBody(fBody);
        article.setTitle(fTitle);
        article.setTestsubject(testsubject);
        article.setTeaser(fTitle);
        article.setSources(fSource);
        article.setRevComment(fComment);

        for (Topic e : addedTopics) {
            if (!(article.getTopics().contains(e))) {
                article.getTopics().add(e);
            }
            //  article.setTopics(tset);

        }
        for (Tag t : addedTags) {
            if (!(article.getTaglist().contains(t))) {
                article.getTaglist().add(t);
            }
            //  article.setTopics(tset);

        }
        if (user != null) {
            article.setUpdatedBy(user);
        }
        System.out.println(article.getTitle());
        Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());

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
        for (Topic t : matches) {
            result.add(t.getName());
        }
        return result;
    }

    List<Topic> onProvideCompletionsFromAutocomplete(String partial) {
        List<Topic> matches = topicDAO.findByPartialName(partial);
        for (Topic t : matches) {
            if (addedTopics.contains(t)) {
                matches.remove(t);
            }
        }
        return matches;

    }

    List<Tag> onProvideCompletionsFromAutocompleteTag(String partial) {
        List<Tag> matches = tagDAO.findByPartialName(partial);
        for (Tag t : matches) {
            if (addedTags.contains(t)) {
                matches.remove(t);
            }
        }
        return matches;

    }

    public FieldTranslator getTranslator() {
        return new FieldTranslator<Topic>() {

            public String toClient(Topic value) {
                String clientValue = "0";
                if (value != null) {
                    clientValue = String.valueOf(value.getId());
                }

                return clientValue;
            }

            public void render(MarkupWriter writer) {
            }

            public Class<Topic> getType() {
                return Topic.class;
            }

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

            public String toClient(Tag value) {
                String clientValue = "0";
                if (value != null) {
                    clientValue = String.valueOf(value.getName());
                }

                return clientValue;
            }

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
                System.out.println(clientValue);

                if (clientValue != null && clientValue.length() > 0 && !clientValue.equals("0")) {
                    System.out.println(clientValue);
                    serverValue = tagDAO.findByName(clientValue).get(0);
                }
                return serverValue;
            }
        };
    }

    //Funtions for adding new tags and topics
    @CommitAfter
    JSONObject onSuccessFromTagForm() {
        List<Tag> tolist = tagDAO.findByName(fname);
        JSONObject json = new JSONObject();
        System.out.print(tolist);
        if (tolist.size() > 0) {
            String markup = "<p>  <b>" + fname +
                    "</b> already exists. <p>";
            json.put("content", markup);

        } else {
            tag = new Tag();
            tag.setName(fname);

            tagDAO.doSave(tag);
            String markup = "<p> You just submitted <b>" + tag.getName() +
                    "</b>. Please add it using the dropdown <p>";
            json.put("content", markup);

        }


        // return new TextStreamResponse("text/json", json.toString());
        return json;
    }

    @CommitAfter
    JSONObject onSuccessFromTopicForm() {
        JSONObject json = new JSONObject();
        System.out.println("trying to save " + fTopicName);
        Topic topic = new Topic();
        topic.setName(fTopicName);
        topic.setTestsubject(topicSubject);

        if (topicDAO.findSizeByName(fTopicName, topicSubject) > 0) {
            String markup = "<p> There is already a <b>" + fTopicName +
                    "</b> topic in " + topicSubject.getName() + " Section.<p>";
            json.put("content", markup);


        } else {
            Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());

            topic.setCreatedAt(now);
            topic.setUpdatedAt(now);
            System.out.println("Topic is being saved");
            topicDAO.doSave(topic);
            String markup = "<p> You just submitted <b>" + topic.getName() +
                    "</b>. Please add it using the topics autocomplete. <p>";
            json.put("content", markup);

        }
        return json;
    }

    Block onActionFromCloseTag() {
        return newtagblock;
    }

    Block onActionFromCloseTopic() {
        return newtopicblock;

    }
}
