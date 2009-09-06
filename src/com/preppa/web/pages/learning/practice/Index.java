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
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.springframework.security.annotation.Secured;

/**
 *
 * @author nwt
 */
@IncludeStylesheet(value = {"context:styles/practice.css"})
@IncludeJavaScriptLibrary(value = {"context:js/jquery-1.3.2.js", "context:js/jquery/tools.tabs-1.0.1.js", "context:js/pratice.js"})
@Secured("ROLE_USER")
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

    /**
     * Validation of the practicset form
     */
    void onValidateFormFromPracticeSetupForm() {
        System.out.println("validating");
        System.out.println(addMath);
        if (!pset.getRandomsubjects()) {
        }
        System.out.println(pset.getNumofquestions());
        if (pset.getNumofquestions() == null) {
            practicesetupform.recordError(messages.get("numberoptions-required-message"));
        }

        if(pset.getMath())
        {
            if(pset.getMultichoice())
            {
                if(pset.getMultinum() == null)
                {
                    practicesetupform.recordError(messages.get("multinumberoptions-required-message"));
                }
            }
            if(pset.getGridin()) {
                if(pset.getGridinnum() == null) {
                    practicesetupform.recordError(messages.get("gridinnumberoptions-required-message"));
                }
            }
        }
        if(pset.getReading())
        {
            if(pset.getLongpassage()) {
                if(pset.getLongpassnum() == null) {
                    practicesetupform.recordError(messages.get("longnumberoptions-required-message"));
                }
            }
            if(pset.getLongdualpassage()) {
                if(pset.getLongdualpassnum() == null) {
                    practicesetupform.recordError(messages.get("longdualnumberoptions-required-message"));
                }
            }
            if(pset.getShortpassage()) {
                if(pset.getShortpassnum() == null) {
                    practicesetupform.recordError(messages.get("shortnumberoptions-required-message"));
                }

            }
            if(pset.getShortdualpassage()) {
                if(pset.getShortdualpassnum() == null) {
                    practicesetupform.recordError(messages.get("shortdualnumberoptions-required-message"));
                }
            }
        }
    }

    /**
     * process object after validation has passed
     */
    @CommitAfter
    void onSuccessFromPracticeSetupForm() {
        System.out.println("Submitting");
        pset.setOwner(user);
        psetDAO.doSave(pset);
    }
}
