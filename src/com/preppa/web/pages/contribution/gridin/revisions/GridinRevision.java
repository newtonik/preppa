  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.pages.contribution.gridin.revisions;

import com.preppa.web.components.questiontypes.gridin.ShowGridin;
import com.preppa.web.components.questiontypes.multichoice.ShowMultiChoice;
import com.preppa.web.data.GridinDAO;
import com.preppa.web.entities.Gridin;
import com.preppa.web.entities.GridinAnswer;
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
public class GridinRevision {

@Property
    @Persist
    private Gridin question;
    @Inject
    private GridinDAO gridinDAO;
    private Integer pid;
    private Integer questionId;
    private Long revisionNumber;
    private String authorname;
    @Property
    private User author;
    @Inject
    private HibernateSessionManager sessionManager;
    @Component
    private ShowGridin showquestion;
    @Property
    private GridinAnswer answer;
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

        this.question = reader.find(Gridin.class, questionId.longValue(), revisionNumber);
        answer = question.getAnswers().get(0);
        //author = reader.find(User.class, article.getUser().getId(), revId);
        //this.article = articleDAO.findArticleByRevision(articleId, revId);
        if(question != null) {
             System.out.println("I'm not null! Question is " + question.getId() );
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
