/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.pages.contribution.vocab;

import com.preppa.web.data.VocabDAO;
import com.preppa.web.entities.Tag;
import com.preppa.web.entities.User;
import com.preppa.web.entities.Vocab;
import com.preppa.web.pages.Index;
import com.preppa.web.utils.ContentType;

import java.util.List;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.IncludeStylesheet;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author newtonik
 */
@IncludeStylesheet(value = {"context:styles/flag.css"})
@IncludeJavaScriptLibrary(value = {"context:js/vocab.js"})
public class ShowVocab {

    @ApplicationState
    private User user;
    @Persist
    @Property
    private Vocab vocab;
    @Inject
    private VocabDAO vocabDAO;
    private String example = "before";
    @InjectPage
    private Index index;
    private Integer vid;
    @Property
    private List<Tag> tags;
    @Property
    private ContentType contType;
    

    void onActivate(int id) {
        contType = ContentType.Vocab;
        if (id > 0) {
            this.vocab = vocabDAO.findById(id);
            this.vid = id;
            if (this.vocab != null) {
                if (vocab.getSentence() == null) {
                    example = "";
                } else {
                    example = vocab.getSentence().getSentence();
                    System.out.println(example);
                }
                vid = vocab.getId();
                tags = vocab.getTaglist();
            }

        }

    }

    Integer onPassivate() {
        return this.vid;

    }

    public String getExample() {
        if (vocab.getSentence() == null) {
            example = "";
        } else {
            example = vocab.getSentence().getSentence();
            System.out.println(example);
        }
        return example;
    }

    void setvocab(Vocab vocab) {
        this.vocab = vocab;
        this.vid = vocab.getId();
    }

    
}
