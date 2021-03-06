  
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
import com.preppa.web.pages.Index;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import org.springframework.security.annotation.Secured;
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

/**
 *
 * @author Jan Jan
 */
@IncludeJavaScriptLibrary(value = {"context:js/confirmexit.js"})
@Secured("ROLE_USER")
public class CreatePrompt {
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

    
    void onValidateForm() {

    }


    Object onValidateFormFromPromptForm(){
      

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
        for(Tag t: addedTags) {
            if(!(prompt.getTaglist().contains(t)))
            {
                prompt.getTaglist().add(t);
            }
          //  article.setTopics(tset);

         }
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
