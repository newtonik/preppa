/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.preppa.web.data;

import com.preppa.web.entities.Testsubject;
import java.util.List;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
import org.chenillekit.hibernate.utils.SQLString;
import org.hibernate.Session;
import org.slf4j.Logger;

/**
 *
 * @author newtonik
 */
public class TestsubjectDAOHibImpl extends AbstractHibernateDAO<Testsubject, Integer> implements TestsubjectDAO {

    public TestsubjectDAOHibImpl(Logger logger, Session session) {
        super(logger, session);
    }

    @Override
    public Testsubject findById(Integer id) {
           SQLString sqlString = new SQLString("FROM Testsubject t");
        if(id > 0)
        {

                 sqlString.addWhereClause("t.id = '" + id + "'");
        }

        return  (Testsubject) findByQuery(sqlString.toString()).get(0);
    }


    @Override
    public List<Testsubject> findAllWithQuestions() {
          SQLString sqlString = new SQLString("FROM Testsubject t");
          sqlString.addWhereClause("t.hasQuestions = true");

          return findByQuery(sqlString.toString());
    }
}
