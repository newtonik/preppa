/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.pages.contribution.vocab;

import com.preppa.web.data.DictionaryWordDAO;
import com.preppa.web.data.TagDAO;
import com.preppa.web.data.VocabDAO;
import com.preppa.web.entities.DictionaryWord;
import com.preppa.web.entities.ExampleSentence;
import com.preppa.web.entities.Vocab;
import com.preppa.web.entities.Tag;
import com.preppa.web.entities.User;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.FieldTranslator;
import org.chenillekit.tapestry.core.components.prototype_ui.AutoComplete;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.ValidationException;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.json.JSONObject;

/**
 *
 * @author newtonik
 */
public class EditVocab {
    @ApplicationState
    private User user;
    @Property
    private Vocab vocab;
    @Inject
    private VocabDAO vocabDAO;
    @InjectPage
    private ShowVocab showvocab;
    @Property
    private String fWord;
    @Property
    private String partofspch;
    @Property
    private String fDefinition;
    @Property
    private String fSentence;
    @Property
    private String fTag;
    @Component
    private AutoComplete autoCompleteTag;
    @Property
    private List<Tag> addedTags = new LinkedList<Tag>();
    @Inject
    private TagDAO tagDAO;
    @Component
    private Form editform;
     @Inject
    @Property
    private Block newtagblock;
    @Property
    private Tag tag;
    @Component
    private TextField tagTextfield;
    @Property
    private String fname;
    @Component
    private Form tagform;
    @Property
    private String fComment;

    @Inject
    private DictionaryWordDAO dictionarywordDAO;
    @Property
    private DictionaryWord dWord;
    
    void onActivate(int id) {
        this.vocab = vocabDAO.findById(id);
        if(vocab != null) {
            fWord = vocab.getName();
            partofspch = vocab.getPartofspeech();
            fDefinition = vocab.getDefinition();
            addedTags = vocab.getTaglist();
            if (vocab.getSentence() == null)
            {
                fSentence = "";
            }
            else
            {
                fSentence = vocab.getSentence().getSentence();
            }
            if (vocab.getTags() == null)
            {
                fTag = "";
            }
            else
            {
                fTag = vocab.getTags();
            }
        }
        this.dWord = dictionarywordDAO.findById(5);
    }
    Integer onPassivate() {

        return this.vocab.getId();
    }
    @CommitAfter
    Object onSuccessFromEditForm() {

         vocab.setName(fWord);
         vocab.setPartofspeech(partofspch);
         vocab.setDefinition(fDefinition);
         vocab.setTags(fTag);
         vocab.setRevComment(fComment);
         vocab.setUser(user);
         if (vocab.getSentence() != null) {
            if (fSentence == null) {
                vocab.getSentence().setSentence("");
            }
            else {
                vocab.getSentence().setSentence(fSentence);
            }
         }
         else if (fSentence != null) {
            ExampleSentence edit = new ExampleSentence();
            edit.setSentence(fSentence);
            vocab.setSentence(edit);
         }



         //set the updated at tag to current time
         vocab.setUpdatedAt(new java.sql.Timestamp(System.currentTimeMillis()));

         System.out.println(dWord.getId());

         vocabDAO.doSave(vocab);
         showvocab.setvocab(vocab);
         return showvocab;
    }

    List<String> onProvideCompletionsFromName(String partial)
    {
        List<Vocab> matches = vocabDAO.findByPartialName(partial);

        List<String> result = new ArrayList<String>();

            for (Vocab a : matches)
            {
                result.add(a.getName());
            }

        return result;
    }

           public static String sanitize(String string) {
    return string
     .replaceAll("(?i)<script.*?>.*?</script.*?>", "")   // case 1
     .replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "") // case 2
     .replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "");     // case 3
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
            if(clientValue == null) {
                Tag t = new Tag();
                t.setName(clientValue);
            }
            System.out.println(clientValue);

            if (clientValue != null && clientValue.length() > 0 && !clientValue.equals("0")) {
                System.out.println(clientValue);
                serverValue = tagDAO.findByName(clientValue).get(0);
            }
            return serverValue;
          }

    };
  }
         Block onActionFromCloseTag() {
            return newtagblock;
        }

       Block onActionFromCancelTag() {
            return null;
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


}
