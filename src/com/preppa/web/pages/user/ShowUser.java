/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.pages.user;

import com.preppa.web.data.UserObDAO;
import com.preppa.web.data.UserProfileDAO;
import com.preppa.web.entities.User;
import com.preppa.web.entities.UserProfile;
import java.io.File;
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


  /*public String getImageURL() {
      String classLocation = UploadImageUser.class.getName().replace('.', '/') + ".class";
      ClassLoader loader = UploadImageUser.class.getClassLoader();
      String copyLocation = loader.getResource(classLocation).toString();
      copyLocation = copyLocation.substring(8, 73); // Remove "file" from the string
      copyLocation = this.formatSpace(copyLocation);
      return copyLocation + "images/" + user.getId() + ".jpg";
  }*/

  public boolean getImageExist() {
      String classLocation = UploadImageUser.class.getName().replace('.', '/') + ".class";
      ClassLoader loader = UploadImageUser.class.getClassLoader();
      String copyLocation = loader.getResource(classLocation).toString();
      copyLocation = copyLocation.substring(8, 73); // Remove "file" from the string
      copyLocation = this.formatSpace(copyLocation);
      System.out.println(copyLocation + "images/" + user.getId() + ".jpg");
      File check = new File(copyLocation + "images/" + user.getId()  +  ".jpg");
      return check.exists();
  }

  private String formatSpace(String s) {
            String returnVal = new String();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '%') {
                    returnVal = s.substring(0, i) + " " + s.substring(i+3, s.length());
                }
            }

            return returnVal;
        }
  
  void onActivate(User user)
  {
    this.user = user;
  }

  public String getPathString() {
      return "/preppa/images/" + user.getId() + ".jpg";
  }

  //public IAsset getImageAsset() { return new ExternalAsset(imageURL, null); }

  public boolean getIsProfile() {
    return !(userprofile == null);
  }

  User onPassivate()
  {
    return user;
  }
}
