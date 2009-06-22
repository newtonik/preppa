/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.preppa.web.data;

import com.preppa.web.entities.DictionaryWord;
import java.util.List;
import org.chenillekit.hibernate.daos.GenericDAO;

/**
 *
 * @author newtonik, modified by Jan Lorenz Soliman
 */
public interface DictionaryWordDAO extends GenericDAO <DictionaryWord, Integer> {

    public List<DictionaryWord> findByName(String fWord);

    DictionaryWord findById(Integer id);
    List<DictionaryWord> findAllOrderedByName();
    List<DictionaryWord> findAllOrderedByPartOfSpeech(String pos);
    public List<DictionaryWord> findByLetter(Character lower);
    List<DictionaryWord> findByPartialName(String partialName);
}
