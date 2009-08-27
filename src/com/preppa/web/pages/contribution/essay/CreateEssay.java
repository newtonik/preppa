  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.pages.contribution.essay;

import com.preppa.web.data.EssayDAO;
import com.preppa.web.data.PromptDAO;
import com.preppa.web.data.TagDAO;
import com.preppa.web.entities.Essay;
import com.preppa.web.entities.Prompt;
import com.preppa.web.entities.Tag;
import com.preppa.web.entities.User;
import com.preppa.web.pages.Index;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.FieldTranslator;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.ValidationException;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.Component;
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
public class CreateEssay {
    @ApplicationState
    private User user;
    @Property
    private Essay essay;
    @Property
    private Prompt prompt;
    @Inject
    private PromptDAO promptDAO;
    @Inject
    private EssayDAO essayDAO;
    @Component
    private Form essayform;
    @Property
    private String eBody;
    @Property
    private String topic;
    @Property
    private Tag tag;
    @Inject
    private TagDAO tagDAO;
    @Property
    private List<Tag> addedTags = new LinkedList<Tag>();
    @Component(parameters = {"value=eBody"})
    private Editor body;
    @Property
    private String fname;
    @Inject
    @Property
    private Block newtagblock;
    @Component
    private TextField tagTextfield;

    private Integer pid;

    Object onActivate(int id) {
        if (id > 0) {
            this.prompt = promptDAO.findById(id);
            this.pid = id;
            System.out.println("Prompt is originally" + prompt);
        }
        return null;
    }

    Integer onPassivate() {
        return this.pid;

    }

    void onValidateForm() {

    }

    @CommitAfter
    Object onSuccessFromEssayForm() {
        Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
        this.essay = new Essay();
        essay.setCreatedAt(now);
        essay.setUpdatedAt(now);
        essay.setBody(eBody);
        essay.setUser(user);
        essay.setTaglist(addedTags);
        essay.setUpdatedBy(user);
        System.out.println("pid is" + pid);
        System.out.println("Prompt is " + prompt);
        essay.setPrompt(prompt);
        essayDAO.doSave(essay);
        return Index.class;
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

            @Override
            public void render(MarkupWriter arg0) {
                throw new UnsupportedOperationException("Not supported yet.");
            }


    };
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

        Block onActionFromCloseTag() {
            return newtagblock;
        }

}
