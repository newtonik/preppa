package com.preppa.web.pages;

import com.preppa.web.data.UserObDAO;
import com.preppa.web.entities.User;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.IncludeStylesheet;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.PasswordField;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Value;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.RequestGlobals;
import org.apache.tapestry5.services.Session;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.security.providers.dao.DaoAuthenticationProvider;
import org.springframework.security.providers.dao.SaltSource;
import org.springframework.security.providers.encoding.PasswordEncoder;
import org.springframework.security.providers.encoding.ShaPasswordEncoder;
import org.springframework.security.ui.AbstractProcessingFilter;
import org.springframework.security.ui.rememberme.RememberMeServices;
import org.springframework.security.ui.savedrequest.SavedRequest;
import org.springframework.security.userdetails.UserDetailsService;

/**
 * The login page (adapted from the tapestry-spring-security project).
 *
 * @author newtonik
 */
@IncludeStylesheet(value = {"context:styles/loginpage.css"})
@IncludeJavaScriptLibrary(value = { "context:js/jquery-1.3.2.js", "context:js/jquery/jquery.tools.min.js", "context:js/loginpage.js"})
public class LoginFailed
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
    @Inject
    private RememberMeServices rememberMeServices;
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
    @Inject
    private RequestGlobals requestGlobals;
    private java.net.URL url = null;
    @Property
    private Boolean rememberme;
    @Inject
    private ComponentResources resources;

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

    @SetupRender
    void setupForm() {
      
    }
    void onValidateForm() {

       provider.setUserDetailsService(userserve);
        
       provider.setPasswordEncoder(new ShaPasswordEncoder());
       authtoken = new UsernamePasswordAuthenticationToken(fLogin, fpass);
       provider.setSaltSource(salt);
       Authentication token = null;
       try {
      token = provider.authenticate(authtoken);
       }
       catch (org.springframework.security.BadCredentialsException e) {
           loginform.recordError("Either the Username or Password is incorrect, Please try again.");
           return;
       }
      if(token.isAuthenticated())
      {
          System.out.println("user has been authenticated");
          this.user = userDAO.findByUsername(fLogin);
          SecurityContextHolder.getContext().setAuthentication(token);
          
          SavedRequest savedRequest =
                  (SavedRequest) requestGlobals.getHTTPServletRequest().getSession().getAttribute(AbstractProcessingFilter.SPRING_SECURITY_SAVED_REQUEST_KEY);
          Session s = request.getSession(false);
          s.invalidate();
          s = request.getSession(true);
           if(savedRequest != null){
                 url = null;

                try {
                        url = new URL(savedRequest.getRequestURL());
                } catch (MalformedURLException e){
                        System.out.println("malformed url:" + savedRequest.getRequestURI());
                }
        }



      }

      else
      {
          //fpass = null;
          //fLogin = null;
        
          loginform.recordError("Either the Username or Password is incorrect, Please try again.");

      }

    }

    @CommitAfter
    Object onSuccess() {
      
        if(url != null)
        {
            System.out.println(user.getLogincount());
            if (user.getLogincount() == null) {
                user.setLogincount(1);
            } else {
                user.setLogincount(user.getLogincount() + 1);
            }
            Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
            user.setLastlogintime(now);
            userDAO.doSave(user);

            return url;

        }
         return "index";
    }

}
