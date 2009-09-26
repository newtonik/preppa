/*
 * Preppa, Inc.
 * 
 * Copyright 2009. All rights reserved.
 * 
 * $Id$
 */
package com.preppa.web.components.questiontypes.shortpassage;

import com.preppa.web.data.ShortDualPassageDAO;
import com.preppa.web.data.TagDAO;
import com.preppa.web.data.TestsubjectDAO;
import com.preppa.web.entities.ShortDualPassage;
import com.preppa.web.entities.Tag;
import com.preppa.web.entities.Testsubject;
import com.preppa.web.entities.User;
import com.preppa.web.services.PassageService;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.FieldTranslator;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.ValidationException;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.chenillekit.tapestry.core.components.Editor;
import org.chenillekit.tapestry.core.components.prototype_ui.AutoComplete;
import org.springframework.security.annotation.Secured;

/**
 *
 * @author newtonik
 */
//@Secured("ROLE_USER")
//@IncludeJavaScriptLibrary(value = {"context:js/passage.js"})
public class NewDualShortPassage {

    @Property
    private ShortDualPassage shortDualpassage;
    @ApplicationState
    private User user;
    @Inject
    private ShortDualPassageDAO shortDualpassageDAO;
    @Component(parameters = {"value=fbodyone"})
    private Editor passeditorone;
    @Component(parameters = {"value=fbodytwo"})
    private Editor passeditortwo;
    private int size;
    @Property
    @Persist
    private Testsubject testsubject;
    private List<Testsubject> testsubjects;
    @Inject
    private TestsubjectDAO testsubjectDAO;
    @Property
    private String fTitle;
    @Property
    private String fTitle2;
    @Property
    private String fBodyone;
    @Property
    private String fBodytwo;
    @Property
    private String fSource;
    @Property
    private String fTag;
    @InjectPage
    private com.preppa.web.pages.contribution.shortpassage.ShowDualShortPassage showdualpasage;
    @Component
    private AutoComplete autoCompleteTag;
    @Property
    private List<Tag> addedTags = new LinkedList<Tag>();
    @Inject
    private TagDAO tagDAO;
    @Inject
    private PassageService passageService;
    @Property
    private String fname;
    @Property
    private Tag tag;
    @Component
    private Form dualpassageform;
    @Parameter
    private ShortDualPassage passed;
    @Inject
    private ComponentResources resources;

    public void NewDualShortPassage() {
        this.shortDualpassage = new ShortDualPassage();
    }

    @SetupRender
    void getSetupItems() {

        if (!dualpassageform.getHasErrors()) {
            addedTags.clear();

        }

    }

    /**
     * Add form validation here
     */
    void onValidateFromDualPassageForm() {
        if (fBodyone == null) {
            dualpassageform.recordError(passeditorone, "You need a value for Passage 1");
        }
        if (fBodytwo == null) {
            dualpassageform.recordError(passeditortwo, "You need a value for Passage 2");
        }

    }

    @CommitAfter
    Object onSuccessFromDualPassageForm() {
        if (shortDualpassage == null) {
            this.shortDualpassage = new ShortDualPassage();
        }
        shortDualpassage.setPassageone(fBodyone);
        shortDualpassage.setPassagetwo(fBodytwo);
        shortDualpassage.setTitle(fTitle);
        shortDualpassage.setSource(fSource);




        for (Tag t : addedTags) {
            if (!(shortDualpassage.getTaglist().contains(t))) {
                shortDualpassage.getTaglist().add(t);
            }
        }

        passageService.checkShortDualPassage(shortDualpassage);
        shortDualpassage.setUser(user);
        Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());

        shortDualpassage.setCreatedAt(now);
        shortDualpassage.setUpdatedAt(now);



        shortDualpassageDAO.doSave(shortDualpassage);
         resources.discardPersistentFieldChanges();
        showdualpasage.setShortDualPassage(shortDualpassage);
        return showdualpasage;
    }

    public static String sanitize(String string) {
        return string.replaceAll("(?i)<script.*?>.*?</script.*?>", "") // case 1
                .replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "") // case 2
                .replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "");     // case 3
    }

    /**
     * @return the testsubjects
     */
    public List<Testsubject> getTestsubjects() {
        this.setTestsubjects(testsubjectDAO.findAll());
        return testsubjects;
    }

    /**
     * @param testsubjects the testsubjects to set
     */
    public void setTestsubjects(List<Testsubject> testsubjects) {
        this.testsubjects = testsubjects;
    }

    List<String> onProvideCompletionsFromTags(String partial) {
        List<Tag> matches = tagDAO.findByPartialName(partial);

        List<String> result = new ArrayList<String>();
        for (Tag t : matches) {
            result.add(t.getName());
        }
        return result;
    }

    List<Tag> onProvideCompletionsFromAutocompleteTag(String partial) {
        List<Tag> matches = tagDAO.findByPartialName(partial);
        return matches;

    }

    public FieldTranslator getTagTranslator() {
        return new FieldTranslator<Tag>() {

            @Override
            public String toClient(Tag value) {
                String clientValue = "0";
                if (value != null) {
                    clientValue = String.valueOf(value.getName());
                }

                return clientValue;
            }

            @Override
            public void render(MarkupWriter writer) {
            }

            @Override
            public Class<Tag> getType() {
                return Tag.class;
            }

            @Override
            public Tag parse(String clientValue) throws ValidationException {
                Tag serverValue = null;
//            if(clientValue == null) {
//                Tag t = new Tag();
//                t.setName(clientValue);
//            }
                System.out.println(clientValue);

                if (clientValue != null && clientValue.length() > 0 && !clientValue.equals("0")) {
                    System.out.println(clientValue);
                    serverValue = tagDAO.findByName(clientValue).get(0);
                }
                return serverValue;
            }
        };
    }
}
