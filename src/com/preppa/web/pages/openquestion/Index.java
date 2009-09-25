package com.preppa.web.pages.openquestion;

import com.preppa.web.data.OpenQuestionDAO;
import com.preppa.web.entities.OpenQuestion;
import java.util.List;
import org.apache.tapestry5.annotations.IncludeStylesheet;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.HibernateSessionManager;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;

/**
 *
 * @author nwt
 */
@IncludeStylesheet(value = {"context:styles/openquestion.css"})
public class Index {
    @Inject
    private OpenQuestionDAO openDAO;
    @Property
    private List<OpenQuestion> questions;
    @Property
    private OpenQuestion question;
    @Property
    private String slink;
    @Inject
    private HibernateSessionManager sessionManager;
    private Session session;


    void onActivate() {
        questions = openDAO.findAll();

        slink = "openquestion/search";

         session = sessionManager.getSession();
         FullTextSession fullTextSession = Search.getFullTextSession(session);
            Transaction tx = fullTextSession.beginTransaction();



            for(OpenQuestion q: questions) {
                fullTextSession.index(q);
            }
            tx.commit();

    }

}
