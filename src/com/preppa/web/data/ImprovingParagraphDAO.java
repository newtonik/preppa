package com.preppa.web.data;

import com.preppa.web.entities.ImprovingParagraph;
import java.util.List;
import org.chenillekit.hibernate.daos.GenericDAO;

/**
 *
 * @author nikhariale
 */
public interface ImprovingParagraphDAO extends GenericDAO<ImprovingParagraph, Long> {

        ImprovingParagraph findById(Integer quesId);
        List<ImprovingParagraph> findAllByAwaiting();
        List<ImprovingParagraph> findAllByApproved();
}
