/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.pages.contribution.question;

import com.preppa.web.components.CQuestion;
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
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.chenillekit.tapestry.core.components.Editor;
import org.chenillekit.tapestry.core.components.RatingField;
import org.chenillekit.tapestry.core.components.prototype_ui.AutoComplete;

/**
 *
 * @author nwt
 */
//@IncludeJavaScriptLibrary(value={"context:js/jquery-1.3.2.js",  "context:js/tools.overlay-1.0.4.js", "context:js/layout.js"})
public class CreateQuestion {
/*    @Property
    private Question question;
    @Inject
    private QuestionDAO questionDAO;
    @InjectPage
    private Index indexpage;
    @Property
    private String fQuestion;
    @Property
    private String fExplanation;
    @Property
    private String fTag;
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
     private TagDAO tagDAO;*/
    /*@Component
    private AutoComplete autoCompleteTag;*/
    @Component
    private CQuestion firstquestion;

    void pageLoaded() {
        firstquestion.setPageTrue();
    }

/*
    void CreateQuestion() {
        //question = new Question();
    }

    void onActivate() {
        //question = new Question();
    }

    Object onPassivate() {
        return question;
    }
    @CommitAfter
    Object onSuccess(){
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
     for(Tag t: addedTags)
     {
            if(!(question.getTaglist().contains(t)))
            {
                question.getTaglist().add(t);
            }
     }
    question.setCorrectAnswer(correct);
      numCorrect = 1;
     question.setNumCorrect(numCorrect);
     question.setDifficulty(ratingValue);
     Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
     question.setCreatedAt(now);
     question.setUpdatedAt(now);
     questionDAO.doSave(question);
     return indexpage;
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
   }*/

}
