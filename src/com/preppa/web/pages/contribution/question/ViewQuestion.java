/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.pages.contribution.question;

import com.preppa.web.data.QuestionDAO;
import com.preppa.web.data.TagDAO;
import com.preppa.web.entities.Question;
import com.preppa.web.entities.Tag;
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

    @Inject
    private TagDAO tagDAO;

    private boolean tags;
    private String tagstring;

    Object onActivate(String input) {
        System.out.println("Input is " + input);
        if(input.contains("tag")) {
            tags = true;
            tagstring = input.substring(4); //Get the substring tag
            System.out.println("Tagstring is " + tagstring);
        }
        return null;
    }

    /**
     * @return the the articles queried
     */
    public List<Question> getAllQuestions() {
        List<Question> returnVal = null;

        if (tags == true) {
            System.out.println("I return Null");
            if (tagDAO.findByName(tagstring) != null) {
                Tag tag = tagDAO.findByName(tagstring).get(0);
                returnVal = tag.getQuestions();
            }
        }
        else {
            returnVal = questionDAO.findAllNoRepeat();
        }

        return returnVal;
    }

}
