  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.pages.contribution.question.revisions;

import com.preppa.web.components.questiontypes.multichoice.ShowMultiChoice;
import com.preppa.web.data.QuestionDAO;
import com.preppa.web.entities.Question;
import com.preppa.web.entities.User;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.hibernate.HibernateSessionManager;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;

/**
 *
 * @author Jan Jan
 */
public class QuestionRevision {

@Property
    @Persist
    private Question question;
    @Inject
    private QuestionDAO questionDAO;
    private Integer pid;
    private Integer questionId;
    private Long revisionNumber;
    private String authorname;
    @Property
    private User author;
    @Inject
    private HibernateSessionManager sessionManager;
    @Component
    private ShowMultiChoice showquestion;
    
    void onpageLoaded() {

    }

    @SetupRender
    void setDefaults() {

    }
    void onActivate(String params) {

        String[] tokens = params.split("_");
        Integer passId = Integer.parseInt(tokens[0]);
        Long revId = Long.parseLong(tokens[1]);
        this.questionId = passId;

        AuditReader reader = AuditReaderFactory.get(sessionManager.getSession());
        this.revisionNumber = revId;

        this.question = reader.find(Question.class, questionId, revisionNumber);
        //author = reader.find(User.class, article.getUser().getId(), revId);
        //this.article = articleDAO.findArticleByRevision(articleId, revId);
        if(question != null) {
             if(author != null) {
                authorname = author.getUsername();
            }
            if(authorname == null)
            {
                authorname = "unknown dude";
            }
        }
    }

    Integer onPassivate() {
        return this.pid;
    }

}
