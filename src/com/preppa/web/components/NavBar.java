/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.components;

import com.preppa.web.entities.User;
import nu.localhost.tapestry5.springsecurity.services.LogoutService;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Value;
import org.apache.tapestry5.services.Request;

/**
 *
 * @author newtonik
 */
public class NavBar {
//    @Parameter(defaultPrefix="literal", required=true)
//    private String pages;
//
//    @Inject
//    private ComponentResources resources;
//
//    @Property
//    private String pageName;
//
//    public String[] getPageNames()
//    {
//        return pages.split(",");
//    }
//
//    public String getTabClass()
//    {
//        if (pageName.equalsIgnoreCase(resources.getPageName()))
//            return "current";
//
//        return null;
//    }
 @Inject
 @Value("${spring-security.logout}")
 private String logoutUrl;
    @Inject
    private Request request;
    @Inject
    private LogoutService logout;
  @ApplicationState
    private User user;
  private boolean userExists;
    /**
     * @return the logoutUrl
     */
    public String getLogoutUrl() {
        return request.getContextPath() + logoutUrl;
    }

    void onActionFromLogoutUrl() {
        logout.logout();
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }
    public boolean getUserExists()
    {
        return userExists;
    }

}
