
package com.preppa.web.pages.user;

import com.preppa.web.data.Gender;
import com.preppa.web.data.UserObDAO;
import com.preppa.web.entities.User;
import com.preppa.web.pages.Index;
import java.sql.Timestamp;
import java.util.Date;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.chenillekit.tapestry.core.components.DateSelector;


/**
 *
 * @author nwt
 */
public class EditUser {
    @Property
    private User user;
    @InjectPage
    private Index index;
    @Inject
    private UserObDAO userDAO;
    @Inject
    private Messages messages;
    @Property
    private String fLogin;
    @Property
    private String ffName;
    @Property
    private String flName;
    @Property
    private Gender fgender;
    @Property
    private Date fdob;
    @Property
    private String femail;
    @Property
    private Integer uid;

    @Component(parameters = {"value=fdob"})
    private DateSelector datefield;
    

    //private Timestamp currentTime;
    void onActivate(int id) {
        if(id > 0) {
            this.user = userDAO.findById(id);
            uid = id;
            fLogin = user.getUsername();
            femail = user.getEmail();
            ffName = user.getFirstName();
            flName = user.getLastName();
            fdob = user.getDob();
        }
    }

    Object onPassivate() {
        return uid;
    }

    void onValidate() {

    }
    @CommitAfter
    Object onSuccess() {

        user.setEmail(femail);
        user.setDob(fdob);
        user.setLastName(flName);
        user.setFirstName(ffName);
        Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
        user.setUpdatedAt(now);
        userDAO.doSave(user);
        //session.persist(user);
        return index;
    }
}