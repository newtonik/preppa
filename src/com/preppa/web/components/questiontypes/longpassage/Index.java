package com.preppa.web.components.questiontypes.longpassage;

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
private List<LongDualPassage> longdualpassages;
private List<LongPassage> longpassages;
private List<ShortDualPassage> shortdualpassages;
private List<ShortPassage> shortpassages;
@Property
private LongDualPassage longdualpassage;
@Property
private LongPassage longpassage;
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
    longdualpassages = longdualpDAO.findAll();
    longpassages = longpDAO.findAll();
}

    /**
     * @return the longdualpassages
     */
    public List<LongDualPassage> getLongdualpassages() {
        return longdualpassages;
    }

    /**
     * @return the longpassages
     */
    public List<LongPassage> getLongpassages() {
        return longpassages;
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
