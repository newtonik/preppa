/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.pages.contribution.shortpassage;

import com.preppa.web.components.CQuestion;
import com.preppa.web.components.SQuestion;
import com.preppa.web.data.ShortDualPassageDAO;
import com.preppa.web.data.PassageDAO;
import com.preppa.web.entities.Question;
import com.preppa.web.entities.ShortDualPassage;
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
public class ShowDualShortPassage {

    @Property
    private ShortDualPassage passage;
    @Inject
    private ShortDualPassageDAO shortpassageDAO;
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
    
    void onpageLoaded() {
        firstquestion.setPageFalse();
        
    }
    @SetupRender
    void setDefaults() {
        lastquestion = true;
        onequestion = true;
    }
    void onActivate(int id) {
        this.passage = shortpassageDAO.findById(id);

        passageid = id;
        size = passage.getQuestions().size();
        if(size > 0) {
            count = 0;
            q1 = passage.getQuestions().get(count);
        }
        
//        return this;
    }
 
    Integer onPassivate() {
        return passageid;
    }
    void setShortDualPassage(ShortDualPassage passage) {
        this.passageid = passage.getId();
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
        passage = shortpassageDAO.findById(passage.getId());
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
            passage = shortpassageDAO.findById(passage.getId());
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
            passage = shortpassageDAO.findById(passage.getId());
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
    void onSubmitForm() {
        System.out.println("submit event has been received here.!!!!");
    }

  
}
