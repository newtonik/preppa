/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.pages.user;

import com.preppa.web.data.UserObDAO;
import com.preppa.web.data.UserProfileDAO;
import com.preppa.web.entities.User;
import com.preppa.web.entities.UserProfile;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author newtonik
 */
public class ShowUser {

  @Property
  private User user;
  @Property
  private UserProfile userprofile;

  @Inject
  private UserObDAO userDAO;

  @Inject 
  private UserProfileDAO userprofileDAO;

  void onActivate(Integer id) {
    this.user = userDAO.findById(id);
    userprofile = userprofileDAO.findByUserId(user.getId());
  }



  void onActivate(User user)
  {
    this.user = user;
  }

  public boolean getIsProfile() {
    return !(userprofile == null);
  }

  User onPassivate()
  {
    return user;
  }
}
