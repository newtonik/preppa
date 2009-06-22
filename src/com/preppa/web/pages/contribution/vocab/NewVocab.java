/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.pages.contribution.vocab;

import com.preppa.web.data.VocabDAO;
import com.preppa.web.data.DictionaryWordDAO;
import com.preppa.web.entities.ExampleSentence;
import com.preppa.web.entities.Vocab;
import com.preppa.web.entities.DictionaryWord;
import java.sql.Timestamp;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.corelib.components.Form;

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
	@Component(id = "vocabform")
	private Form _form;
    @Inject
    private DictionaryWordDAO dictionarywordDAO;

    void onValidateForm() {
        List<Vocab> matches = vocabDAO.findByName(fWord);
        if (matches.isEmpty() == false)
        {
            _form.recordError("The word already exists.");
        }
    }


    void onActivate(Vocab word) {
        this.vocab = word;
    }

    List<String> onProvideCompletionsFromfWord(String partial)
    {
        List<DictionaryWord> matches = dictionarywordDAO.findByPartialName(partial);

        List<String> result = new ArrayList<String>();

            for (DictionaryWord a : matches)
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
        this.vocab = new Vocab();
         vocab.setName(fWord);
         vocab.setPartofspeech(partofspch);
         vocab.setDefinition(fDefinition);
         vocab.setTags(fTag);
         Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
         vocab.setCreatedAt(now);
         vocab.setUpdatedAt(now);
         ExampleSentence sent = new ExampleSentence();
         sent.setSentence(fSentence);
         vocab.setSentence(sent);

         vocabDAO.doSave(vocab);
         showvocab.setvocab(vocab);
        return showvocab;
    }
}
