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
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.chenillekit.tapestry.core.components.Editor;
import org.chenillekit.tapestry.core.components.prototype_ui.AutoComplete;

/**
 *
 * @author nikhariale
 */
public class EditImproving {

    @ApplicationState
    private User user;
    @Parameter
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
    private List<Tag> addedTags = new LinkedList<Tag>();

    ;
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
    @Property
    private String fComment;
    @Inject
    private ComponentResources resources;

    @SetupRender
    void setDefaults() {
        if (improving != null) {
            fBody = improving.getParagraph();
            fTitle = improving.getTitle();
            addedTags = improving.getTaglist();
        }
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

        if (fBody == null) {
            improvingform.recordError(passeditor, "You must include a paragraph");
        }
        if (fTitle == null) {
            improvingform.recordError("Please add a title");
        }
    }

    @CommitAfter
    Object onSuccessFromImprovingForm() {
        improving.setTitle(fTitle);

        Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
        improving.setParagraph(fBody);

        improving.setUpdatedAt(now);

        improving.setUpdatedBy(user);
        improving.setTaglist(addedTags);
        improving.setRevComment(fComment);

        improvingParagraphDAO.doSave(improving);

        showimproving.setImprovingParagraph(improving);
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
