package com.preppa.web.pages.contribution.gridin;

import com.preppa.web.data.GridinDAO;
import com.preppa.web.entities.Gridin;
import com.preppa.web.entities.GridinAnswer;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author nwt
 */
@IncludeJavaScriptLibrary(value = {"context:js/gridin.js"})
public class ShowGridin {
    @Property
    private Gridin question;
    @Inject
    private GridinDAO gridinDAO;
    @Property
    private GridinAnswer answer;
    private Long gridId;
    
    void onActivate(Long id)  {
        if(id > 0) {
            question = gridinDAO.findById(id);
            answer = question.getAnswers().get(0);
            this.gridId = id;
        }
    }

    Long onPassivate() {
        return gridId;
    }

    void setGridin(Gridin question) {
        this.question = question;
        this.gridId = question.getId();
    }

}
