/*
 * Preppa, Inc.
 *
 * Copyright 2009. All rights
reserved.
 *
 * $Id$
 */
package com.preppa.web.pages.user;

import com.preppa.web.data.UserObDAO;
import com.preppa.web.data.UserProfileDAO;
import com.preppa.web.entities.User;
import com.preppa.web.entities.UserProfile;
import com.preppa.web.pages.Index;
import java.sql.Timestamp;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.springframework.security.annotation.Secured;

/**
 *
 * @author Jan Jan
 */
@Secured("ROLE_USER")
public class EditProfileUser {

    @ApplicationState
    private User user;
    @Persist
    @Property
    private UserProfile userprofile;
    @Property
    private String aboutme;
    @Property
    private String activities;
    @Property
    private String interests;
    @Component(id = "profileform")
    private Form _form;
    @Inject
    private UserProfileDAO userprofileDAO;
    @Inject
    private UserObDAO userDAO;
    @InjectPage
    private ShowUser show;

    void onActivate(int id) {
        userprofile = userprofileDAO.findById(id);
        aboutme = userprofile.getAboutMe();
        activities = userprofile.getActivities();
        interests = userprofile.getInterests();
        //    user = userprofile.getUser();
    }

    @CommitAfter
    Object onSuccess() {
        //userprofile = new UserProfile();
        userprofile.setAboutMe(aboutme);
        userprofile.setActivities(activities);
        userprofile.setInterests(interests);
        Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());

        userprofile.setUpdatedAt(now);

        userprofileDAO.doSave(userprofile);
        user.setUserProfile(userprofile);
        user.setUpdatedAt(now);
        userDAO.doSave(user);

        show.onActivate(user.getId());

        return show;
    }
}
