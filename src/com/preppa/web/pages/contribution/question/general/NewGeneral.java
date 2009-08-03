package com.preppa.web.pages.contribution.question.general;

import com.preppa.web.components.CQuestion;
import com.preppa.web.data.QuestiontypeDAO;
import com.preppa.web.data.TestsubjectDAO;
import com.preppa.web.entities.Questiontype;
import com.preppa.web.entities.Testsubject;
import com.preppa.web.entities.User;
import com.preppa.web.utils.InjectSelectionModel;
import java.util.ArrayList;
import java.util.List;
import org.acegisecurity.annotation.Secured;
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
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.json.JSONObject;

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
    private List<Questiontype> questiontypes = new ArrayList<Questiontype> ();
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
    @Component
    private CQuestion multiplequestion;
    @Inject
    @Property
    private Block multiplequesblock;

    /**Components**/
    @Component(parameters = {"value=testsubject",  "event=change",
                         "onCompleteCallback=literal:onChangeTestsubject"})
    @Mixins({"ck/OnEvent"})
    private Select testSubSelect;

    @Component(parameters = {"value=questiontype",  "event=change",
                "onCompleteCallback=literal:onChangeQuestiontype"})
    @Mixins({"ck/OnEvent"})
    private Select QuestiontypeSelect;
    @Component
    private Form wizardform;


    void onActivate() {
        testsubjects = testSubDAO.findAll();
        questiontypes = null;
    }

    JSONObject onChangeFromTestSubSelect(String testId) {
        JSONObject json = new JSONObject();
        
            JSONArray ids = new JSONArray();
            JSONArray qt = new JSONArray();
            JSONArray counter = new JSONArray();
          
        if(testId != null && !testId.equals("")) {
            questiontypes = questiontypeDAO.findByTestsubject(testsubjects.get(Integer.parseInt(testId)-1));

            
            ids.put("");
            qt.put("");
            int i = 1;
            System.out.println("Counter is " + questiontypes.size());
            for(Questiontype t: questiontypes) {
                qt.put(i, t.getName());
                ids.put(i, t.getId().toString());
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


    Block onChangeFromQuestiontypeSelect(String quesId) {

        if(quesId.equals("1"))
            return multiplequesblock;
        else
            return null;
    }

     Block onActionFromTestSelect() {
        String quesId = "6";
        if(quesId.equals("6"))
            return multiplequesblock;
        else
            return null;
    }
}
