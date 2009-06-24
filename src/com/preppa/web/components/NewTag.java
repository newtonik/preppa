
package com.preppa.web.components;

import com.preppa.web.data.TagDAO;
import com.preppa.web.entities.Tag;
import com.preppa.web.pages.contribution.tag.TagSubmitted;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;


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
    @Component
    private Form tagform;
    @InjectPage
    private TagSubmitted tagpage;


//    void onSubmitForm() {
//        List<Tag> tolist =  tagDAO.findByName(fname);
//
//        if(tolist.size() > 0) {
//            fname=null;
//            tagform.recordError(tagTextfield, messages.get("passwords-dont-match"));
//        }
//    }
//    @CommitAfter
//    void onSuccess()
//    {
//        tag = new Tag();
//        tag.setName(fname);
//
//        tagDAO.doSave(tag);
//
//    }
    void onActionFromFormZone() {
        System.out.println("I have been submitted Ajax stype");
    }
     void onSelectedFromFormZone() {
        System.out.println("I have been submitted Ajax stype");
    }
}
