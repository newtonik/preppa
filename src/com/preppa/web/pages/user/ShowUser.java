/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.pages.user;

import com.preppa.web.data.ArticleDAO;
import com.preppa.web.data.LongDualPassageDAO;
import com.preppa.web.data.LongPassageDAO;
import com.preppa.web.data.ShortDualPassageDAO;
import com.preppa.web.data.ShortPassageDAO;
import com.preppa.web.data.UserObDAO;
import com.preppa.web.data.UserProfileDAO;
import com.preppa.web.entities.Article;
import com.preppa.web.entities.LongDualPassage;
import com.preppa.web.entities.LongPassage;
import com.preppa.web.entities.ShortDualPassage;
import com.preppa.web.entities.ShortPassage;
import com.preppa.web.entities.User;
import com.preppa.web.entities.UserProfile;
import java.io.File;
import java.util.List;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.IncludeStylesheet;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.HibernateSessionManager;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Context;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.hibernate.envers.query.criteria.AuditConjunction;
import org.hibernate.envers.query.criteria.AuditCriterion;
import org.hibernate.envers.query.criteria.AuditProperty;
import org.hibernate.envers.query.criteria.LogicalAuditExpression;

/**
 *
 * @author newtonik
 */
@IncludeStylesheet(value = {"context:styles/showuser.css"})
@IncludeJavaScriptLibrary(value = {"context:js/jquery-1.3.2.js", "context:js/jquery/tools.tabs-1.0.1.js", "context:js/showuser.js"})
public class ShowUser {

    @Property
    private User user;
    @Property
    private UserProfile userprofile;
    @Inject
    private UserObDAO userDAO;
    @Inject
    private UserProfileDAO userprofileDAO;
    @Inject
    private Context c;
    @Inject
    private HibernateSessionManager sessionManager;
    @Inject
    private Block clickblock;
    @Property
    private List<Article> articles;
    @Inject
    private ArticleDAO articleDAO;
    @Property
    private Article article;
    @Inject
    @Property
    private Block resultblock;
    @Inject
    private LongPassageDAO longpassageDAO;
    @Property
    private LongPassage longpassage;
    @Property
    private List<LongPassage> longpassages;
    @Inject
    private LongDualPassageDAO longdualpassageDAO;
    @Property
    private LongDualPassage longdualpassage;
    @Property
    private List<LongDualPassage> longdualpassages;
    @Inject
    private ShortPassageDAO shortpassageDAO;
    @Property
    private ShortPassage shortpassage;
    @Property
    private List<ShortPassage> shortpassages;
    @Inject
    private ShortDualPassageDAO shortdualpassageDAO;
    @Property
    private ShortDualPassage shortdualpassage;
    @Property
    private List<ShortDualPassage> shortdualpassages;
    @Inject
    @Property
    private Block passageblock;

    void onActivate(Integer id) {
        this.user = userDAO.findById(id);
        userprofile = user.getUserProfile();
//        userprofile = userprofileDAO.findByUserId(user.getId());

    }

    /*public String getImageURL() {
    String classLocation = UploadImageUser.class.getName().replace('.', '/') + ".class";
    ClassLoader loader = UploadImageUser.class.getClassLoader();
    String copyLocation = loader.getResource(classLocation).toString();
    copyLocation = copyLocation.substring(8, 73); // Remove "file" from the string
    copyLocation = this.formatSpace(copyLocation);
    return copyLocation + "images/" + user.getId() + ".jpg";
    }*/
    public boolean getImageExist() {
        /*String classLocation = UploadImageUser.class.getName().replace('.', '/') + ".class";
        ClassLoader loader = UploadImageUser.class.getClassLoader();
        String copyLocation = loader.getResource(classLocation).toString();
        copyLocation = copyLocation.substring(8, 73); // Remove "file" from the string
        copyLocation = this.formatSpace(copyLocation);
        System.out.println(copyLocation + "images/" + user.getId() + ".jpg");
        File check = new File(copyLocation + "images/" + user.getId()  +  ".jpg");*/
        char slash = (char) 92;
        System.out.println("This " + c.getRealFile("/").getPath() + "/images/" + user.getId() + ".jpg");
        File check = new File(c.getRealFile("/").getPath() + "/images/" + user.getId() + ".jpg");

        return check.exists();
    }

    private String formatSpace(String s) {
        String returnVal = new String();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '%') {
                returnVal = s.substring(0, i) + " " + s.substring(i + 3, s.length());
            }
        }

        return returnVal;
    }

    void onActivate(User user) {
        this.user = user;
        articles = articleDAO.findByUserId(user.getId());
    }

    public String getPathString() {
        return "/preppa/images/" + user.getId() + ".jpg";
    }

    //public IAsset getImageAsset() { return new ExternalAsset(imageURL, null); }
    public boolean getIsProfile() {
        return !(userprofile == null);
    }

    User onPassivate() {
        return user;
    }

    void setUserPage(User u) {
        if (u != null) {
            this.user = userDAO.findById(u.getId());

        }
    }

    Block onActionFromGetArticles() {

        //articles = articleDAO.findByUserId(user.getId());

        AuditReader reader = AuditReaderFactory.get(sessionManager.getSession());

        

                AuditCriterion usercrit =  AuditEntity.property("user").eq(user);
                AuditCriterion upudatercrit = AuditEntity.property("updatedBy").eq(user);

        LogicalAuditExpression orExp = new LogicalAuditExpression(usercrit, upudatercrit, "or");
        AuditQuery query = reader.createQuery().forRevisionsOfEntity(Article.class, true, false)
                .add(orExp)
                .addOrder(AuditEntity.revisionNumber().asc());
         List<Article> results = query.getResultList();
         if(results.size() > 0)
            articles.addAll(results);

         
        return resultblock;
    }

    Block onActionFromGetPassages() {
        shortpassages = shortpassageDAO.findByUserId(user.getId());
        shortdualpassages = shortdualpassageDAO.findByUserId(user.getId());
        longpassages = longpassageDAO.findByUserId(user.getId());
        longdualpassages = longdualpassageDAO.findByUserId(user.getId());
        return passageblock;
    }

    Block onActionFromGetMultipleChoices() {
        return clickblock;
    }

    Block onActionFromGetGridins() {
        return clickblock;

    }

    Block onActionFromGetOpenQuestions() {
        return clickblock;
    }
}
