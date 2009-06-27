package com.preppa.web.pages;

import com.preppa.web.data.UserObDAO;
import com.preppa.web.entities.User;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.PasswordField;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Value;
import org.apache.tapestry5.services.Request;
import org.springframework.security.Authentication;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.security.providers.dao.DaoAuthenticationProvider;
import org.springframework.security.providers.dao.SaltSource;
import org.springframework.security.providers.encoding.PasswordEncoder;
import org.springframework.security.providers.encoding.ShaPasswordEncoder;
import org.springframework.security.userdetails.UserDetailsService;

/**
 * The login page (adapted from the tapestry-spring-security project).
 *
 * @author newtonik
 */
public class LoginPage
{
    @Inject
    @Value("${spring-security.check.url}")
    private String checkUrl;
    @Inject
    @Value("${spring-security.target.url}")
    private String returnurl;
    @Inject
    @Value("${spring-security.openidcheck.url}")
    private String openidCheckUrl;
    @Property
    private String fpass;
    @Property
    private String fLogin;
    @Inject
    private Request request;
    private DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    private UsernamePasswordAuthenticationToken authtoken;
    @Inject
    private UserDetailsService userserve;
    private boolean failed = false;
    @Inject
    private Messages messages;
    @Component
    private Form loginform;
    @Component
    private PasswordField passwordField;
    @InjectPage
    private Index index;
    @ApplicationState
    private User user;
    @Inject
    private UserObDAO userDAO;
    @Inject
    private PasswordEncoder encoder;
    @Inject
    private SaltSource salt;
    
    public String getfLogin()
    {
        return fLogin;
    }

    public void setfLogin(String login) {
        this.fLogin = login;
    }
    public boolean isFailed()
    {
        return failed;
    }

    public String getLoginCheckUrl()
    {
        return request.getContextPath() + checkUrl;
    }

    public String getOpenIdCheckUrl()
    {
        return request.getContextPath() + openidCheckUrl;
    }

    void onActivate(String extra)
    {
        if (extra.equals("failed"))
        {
            failed = true;
        }
    }

    void onValidateForm() {

         provider.setUserDetailsService(userserve);
        
        provider.setPasswordEncoder(new ShaPasswordEncoder());
       authtoken = new UsernamePasswordAuthenticationToken(fLogin, fpass);
       provider.setSaltSource(salt);

      Authentication token = provider.authenticate(authtoken);
      if(token.isAuthenticated())
      {
          System.out.println("user has been authenticated");
          this.user = userDAO.findByUsername(fLogin);
      }

      else
      {
          //fpass = null;
          //fLogin = null;
          loginform.recordError("Either the Username or Password is incorrect, Please try again.");

      }

    }

    String onSuccess() {
        System.out.println("user has been authenticated");
         return "index";
    }

}
