/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.pages.user;

import com.preppa.web.data.UserObDAO;
import com.preppa.web.entities.UserOb;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author newtonik
 */
public class ShowUser {

  @Property
  private UserOb user;

  @Inject
  private UserObDAO userDAO;

  void onActivate(Integer id) {

    this.user = userDAO.findById(id);
  }



  void onActivate(UserOb user)
  {
    this.user = user;
  }

  UserOb onPassivate()
  {
    return user;
  }
}
