package com.preppa.web.components.questiontypes.gridin;

import com.preppa.web.data.GridinDAO;
import com.preppa.web.entities.Gridin;
import com.preppa.web.entities.GridinAnswer;
import com.preppa.web.utils.ContentType;
import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author nwt
 */
public class ShowGridin {
    @Property
    private Gridin question;
    @Inject
    private GridinDAO gridinDAO;
    @Property
    private List<GridinAnswer> answers;
    private Long gridId;
    @Property
    private ContentType contType;
    void onActivate(Long id)  {
        if(id > 0) {
            question = gridinDAO.findById(id);
            this.gridId = id;
            contType  = ContentType.GridIn;
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
