  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.pages.contribution.question.approve;

import com.preppa.web.data.VocabDAO;
import com.preppa.web.entities.Flag;
import com.preppa.web.entities.Tag;
import com.preppa.web.entities.User;
import com.preppa.web.entities.Vocab;
import com.preppa.web.pages.Index;
import com.preppa.web.utils.ContentFlag;
import com.preppa.web.utils.ContentType;
import com.preppa.web.utils.FlagStatus;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.IncludeStylesheet;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.springframework.security.annotation.Secured;

/**
 *
 * @author Jan Jan
 */
@IncludeStylesheet(value = {"context:styles/flag.css"})
@IncludeJavaScriptLibrary(value = {"context:js/vocab.js"})
public class Test {

    @ApplicationState
    private User user;
    @Persist
    @Property
    private Vocab vocab;
    @Inject
    private VocabDAO vocabDAO;
    @Component
    private Form flagform;
    @Property
    @Persist
    private String reason;
    @Property
    private String reasonDesc;
    private String example = "before";
    @InjectPage
    private Index index;
    private Integer vid;
    private List<Flag> vocabflags;
    @Inject
    @Property
    private Block flagresponse;
    @Inject
    @Property
    private Block flagblock;
    @Property
    private List<Tag> tags;

    void onActivate(int id) {
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
                vocabflags = vocab.getFlags();
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

    @Secured("ROLE_USER")
    @CommitAfter
    Block onSuccessFromFlagForm() {
        if (reason != null) {
            Flag f = new Flag();
            if (reason.equals("A")) {
                f.setFlagtype(ContentFlag.Inappropriate);
            } else if (reason.equals("B")) {
                f.setFlagtype(ContentFlag.Spam);
            } else if (reason.equals("C")) {
                f.setFlagtype(ContentFlag.Attention);
            } else if (reason.equals("D")) {
                f.setFlagtype(ContentFlag.Incorrect);
            } else if (reason.equals("E")) {

                f.setFlagtype(ContentFlag.Copyright);
            } else {
                System.out.println(reason);
                f.setFlagtype(ContentFlag.Attention);
            }

            f.setDescription(reasonDesc);
            f.setContentType(ContentType.Vocab);
            f.setFlagger(user);
            f.setStatus(FlagStatus.NEW);
            f.setVocab(vocab);



            if (vocabflags == null) {
                vocabflags = new ArrayList<Flag>();
                vocabflags.add(f);
                vocab.setFlags(vocabflags);

            } else {
                //vocabflags.add(f);
                vocab.getFlags().add(f);

            }
            Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());

            f.setUpdatedAt(now);
            f.setCreatedAt(now);

            vocab.setUpdatedAt(now);

        }
        vocabDAO.doSave(vocab);
        return flagresponse;
    }
  Block onActionFromCloseFlagBlock() {
      return flagblock;
  }
}
