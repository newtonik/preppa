package com.preppa.web.data;

import com.preppa.web.entities.ImprovingParagraph;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
import org.hibernate.Session;
import org.slf4j.Logger;

/**
 *
 * @author nikhariale
 */
public class ImprovingParagraphHimplDAO  extends AbstractHibernateDAO<ImprovingParagraph, Long> implements ImprovingParagraphDAO{

     public ImprovingParagraphHimplDAO(Logger logger, Session session) {
        super(logger, session);
    }
}
