package com.preppa.web.utils;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.springframework.security.Authentication;
import org.springframework.security.providers.dao.DaoAuthenticationProvider;

/**
 *
 * @author nwt
 */
public class RegistrationPage {
    @Inject
    private DaoAuthenticationProvider authenticator;
    @Property
    private Authentication authentication;

    void onSubmitForm()
    {
        
        authenticator.authenticate(authentication);
    }
}
