package com.preppa.web.pages.contribution.article;

import com.preppa.web.data.ArticleDAO;
import com.preppa.web.data.UserObDAO;
import com.preppa.web.entities.Article;
import com.preppa.web.entities.User;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.HibernateSessionManager;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;

/**
 *
 * @author nwt
 */
public class RevisionArticle {

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
    @Property
    private Integer revisionNumber;
    @Inject
    private UserObDAO userDAO;

    void onActivate(Integer articleId, Integer revId) {
        System.out.println("ArticleId is " + articleId + " RevId is " + revId );
        this.articleId = articleId;
        
        AuditReader reader = AuditReaderFactory.get(sessionManager.getSession());
        this.revisionNumber = revId;

        this.article = reader.find(Article.class, articleId, revId);
        //author = reader.find(User.class, article.getUser().getId(), revId);
        //this.article = articleDAO.findArticleByRevision(articleId, revId);
        if(article != null) {
       //     author = article.getUser();
         //   author = userDAO.findById(author.getId());
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

        return this.articleId;
    }

}
