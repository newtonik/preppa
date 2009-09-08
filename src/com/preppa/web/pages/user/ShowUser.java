/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.pages.user;

import com.preppa.web.data.ArticleDAO;
import com.preppa.web.data.GridinDAO;
import com.preppa.web.data.LongDualPassageDAO;
import com.preppa.web.data.LongPassageDAO;
import com.preppa.web.data.QuestionDAO;
import com.preppa.web.data.ShortDualPassageDAO;
import com.preppa.web.data.ShortPassageDAO;
import com.preppa.web.data.UserObDAO;
import com.preppa.web.data.UserProfileDAO;
import com.preppa.web.data.VocabDAO;
import com.preppa.web.entities.Article;
import com.preppa.web.entities.Gridin;
import com.preppa.web.entities.LongDualPassage;
import com.preppa.web.entities.LongPassage;
import com.preppa.web.entities.Question;
import com.preppa.web.entities.ShortDualPassage;
import com.preppa.web.entities.ShortPassage;
import com.preppa.web.entities.User;
import com.preppa.web.entities.UserProfile;
import com.preppa.web.entities.Vocab;
import com.preppa.web.pages.Index;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.IncludeStylesheet;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.HibernateSessionManager;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Context;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.hibernate.envers.query.criteria.AuditCriterion;
import org.hibernate.envers.query.criteria.LogicalAuditExpression;

/**
 *
 * @author newtonik
 */
@IncludeStylesheet(value = {"context:styles/showuser.css"})
@IncludeJavaScriptLibrary(value = {"context:js/jquery-1.3.2.js", "context:js/jquery/tools.tabs-1.0.1.js", "context:js/showuser.js"})
public class ShowUser {
    @ApplicationState
    private User user;
    @Property
    private User owner;
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
    @Property
    private List<Vocab> vocabs;
    @Property
    private Vocab vocab;
    @Inject
    private VocabDAO vocabDAO;
     @Inject
    @Property
    private Block vocabblock;
    @Property
    private List<Question> questions;
    @Property
    private Question question;
    @Inject
    private QuestionDAO questionDAO;
    @Inject
    @Property
    private Block questionblock;
    @Property
    private List<Gridin> gridins;
    @Property
    private Gridin gridin;
    @Inject
    @Property
    private Block gridinblock;
    @Inject
    private GridinDAO gridinDAO;
    @InjectPage
    private Index index;

    Object onActivate(Integer id) {
        if(user != null ) {

            this.owner = userDAO.findById(id);
            if(user.getId() == owner.getId()) {
                userprofile = owner.getUserProfile();
                return null;
            }
            else 
            {
                return index;
            }
        }
        else
        {
            return index;
        }
        
    }

    /*public String getImageURL() {
    String classLocation = UploadImageUser.class.getName().replace('.', '/') + ".class";
    ClassLoader loader = UploadImageUser.class.getClassLoader();
    String copyLocation = loader.getResource(classLocation).toString();
    copyLocation = copyLocation.substring(8, 73); // Remove "file" from the string
    copyLocation = this.formatSpace(copyLocation);
    return copyLocation + "images/" + owner.getId() + ".jpg";
    }*/
    public boolean getImageExist() {
        /*String classLocation = UploadImageUser.class.getName().replace('.', '/') + ".class";
        ClassLoader loader = UploadImageUser.class.getClassLoader();
        String copyLocation = loader.getResource(classLocation).toString();
        copyLocation = copyLocation.substring(8, 73); // Remove "file" from the string
        copyLocation = this.formatSpace(copyLocation);
        System.out.println(copyLocation + "images/" + owner.getId() + ".jpg");
        File check = new File(copyLocation + "images/" + owner.getId()  +  ".jpg");*/
        char slash = (char) 92;
        System.out.println("This " + c.getRealFile("/").getPath() + "/images/" + owner.getId() + ".jpg");
        File check = new File(c.getRealFile("/").getPath() + "/images/" + owner.getId() + ".jpg");

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
        this.owner = user;
        updateArticles();
    }

    public String getPathString() {
        return "/preppa/images/" + owner.getId() + ".jpg";
    }

    //public IAsset getImageAsset() { return new ExternalAsset(imageURL, null); }
    public boolean getIsProfile() {
        return !(userprofile == null);
    }

    User onPassivate() {
        return owner;
    }

    void setUserPage(User u) {
        if (u != null) {
            this.owner = userDAO.findById(u.getId());

        }
    }

    Block onActionFromGetArticles() {

        updateArticles();

        return resultblock;
    }

    void updateLongPassages() {
        AuditReader reader = AuditReaderFactory.get(sessionManager.getSession());
        AuditCriterion usercrit = AuditEntity.property("user").eq(owner);
        AuditCriterion upudatercrit = AuditEntity.property("updatedBy").eq(owner);

        LogicalAuditExpression orExp = new LogicalAuditExpression(usercrit, upudatercrit, "or");
        AuditQuery query = reader.createQuery().forRevisionsOfEntity(LongPassage.class, false, false).add(orExp).addProjection(AuditEntity.property("id").distinct());



        List results = query.getResultList();

        Iterator iter = results.iterator();
        List<Integer> ids = new ArrayList<Integer>();

        while (iter.hasNext()) {

            Map test = (HashMap) iter.next();

            Integer i = new Integer((Integer) test.get("id"));

            if (!ids.contains(i)) {
                ids.add(i);
            }

        }

        if(ids.size() > 0) {
            longpassages = longpassageDAO.findByUserIds(ids);
        }
        else
        {
            longpassages = null;
        }
    }

     void updateGridins() {
        AuditReader reader = AuditReaderFactory.get(sessionManager.getSession());
        AuditCriterion usercrit = AuditEntity.property("user").eq(owner);
        AuditCriterion upudatercrit = AuditEntity.property("updatedBy").eq(owner);

        LogicalAuditExpression orExp = new LogicalAuditExpression(usercrit, upudatercrit, "or");
        AuditQuery query = reader.createQuery().forRevisionsOfEntity(LongPassage.class, false, false).add(orExp).addProjection(AuditEntity.property("id").distinct());



        List results = query.getResultList();

        Iterator iter = results.iterator();
        List<Long> ids = new ArrayList<Long>();

        while (iter.hasNext()) {

            Map test = (HashMap) iter.next();
             Integer in = new Integer((Integer) test.get("id"));
            Long i = in.longValue();

            if (!ids.contains(i)) {
                ids.add(i);
            }

        }

         if(ids.size() > 0) {
            gridins = gridinDAO.findByUserIds(ids);
         }
         else
         {
            gridins = null;
         }
    }
    void updateVocabs() {
        AuditReader reader = AuditReaderFactory.get(sessionManager.getSession());
        AuditCriterion usercrit = AuditEntity.property("user").eq(owner);
        AuditCriterion upudatercrit = AuditEntity.property("updatedBy").eq(owner);

        LogicalAuditExpression orExp = new LogicalAuditExpression(usercrit, upudatercrit, "or");
        AuditQuery query = reader.createQuery().forRevisionsOfEntity(Vocab.class, false, false).add(orExp).addProjection(AuditEntity.property("id").distinct());



        List results = query.getResultList();

        Iterator iter = results.iterator();
        List<Integer> ids = new ArrayList<Integer>();

        while (iter.hasNext()) {

            Map test = (HashMap) iter.next();

            Integer i = new Integer((Integer) test.get("id"));

            if (!ids.contains(i)) {
                ids.add(i);
            }

        }

         if(ids.size() > 0) {
            vocabs = vocabDAO.findByUserIds(ids);
         }
         else
         {
            vocabs = null;
         }
    }

    void updateLongDualPassages() {
        AuditReader reader = AuditReaderFactory.get(sessionManager.getSession());
        AuditCriterion usercrit = AuditEntity.property("user").eq(owner);
        AuditCriterion upudatercrit = AuditEntity.property("updatedBy").eq(owner);

        LogicalAuditExpression orExp = new LogicalAuditExpression(usercrit, upudatercrit, "or");
        AuditQuery query = reader.createQuery().forRevisionsOfEntity(LongDualPassage.class, false, false).add(orExp).addProjection(AuditEntity.property("id").distinct());



        List results = query.getResultList();

        Iterator iter = results.iterator();
        List<Integer> ids = new ArrayList<Integer>();

        while (iter.hasNext()) {

            Map test = (HashMap) iter.next();

            Integer i = new Integer((Integer) test.get("id"));

            if (!ids.contains(i)) {
                ids.add(i);
            }

        }
         if(ids.size() > 0) {
            longdualpassages = longdualpassageDAO.findByUserIds(ids);
         }
         else
         {
            longdualpassages = null;
         }
    }

    void updateShortPassages() {
        AuditReader reader = AuditReaderFactory.get(sessionManager.getSession());
        AuditCriterion usercrit = AuditEntity.property("user").eq(owner);
        AuditCriterion upudatercrit = AuditEntity.property("updatedBy").eq(owner);

        LogicalAuditExpression orExp = new LogicalAuditExpression(usercrit, upudatercrit, "or");
        AuditQuery query = reader.createQuery().forRevisionsOfEntity(ShortPassage.class, false, false).add(orExp).addProjection(AuditEntity.property("id").distinct());



        List results = query.getResultList();

        Iterator iter = results.iterator();
        List<Integer> ids = new ArrayList<Integer>();

        while (iter.hasNext()) {

            Map test = (HashMap) iter.next();

            Integer i = new Integer((Integer) test.get("id"));

            if (!ids.contains(i)) {
                ids.add(i);
            }

        }

         if(ids.size() > 0) {
            shortpassages = shortpassageDAO.findByUserIds(ids);
         }
         else
         {
            shortpassages = null;
         }
    }

    void updateQuestions() {
                AuditReader reader = AuditReaderFactory.get(sessionManager.getSession());
        AuditCriterion usercrit = AuditEntity.property("user").eq(owner);
        AuditCriterion upudatercrit = AuditEntity.property("updatedBy").eq(owner);

        LogicalAuditExpression orExp = new LogicalAuditExpression(usercrit, upudatercrit, "or");
        AuditQuery query = reader.createQuery().forRevisionsOfEntity(Question.class, false, false).add(orExp).addProjection(AuditEntity.property("id").distinct());



        List results = query.getResultList();

        Iterator iter = results.iterator();
        List<Integer> ids = new ArrayList<Integer>();

        while (iter.hasNext()) {

            Map test = (HashMap) iter.next();

            Integer i = new Integer((Integer) test.get("id"));

            if (!ids.contains(i)) {
                ids.add(i);
            }

        }
         if(ids.size() > 0) {
            questions = questionDAO.findByUserIds(ids);
         }
         else
         {
            questions = null;
         }

    }
    void updateShortDualPassages() {
        AuditReader reader = AuditReaderFactory.get(sessionManager.getSession());
        AuditCriterion usercrit = AuditEntity.property("user").eq(owner);
        AuditCriterion upudatercrit = AuditEntity.property("updatedBy").eq(owner);

        LogicalAuditExpression orExp = new LogicalAuditExpression(usercrit, upudatercrit, "or");
        AuditQuery query = reader.createQuery().forRevisionsOfEntity(ShortDualPassage.class, false, false).add(orExp).addProjection(AuditEntity.property("id").distinct());



        List results = query.getResultList();

        Iterator iter = results.iterator();
        List<Integer> ids = new ArrayList<Integer>();

        while (iter.hasNext()) {

            Map test = (HashMap) iter.next();

            Integer i = new Integer((Integer) test.get("id"));

            if (!ids.contains(i)) {
                ids.add(i);
            }

        }

         if(ids.size() > 0) {
            shortdualpassages = shortdualpassageDAO.findByUserIds(ids);
         }
         else
         {
            shortdualpassages = null;
         }
    }

    void updateArticles() {

        AuditReader reader = AuditReaderFactory.get(sessionManager.getSession());



        AuditCriterion usercrit = AuditEntity.property("user").eq(owner);
        AuditCriterion updatercrit = AuditEntity.property("updatedBy").eq(owner);
        AuditCriterion orcrit = AuditEntity.or(updatercrit, usercrit);

    
        AuditQuery query = reader.createQuery().forRevisionsOfEntity(Article.class, false, false)
                .add(orcrit)
                
                .addProjection(AuditEntity.property("id").distinct());

        


        List results = query.getResultList();

        Iterator iter = results.iterator();
        List<Integer> ids = new ArrayList<Integer>();

        while (iter.hasNext()) {

            Map test = (HashMap) iter.next();

            Integer i = new Integer((Integer) test.get("id"));

            if (!ids.contains(i)) {
                ids.add(i);
            }
          
        }

        if(ids.size() > 0) {
            articles = articleDAO.findByUserIds(ids);
        }
        else {
            articles = null;
        }

    }

    Block onActionFromGetPassages() {

        updateLongDualPassages();
        updateLongPassages();
        updateShortDualPassages();
        updateShortPassages();

        return passageblock;
    }

    Block onActionFromGetQuestions() {
        updateQuestions();
        return questionblock;
    }

    Block onActionFromGetGridins() {
        updateGridins();
        return gridinblock;

    }

    Block onActionFromGetOpenQuestions() {
        return clickblock;
    }
    Block onActionFromGetVocabs() {
        updateVocabs();

        return vocabblock;
    }
}
