package com.preppa.web.components.questiontypes.gridin;

import com.preppa.web.data.GridinDAO;
import com.preppa.web.entities.Gridin;
import com.preppa.web.entities.GridinAnswer;
import com.preppa.web.utils.ContentType;
import java.util.List;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author nwt
 */
public class ShowGridin {
    @Property
    @Parameter(required = true)
    private Gridin question;
    @Property
    @Parameter
    private boolean isRevision;
    @Inject
    private GridinDAO gridinDAO;
    @Property
    private List<GridinAnswer> answers;
    private Long gridId;
    @Property
    private ContentType contType;
    @Property
    @Parameter
    private GridinAnswer answer;

    void onActivate(Long id)  {
        if(id > 0) {
            question = gridinDAO.findById(id);
            this.gridId = id;
            answer = question.getAnswers().get(0);
            if (answer == null) {
                answer = new GridinAnswer();
                answer.setAnswer("");
                answer.setHighAnswer("");
                answer.setLowAnswer("");
                answer.setDescription("");
            }
            contType  = ContentType.GridIn;
        }
    }

    void onActivate() {
        answer = question.getAnswers().get(0);
        System.out.println("I'm in this onActivate;");
    }

    Long onPassivate() {
        return gridId;
    }

    void setGridin(Gridin question) {
        this.question = question;
        this.gridId = question.getId();
    }
}
