package com.preppa.web.components.questiontypes.gridin;

import com.preppa.web.data.GridinDAO;
import com.preppa.web.data.TagDAO;
import com.preppa.web.data.TestsubjectDAO;
import com.preppa.web.data.TopicDAO;
import com.preppa.web.entities.Gridin;
import com.preppa.web.entities.GridinAnswer;
import com.preppa.web.entities.Tag;
import com.preppa.web.entities.Testsubject;
import com.preppa.web.entities.Topic;
import com.preppa.web.entities.User;
import com.preppa.web.utils.InjectSelectionModel;
import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.FieldTranslator;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.ValidationException;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Mixins;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Radio;
import org.apache.tapestry5.corelib.components.RadioGroup;
import org.apache.tapestry5.corelib.components.Zone;
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
import org.springframework.security.annotation.Secured;

/**
 *
 * @author nwt
 */
@IncludeJavaScriptLibrary(value = {"context:js/gridin.js"})
@Secured("ROLE_USER")
public class NewGridin {

    @ApplicationState
    private User user;
    @Inject
    private GridinDAO gridinDAO;
    @Property
    private Gridin question;
    @Property
    private String fTitle;
    @Component
    private Form gridinForm;
    @Property
    private String fQuestion;
    @Component(parameters = {"value=fQuestion"})
    private Editor questionBody;
    private List<GridinAnswer> answers;
    @Property
    private String fRangehigh;
    @Property
    private String fRangelow;
    @Component
    private Form rangeform;
    @Component
    private Form singleform;
    @Component
    private RadioGroup gridinchooserange;
    @Component(parameters = {"event=onclick"})
    @Mixins({"ck/OnEvent"})
    private Radio gridinyesradio;
    @Component(parameters = {"event=onclick"})
    @Mixins({"ck/OnEvent"})
    private Radio gridinnoradio;
    @Component
    private RadioGroup gridinchooseimage;
    @Component(parameters = {"event=onclick"})
    @Mixins({"ck/OnEvent"})
    private Radio gridinyesimage;
    @Component(parameters = {"event=onclick"})
    @Mixins({"ck/OnEvent"})
    private Radio gridinnoimage;
    @Property
    private String answertype;
    @InjectComponent
    private Zone rangezone;
    @Inject
    @Property
    private Block rangeblock;
    @Property
    private String fSingle;
    @Inject
    private Block testblock;
    @Property
    private String fAnswer;
    @Property
    private String fDescription;
    @Component
    private RatingField ratingField;
    @Property
    private Integer ratingValue;
    @Property
    private String hasimage;
    @Property
    private UploadedFile imageupload;
    @Component
    private Upload gridinupload;
    @Inject
    private Context context;
    @InjectPage
    private com.preppa.web.pages.contribution.gridin.ShowGridin showgridin;
    @Component
    private AutoComplete autoCompletegridinTag;
    @Property
    private List<Tag> addedTags = new LinkedList<Tag>();
    @Inject
    private TagDAO tagDAO;

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
    private AutoComplete autoCompleteGridinTopics;
    @Property
    @Persist
    private Testsubject testsubject1;
    @Inject
    private TestsubjectDAO testsubjectDAO;
    @Inject
    private Logger logger;
    @Inject
    private ComponentResources resources;

    
    public void NewGridin() {
        this.question = new Gridin();
    }
    @SetupRender
    void setDefaults() {
        hasimage = "false";
        //answertype = "range";
        testsubject1 = testsubjectDAO.findByName("Mathematics");

        if(!gridinForm.getHasErrors())
        {
            addedTags.clear();
            addedTopics.clear();
        }
        
    }

    void onValidateFormFromGridinForm() {
        if(hasimage == null) {
            gridinForm.recordError(gridinchooseimage, "Please specify if you have an image");
        }
        if (answertype == null) {
            gridinForm.recordError("You need to select an answer");
        } else {
            if ((answertype.equals("single")) && (fAnswer == null)) {
                gridinForm.recordError("Please enter an answer!");
            }
            if ((answertype.equals("range")) && ((fRangehigh == null) || fRangelow == null)) {
                gridinForm.recordError("Please Enter the ranges for your answer below!");
            }

        }


    }

    @CommitAfter
    Object onSuccessFromGridinForm() {
        this.question = new Gridin();
        question.setTitle(fTitle);
        question.setQuestion(fQuestion);
        question.setUser(user);
        question.setUpdatedBy(user);
        question.setRating(ratingValue);


         for(Tag t: addedTags) {
            if(!(question.getTaglist().contains(t)))
            {
                question.getTaglist().add(t);
            }

         }
        GridinAnswer a = new GridinAnswer();
        if (answertype.equals("range")) {

            a.setRange(true);
            a.setDescription(fDescription);
            a.setHighAnswer(fRangehigh);
            a.setLowAnswer(fRangelow);
        } else {
            a.setRange(false);
            a.setAnswer(fAnswer);
            a.setDescription(fDescription);
        }
        if (question.getAnswers() == null) {
            List<GridinAnswer> answerlist = new ArrayList<GridinAnswer>();
            answerlist.add(a);
            question.setAnswers(answerlist);
        } else {
            question.getAnswers().add(a);
        }

         Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
        question.setUpdatedAt(now);
        question.setCreatedAt(now);
        gridinDAO.doSave(question);
        if(hasimage.equals("yes")) {
           String impath = context.getRealFile("/").getPath() + "/images/gridin" + question.getId() + ".jpg";
            System.out.println(impath);
            File copied = new File( context.getRealFile("/").getPath() + "/images/gridin"  + question.getId() + ".jpg");
            imageupload.write(copied);
            question.setImagePath(impath);
            question.setImage(Boolean.TRUE);
        }
         resources.discardPersistentFieldChanges();
        showgridin.setGridin(question);
        return showgridin;
    }

    Block onClickFromYesRadio() {
        System.out.println("Yes has been clicked");

        return rangeblock;
    }
  List<Tag> onProvideCompletionsFromAutocompleteGridinTag(String partial) {
        List<Tag> matches = tagDAO.findByPartialName(partial);

        return matches;

    }


       public FieldTranslator getTagTranslator()
    {
        return new FieldTranslator<Tag>()
        {
            @Override
          public String toClient(Tag value)
          {
                String clientValue = "0";
                if (value != null)
                clientValue = String.valueOf(value.getName());

                return clientValue;
          }

            @Override
          public void render(MarkupWriter writer) { }

            @Override
          public Class<Tag> getType() { return Tag.class; }

            @Override
          public Tag parse(String clientValue) throws ValidationException
          {
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


//Topic

    public void onPrepare(){
              Set setItems = new LinkedHashSet(testsubjectDAO.findAll());
                testsubjects1.clear();
              testsubjects1.addAll(setItems);

    }

    void Article() {
       Set setItems = new LinkedHashSet(testsubjectDAO.findAll());
       testsubjects1.addAll(setItems);



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

    List<Topic> onProvideCompletionsFromAutoCompleteGridinTopics(String partial) {
         List<Topic> matches = null;

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

    public void setSubject(Testsubject testsubject1) {
        System.out.println("Setting subject in grid in." + testsubject1.getName());
        this.testsubject1 = testsubject1;
    }
}
