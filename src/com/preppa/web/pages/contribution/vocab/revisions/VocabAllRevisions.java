package com.preppa.web.pages.contribution.vocab.revisions;

import com.preppa.web.data.UserObDAO;
import com.preppa.web.data.VocabDAO;
import com.preppa.web.entities.User;
import com.preppa.web.entities.Vocab;
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
 * @author nwt
 */
public class VocabAllRevisions {


    @Property
    private Vocab vocab;
    @Property
    private String title;
    @Inject
    private VocabDAO vocabDAO;
    private Integer vocabId;
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
    private List<Vocab> allVocab;
    @Property
    private List<Revision> revisions;
    @Property
    private Revision revision;



    void onActivate(Integer vId)
    {
        if(vId != null)
        {
            vocabId = vId;

            vocab = vocabDAO.findById(vId);

        }
        AuditReader reader = AuditReaderFactory.get(sessionManager.getSession());

        AuditQuery query = reader.createQuery().forRevisionsOfEntity(Vocab.class, false, true)
                .addProjection(AuditEntity.revisionNumber())
                .addProjection(AuditEntity.property("name"))
                .addProjection(AuditEntity.property("user_id"))
                .addProjection(AuditEntity.property("updatedAt"))
                .addProjection(AuditEntity.property("revComment"))
                .addOrder(AuditEntity.revisionNumber().asc())
                .add(AuditEntity.id().eq(vocab.getId()));

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
        //System.out.println("Count is " + count);
    }

    Integer onPassivate() {
        return this.vocabId;
    }
}
