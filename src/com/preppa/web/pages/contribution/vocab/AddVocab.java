/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.pages.contribution.vocab;

import com.preppa.web.data.DictionaryWordDAO;
import com.preppa.web.data.VocabDAO;
import com.preppa.web.entities.DictionaryWord;
import com.preppa.web.entities.Vocab;
import com.preppa.web.pages.Index;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.corelib.components.Form;
import org.springframework.security.annotation.Secured;

/**
 *
 * @author newtonik
 */
@Secured("ROLE_USER")
public class AddVocab {
    @Property
    private Vocab word;
    @Property
    private Vocab view;
	@Component(id = "addvocabform")
	private Form myform;
    @Property
    private String dWord;
    @Inject
    private DictionaryWordDAO dictionarywordDAO;
	@InjectPage
	private DefinitionVocab _next;

    void onValidateForm() {
    }

	Object onSuccessFromAddVocabForm() {
		_next.set(dWord);
		return _next;
	}

    void onActivate(Vocab word) {
        this.view = word;
    }

    Object onPassivate() {
        return view;
    }

    List<String> onProvideCompletionsFromdWord(String partial)
    {
        List<DictionaryWord> matches = dictionarywordDAO.findByPartialName(partial);

        List<String> result = new ArrayList<String>();

            for (DictionaryWord a : matches)
            {
                if (!result.contains(a.getName())) {
                    result.add(a.getName());
                }
            }
        
        
        return result;
    }

    /*@CommitAfter
    Object onSuccess() {
        return EditVocab.class;
    }*/
}
