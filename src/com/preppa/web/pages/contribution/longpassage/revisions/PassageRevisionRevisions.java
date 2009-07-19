package com.preppa.web.pages.contribution.longpassage.revisions;

import com.preppa.web.components.CQuestion;
import com.preppa.web.components.SQuestion;
import com.preppa.web.data.LongPassageDAO;
import com.preppa.web.data.PassageDAO;
import com.preppa.web.entities.LongPassage;
import com.preppa.web.entities.Question;

import com.preppa.web.entities.User;
import java.util.LinkedList;
import java.util.List;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.HibernateSessionManager;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;

/**
 *
 * @author nwt
 */
public class PassageRevisionRevisions {

@Property
    @Persist
    private LongPassage passage;
    @Inject
    private LongPassageDAO passageDAO;
    @Inject
    private PassageDAO passDA0;
    private Integer pid;
    @Inject
    @Property
    private Block questionblock;
    private List<Block> questionBlocks = new LinkedList<Block>();
    @InjectComponent
    private Zone questionZone;
    @InjectComponent
    private Zone showquestionZone;
    @Inject
    @Property
    private Block showquestionBlock;
    @Component
    private CQuestion firstquestion;
    @Component
    private SQuestion showquestion;
    @Property
    private Question q1;
    @Property
    @Persist
    private int size;
    @Persist
    private int count;
    @Property
    private boolean questionschanged = false;
    private Integer passageid;
    @Property
    private boolean lastquestion;
    @Property
    private boolean onequestion;
    @Persist
    private List<Question> listquestions;
    private Long revisionNumber;
    private String authorname;
    @Property
    private User author;
    @Inject
    private HibernateSessionManager sessionManager;

    void onpageLoaded() {
            firstquestion.setPageFalse();

    }
    @SetupRender
    void setDefaults() {
            lastquestion = true;
            onequestion = true;
    }
    void onActivate(String params) {

        String[] tokens = params.split("_");
        Integer passId = Integer.parseInt(tokens[0]);
        Long revId = Long.parseLong(tokens[1]);
        this.passageid = passId;

        AuditReader reader = AuditReaderFactory.get(sessionManager.getSession());
        this.revisionNumber = revId;

        this.passage = reader.find(LongPassage.class, passageid, revisionNumber);
        //author = reader.find(User.class, article.getUser().getId(), revId);
        //this.article = articleDAO.findArticleByRevision(articleId, revId);
        if(passage != null) {
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
    void setPassagePage(LongPassage passage) {
            this.passage = passage;
        }
        Block onActionFromAddQuestion() {

        return questionblock;
    }

      Block onActionFromShowQuestionlink() {
        count = 0;

         if(count == 0) {
             onequestion = false;
         }
         else
             onequestion = true;
         if(count == size-1) {
             lastquestion = false;
         }
         else
             lastquestion = true;
        passage = passageDAO.findById(passage.getId());
        listquestions = passage.getQuestions();
        size = listquestions.size();
         if(size == 0)
            return null;
        q1 = listquestions.get(count);

        return showquestionBlock;
    }
    Block onActionFromRemoveShowQuestion() {
        questionschanged = true;
        return null;
    }
     Block onActionFromRemoveNewQuestion() {
        return null;
    }
     Block onActionFromNextShowQuestion() {
         if(questionschanged) {
             System.out.println("questions have been updated");
            passage = passageDAO.findById(passage.getId());
            listquestions = passage.getQuestions();
            size = listquestions.size();
            questionschanged = false;
         }
         System.out.println("Size is " + size);
         if(count < size-1 && (size != 0))
             count++;
         if(count == 0) {
             onequestion = false;
         }
         else
             onequestion = true;

         if(count == size-1) {
             lastquestion = false;
         }
         else
             lastquestion = true;


         q1 = listquestions.get(count);
         return showquestionBlock;
     }
      Block onActionFromPrevShowQuestion() {
         if(questionschanged) {

          System.out.println("questions have been updated");
            passage = passageDAO.findById(passage.getId());
            listquestions = passage.getQuestions();
            size = listquestions.size();
            questionschanged = false;
         }
         if(count > 0 && count <= (size-1))
         {
             count--;
         }
         if(count == 0) {
             onequestion = false;
         }
         else
             onequestion = true;
         if(count == size-1) {
             lastquestion = false;
         }
         else
             lastquestion = true;

         q1 = listquestions.get(count);
         return showquestionBlock;
     }

}
