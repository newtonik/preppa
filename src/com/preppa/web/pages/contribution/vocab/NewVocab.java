/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.pages.contribution.vocab;

import com.preppa.web.data.VocabDAO;
import com.preppa.web.entities.ExampleSentence;
import com.preppa.web.entities.Vocab;
import com.preppa.web.pages.contribution.vocab.ShowVocab;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Lob;

/**
 *
 * @author newtonik
 */
public class NewVocab {

    @Property
    private Vocab vocab;
    @Inject
    private VocabDAO vocabDAO;
    @InjectPage
    private ShowVocab showvocab;



    void onActivate(Vocab word) {
        this.vocab = word;
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

    Object onPassivate() {
        return vocab;
    }

    @CommitAfter
    Object onSuccess() {
         vocab.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
         vocab.setUpdatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
         ExampleSentence sent = new ExampleSentence();
         sent.setSentence(vocab.getFormsentence());
         vocab.setSentence(sent);

         vocabDAO.doSave(vocab);
         showvocab.setvocab(vocab);
        return showvocab;
    }
}
