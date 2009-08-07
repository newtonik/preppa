/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.pages.contribution.question;

import com.preppa.web.data.QuestionDAO;
import com.preppa.web.entities.Question;
import java.util.List;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.annotations.InjectPage;

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
    @Persist
    private Question question;

    private boolean tags;
    private String tagstring;

    Object onActivate() {

        return null;
    }

    Object onActivate(String input) {
        if(input.contains("tags")) {
            tags = true;
            tagstring = tagstring.substring(4); //Get the substring tag
        }
        return null;
    }

    /**
     * @return the the articles queried
     */
    public List<Question> getAllQuestions() {
        List<Question> returnVal;

        if (tags == true) {
            returnVal = null;
        }
        else {
            returnVal = questionDAO.findAllNoRepeat();
        }

        return returnVal;
    }

}
