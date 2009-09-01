package com.preppa.web.pages.contribution.gridin;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.springframework.security.annotation.Secured;



/**
 *
 * @author nwt
 */

@IncludeJavaScriptLibrary(value = {"context:js/confirmexit.js"})
@Secured("ROLE_USER")
public class NewGridin {
    @Component
    private com.preppa.web.components.questiontypes.gridin.NewGridin newgridin;
    
}
