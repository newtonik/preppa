/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.pages.contribution.vocab;

import com.preppa.web.data.VocabDAO;
import com.preppa.web.entities.Vocab;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author newtonik
 */
public class ShowVocab {
@Persist
@Property
private Vocab vocab;
@Inject
private VocabDAO vocabDAO;


Object onActivate(int id) {
        this.vocab = vocabDAO.findById(id);

        return this;
    }



void setvocab(Vocab vocab) {
        this.vocab = vocab;
    }

}
