package com.preppa.web.pages.openquestion;

import com.preppa.web.data.OpenQuestionDAO;
import com.preppa.web.entities.OpenQuestion;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author nwt
 */
public class ShowOpenQuestion {
    @Property
    private OpenQuestion question;
    @Inject
    private OpenQuestionDAO openDAO;
  

    private Long qid;

    void onActivate(Long id)
    {
        if(id > 0) {
            question = openDAO.findById(id);
            qid = id;
        }
    }

    Long onPassivate() {
        return qid;
    }

}
