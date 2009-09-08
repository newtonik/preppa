
package com.preppa.web.components;

import com.preppa.web.data.TagDAO;
import com.preppa.web.entities.Tag;
import com.preppa.web.pages.contribution.tag.TagSubmitted;
import java.util.List;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.Request;
import org.slf4j.Logger;


/**
 *
 * @author nwt
 */
@IncludeJavaScriptLibrary(value = {"context:js/tagcomp.js"})
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
    @Inject
    private Block responseblock;
    @InjectComponent
    private Zone tagzone;
    @Inject
    private Logger logger;
    @Inject
    private Request request;
    //Tag Form data
    @Inject
    @Property
    private Block newtagblock;

   Object onValidateFormFromTagForm() {
        List<Tag> tolist =  tagDAO.findByName(fname);

        if(tolist.size() > 0) {
            fname=null;
            tagform.recordError(tagTextfield, "Tag Exists!");
        }

        if(tagform.getHasErrors() && request.isXHR())
        {
            return tagzone;
        }
        else
        {
            return null;
        }
    }
           //Funtions for adding new tags and topics
        @CommitAfter
        JSONObject onSuccessFromTagForm() {
            List<Tag> tolist =  tagDAO.findByName(fname);
            JSONObject json = new JSONObject();
            System.out.print(tolist);
            if(tolist.size() > 0) {
                String markup = "<p>  <b>" + fname +
                    "</b> already exists. <p>";
                json.put("content", markup);

            }
            else
            {
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
