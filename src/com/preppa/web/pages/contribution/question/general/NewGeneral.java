package com.preppa.web.pages.contribution.question.general;

import com.preppa.web.components.questiontypes.gridin.NewGridin;
import com.preppa.web.components.questiontypes.longpassage.NewDualLongPassage;
import com.preppa.web.components.questiontypes.longpassage.NewLongPassage;
import com.preppa.web.components.questiontypes.multichoice.NewMultiChoice;
import com.preppa.web.components.questiontypes.shortpassage.NewDualShortPassage;
import com.preppa.web.components.questiontypes.shortpassage.NewShortPassage;
import com.preppa.web.data.QuestiontypeDAO;
import com.preppa.web.data.TagDAO;
import com.preppa.web.data.TestsubjectDAO;
import com.preppa.web.entities.Questiontype;
import com.preppa.web.entities.Tag;
import com.preppa.web.entities.Testsubject;
import com.preppa.web.entities.User;
import com.preppa.web.utils.InjectSelectionModel;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.IncludeStylesheet;
import org.apache.tapestry5.annotations.Mixins;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Select;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.json.JSONObject;
import org.springframework.security.annotation.Secured;

/**
 *
 * @author nwt
 */
@Secured("ROLE_USER")
@IncludeStylesheet(value = {"context:styles/question.css"})
@IncludeJavaScriptLibrary(value = {"context:js/question.js"})
public class NewGeneral {

    @ApplicationState
    private User user;
    @InjectSelectionModel(labelField = "name", idField = "id")
    private List<Testsubject> testsubjects = new ArrayList<Testsubject>();
    @InjectSelectionModel(labelField = "name", idField = "id")
    private List<Questiontype> questiontypes = new ArrayList<Questiontype>();
    @Property
    @Persist
    private Testsubject testsubject;
    @Property
    @Persist
    private Questiontype questiontype;
    @Inject
    private TestsubjectDAO testSubDAO;
    @Inject
    private QuestiontypeDAO questiontypeDAO;
    //@Inject
    //@Property
    //private Block multiplequesblock;
    /**Components**/
    @Component(parameters = {"value=testsubject", "event=change",
        "onCompleteCallback=literal:onChangeTestsubject"})
    @Mixins({"ck/OnEvent"})
    private Select testSubSelect;
    @Component(parameters = {"value=questiontype", "event=change",
        "onCompleteCallback=literal:onChangeQuestiontype"})
    @Mixins({"ck/OnEvent"})
    private Select QuestiontypeSelect;
    @Component
    private Form wizardform;
    @Property
    private String fname;
    @Property
    private List<Testsubject> subjects;
    /** Questiontype Components **/
    @Component
    private NewMultiChoice firstquestion;
    @Component
    private NewDualShortPassage newshortdualpassage;
    @Component
    private NewShortPassage newshortpassage;
    @Component
    private NewGridin newgridin;
    @Inject
    @Property
    private Block newtagblock;
    //@Inject
    //private Block shortpassageblock;
    @Component
    private NewMultiChoice aquestion;
    //@Inject
    //private Block cquestionblock;
    @Component
    private NewLongPassage newlongpassage;
    @Component
    private NewDualLongPassage newlongdualpassage;
    @Inject
    private TagDAO tagDAO;
    @Component
    private Form tagform;
    
    //@Property
    //@Inject
    //private Block longpassageblock;
    //@Property
    //@Inject
    //private Block longdualpassageblock;
    //@Inject
    //private Block firstblock;

    void onActivate() {
        testsubjects = testSubDAO.findAll();
        questiontypes = null;
    }

    JSONObject onChangeFromTestSubSelect(String testId) {
        JSONObject json = new JSONObject();

        JSONArray ids = new JSONArray();
        JSONArray qt = new JSONArray();
        JSONArray counter = new JSONArray();

        if (testId != null && !testId.equals("")) {
            questiontypes = questiontypeDAO.findByTestsubject(testsubjects.get(Integer.parseInt(testId) - 1));


            ids.put("");
            qt.put("");
            int i = 1;
            System.out.println("Counter is " + questiontypes.size());
            for (Questiontype t : questiontypes) {
                qt.put(i, t.getName());
                ids.put(i, t.getName());
                counter.put(new Integer(i).toString());
                i++;
            }
            System.out.println("Counter is " + i);
        }
        json.put("ids", ids);
        json.put("qt", qt);
        json.put("counter", counter);

        //return new TextStreamResponse("text/json", json.toString());
        return json;
    }

    JSONObject onChangeFromQuestiontypeSelect(String quesType) {
        JSONObject json = new JSONObject();
        if (quesType.equals("Multiple Choice")) {
            json.put("type", "multichoice");
        } else if (quesType.equals("Long Passage")) {
            json.put("type", "longpassage");
        } else if (quesType.equals("Long Dual Passage")) {
            json.put("type", "longdualpassage");
        } else if (quesType.equals("Short Dual Passage")) {
            json.put("type", "shortdualpassage");
        } else if (quesType.equals("Short Passage")) {
            json.put("type", "shortpassage");
        } else if (quesType.equals("Grid In")) {
            json.put("type", "gridin");
        }
        return json;

    }

    Block onActionFromTestSelect() {

        return null;
    }

    Block onActionFromCloseTag() {
        return newtagblock;
    }
    //Funtions for adding new tags and topics

    @CommitAfter
    JSONObject onSuccessFromTagForm() {
        List<Tag> tolist = tagDAO.findByName(fname);
        JSONObject json = new JSONObject();
        Tag tag = new Tag();
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
}
