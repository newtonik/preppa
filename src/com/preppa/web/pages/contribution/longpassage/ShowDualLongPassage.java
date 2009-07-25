/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.pages.contribution.longpassage;

import com.preppa.web.components.CQuestion;
import com.preppa.web.components.SQuestion;
import com.preppa.web.data.LongDualPassageDAO;
import com.preppa.web.data.PassageDAO;
import com.preppa.web.entities.LongDualPassage;
import com.preppa.web.entities.Question;
import com.preppa.web.entities.Tag;
import java.util.LinkedList;
import java.util.List;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author nwt
 */
public class ShowDualLongPassage {

    @Property
    private LongDualPassage passage;
    @Inject
    private LongDualPassageDAO longpassageDAO;
    @Inject
    private PassageDAO passDA0;
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
    @Property
    private List<Tag> tags = new LinkedList<Tag>();

    void onpageLoaded() {
        firstquestion.setPageFalse();
        
    }
    @SetupRender
    void setDefaults() {
        lastquestion = true;
        onequestion = true;
    }
    void onActivate(int id) {
        this.passage = longpassageDAO.findById(id);
        this.tags = passage.getTaglist();
        passageid = id;

//        return this;
    }
 
    Integer onPassivate() {
        return passageid;
    }
    void setLongDualPassage(LongDualPassage passage) {
        if(passage != null) {
            this.passage = passage;
            this.passageid = passage.getId();
        }
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
        passage = longpassageDAO.findById(passage.getId());
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
            passage = longpassageDAO.findById(passage.getId());
            listquestions = passage.getQuestions();
            size = listquestions.size();
            questionschanged = false;
         }
             System.out.println("Size is " + size + " count is " + count);
         
         if(count < (size-1) && (size != 0))
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
             System.out.println("Size is " + size + " count is " + count);
         

         q1 = listquestions.get(count);
         return showquestionBlock;
     }
      Block onActionFromPrevShowQuestion() {
         if(questionschanged) {

          System.out.println("questions have been updated");
            passage = longpassageDAO.findById(passage.getId());
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
          System.out.println("Size is " + size + " count is " + count);

         q1 = listquestions.get(count);
         return showquestionBlock;
     }
    void onSubmitForm() {
        System.out.println("submit event has been received here.!!!!");
    }
}
