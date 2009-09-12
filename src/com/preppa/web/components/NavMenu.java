package com.preppa.web.components;

import com.preppa.web.entities.User;
import javax.servlet.http.HttpServletRequest;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.IncludeStylesheet;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author nwt
 */
@IncludeStylesheet(value = {"context:styles/navmenu.css"})
public class NavMenu {

    @Inject
    private HttpServletRequest _request;
    @ApplicationState
    private User user;
    private boolean userExists;

    public User getUser() {
        return user;
    }
    public boolean getuserExists() {
        return userExists;
    }
    public String getpath() {
        String url = _request.getRequestURL().toString();
        return url.substring(url.length() - 8, url.length());
    }

    public boolean getHome() {
        String url = _request.getRequestURL().toString();
        String check = url.substring(url.length() - 8, url.length());
        return check.contains("/preppa/") || check.contains("/preppa");
    }

    public boolean getProfile() {
        String url = _request.getRequestURL().toString();
        return url.contains("/user/") || url.contains("loginpage");
    }

    public boolean getOpenQuestion() {
        String url = _request.getRequestURL().toString();
        return url.contains("openquestion");
    }

    public boolean getLearning() {
        String url = _request.getRequestURL().toString();
        return url.contains("learning");
    }
}
