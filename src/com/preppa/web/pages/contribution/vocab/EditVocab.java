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
    Object onActivate(int id) {
        this.vocab = vocabDAO.findById(id);

        return this;
    }
        @CommitAfter
    Object onSuccess() {
        
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
