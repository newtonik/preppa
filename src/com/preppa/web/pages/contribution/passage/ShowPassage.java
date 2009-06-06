/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.pages.contribution.passage;

import com.preppa.web.data.LongPassageDAO;
import com.preppa.web.data.PassageDAO;
import com.preppa.web.entities.LongPassage;
import com.preppa.web.entities.Passage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author newtonik
 */
public class ShowPassage {
@Property
@Persist
private LongPassage passage;
@Inject
private LongPassageDAO passageDAO;
@Inject
private PassageDAO passDA0;
@Property
private String ptitle;
@Property
private Passage apassage;
Object onActivate(int id) {
        this.passage = passageDAO.findById(id);
        apassage = passage.getPassage();
        
        return this;
    }



void setarticle(LongPassage passage) {
        this.passage = passage;
    }

}
