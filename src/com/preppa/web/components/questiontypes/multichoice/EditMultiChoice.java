    package com.preppa.web.components.questiontypes.multichoice;

import com.preppa.web.data.QuestionDAO;
import com.preppa.web.data.TagDAO;
import com.preppa.web.data.TopicDAO;
import com.preppa.web.entities.Question;
import com.preppa.web.entities.QuestionAnswer;
import com.preppa.web.entities.Tag;
import com.preppa.web.entities.Testsubject;
import com.preppa.web.entities.Topic;
import com.preppa.web.entities.User;
import com.preppa.web.pages.Index;
import com.preppa.web.pages.contribution.question.ShowQuestion;
import com.preppa.web.utils.InjectSelectionModel;
import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.FieldTranslator;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.ValidationException;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.IncludeStylesheet;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.RadioGroup;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.Context;
import org.apache.tapestry5.upload.components.Upload;
import org.apache.tapestry5.upload.services.UploadedFile;
import org.chenillekit.tapestry.core.components.Editor;
import org.chenillekit.tapestry.core.components.RatingField;
import org.chenillekit.tapestry.core.components.prototype_ui.AutoComplete;
import org.slf4j.Logger;

/**
 *
 * @author nwt
 */
@IncludeStylesheet(value = {"context:styles/question.css"})
@IncludeJavaScriptLibrary(value = {"context:js/jquery-1.3.2.js", "context:js/jquery/jquery.form.js", "context:js/multiplequestion.js", "context:js/question.js", "context:js/confirmexit.js"})
public class EditMultiChoice {
     @ApplicationState
    private User user;
    //@Property
    //private Question question;
    @Inject
    private QuestionDAO questionDAO;
    @Component(parameters = {"value=fQuestion"})
    private Editor questioneditor;
    @InjectPage
    private Index indexpage;
    @Property
    private String fQuestion;
    @Property
    private String fExplanation;
    @Property
    private String fTag;
    @Component(parameters = {"value=ans1"})
    private Editor choice1;
    @Component(parameters = {"value=ans2"})
    private Editor choice2;
    @Component(parameters = {"value=ans3"})
    private Editor choice3;
    @Component(parameters = {"value=ans4"})
    private Editor choice4;
    @Component(parameters = {"value=ans5"})
    private Editor choice5;
    @Component(parameters = {"value=ratingValue"})
    private RatingField ratingField;
    @Property
    private Integer ratingValue;
    @Property
    private String ans1;
    @Property
    private String ans2;
    @Property
    private String ans3;
    @Property
    private String ans4;
    @Property
    private String ans5;
    @Property
    private String correct;
    @InjectPage
    private ShowQuestion showquestion;
    @Property
    private List<Tag> addedTags = new LinkedList<Tag>();
    @Inject
    private TagDAO tagDAO;
    @Component
    private AutoComplete autoCompleteTag;
    @Property
    private Boolean mywork;
    @Component
    private Form editquestionform;
    @Component
    private RadioGroup answergroup;
    @Property
    private String fname;
    @Property
    private Tag tag;
    @Parameter(required = true)
    @Property
    private Question question;

    @Property
    private String fComment;
    @Property
    private String hasimage;
    @Property
    private UploadedFile imageupload;
    @Component
    private Upload upload;
    @Inject
    private Context context;
    @Property
    private String imgpath;
    @Property
    private List<QuestionAnswer> choices;

    //Topics
    @Inject
    private TopicDAO topicDAO;
    @Property
    private Topic top;
    @Property
    private List<Topic> addedTopics = new LinkedList<Topic>();
    @Property
    private Testsubject topicSubject;
     @Property
    private String fTopic;
    @Property
    private String fTopicName;
    @InjectSelectionModel(labelField = "name", idField = "id")
    private List<Testsubject> testsubjects1 = new ArrayList<Testsubject>();
    @Component
    private AutoComplete autoCompleteMultiTopics;
    @Property
    @Persist
    private Testsubject testsubject1;
    @Inject
    private Logger logger;
    
    void CreateQuestion() {
        //question = new Question();
    }

    @SetupRender
    void setQuestionParameter() {

        if (question != null) {
            System.out.println("Seting Questions");
            this.question = questionDAO.findById(question.getId());
            fQuestion = question.getQuestion();
            fExplanation = question.getExplanation();
            choices = question.getChoices();
            ans1 = choices.get(0).getAnswer();
            ans2 = choices.get(1).getAnswer();
            ans3 = choices.get(2).getAnswer();
            ans4 = choices.get(3).getAnswer();
            ans5 = choices.get(4).getAnswer();
            addedTags = question.getTaglist();
            ratingValue = question.getDifficulty();
            correct = question.getCorrectAnswer();
            addedTopics = question.getTopics();
            System.out.println("Question is " + question);
            if(question.getImage())
            {
                hasimage = "yes";
                imgpath = question.getImagePath();
            }
            else
            {
                hasimage = "no";
            }

        }

    }
    /*
    void onActivate(int id) {
    this.question = questionDAO.findById(id);
    if (question != null) {
    fQuestion = question.getQuestion();
    fExplanation = question.getExplanation();
    ans1 = question.getChoices().get(0).getAnswer();
    ans2 = question.getChoices().get(1).getAnswer();
    ans3 = question.getChoices().get(2).getAnswer();
    ans4 = question.getChoices().get(3).getAnswer();
    ans5 = question.getChoices().get(4).getAnswer();
    addedTags = question.getTaglist();
    ratingValue = question.getDifficulty();
    correct = question.getCorrectAnswer();
    }
    }
     */

    Integer onPassivate() {
        return question.getId();
    }

    void onValidateFormFromEditQuestionForm() {

        if (mywork == false) {
            editquestionform.recordError("You must verify that this is your own work.");
        }

        if ((correct == null)) {
            editquestionform.recordError(answergroup, "You did not specify an answer.");
        }

        if (ratingValue == null) {
            editquestionform.recordError(ratingField, "You need to select a difficulty");
        }
    }

    @CommitAfter
    Object onSuccessFromEditQuestionForm() {

        question.setExplanation(fExplanation);
        question.setQuestion(fQuestion);
        if (ans1.length() > 0) {
            question.getChoices().get(0).setAnswer(ans1);

        }
        if (ans2.length() > 0) {
            question.getChoices().get(1).setAnswer(ans2);
        }
        if (ans3.length() > 0) {
            question.getChoices().get(2).setAnswer(ans3);
        }
        if (ans4.length() > 0) {
            question.getChoices().get(3).setAnswer(ans4);
        }
        if (ans5.length() > 0) {
            question.getChoices().get(4).setAnswer(ans5);
        }
        for (Tag t : addedTags) {
            if (!(question.getTaglist().contains(t))) {
                question.getTaglist().add(t);
            }
        }
       
        //question.setUser(user);
        question.setUpdatedBy(user);
        question.setRevComment(fComment);
        question.setDifficulty(ratingValue);
        question.setTopics(addedTopics);
        Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
        question.setUpdatedAt(now);
        questionDAO.doSave(question);
    
        if(hasimage.equals("yes")) {
           String impath = context.getRealFile("/").getPath() + "/images/multiplechoice/question" + question.getId() + "/"+ question.getId() + ".jpg";
            System.out.println(impath);
            boolean status = new File(context.getRealFile("/").getPath() + "/images/multiplechoice/question" + question.getId() ).mkdirs();
            File copied = new File(impath);
            imageupload.write(copied);
            question.setImagePath(impath);
            question.setImage(Boolean.TRUE);
       }
        showquestion.setquestion(question);
        return showquestion;
    }

    /**
     * Server side function for adding a tag dynamically
     * @return
     */
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

        return json;

    }

    List<Tag> onProvideCompletionsFromAutocompleteTag(String partial) {
        List<Tag> matches = tagDAO.findByPartialName(partial);
        return matches;

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
                System.out.println(clientValue);

                if (clientValue != null && clientValue.length() > 0 && !clientValue.equals("0")) {
                    System.out.println(clientValue);
                    serverValue = tagDAO.findByName(clientValue).get(0);
                }
                return serverValue;
            }
        };
    }


    public FieldTranslator getTranslator()
  {
    return new FieldTranslator<Topic>()
    {
            @Override
      public String toClient(Topic value)
      {
        String clientValue = "0";
        if (value != null)
          clientValue = String.valueOf(value.getId());

        return clientValue;
      }

            @Override
      public void render(MarkupWriter writer) { }

            @Override
      public Class<Topic> getType() { return Topic.class; }

            @Override
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

    List<Topic> onProvideCompletionsFromAutoCompleteMultiTopics(String partial) {
         List<Topic> matches = null;
        testsubject1 = question.getQuestiontype().getTestsubject();
        if(testsubject1 != null)
        {
            logger.warn("Test subject is not null");
            matches = topicDAO.findByPartialName(partial, testsubject1);
            logger.warn("Size is " + matches.size());
        }
        else
        {
            System.out.println("Partial is " + partial);
            matches = topicDAO.findByPartialName(partial);
        }
       // matches = topicDAO.findByPartialName(partial);
        return matches;

    }



        @CommitAfter
        JSONObject onSuccessFromTopicForm() {
            JSONObject json = new JSONObject();
            System.out.println("trying to save " + fTopicName);
          Topic topic = new Topic();
           topic.setName(fTopicName);
          topic.setTestsubject(topicSubject);
          System.out.println("Topic name and subject set.");
         if(topicDAO.findSizeByName(fTopicName, topicSubject) > 0) {
             String markup = "<p> There is already a <b>" + fTopicName +
                    "</b> topic in " + topicSubject.getName() + " Section.<p>";
                json.put("content", markup);


         }
         else
         {
             Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());

             topic.setCreatedAt(now);
             topic.setUpdatedAt(now);
             System.out.println("Topic is being saved");
             System.out.println("Topic is " + topicSubject);
            topicDAO.doSave(topic);
             String markup = "<p> You just submitted <b>" + topic.getName() +
                    "</b>. Please add it using the topics autocomplete. <p>";
               json.put("content", markup);

         }
          return json;
        }

}
