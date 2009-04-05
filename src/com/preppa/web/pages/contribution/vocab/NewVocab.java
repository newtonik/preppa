/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.pages.contribution.vocab;

import com.preppa.web.data.VocabDAO;
import com.preppa.web.entities.Vocab;
import com.preppa.web.pages.Index;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author newtonik
 */
public class NewVocab {

    @Property
    private Vocab vocab;
    @InjectPage
    private Index index;
    @Inject
    private VocabDAO vocabDAO;

    void onActivate(Vocab word) {
        this.vocab = word;
    }

    Object onPassivate() {
        return vocab;
    }

    @CommitAfter
    Object onSuccess() {
         vocab.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
         vocab.setUpdatedAt(new java.sql.Timestamp(System.currentTimeMillis()));

         vocabDAO.doSave(vocab);
        return index;
    }
}