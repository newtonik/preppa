/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.pages.contribution.vocab;

import com.preppa.web.data.VocabDAO;
import com.preppa.web.entities.Vocab;
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
public class ViewVocab {
    @Inject
    private VocabDAO vocabDAO;

	@Persist(PersistenceConstants.FLASH)
	private String _errorMessage;

   	@InjectPage
	private ListVocab list;


    @Property
    @Persist
    private Vocab vocab;

	Object onAction(String pass) {
		try {
			list.set(pass);
			return list;
		}
		catch (Exception e) {
			_errorMessage = e.getMessage();
			return null;
		}
	}


    Object onActivate() {

        return null;
    }

    /**
     * @return the the articles queried
     */
    public List<Vocab> getAllVocab() {
        List<Vocab> returnVal;

        /*if (Character.isLetter(c) == true)
        {
            returnVal = vocabDAO.findByLetter(c);
        }
        else
        {*/
            returnVal = vocabDAO.findAll();
        //}

        return returnVal;
    }

}
