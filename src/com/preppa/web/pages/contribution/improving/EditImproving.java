package com.preppa.web.pages.contribution.improving;

import com.preppa.web.data.ImprovingParagraphDAO;
import com.preppa.web.entities.ImprovingParagraph;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author nikhariale
 */
public class EditImproving {

    @Property
    private ImprovingParagraph iparagraph;
    @Inject
    private ImprovingParagraphDAO improvingDAO;

    void onActivate(Long id) {
        if(id > 0) {
            iparagraph = improvingDAO.doRetrieve(id, true);
        }
    }

    Long onPassivate() {
        return iparagraph.getId();
    }
}
