/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.pages.contribution.shortpassage;

import com.preppa.web.data.ShortPassageDAO;
import com.preppa.web.data.PassageDAO;
import com.preppa.web.entities.ShortPassage;
import com.preppa.web.entities.ShortPassage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author newtonik
 */
public class ShowShortPassage {
@Property
@Persist
private ShortPassage passage;
@Inject
private ShortPassageDAO passageDAO;
@Inject
private PassageDAO passDA0;
private Integer pid;
void onActivate(int id) {
        this.passage = passageDAO.findById(id);
        this.pid = passage.getId();
}

Integer onPassivate() {
    return this.pid;
}
void setPassagePage(ShortPassage passage) {
        this.passage = passage;
    }

 

}
