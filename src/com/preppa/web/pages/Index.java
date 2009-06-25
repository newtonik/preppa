package com.preppa.web.pages;

import com.preppa.web.data.UserObDAO;
import com.preppa.web.data.VocabDAO;
import com.preppa.web.entities.User;
import com.preppa.web.entities.Vocab;
import java.util.List;
import java.util.Random;

import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.ioc.annotations.Inject;


public class Index {

    private final Random random = new Random();
    private String message = "mymessage";
    @ApplicationState
    private User user;

    private boolean userExists;


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
    private UserObDAO userDAO;
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
