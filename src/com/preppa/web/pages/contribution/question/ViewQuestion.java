/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.pages.contribution.question;

import com.preppa.web.components.questiontypes.QuestionMenu;
import com.preppa.web.data.QuestionDAO;
import com.preppa.web.entities.Question;
import java.util.List;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;


/**
 *
 * @author newtonik
 */
public class ViewQuestion {
    @Inject
    private QuestionDAO questionDAO;

	@Persist(PersistenceConstants.FLASH)
	private String _errorMessage;

    @Property
    private Question question;
    @Property
    private List<Question> allquestions;
    @Component
    private QuestionMenu questionmenu;
    void onAction(String pass) {
		/*try {
			list.set(pass);
			return list;
		}
		catch (Exception e) {
			_errorMessage = e.getMessage();
			return null;
		}*/
    }


    Object onActivate(String questiontype) {

        if(questiontype.equals("Math"))
        {

            return null;
        }
        else if(questiontype.equals("CriticalReading"))
        {

            return null;
        }
        else if(questiontype.equals("Writing"))
        {


            return null;
        }
        else {
            allquestions = questionDAO.findAllNoRepeat();
        }

        return null;
    }


//    /**
//     * @return the the articles queried
//     */
//    public List<Question> getAllQuestions() {
//        List<Question> returnVal;
//
//        returnVal = questionDAO.findAllNoRepeat();
//
//        return returnVal;
//    }

}
