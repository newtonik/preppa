package com.preppa.web.components.questiontypes.shortpassage;

import com.preppa.web.data.LongDualPassageDAO;
import com.preppa.web.data.LongPassageDAO;
import com.preppa.web.data.ShortDualPassageDAO;
import com.preppa.web.data.ShortPassageDAO;
import com.preppa.web.entities.LongDualPassage;
import com.preppa.web.entities.LongPassage;
import com.preppa.web.entities.ShortDualPassage;
import com.preppa.web.entities.ShortPassage;
import java.util.List;
import org.apache.tapestry5.annotations.IncludeStylesheet;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author nwt
 */
@IncludeStylesheet(value = {"context:styles/passage.css"})
public class Index {
private List<ShortDualPassage> shortdualpassages;
private List<ShortPassage> shortpassages;
@Property
private ShortDualPassage shortdualpassage;
@Property
private ShortPassage shortpassage;
@Inject
private LongDualPassageDAO longdualpDAO;
@Inject
private LongPassageDAO longpDAO;
@Inject
private ShortDualPassageDAO shortdualpDAO;
@Inject
private ShortPassageDAO shortpDAO;

void onActivate() {
    shortdualpassages = shortdualpDAO.findAll();
    shortpassages = shortpDAO.findAll();
   
}


    /**
     * @return the shortdualpassages
     */
    public List<ShortDualPassage> getShortdualpassages() {
        return shortdualpassages;
    }

    /**
     * @return the shortpassages
     */
    public List<ShortPassage> getShortpassages() {
        return shortpassages;
    }

}
