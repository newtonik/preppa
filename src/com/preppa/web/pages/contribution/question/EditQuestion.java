/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.pages.contribution.question;

import com.preppa.web.data.QuestionDAO;
import com.preppa.web.data.TagDAO;
import com.preppa.web.entities.Question;
import com.preppa.web.entities.QuestionAnswer;
import com.preppa.web.entities.Tag;
import com.preppa.web.pages.Index;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import org.apache.tapestry5.FieldTranslator;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.ValidationException;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.chenillekit.tapestry.core.components.Editor;
import org.chenillekit.tapestry.core.components.RatingField;
import org.chenillekit.tapestry.core.components.prototype_ui.AutoComplete;


/**
 *
 * @author newtonik
 */
public class EditQuestion {
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
    
    void CreateQuestion() {
        //question = new Question();
    }

    void onActivate(int id) {
        this.question = questionDAO.findById(id);
        if(question != null) {
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
    Object onPassivate() {
        return question;
    }

@CommitAfter
    Object onSuccess(){
    question = new Question();
    question.setExplanation(fExplanation);
    question.setQuestion(fQuestion);
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
      for(Tag t: addedTags)
     {
            if(!(question.getTaglist().contains(t)))
            {
                question.getTaglist().add(t);
            }
     }
     question.setNumCorrect(1);
     question.setDifficulty(ratingValue);
     Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
     question.setCreatedAt(now);
     question.setUpdatedAt(now);
     questionDAO.doSave(question);
     showquestion.setquestion(question);
     return showquestion;
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
}
