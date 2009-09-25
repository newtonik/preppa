package com.preppa.web.pages.openquestion;

import com.preppa.web.data.OpenQuestionDAO;
import com.preppa.web.entities.OpenQuestion;
import java.util.List;
import org.apache.tapestry5.annotations.IncludeStylesheet;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author nwt
 */
@IncludeStylesheet(value = {"context:styles/openquestion.css"})
public class Index {
    @Inject
    private OpenQuestionDAO openDAO;
    @Property
    private List<OpenQuestion> questions;
    @Property
    private OpenQuestion question;
    @Property
    private String slink;


    void onActivate() {
        questions = openDAO.findAll();
        slink = "openquestion/search";
    }

}
