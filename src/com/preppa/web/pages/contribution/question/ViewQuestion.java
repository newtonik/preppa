/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.pages.contribution.question;

import com.preppa.web.components.questiontypes.QuestionMenu;
import com.preppa.web.data.QuestionDAO;
import com.preppa.web.data.TagDAO;
import com.preppa.web.entities.Question;
import com.preppa.web.entities.Tag;
import java.util.ArrayList;
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
    @Inject
    private TagDAO tagDAO;
    @Property
    private Tag tag;

    void onAction(String pass) {

    }


    Object onActivate(String questiontype) {
        System.out.println("Qtype is " + questiontype);
        if (questiontype.contains("tag:")) {
            String tagString = questiontype.substring(4); //get the tag
            System.out.println("Tag String is " + tagString);
            List<Tag> temp = tagDAO.findByName(tagString);
            allquestions = new ArrayList<Question>();

            for (int i = 0; i < temp.size(); i++) {
                if (temp.get(i) != null) {
                    //allquestions.addAll(temp.get(i).getQuestions());
                    Question tempAdd;
                    for (int j = 0; j < temp.get(i).getQuestions().size(); j++) {
                        tempAdd = temp.get(i).getQuestions().get(j);
                        if (allquestions.contains(tempAdd) == false && tempAdd != null) {
                            allquestions.add(tempAdd);
                        }
                    }
                }
            }
        }
        else if(questiontype.equals("Math"))
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
