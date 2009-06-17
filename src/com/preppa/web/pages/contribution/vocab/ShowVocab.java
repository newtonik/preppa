/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.pages.contribution.vocab;

import com.preppa.web.data.VocabDAO;
import com.preppa.web.entities.Vocab;
import com.preppa.web.pages.Index;
import org.apache.tapestry5.annotations.InjectPage;
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
@Property
private String example;
@InjectPage
private Index index;
private Integer vid;

Object onActivate(int id) {
    if(id > 0 ) {
        this.vocab = vocabDAO.findById(id);
        if(this.vocab != null) {
            example = vocab.getSentence().getSentence();
            vid = vocab.getId();    
        }
        else
        {
            return index;
        }
    }
     return this;
    }



void setvocab(Vocab vocab) {
        this.vocab = vocab;
        this.vid = vocab.getId();
    }

}
