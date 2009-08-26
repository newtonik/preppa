package com.preppa.web.pages.openquestion;

import com.preppa.web.data.OpenQuestionDAO;
import com.preppa.web.data.TagDAO;
import com.preppa.web.entities.OpenQuestion;
import com.preppa.web.entities.Tag;
import com.preppa.web.entities.User;
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
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.chenillekit.tapestry.core.components.Editor;
import org.chenillekit.tapestry.core.components.prototype_ui.AutoComplete;
import org.springframework.security.annotation.Secured;

/**
 *
 * @author nwt
 */
@IncludeJavaScriptLibrary(value = {"context:js/openquestion.js"})
@Secured("ROLE_USER")
public class NewOpenQuestion {

    @ApplicationState
    private User user;
    @Property
    private OpenQuestion question;
    @Inject
    private OpenQuestionDAO openDAO;
    @Component
    private Form questionForm;
    @Property
    private String fTitle;
    @Property
    private String fQuestion;
    @Component(parameters = {"value=fQuestion"})
    private Editor pass1;
    @Property
    private List<Tag> addedTags = new LinkedList<Tag>();
    @Component
    private AutoComplete autoCompleteTag;
    @Component
    private TextField titleField;
    @Property
    private String fname;
    @Inject
    private TagDAO tagDAO;
    @InjectPage
    private ShowOpenQuestion showquestion;
    @Property
    private Tag tag;
    @Inject
    @Property
    private Block newtagblock;

    void onActivate() {
        question = new OpenQuestion();
    }

    void onValidateFormQuestionForm() {
        if (fTitle == null) {
            questionForm.recordError(titleField, "You need a Title");
        }


    }

    @CommitAfter
    Object onSuccessFromQuestionForm() {

        question.setTitle(fTitle);
        question.setQuestion(fQuestion);
        question.setOwner(user);
        for (Tag t : addedTags) {
            if (!(question.getTaglist().contains(t))) {
                question.getTaglist().add(t);
            }

        }
        Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
        question.setUpdatedAt(now);
        question.setCreatedAt(now);

        openDAO.doSave(question);
        showquestion.setOpenQuestion(question);
        return showquestion;
    }
    //Funtions for adding new tags and topics

    @CommitAfter
    JSONObject onSuccessFromTagForm() {
        List<Tag> tolist = tagDAO.findByName(fname);
        JSONObject json = new JSONObject();
        System.out.print(tolist);
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

            public String toClient(Tag value) {
                String clientValue = "0";
                if (value != null) {
                    clientValue = String.valueOf(value.getName());
                }

                return clientValue;
            }

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

    Block onActionFromCloseTag() {
        return newtagblock;
    }
}
