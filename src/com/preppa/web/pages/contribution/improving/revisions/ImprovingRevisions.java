  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.pages.contribution.improving.revisions;

import com.preppa.web.data.ImprovingParagraphDAO;
import com.preppa.web.data.UserObDAO;
import com.preppa.web.entities.ImprovingParagraph;
import com.preppa.web.entities.Question;
import com.preppa.web.entities.User;
import com.preppa.web.utils.Revision;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.HibernateSessionManager;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;

/**
 *
 * @author Jan Jan
 */
public class ImprovingRevisions {

    @Property
    private ImprovingParagraph question;
    @Property
    private String title;
    @Inject
    private ImprovingParagraphDAO questionDAO;
    private Integer questionId;
    @Inject
    private Session session;
    @Property
    private User author;
    @Property
    private String authorname;
    @Inject
    private HibernateSessionManager sessionManager;
    @Property
    private Integer revisionNumber;
    @Inject
    private UserObDAO userDAO;
    @Property
    private List<Question> allQuestions;
    @Property
    private List<Revision> revisions;
    @Property
    private Revision revision;



void onActivate(Integer quesId)
    {
        if(quesId != null)
        {
            questionId = quesId;

            question = questionDAO.findById(quesId);

        }
                AuditReader reader = AuditReaderFactory.get(sessionManager.getSession());

        AuditQuery query = reader.createQuery().forRevisionsOfEntity(ImprovingParagraph.class, false, true)
                .addProjection(AuditEntity.revisionNumber())
                .addProjection(AuditEntity.property("title"))
                .addProjection(AuditEntity.property("user_id"))
                .addProjection(AuditEntity.property("updatedAt"))
                .addProjection(AuditEntity.property("revComment"))
                .addOrder(AuditEntity.revisionNumber().asc())
                .add(AuditEntity.id().eq(question.getId()));

        List results = query.getResultList();

        Iterator iter = results.iterator();
        int count = 0;
         revisions = new ArrayList<Revision>();

         while (iter.hasNext())
        {
            Revision result = new Revision();
            Object[] obj = (Object[]) iter.next();
            result.setRevisionNumber((Long) obj[0]);
            result.setName((String) obj[1]);

            User u = userDAO.findById((Integer) obj[2]);

            result.setUser(u);
            result.setRevisionTime((Date) (obj[3]));
            result.setRevComment((String) (obj[4]));
            revisions.add(result);
            count++;
        }
        System.out.println("Count is " + count);
    }

    Integer onPassivate() {

        return this.questionId;
    }
}
