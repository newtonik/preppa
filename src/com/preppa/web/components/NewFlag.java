/*
 * Preppa, Inc.
 * 
 * Copyright 2009. All rights reserved.
 * 
 * $Id$
 */
package com.preppa.web.components;

import com.preppa.web.data.ArticleDAO;
import com.preppa.web.data.FlagDAO;
import com.preppa.web.data.ImprovingParagraphDAO;
import com.preppa.web.data.LongDualPassageDAO;
import com.preppa.web.data.LongPassageDAO;
import com.preppa.web.data.OpenAnswerDAO;
import com.preppa.web.data.OpenQuestionDAO;
import com.preppa.web.data.PromptDAO;
import com.preppa.web.data.QuestionDAO;
import com.preppa.web.data.ShortDualPassageDAO;
import com.preppa.web.data.ShortPassageDAO;
import com.preppa.web.data.VocabDAO;
import com.preppa.web.entities.Article;
import com.preppa.web.entities.Flag;
import com.preppa.web.entities.ImprovingParagraph;
import com.preppa.web.entities.LongDualPassage;
import com.preppa.web.entities.LongPassage;
import com.preppa.web.entities.OpenAnswer;
import com.preppa.web.entities.OpenQuestion;
import com.preppa.web.entities.Prompt;
import com.preppa.web.entities.Question;
import com.preppa.web.entities.ShortDualPassage;
import com.preppa.web.entities.ShortPassage;
import com.preppa.web.entities.User;
import com.preppa.web.entities.Vocab;
import com.preppa.web.utils.ContentFlag;
import com.preppa.web.utils.ContentType;
import com.preppa.web.utils.FlagStatus;
import java.sql.Timestamp;
import java.util.List;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;

/**
 * 
 * @author newtonik
 */
@IncludeJavaScriptLibrary(value = {"context:js/flagform.js"})
public class NewFlag {

    @ApplicationState
    private User user;
    private List<Flag> articleflags;
    @Component
    private Form flagform;
    @Inject
    private Block flagresponse;
    @Inject
    @Property
    private Block flagblock;
    @Component
    private TextField flagfield;
    @Parameter(required = true)
    @Property
    /** Content Type for flag**/
    private ContentType contType;
    @Parameter(required = true)
    private Long contId;
    @Property
    private String reason;
    @Property
    private String reasonDesc;
    @Inject
    private FlagDAO flagDAO;
    @InjectComponent
    private Zone flagZone;
    @Parameter(required = true)
    private Object objectToflag;
    private Flag f;
    @Inject
    private Logger logger;
    @Inject
    private LongPassageDAO longpassageDAO;
    @Inject
    private LongDualPassageDAO longDualPassageDAO;
    @Inject
    private ShortPassageDAO shortpassageDAO;
    @Inject
    private ShortDualPassageDAO shortDualPassageDAO;
    @Inject
    private PromptDAO promptDAO;
    @Inject
    private ImprovingParagraphDAO improvingDAO;
    @Inject
    private ArticleDAO articleDAO;
    @Inject
    private QuestionDAO questionDAO;
    @Inject
    private VocabDAO vocabDAO;
    @Inject
    private OpenQuestionDAO openDAO;
    @Inject
    private OpenAnswerDAO openAnswerDAO;

    @CommitAfter
    Block onSuccessFromFlagForm() {
        if (reason != null) {
            f = new Flag();
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
            f.setFlagger(user);
            f.setStatus(FlagStatus.NEW);
            



            Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());

            f.setUpdatedAt(now);
            f.setCreatedAt(now);
            saveFlagToObject();
            flagDAO.doSave(f);


        }

        return flagresponse;
    }

    Block onActionFromCloseFlagBlock() {
        flagZone.getBody();
        return flagblock;
    }

    Boolean saveFlagToObject() {

        if (objectToflag == null) {
            logger.error("No Object was set as parameter for new flag or object is null");
            return false;
        }
        if (objectToflag instanceof Article) {
            Article article = (Article) objectToflag;
            article = articleDAO.findById(article.getId());

            f.setContentType(ContentType.Article);
            f.setArticle(article);
            article.getFlags().add(f);
            articleDAO.doSave(article);
            return true;
        }
        if (objectToflag instanceof LongPassage) {
            LongPassage longpassage = (LongPassage) objectToflag;
            longpassage = longpassageDAO.findById(longpassage.getId());

            f.setContentType(ContentType.LongPassage);
            f.setlongpassage(longpassage);
            longpassage.getFlags().add(f);
            longpassageDAO.doSave(longpassage);

            return true;
        }
        if (objectToflag instanceof LongDualPassage) {
            LongDualPassage longpassage = (LongDualPassage) objectToflag;
            f.setContentType(ContentType.LongDualPassage);
            longpassage = longDualPassageDAO.findById(longpassage.getId());


            f.setlongdualpassage(longpassage);
            longpassage.getFlags().add(f);

            longDualPassageDAO.doSave(longpassage);
            return true;
        }

        if (objectToflag instanceof ShortPassage) {
            ShortPassage shortpassage = (ShortPassage) objectToflag;
            f.setContentType(ContentType.ShortPassage);
            shortpassage = shortpassageDAO.findById(shortpassage.getId());

            f.setshortpassage(shortpassage);
            shortpassage.getFlags().add(f);

            shortpassageDAO.doSave(shortpassage);

            return true;
        }

        if (objectToflag instanceof ShortDualPassage) {
            ShortDualPassage shortdualpassage = (ShortDualPassage) objectToflag;
            f.setContentType(ContentType.ShortDualPassage);
            shortdualpassage = shortDualPassageDAO.findById(shortdualpassage.getId());
            shortdualpassage.getFlags().add(f);

            f.setshortdualpassage(shortdualpassage);
            shortDualPassageDAO.doSave(shortdualpassage);
            return true;
        }
        if (objectToflag instanceof Prompt) {
            Prompt prompt = (Prompt) objectToflag;
            f.setContentType(ContentType.Prompt);

            prompt = promptDAO.doRetrieve(prompt.getId(), true);
            prompt.getFlags().add(f);
            f.setPrompt(prompt);
            promptDAO.doSave(prompt);
            
            return true;
        }
        if (objectToflag instanceof Question) {
            Question question = (Question) objectToflag;

            question = questionDAO.findById(question.getId());
            
            f.setContentType(ContentType.Question);
            f.setQuestion(question);
            //question.getFlags().add(f);
            //questionDAO.doSave(question);
            
            
            return true;
        }

        if (objectToflag instanceof ImprovingParagraph) {
            ImprovingParagraph iparagraph = (ImprovingParagraph) objectToflag;
            f.setContentType(ContentType.ImprovingParagraph);

            iparagraph = improvingDAO.doRetrieve(iparagraph.getId(), true);
            iparagraph.getFlags().add(f);

            f.setImprovingParagraph(iparagraph);
            improvingDAO.doSave(iparagraph);
            return true;
        }

        if (objectToflag instanceof Vocab) {
            Vocab vocab = (Vocab) objectToflag;
            f.setContentType(ContentType.Vocab);

            vocab = vocabDAO.findById(vocab.getId());
           

            f.setVocab(vocab);
            if(vocab.getFlags() == null) {

            }
             vocab.getFlags().add(f);
            vocabDAO.doSave(vocab);
            return true;
        }
          if (objectToflag instanceof OpenQuestion) {
            OpenQuestion opq = (OpenQuestion) objectToflag;
            f.setContentType(ContentType.OpenQuestion);

           opq = openDAO.findById(opq.getId());


            f.setOpenQuestion(opq);
            if(opq.getFlags() == null) {

            }
             opq.getFlags().add(f);
             openDAO.doSave(opq);
            return true;
        }

         if (objectToflag instanceof OpenAnswer) {
            OpenAnswer opq = (OpenAnswer) objectToflag;
            f.setContentType(ContentType.OpenAnswer);

           opq = openAnswerDAO.doRetrieve(opq.getId(), false);


            f.setOpenAnswer(opq);
            if(opq.getFlags() == null) {

            }
             opq.getFlags().add(f);
             openAnswerDAO.doSave(opq);
            return true;
        }

        logger.error("Object is not of a valid contentType");
        return false;
    }
}
