package com.preppa.web.pages.contribution.article;

import com.preppa.web.data.ArticleDAO;
import com.preppa.web.data.UserObDAO;
import com.preppa.web.entities.Article;
import com.preppa.web.entities.User;
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
public class RevisionsArticle {


    @Property
    private Article article;
    @Property
    private String title;
    @Inject
    private ArticleDAO articleDAO;
    private Integer articleId;
    @Inject
    private Session session;
    @Property
    private User author;
    @Property
    private String authorname;
    @Inject
    private HibernateSessionManager sessionManager;
    @Inject
    private UserObDAO userDAO;
    @Property
    private List<Article> articles;
    @Property
    private List<Integer> revisions;
    @Property
    private Integer revision;

    void onActivate(Integer id) {
        if( id != null)
        {
            this.article = articleDAO.findById(id);
        }
        AuditReader reader = AuditReaderFactory.get(sessionManager.getSession());

        AuditQuery query = reader.createQuery().forRevisionsOfEntity(Article.class, false, true)
                .addProjection(AuditEntity.revisionNumber())
                .add(AuditEntity.id().eq(article.getId()));

        revisions = query.getResultList();

    }
}
