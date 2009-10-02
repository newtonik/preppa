package com.preppa.web.components.questiontypes.improving;

import com.preppa.web.data.ImprovingParagraphDAO;
import com.preppa.web.data.TagDAO;
import com.preppa.web.entities.ImprovingParagraph;
import com.preppa.web.entities.Tag;
import com.preppa.web.entities.User;
import com.preppa.web.pages.Index;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
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

    
    @SetupRender
    void setDefaults() {
          
    }

    void onActivate() {
        
    }
    Object onActionFromCancel() {
        return indexpage;
    }

        List<Tag> onProvideCompletionsFromAutoCompleteImprovingTag(String partial) {
        List<Tag> matches = tagDAO.findByPartialName(partial);
        return matches;

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

        return null;
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
