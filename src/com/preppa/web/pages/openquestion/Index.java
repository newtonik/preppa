package com.preppa.web.pages.openquestion;

import com.preppa.web.data.OpenQuestionDAO;
import com.preppa.web.entities.OpenQuestion;
import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author nwt
 */
public class Index {
    @Inject
    private OpenQuestionDAO openDAO;
    @Property
    private List<OpenQuestion> questions;
    @Property
    private OpenQuestion question;

    void onActivate() {
        questions = openDAO.findAll();
    }

}
