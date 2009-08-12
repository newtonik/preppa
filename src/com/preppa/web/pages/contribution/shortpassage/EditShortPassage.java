/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.pages.contribution.shortpassage;

import com.preppa.web.data.ShortPassageDAO;
import com.preppa.web.data.PassageDAO;
import com.preppa.web.data.TagDAO;
import com.preppa.web.data.TestsubjectDAO;
import com.preppa.web.entities.ShortPassage;
import com.preppa.web.entities.Tag;
import com.preppa.web.entities.Testsubject;

import com.preppa.web.entities.User;
import com.preppa.web.services.PassageService;
import com.preppa.web.utils.PassageType;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.FieldTranslator;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.ValidationException;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.chenillekit.tapestry.core.components.Editor;
import org.chenillekit.tapestry.core.components.prototype_ui.AutoComplete;
import org.springframework.security.annotation.Secured;

/**
 *
 * @author newtonik
 */
@Secured("ROLE_USER")
@IncludeJavaScriptLibrary(value = {"context:js/passage.js"})
public class EditShortPassage {

    @Property
    private ShortPassage shortpassage;
    @ApplicationState
    private User user;
    @Inject
    private ShortPassageDAO shortpassageDAO;
    @Inject
    private PassageDAO passageDAO;
    @Component(parameters = {"value=fbody"})
    private Editor pass1;
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
    private String fBody;
    @Property
    private String fSource;
    @InjectPage
    private ShowShortPassage showpassage;
    @Component
    private AutoComplete autoCompleteTag;
    @Property
    private List<Tag> addedTags = new LinkedList<Tag>();
    @Inject
    private TagDAO tagDAO;
    @Inject
    private PassageService passageService;
    @Property
    private String fComment;
    @Inject
    @Property
    private Block newtagblock;
    @Property
    private String fname;
    @Property
    private Tag tag;
    @Component
    private Form editpassageform;

    void onActivate(int id) {
        this.shortpassage = shortpassageDAO.findById(id);
        if (shortpassage != null) {
            fTitle = shortpassage.getTitle();
            fBody = shortpassage.getPassage();
            fSource = shortpassage.getSources();
            addedTags = shortpassage.getTaglist();
        }
    }

    Integer onPassivate() {
        return shortpassage.getId();
    }

    @CommitAfter
    Object onSuccessFromEditPassageForm() {




        // passageDAO.doSave(p);
        shortpassage.setPassage(fBody);
        shortpassage.setSources(fSource);
        shortpassage.setTitle(fTitle);
        shortpassage.setComplete(true);
        shortpassage.setUser(user);
        shortpassage.setRevComment(fComment);
        if (fBody.length() > 100) {
            shortpassage.setPassagetype(PassageType.LONG_DUAL);
        } else {
            shortpassage.setPassagetype(PassageType.SHORT_DUAL);
        }


        for (Tag t : addedTags) {
            if (!(shortpassage.getTaglist().contains(t))) {
                shortpassage.getTaglist().add(t);
            }
        }

        passageService.checkShortPassage(shortpassage);
        Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());

        shortpassage.setUpdatedAt(now);



        shortpassageDAO.doSave(shortpassage);

        showpassage.setPassagePage(shortpassage);
        return showpassage;
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

    /**
     * autocomplete function for tags
     * @param partial - partial string
     * @return matches - returns matches from the database
     */
    List<Tag> onProvideCompletionsFromAutocompleteTag(String partial) {
        List<Tag> matches = tagDAO.findByPartialName(partial);
        for (Tag t : matches) {
            if (addedTags.contains(t)) {
                matches.remove(t);
            }
        }
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
    //Funtions for adding new tags and topics

    @CommitAfter
    JSONObject onSuccessFromTagForm() {
        List<Tag> tolist = tagDAO.findByName(fname);
        JSONObject json = new JSONObject();
        if (tolist.size() > 0) {
            String markup = "<p>  <b>" + fname +
                    "</b> already exists. <p>";
            json.put("content", markup);

        } else {
            tag = new Tag();
            tag.setName(fname);

            tagDAO.doSave(tag);
            String markup = "<p> You just submitted <b>" + tag.getName() +
                    "</b>. Please add it using the dropdown <p>";
            json.put("content", markup);

        }


        // return new TextStreamResponse("text/json", json.toString());
        return json;
    }

    Block onActionFromCloseTag() {
        return newtagblock;
    }
}
