package com.preppa.web.pages.contribution.gridin;

import org.apache.tapestry5.annotations.Component;
import org.springframework.security.annotation.Secured;



/**
 *
 * @author nwt
 */


@Secured("ROLE_USER")
public class NewGridin {
    @Component
    private com.preppa.web.components.questiontypes.gridin.NewGridin newgridin;
    
}
