package com.preppa.web.pages.learning.practice;

import com.preppa.web.data.PracticeSetDAO;
import com.preppa.web.entities.PracticeSet;
import com.preppa.web.entities.User;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.IncludeStylesheet;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Checkbox;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author nwt
 */
@IncludeStylesheet(value = {"context:styles/practice.css"})
@IncludeJavaScriptLibrary(value = {"context:js/jquery-1.3.2.js", "context:js/jquery/tools.tabs-1.0.1.js", "context:js/pratice.js"})
public class Index {

    @ApplicationState
    private User user;
    @Property
    private PracticeSet pset;
    @Inject
    private PracticeSetDAO psetDAO;
    @Component
    private Form practicesetupform;
    @Component
    private Checkbox randomSubjects;
    @Property
    private Boolean addrandom;
    @Component
    private Checkbox addMathcheck;
    @Property
    private Boolean addMath;
    @Component
    private Checkbox addWritingcheck;
    @Property
    private Boolean addWriting;
    @Component
    private Checkbox addCriticalcheck;
    @Property
    private Boolean addCriticalRead;
    @Component
    private Checkbox gridincheck;
    @Component
    private Checkbox multichoicecheck;
    @Inject
    private Messages messages;

    void onActivate() {
        pset = new PracticeSet();
    }

    void onValidateFormFromPracticeSetupForm() {
        System.out.println("validating");
        System.out.println(addMath);
        if (!addrandom) {
        }
        System.out.println(pset.getNumofquestions());
        if (pset.getNumofquestions() == null) {
            practicesetupform.recordError(messages.get("numberoptions-required-message"));
        }
    }

    void onSuccessFromPracticeSetupForm() {
        
    }
}
