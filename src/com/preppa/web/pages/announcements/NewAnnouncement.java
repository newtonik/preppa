/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.pages.announcements;

import com.preppa.web.data.AnnouncementDAO;
import com.preppa.web.entities.Announcement;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.chenillekit.tapestry.core.components.Editor;

/**
 *
 * @author newtonik
 */
public class NewAnnouncement {

    @Property
    private Announcement announce;
    @Inject
    private AnnouncementDAO announceDAO;
    @Component(parameters = {"value=announce.message"})
    private Editor message;
    @InjectPage
    private ShowAnnouncement showannounce;


    void onActivate( Announcement announce) {
        this.announce = announce;
    }

    Object onPassivate() {
        return announce;
    }

     @CommitAfter
    Object onSuccess() {

         announce.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
         announce.setUpdatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
         


         
         //article.setUser(userDAO.findById(1));

         announceDAO.doSave(announce);
         //showarticle.setarticle(article);
         return showannounce;
    }

}
