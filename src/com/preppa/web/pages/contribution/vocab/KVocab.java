/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.pages.contribution.vocab;

import com.preppa.web.data.VocabDAO;
import com.preppa.web.entities.Vocab;
import java.util.List;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author newtonik
 */
public class KVocab {
    @Inject
    private VocabDAO vocabDAO;

    @Property
    @Persist
    private Vocab vocabK;


    Object onActivate() {

        return null;
    }

    /**
     * @return the allarticles
     */
    public List<Vocab> getAllVocab() {
        return vocabDAO.findByLetter('k');
    }

}
