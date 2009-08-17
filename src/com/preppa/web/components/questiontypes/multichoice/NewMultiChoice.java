package com.preppa.web.components.questiontypes.multichoice;

import com.preppa.web.data.LongDualPassageDAO;
import com.preppa.web.data.LongPassageDAO;
import com.preppa.web.data.QuestionDAO;
import com.preppa.web.data.QuestiontypeDAO;
import com.preppa.web.data.ShortDualPassageDAO;
import com.preppa.web.data.ShortPassageDAO;
import com.preppa.web.data.TagDAO;
import com.preppa.web.data.TestsubjectDAO;
import com.preppa.web.entities.LongDualPassage;
import com.preppa.web.entities.LongPassage;
import com.preppa.web.entities.Question;
import com.preppa.web.entities.QuestionAnswer;
import com.preppa.web.entities.Questiontype;
import com.preppa.web.entities.ShortDualPassage;
import com.preppa.web.entities.ShortPassage;
import com.preppa.web.entities.Tag;
import com.preppa.web.entities.Testsubject;
import com.preppa.web.entities.User;
import com.preppa.web.pages.Index;
import com.preppa.web.pages.contribution.question.ShowQuestion;
import com.preppa.web.utils.InjectSelectionModel;
import java.io.File;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.FieldTranslator;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.ValidationException;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.IncludeStylesheet;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Mixins;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.RadioGroup;
import org.apache.tapestry5.corelib.components.Select;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.Context;
import org.apache.tapestry5.services.Request;
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
@IncludeJavaScriptLibrary(value = {"context:js/multiplequestion.js", "context:js/question.js"})
public class NewMultiChoice {
    @ApplicationState
    private User user;
    @Property
    private Question question;
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
    @Component
    private RatingField ratingField;
    @Property
    private Integer ratingValue;
    @Property
    @Persist
    private String ans1;
    @Property
    @Persist
    private String ans2;
    @Property
    @Persist
    private String ans3;
    @Property
    @Persist
    private String ans4;
    @Property
    @Persist
    private String ans5;
    @Property
    private Boolean c1;
    @Property
    private Boolean c2;
    @Property
    private Boolean c3;
    @Property
    private Boolean c4;
    @Property
    private Boolean c5;
    @Property
    private String correct;
     @Property
    private List<Tag> addedTags = new LinkedList<Tag>();
     @Inject
     private TagDAO tagDAO;
    @Component
    private AutoComplete autoCompleteTag;
    @Parameter
    private LongPassage longpassage;
    @Inject
    private LongDualPassageDAO longdualpassageDAO;
    @Inject
    private ShortPassageDAO shortpassageDAO;
    @Inject
    private ShortDualPassageDAO shortdualpassageDAO;
    @Inject
    private LongPassageDAO longpassageDAO;
    @Parameter
    private boolean newquestion;
    @Parameter
    private Object owner;
    @InjectPage
    private ShowQuestion show;
    @Inject
    private Logger logger;
    private boolean showpage = false;
    private ShortPassage shortpassage;
    private ShortDualPassage shortdualpassage;
    private LongDualPassage longdualpassage;
    @Property
    private Boolean mywork;
    @Component(id = "createquestionform")
    private Form createquestionform;
    @Persist
    private boolean error;
    @Persist
    private String emessage;
    @Component
    private RadioGroup answergroup;
    @Property
    private String fname;
    @Property
    private Tag tag;
    @Inject
    @Property
    private Block newtagblock;
    @Property
    @Persist
    private Integer questType;
    @Property
    private String hasimage;
    @Property
    private UploadedFile imageupload;
    @Component
    private Upload upload;
    @Inject
    private Context context;
    /** Components and Objects for Select forms **/
    @Parameter
    @InjectSelectionModel(labelField = "name", idField = "id")
    private List<Testsubject> testsubjects;
    @InjectSelectionModel(labelField = "name", idField = "id")
    private List<Questiontype> questiontypes;
    @Property
    @Persist
    private Testsubject testsubject;
    @Property
    @Persist
    private Questiontype questiontype;
    @Component(parameters = {"value=testsubject",  "event=change",
                         "onCompleteCallback=literal:onChangeTestsubject"})
    @Mixins({"ck/OnEvent"})
    private Select testSubSelect;

    @Component(parameters = {"value=questiontype",  "event=change",
                         "onCompleteCallback=literal:onChangeQuestionTestsubject"})
    @Mixins({"ck/OnEvent"})
    private Select QuestiontypeSelect;
//    @InjectComponent
  //  private Zone questionzone;

    @Inject
    private QuestiontypeDAO questiontypeDAO;
    @Inject
    private TestsubjectDAO testsubjectDAO;
    @Inject
    private Request request;
    @InjectPage
    private ShowQuestion showquestion;

    public boolean getError() {
        return error;
    }

    public String getEMessage() {
        error = !error;
        return emessage;
    }

    public void setPageTrue() {
        showpage = true;
    }

    public void setPageFalse() {
        showpage = false;
    }

    void CreateQuestion() {
        question = new Question();
         newquestion = true;
         
    }

    void onActivate() {
        question = new Question();
        if(ans1 != null) {
            ans1 = null;
        }
        if(ans2 != null) {
            ans2 = null;
        }if(ans3 != null) {
            ans3 = null;
        }if(ans4 != null) {
            ans4 = null;
        }if(ans5 != null) {
            ans5 = null;
        }
    }
    @SetupRender
    void getSetupItems() {
        testsubjects = testsubjectDAO.findAllWithQuestions();
         
        questiontypes = questiontypeDAO.findAll();
    }
    Question getSubmittedQuestion() {
        return this.question;
    }
//    void addToPassage(LongDualPassage passage) {
//        if(passage != null) {
//            passage.getQuestions().add(this.question);
//        }
//    }
    Object onPassivate() {
        return question;
    }
    void onSubmitForm() {
        if(!mywork) {
            createquestionform.recordError("You cannot submit a question to Preppa, that isn't your own work.");
        }
    }


    Object onValidateFormFromCreateQuestionForm(){
        System.out.println("Choices are "+ans1 + ans2 + ans3 + ans4 + ans5);
        if(mywork == false) {
            error = true;
            emessage = "You must verify that this is your own work.";
            createquestionform.recordError("You must verify that this is your own work.");
        }
        if ((correct == null)) {
            error = true;
            emessage = "You did not specify an answer.";
            createquestionform.recordError(answergroup, "You did not specify an answer.");
        }

        if(ratingValue == null) {
            createquestionform.recordError(ratingField, "You need to select a difficulty");
        }
        if(question != null) {
        if(question.getQuestiontype() == null) {
            System.out.println("There isn't a questiontype");
            createquestionform.recordError(QuestiontypeSelect, "You have to select a Question subject to add this question");
        }
        }
        if(request.isXHR() && createquestionform.getHasErrors()) {
    //        return questionzone;
            return null;
        }
        else
        {
            //showquestion.setquestion(question);
            return null;
        }
    }

     JSONObject onChangeFromTestSubSelect(String testId) {
        JSONObject json = new JSONObject();

            JSONArray ids = new JSONArray();
            JSONArray qt = new JSONArray();
            JSONArray counter = new JSONArray();
              System.out.println("Counter is " + testId);
            System.out.println("Testsubjsets are " + testsubjects.size());
            System.out.println("Index to get is " + (Integer.parseInt(testId)-1));
        if(testId != null && !testId.equals("")) {
            questiontypes = questiontypeDAO.findByTestsubject(testsubjects.get(Integer.parseInt(testId)-1));


            ids.put("");
            qt.put("");
            int i = 1;
            System.out.println("Counter is " + questiontypes.size());
            for(Questiontype t: questiontypes) {
                qt.put(i, t.getName());
                ids.put(i, t.getId().toString());
                counter.put(new Integer(i).toString());
                i++;
            }
            System.out.println("Counter is " + i);
        }
        json.put("ids", ids);
        json.put("qt", qt);
        json.put("counter", counter);

        //return new TextStreamResponse("text/json", json.toString());
        return json;
    }

   void onChangeFromQuestiontypeSelect(String quesId) {

      // System.out.println("I just got selected " + quesId);
        if(!quesId.equals("") && quesId != null)
        {
            questType = Integer.parseInt(quesId);
            questiontype = questiontypeDAO.findById(questType);
            if(question == null)
                question = new Question();
            question.setQuestiontype(questiontype);
        }
    }


    @CommitAfter
    Object onSuccessFromCreateQuestionForm(){
        if(question == null)
            question = new Question();
    question.setExplanation(fExplanation);
    question.setQuestion(fQuestion);
    int numCorrect = 0;
    if(ans1.length() > 0) {
        QuestionAnswer ch = new QuestionAnswer(ans1);
        question.getChoices().add(ch);


    }
        if(ans2.length() > 0) {
        QuestionAnswer ch = new QuestionAnswer(ans2);
        question.getChoices().add(ch);
    }
        if(ans3.length() > 0) {
        QuestionAnswer ch = new QuestionAnswer(ans3);
        question.getChoices().add(ch);
    }
        if(ans4.length() > 0) {
        QuestionAnswer ch = new QuestionAnswer(ans4);
        question.getChoices().add(ch);
    }
        if(ans5.length() > 0) {
        QuestionAnswer ch = new QuestionAnswer(ans5);
        question.getChoices().add(ch);
    }

    questiontype = questiontypeDAO.findById(questType);
    question.setQuestiontype(questiontype);
     for(Tag t: addedTags)
     {
            if(!(question.getTaglist().contains(t)))
            {
                question.getTaglist().add(t);
            }
     }
     
   
     question.setImage(Boolean.FALSE);
     //question.setQuestiontype(questiontype);
     question.setUser(user);
    question.setCorrectAnswer(correct);
      numCorrect = 1;
     question.setNumCorrect(numCorrect);
     question.setDifficulty(ratingValue);
     Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
     question.setCreatedAt(now);
     question.setUpdatedAt(now);
     newquestion = true;
     questionDAO.doSave(question);
  if(hasimage.equals("yes")) {
           String impath = context.getRealFile("/").getPath() + "/images/multiplechoice/question" + question.getId() + "/"+ question.getId() +".jpg";
             boolean status = new File(context.getRealFile("/").getPath() + "/images/multiplechoice/question" + question.getId() ).mkdirs();
            System.out.println(impath);
            File copied = new File(impath);
            imageupload.write(copied);
            question.setImagePath(impath);
            question.setImage(Boolean.TRUE);
       }
     if(owner != null)
     {
        if(!saveQuestionToObject(owner, question))
        {
            logger.debug("Just saving the question, object is null");
            questionDAO.doSave(question);
        }
     }
     else {
         questionDAO.doSave(question);
     }

     if (showpage == false) {
         return null;
     }
     else {
        show.setquestion(this.question);
        return show;
     }
    }
    //Funtions for adding new tags and topics
        @CommitAfter
        JSONObject onSuccessFromTagForm() {
            List<Tag> tolist =  tagDAO.findByName(fname);
            JSONObject json = new JSONObject();
            System.out.print(tolist);
            if(tolist.size() > 0) {
                String markup = "<p>  <b>" + fname +
                    "</b> already exists. <p>";
                json.put("content", markup);

            }
            else
            {
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
     List<Tag> onProvideCompletionsFromAutocompleteTag(String partial) {
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
            System.out.println(clientValue);

            if (clientValue != null && clientValue.length() > 0 && !clientValue.equals("0")) {
                System.out.println(clientValue);
                serverValue = tagDAO.findByName(clientValue).get(0);
            }
            return serverValue;
          }

    };
   }
        /**
         *This function determines the object passed to the component then saves a question to it.
         * @param toSave
         * @param questiontoSave
         * @return
         */
        @CommitAfter
   Boolean saveQuestionToObject(Object toSave, Question questiontoSave) {
       if(toSave instanceof LongPassage) {
          longpassage = (LongPassage)toSave;
            longpassage = longpassageDAO.findById(longpassage.getId());
            longpassage.getQuestions().add(question);
            logger.debug("Object to save is long passage");
            return true;
       }
       else if(toSave instanceof ShortPassage)
       {
           shortpassage = (ShortPassage)toSave;
           shortpassage = shortpassageDAO.findById(shortpassage.getId());
           shortpassage.getQuestions().add(question);
           logger.debug("Object to save is sshortpassage");
           return true;
       }
       else if(toSave instanceof ShortDualPassage)
       {
           shortdualpassage = (ShortDualPassage)toSave;
           shortdualpassage = shortdualpassageDAO.findById(shortdualpassage.getId());
           shortdualpassage.getQuestions().add(question);

           return true;
       }
       else if(toSave instanceof LongDualPassage)
       {
           longdualpassage = (LongDualPassage)toSave;
           longdualpassage = longdualpassageDAO.findById(longdualpassage.getId());
           longdualpassage.getQuestions().add(question);
          return true;
       }
       else
       {
            logger.error("Object to save is not handled");
            return false;
       }

   }
         Block onActionFromCloseTag() {
            return newtagblock;
        }
        /**
         * <t:if t:test="error">
 <font color="FF0000">ERROR : ${emessage}</font>
 </t:if>
         */
}
