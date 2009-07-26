/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.pages.announcements;

import com.preppa.web.data.AnnouncementDAO;
import com.preppa.web.entities.Announcement;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.chenillekit.tapestry.core.components.Editor;

/**
 *
 * @author newtonik
 */
public class NewAnnouncement {


    @Property
    @Persist
    private Announcement announce;
    @Inject
    private AnnouncementDAO announceDAO;
    @Component(parameters = {"value=aAnnouncement"})
    private Editor message;
    @InjectPage
    private ShowAnnouncement showannounce;
    @Property
    private String aTitle;
    @Property
    private String aAnnouncement;
	@Component(id = "announcementform")
	private Form _form;

    void onActivate( Announcement announce) {
        this.announce = announce;
    }

    Object onPassivate() {
        return announce;
    }

    @CommitAfter
    Object onSuccess() {
        if (announce == null) {
            announce = new Announcement();
        }
        announce.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        announce.setUpdatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        announce.setMessage(aAnnouncement);
        announce.setTitle(aTitle);


         

        announceDAO.doSave(announce);
        showannounce.setannounce(announce);
        return showannounce;
    }

}
