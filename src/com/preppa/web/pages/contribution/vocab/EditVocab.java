/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.pages.contribution.vocab;

import com.preppa.web.data.VocabDAO;
import com.preppa.web.entities.Vocab;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.corelib.mixins.Autocomplete;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author newtonik
 */
public class EditVocab {

    @Persist
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

    void onActivate(int id) {
        this.vocab = vocabDAO.findById(id);
        if(vocab != null) {
            fWord = vocab.getName();
            partofspch = vocab.getPartofspeech();
            fDefinition = vocab.getDefinition();
            fSentence = vocab.getSentence().getSentence();
            fTag = vocab.getTags();
        }

    }
    Integer onPassivate() {

        return this.vocab.getId();
    }
        @CommitAfter
    Object onSuccess() {

         vocab.setName(fWord);
         vocab.setPartofspeech(partofspch);
         vocab.setDefinition(fDefinition);
         vocab.setTags(fTag);
         vocab.getSentence().setSentence(fSentence);

         //set the updated at tag to current time
         vocab.setUpdatedAt(new java.sql.Timestamp(System.currentTimeMillis()));

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

}
