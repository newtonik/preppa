package com.preppa.web.templates;

import com.preppa.web.entities.User;
import com.preppa.web.services.EmailService;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.chenillekit.template.services.TemplateService;
import org.chenillekit.template.services.Velocity;

/**
 *
 * @author nwt
 */
public class Welcome {
    @Inject @Velocity
    private TemplateService _templateService;
    @Inject
    EmailService emailer;

    public void sendWelcomeEmail(User user) {
        
    }
}
