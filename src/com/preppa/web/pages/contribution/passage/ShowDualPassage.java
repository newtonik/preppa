/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.pages.contribution.passage;

import com.preppa.web.components.CQuestion;
import com.preppa.web.data.LongDualPassageDAO;
import com.preppa.web.data.PassageDAO;
import com.preppa.web.entities.LongDualPassage;
import java.util.LinkedList;
import java.util.List;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author nwt
 */
public class ShowDualPassage {

    @Property
    @Persist
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
    @Component
    private CQuestion firstquestion;

    Object onActivate(int id) {
        this.passage = longpassageDAO.findById(id);
        //passage1 = passage.getPassageone();
//        passage2 = passage.getPassagetwo();
//        ftitle = passage.getTitle();
//        if(ftitle == null) {
//            ftitle = passage.getPassageone().getTitle();
//        }
        return this;
    }
 

    void setLongDualPassage(LongDualPassage passage) {
        this.passage = passage;
    }

    Block onActionFromAddQuestion() {
        return questionblock;
    }
    void onSubmitForm() {
        System.out.println("submit event has been received here.!!!!");
    }
}
