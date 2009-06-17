/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.pages.contribution.vocab;

import com.preppa.web.data.VocabDAO;
import com.preppa.web.entities.Vocab;
import java.util.List;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author newtonik
 */
public class ListVocab {
    @Inject
    private VocabDAO vocabDAO;

    private Character letter;
    private String input;

    @Property
    @Persist
    private Vocab listVocab;

    Object onActivate(String input) {
        this.input = input;
        this.letter = input.charAt(0);
        return null;
    }

	public void set(String input) {
		this.letter = input.charAt(0);
	}

    public String getLetter()
    {
        char[] arr = new char[1];
        arr[0] = letter;
        String returnVal = new String(arr);
        return returnVal;
    }

    /**
     * @return the allarticles
     */
    public List<Vocab> getAllVocab() {
        if (input.compareTo("Noun") == 0)
        {
             return vocabDAO.findAllOrderedByPartOfSpeech("Noun");
        }
        else if (input.compareTo("Adjective") == 0)
        {
             return vocabDAO.findAllOrderedByPartOfSpeech("Adjective");
        }
        else if (input.compareTo("Verb") == 0)
        {
             return vocabDAO.findAllOrderedByPartOfSpeech("Verb");
        }
        else
        {
            return vocabDAO.findByLetter(letter);
        }
    }

}
