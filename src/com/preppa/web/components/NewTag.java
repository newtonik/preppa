
package com.preppa.web.components;

import com.preppa.web.data.TagDAO;
import com.preppa.web.entities.Tag;
import com.preppa.web.pages.contribution.tag.TagSubmitted;
import java.util.List;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;


/**
 *
 * @author nwt
 */
public class NewTag {

    @Inject
    private TagDAO tagDAO;
    @Property
    private Tag tag;
    @Inject
    private Messages messages;
    @Component
    private TextField tagTextfield;
    @Property
    private String fname;
    @InjectComponent
    private Form tagform;
    @InjectPage
    private TagSubmitted tagpage;
    @Inject
    private Block responseblock;
    @InjectComponent
    private Zone formzone;
    @Inject
    private Logger logger;

    void onValidateForm() {
        List<Tag> tolist =  tagDAO.findByName(fname);

        if(tolist.size() > 0) {
            fname=null;
            tagform.recordError(tagTextfield, "Tag Exists!");
        }
    }
//    @CommitAfter
//    Object onSuccessFromTagForm()
//    {
//        tag = new Tag();
//        tag.setName(fname);
//
//        tagDAO.doSave(tag);
//
//        return tagform;
//    }
    void onActionFromFormZone() {
        System.out.println("I have been submitted Ajax stype");
    }
   Object onActionFromSubmitButton() {
        System.out.println("I have been submitted Ajax stype");
        return tagform;
    }
     void onSelectedFromSubmitButton() {
        System.out.println("I have been submitted Ajax stype");
    }
     Block onActionFromSubmitLink() {
           logger.debug("I have been submitted Ajax stype");
                   tag = new Tag();
        tag.setName(fname);

        tagDAO.doSave(tag);


         return responseblock;
     }
}
