  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.pages.contribution.prompt;

import com.preppa.web.data.PromptDAO;
import com.preppa.web.data.TagDAO;
import com.preppa.web.entities.Prompt;
import com.preppa.web.entities.Tag;
import com.preppa.web.entities.User;
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
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.chenillekit.tapestry.core.components.Editor;
import org.springframework.security.annotation.Secured;

/**
 *
 * @author Jan Jan
 */
@Secured("ROLE_USER")
@IncludeJavaScriptLibrary(value = {"context:js/confirmexit.js"})
public class EditPrompt {
    @ApplicationState
    private User user;
    @Property
    private Prompt prompt;
    @Inject
    private PromptDAO promptDAO;
    @Component
    private Form promptform;
    @Property
    private String quote;
    @Property
    private String question;
    @Property
    private String topic;
    @Property
    private Tag tag;
    @Inject
    private TagDAO tagDAO;
    @Property
    private List<Tag> addedTags = new LinkedList<Tag>();
    @Component(parameters = {"value=question"})
    private Editor body;
    @Property
    private String fname;
    @InjectPage
    private ShowPrompt showprompt;


    void onActivate(int id) {
        this.prompt = promptDAO.findById(id);
        if(prompt != null) {
            quote = prompt.getQuote();
            question = prompt.getQuestion();
            topic = prompt.getTopic();
            addedTags = prompt.getTaglist();

            /*if (prompt.getTags() == null)
            {
                fTag = "";
            }
            else
            {
                fTag = vocab.getTags();
            }*/
        }
    }
    Integer onPassivate() {

        return this.prompt.getId();
    }
    Object onValidateFormFromPromptForm(){
        /*if(mywork == false) {
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

        if (question == null) {
            promptform.recordError(answergroup, "You did not specify an answer.");
        }*/

        return null;
    }

    @CommitAfter
    Object onSuccessFromPromptForm() {
        Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
        this.prompt = new Prompt();
        prompt.setCreatedAt(now);
        prompt.setUpdatedAt(now);
        prompt.setQuote(quote);
        prompt.setQuestion(question);
        prompt.setTopic(topic);
        prompt.setUser(user);
        prompt.setTaglist(addedTags);
        prompt.setUpdatedBy(user);
        promptDAO.doSave(prompt);
        showprompt.setprompt(prompt);
        return showprompt;
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


            if (clientValue != null && clientValue.length() > 0 && !clientValue.equals("0")) {
                System.out.println(clientValue);
                serverValue = tagDAO.findByName(clientValue).get(0);
            }
            return serverValue;
          }


    };
   }




}
