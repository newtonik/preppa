package com.preppa.web.components.questiontypes.improving;

import com.preppa.web.data.ImprovingParagraphDAO;
import com.preppa.web.data.TagDAO;
import com.preppa.web.entities.ImprovingParagraph;
import com.preppa.web.entities.Tag;
import com.preppa.web.entities.User;
import com.preppa.web.pages.Index;
import com.preppa.web.pages.contribution.improving.ShowImproving;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.FieldTranslator;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.ValidationException;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
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
 * @author nikhariale
 */
@Secured("ROLE_USER")
public class NewImproving {
    @ApplicationState
    private User user;
    @Property
    private ImprovingParagraph improving;
    @Component
    private Form improvingform;
    @Property
    private String fBody;
    @Component(parameters = {"value=fBody"})
    private Editor passeditor;
    @Component
    private AutoComplete autoCompleteImprovingTag;
    @Property
    private List<Tag> addedTags = new LinkedList<Tag>();;
    @InjectPage
    private Index indexpage;
    @Inject
    private TagDAO tagDAO;
    @Property
    private String fTitle;
    @Property
    private Tag tag;
    @Inject
    private ImprovingParagraphDAO improvingParagraphDAO;
    @InjectPage
    private ShowImproving showimproving;
    @Inject
    private ComponentResources resources;

    
    @SetupRender
    void setDefaults() {
        if(!improvingform.getHasErrors())
        {
            addedTags.clear();

        }
    }

    void onActivate() {
        
    }
    Object onActionFromCancel() {
        resources.discardPersistentFieldChanges();
        return indexpage;
    }

        List<Tag> onProvideCompletionsFromAutoCompleteImprovingTag(String partial) {
        List<Tag> matches = tagDAO.findByPartialName(partial);
        return matches;

    }

    void onValidateFormFromImprovingForm() {

        if(fBody == null) {
            improvingform.recordError(passeditor, "You must include a paragraph");
        }
        if(fTitle == null) {
             improvingform.recordError("Please add a title");
        }
    }

    @CommitAfter
    Object onSuccessFromImprovingForm() {
        improving = new ImprovingParagraph();
        improving.setTitle(fTitle);

        Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
        improving.setParagraph(fBody);
        improving.setCreatedAt(now);
        improving.setUpdatedAt(now);
        improving.setUser(user);
        improving.setUpdatedBy(user);
        improving.setTaglist(addedTags);

        improvingParagraphDAO.doSave(improving);

        showimproving.setImprovingParagraph(improving);

        resources.discardPersistentFieldChanges();
        return showimproving;
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
