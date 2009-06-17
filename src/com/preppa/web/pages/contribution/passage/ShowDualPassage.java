/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.pages.contribution.passage;

import com.preppa.web.data.LongDualPassageDAO;
import com.preppa.web.data.PassageDAO;
import com.preppa.web.entities.LongDualPassage;
import com.preppa.web.entities.Passage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
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
}
