/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.pages.contribution.shortpassage;

import com.preppa.web.data.ShortDualPassageDAO;
import com.preppa.web.data.PassageDAO;
import com.preppa.web.data.TagDAO;
import com.preppa.web.data.TestsubjectDAO;
import com.preppa.web.entities.ShortDualPassage;
import com.preppa.web.entities.Tag;
import com.preppa.web.entities.Testsubject;
import com.preppa.web.entities.User;
import com.preppa.web.services.PassageService;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import org.apache.tapestry5.FieldTranslator;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.ValidationException;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.chenillekit.tapestry.core.components.Editor;
import org.chenillekit.tapestry.core.components.prototype_ui.AutoComplete;
import org.springframework.security.annotation.Secured;

/**
 *
 * @author nwt
 */
@Secured("ROLE_USER")
public class EditDualShortPassage {
    @Property
    private ShortDualPassage shortDualpassage;
    @ApplicationState
    private User user;
    @Inject
    private ShortDualPassageDAO shortDualpassageDAO;
    @Inject
    private PassageDAO passageDAO;
    @Component(parameters = {"value=fBodyone"})
    private Editor pass1;
     @Component(parameters = {"value=fBodytwo"})
    private Editor pass2;
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
    private ShowDualShortPassage showdualpasage;
    @Component
    private AutoComplete autoCompleteTag;
    @Property
    private List<Tag> addedTags = new LinkedList<Tag>();
    @Inject
    private TagDAO tagDAO;
    @Inject
    private PassageService passageService;



    void onActivate(int id) {
        this.shortDualpassage = shortDualpassageDAO.findById(id);
        if(shortDualpassage != null)
        {
                    fBodyone = shortDualpassage.getPassageone();
                    fBodytwo = shortDualpassage.getPassagetwo();
                    fSource = shortDualpassage.getSource();
                    fTitle = shortDualpassage.getTitle();
                    addedTags = shortDualpassage.getTaglist();

        }

        
    }

    Integer onPassivate() {
        return shortDualpassage.getId();
    }
    @CommitAfter
    Object onSuccess() {


         shortDualpassage.setPassageone(fBodyone);
         shortDualpassage.setPassagetwo(fBodytwo);
         shortDualpassage.setTitle(fTitle);
         shortDualpassage.setSource(fSource);
         shortDualpassage.setUser(user);


          for(Tag t: addedTags) {
            if(!(shortDualpassage.getTaglist().contains(t)))
            {
                shortDualpassage.getTaglist().add(t);
            }
          }
          passageService.checkShortDualPassage(shortDualpassage);
         Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());

         shortDualpassage.setUpdatedAt(now);

         shortDualpassage.setComplete(true);

         shortDualpassageDAO.doSave(shortDualpassage);
         showdualpasage.setShortDualPassage(shortDualpassage);
         return showdualpasage;
    }
    public static String sanitize(String string) {
    return string
     .replaceAll("(?i)<script.*?>.*?</script.*?>", "")   // case 1
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

  List<Tag> onProvideCompletionsFromAutocompleteTag(String partial) {
        List<Tag> matches = tagDAO.findByPartialName(partial);
        return matches;

    }
             public FieldTranslator getTagTranslator()
    {
        return new FieldTranslator<Tag>()
        {
            @Override
          public String toClient(Tag value)
          {
                String clientValue = "0";
                if (value != null)
                clientValue = String.valueOf(value.getName());

                return clientValue;
          }

            @Override
          public void render(MarkupWriter writer) { }

            @Override
          public Class<Tag> getType() { return Tag.class; }

            @Override
          public Tag parse(String clientValue) throws ValidationException
          {
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
