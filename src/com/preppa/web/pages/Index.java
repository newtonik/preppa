package com.preppa.web.pages;

import com.preppa.web.data.UserObDAO;
import com.preppa.web.data.VocabDAO;
import com.preppa.web.entities.User;
import com.preppa.web.entities.Vocab;
import java.util.List;
import java.util.Random;

import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;


public class Index {

    private final Random random = new Random();
    private String message = "mymessage";
    @ApplicationState
    private User user;
    @Inject
    private UserObDAO userDAO;
    private boolean userExists;
    
    void onActivate() {
        //Attempt to get the authentication token if the user is already logged in but not in
        //the ASO object.
       if(!userExists)
       {
            Authentication token  = SecurityContextHolder.getContext().getAuthentication();
            if(token != null) {
                System.out.println(token.getPrincipal());
                if((token.getPrincipal() instanceof String)) {
                    String username = (String) token.getPrincipal();
                    if(username != null && !username.equals("anonymous"))
                            user = userDAO.findByUsername(username);

                }
                else
                {
                    user = (User)token.getPrincipal();
                }
            }

       }

    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        System.out.println("Setting the message: " + message);
        this.message = message;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    public boolean getUserExists() {
        return userExists;
    }
    //@Inject
    //private Session session;
    @Inject
    private VocabDAO vocabDAO;
    /*@Inject
    private DictionaryDAO dictionaryDAO;

    public List<DictionaryWord> getDictionary()
    {
        //return session.createCriteria(UserOb.class).list();
        return dictionaryDAO.findByPartialName("Ab");
    }*/

    public List<User> getUsers()
    {
        //return session.createCriteria(UserOb.class).list();
        return userDAO.findAll();
    }
    public List<Vocab> getVocab()
    {
        //return session.createCriteria(UserOb.class).list();
        return vocabDAO.findAll();
    }

}
