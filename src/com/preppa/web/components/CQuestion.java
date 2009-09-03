/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.components;

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
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
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
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.RadioGroup;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Context;
import org.apache.tapestry5.services.Request;
import org.chenillekit.tapestry.core.components.Editor;
import org.chenillekit.tapestry.core.components.RatingField;
import org.chenillekit.tapestry.core.components.prototype_ui.AutoComplete;
import org.slf4j.Logger;

/**
 *
 * @author nwt
 */
@IncludeJavaScriptLibrary(value = {"context:js/jquery-1.3.2.js", "context:js/jquery/jquery.form.js", "context:js/multiplequestion.js", "context:js/question.js", "context:js/confirmexit.js"})
public class CQuestion {
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
    @Parameter
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
    @Parameter
    private Questiontype qtype;
    @Property
    @Persist
    private Questiontype questiontype;
    @InjectComponent
    private Zone newquestionzone;
    @Inject
    private QuestiontypeDAO questiontypeDAO;
    @Inject
    private TestsubjectDAO testsubjectDAO;
    @Inject
    private Request request;
    @Component
    private SQuestion showquestion;
    @Inject
    private Block showquestionblock;
    @Inject
    private ComponentResources _resources;
    @Property
    @Persist
    private Boolean questiontExists;
    @Parameter
    private String hasquestiontype;
    @Persist
    private String hasOwner;

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
        if (ans1 != null) {
            ans1 = null;
        }
        if (ans2 != null) {
            ans2 = null;
        }
        if (ans3 != null) {
            ans3 = null;
        }
        if (ans4 != null) {
            ans4 = null;
        }
        if (ans5 != null) {
            ans5 = null;
        }
    }

    @SetupRender
    void getSetupItems() {
        _resources.discardPersistentFieldChanges();
        if(qtype != null) {
             questiontype = qtype;
            questiontExists = false;
        }
        else
        {
            questiontExists = true;
        }
         System.out.println(hasquestiontype);
        if(hasquestiontype != null) {

            if(hasquestiontype.equals("true")) {
                System.out.println(hasquestiontype + " " + questiontExists);
                questiontExists = false;
            }
        }
        testsubjects = testsubjectDAO.findAllWithQuestions();

        questiontypes = questiontypeDAO.findAll();
        if(owner != null)
            hasOwner = "true";
        else
        {
            hasOwner = "false";
        }
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
        _resources.discardPersistentFieldChanges();
        return question;
    }

    void onSubmitForm() {
        if (!mywork) {
            createquestionform.recordError("You cannot submit a question to Preppa, that isn't your own work.");
        }
    }

    Object onValidateFormFromCreateQuestionForm() {
        if (mywork == false) {
            error = true;
            emessage = "You must verify that this is your own work.";
            createquestionform.recordError("You must verify that this is your own work.");
        }
        if ((correct == null)) {
            error = true;
            emessage = "You did not specify an answer.";
            createquestionform.recordError(answergroup, "You did not specify an answer.");
        }

        if (ratingValue == null) {
            createquestionform.recordError(ratingField, "You need to select a difficulty");
        }
        

       if (request.isXHR() && createquestionform.getHasErrors()) {
            testsubjects = testsubjectDAO.findAllWithQuestions();

        questiontypes = questiontypeDAO.findAll();
            return newquestionzone;
            //return null;
        } else {
            //showquestion.setquestion(question);
            return null;
        }
    }


   
    @CommitAfter
    Object onSuccessFromCreateQuestionForm() {
        if (question == null) {
            question = new Question();
        }
        question.setExplanation(fExplanation);
        question.setQuestion(fQuestion);
        int numCorrect = 0;
        if (ans1.length() > 0) {
            QuestionAnswer ch = new QuestionAnswer(ans1);
            question.getChoices().add(ch);


        }
        if (ans2.length() > 0) {
            QuestionAnswer ch = new QuestionAnswer(ans2);
            question.getChoices().add(ch);
        }
        if (ans3.length() > 0) {
            QuestionAnswer ch = new QuestionAnswer(ans3);
            question.getChoices().add(ch);
        }
        if (ans4.length() > 0) {
            QuestionAnswer ch = new QuestionAnswer(ans4);
            question.getChoices().add(ch);
        }
        if (ans5.length() > 0) {
            QuestionAnswer ch = new QuestionAnswer(ans5);
            question.getChoices().add(ch);
        }

        for (Tag t : addedTags) {
            if (!(question.getTaglist().contains(t))) {
                question.getTaglist().add(t);
            }
        }


        question.setImage(Boolean.FALSE);
        question.setQuestiontype(questiontype);
        question.setUser(user);
        question.setUpdatedBy(user);
        question.setCorrectAnswer(correct);
        numCorrect = 1;
        question.setNumCorrect(numCorrect);
        question.setDifficulty(ratingValue);
        Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
        question.setCreatedAt(now);
        question.setUpdatedAt(now);
        newquestion = true;
        questionDAO.doSave(question);
        String impath = null;
      
     if(hasOwner.equals("true")) {
       if (!saveQuestionToObject(owner, question)) {
                logger.debug("Just saving the question, object is null");
                questionDAO.doSave(question);
        }
     }

        if (request.isXHR() ) {
            showquestion.setquestion(question);
            return showquestionblock;
        } else {
            show.setquestion(this.question);
            return show;
        }
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

    /**
     *This function determines the object passed to the component then saves a question to it.
     * @param toSave
     * @param questiontoSave
     * @return
     */
    @CommitAfter
    Boolean saveQuestionToObject(Object toSave, Question questiontoSave) {
        if(toSave == null)
            return false;
        if (toSave instanceof LongPassage) {
            longpassage = (LongPassage) toSave;
            longpassage = longpassageDAO.findById(longpassage.getId());
            longpassage.getQuestions().add(question);
            logger.debug("Object to save is long passage");
            return true;
        } else if (toSave instanceof ShortPassage) {
            shortpassage = (ShortPassage) toSave;
            shortpassage = shortpassageDAO.findById(shortpassage.getId());
            shortpassage.getQuestions().add(question);
            logger.debug("Object to save is sshortpassage");
            return true;
        } else if (toSave instanceof ShortDualPassage) {
            shortdualpassage = (ShortDualPassage) toSave;
            shortdualpassage = shortdualpassageDAO.findById(shortdualpassage.getId());
            shortdualpassage.getQuestions().add(question);

            return true;
        } else if (toSave instanceof LongDualPassage) {
            longdualpassage = (LongDualPassage) toSave;
            longdualpassage = longdualpassageDAO.findById(longdualpassage.getId());
            longdualpassage.getQuestions().add(question);
            return true;
        } else {
            logger.error("Object to save is not handled");
            return false;
        }

    }
}
