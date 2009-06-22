/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.DictionaryWord;
import com.preppa.web.entities.Vocab;
import java.util.List;
import org.chenillekit.hibernate.daos.AbstractHibernateDAO;
import org.chenillekit.hibernate.utils.SQLString;
import org.hibernate.Session;
import org.slf4j.Logger;


/**
 *
 * @author newtonik, modified by Jan Lorenz Soliman
 */
public class DictionaryWordDAOHibImpl extends AbstractHibernateDAO<DictionaryWord, Integer> implements DictionaryWordDAO {
    public DictionaryWordDAOHibImpl(Logger logger, Session session)
    {
        super(logger, session);
    }

    public DictionaryWord findById(Integer id) {
        SQLString sqlString = new SQLString("FROM DictionaryWord d");
        if(id != null)
        {
             sqlString.addWhereClause("d.id = '" + id + "'");
        }

        return (DictionaryWord) findByQuery(sqlString.toString()).get(0);
    }

    public List<DictionaryWord> findByLetter(Character lower) {
        Character upper = Character.toUpperCase(lower);
        SQLString sqlString = new SQLString("FROM DictionaryWord d");
        if(lower != null && upper != null)
        {
             sqlString.addWhereClause("d.name LIKE '" + lower + "%' OR d.name LIKE '" + upper + "%'");
        }
        sqlString.addOrderField("name");

        return (List <DictionaryWord>) findByQuery(sqlString.toString());
    }

    public List<DictionaryWord> findAllOrderedByPartOfSpeech(String pos) {
        SQLString sqlString = new SQLString("FROM DictionaryWord d");
        if(pos != null)
        {
             sqlString.addWhereClause("d.partofspeech = '" + pos + "'");
        }
        return (List<DictionaryWord>) findByQuery(sqlString.toString());
    }

    public List<DictionaryWord> findAllOrderedByName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<DictionaryWord> findByPartialName(String partialName) {
        //throw new UnsupportedOperationException("Not supported yet.");
        SQLString sqlString = new SQLString("FROM DictionaryWord d");
        if(partialName != null)
        {
             sqlString.addWhereClause("d.name LIKE '" + partialName + "%'");
        }

        return (List <DictionaryWord>) findByQuery(sqlString.toString());
    }

    public List<DictionaryWord> findByName(String name) {
        //throw new UnsupportedOperationException("Not supported yet.");
        SQLString sqlString = new SQLString("FROM DictionaryWord d");
        if(name != null)
        {
             sqlString.addWhereClause("d.name = '" + name + "'");
        }

        return (List <DictionaryWord>) findByQuery(sqlString.toString());
    }
}
